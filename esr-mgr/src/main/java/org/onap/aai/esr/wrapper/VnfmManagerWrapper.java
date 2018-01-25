/**
 * Copyright 2017 ZTE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onap.aai.esr.wrapper;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;
import org.onap.aai.esr.entity.aai.EsrVnfmList;
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.onap.aai.esr.entity.rest.VnfmRegisterInfo;
import org.onap.aai.esr.exception.ExceptionUtil;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.VnfmManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

public class VnfmManagerWrapper {
    private static VnfmManagerWrapper vnfmManagerWrapper;
    private static final Logger LOG = LoggerFactory.getLogger(VnfmManagerWrapper.class);

    private static VnfmManagerUtil vnfmManagerUtil = new VnfmManagerUtil();
    private static ExternalSystemProxy externalSystemProxy = new ExternalSystemProxy();

    /**
     * get VnfmManagerWrapper instance.
     * 
     * @return vnfm manager wrapper instance
     */
    public static VnfmManagerWrapper getInstance() {
        if (vnfmManagerWrapper == null) {
            vnfmManagerWrapper = new VnfmManagerWrapper(externalSystemProxy);
        }
        return vnfmManagerWrapper;
    }
    
    public VnfmManagerWrapper(ExternalSystemProxy externalSystemProxy){
        VnfmManagerWrapper.externalSystemProxy = externalSystemProxy;
    }

    public Response registerVnfm(VnfmRegisterInfo vnfm) {
        CommonRegisterResponse result = new CommonRegisterResponse();
        EsrVnfmDetail esrVnfmDetail = vnfmManagerUtil.vnfmRegisterInfo2EsrVnfm(vnfm);
        String vnfmId = esrVnfmDetail.getVnfmId();
        try {
            externalSystemProxy.registerVnfm(vnfmId, esrVnfmDetail);
            result.setId(vnfmId);
            return Response.ok(result).build();
        } catch (ExtsysException e) {
            LOG.error("Register VNFM failed !", e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }

    public Response updateVnfm(VnfmRegisterInfo vnfm, String vnfmId) {
        CommonRegisterResponse result = new CommonRegisterResponse();
        EsrVnfmDetail originalEsrVnfmDetail = queryEsrVnfmDetail(vnfmId);
        EsrVnfmDetail esrVnfmDetail = vnfmManagerUtil.vnfmRegisterInfo2EsrVnfm(vnfm);
        String resourceVersion = getResourceVersion(vnfmId);
        esrVnfmDetail.setResourceVersion(resourceVersion);
        esrVnfmDetail.setVnfmId(vnfmId);
        EsrSystemInfo originalEsrSystemInfo = originalEsrVnfmDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0);
        esrVnfmDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
                .setEsrSystemInfoId(originalEsrSystemInfo.getEsrSystemInfoId());
        esrVnfmDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
                .setResouceVersion(originalEsrSystemInfo.getResouceVersion());
        try {
            externalSystemProxy.registerVnfm(vnfmId, esrVnfmDetail);
            result.setId(vnfmId);
            return Response.ok(result).build();
        } catch (ExtsysException e) {
            LOG.error("Update VNFM failed !", e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }

    public Response queryVnfmList() {
        List<VnfmRegisterInfo> vnfmList = new ArrayList<>();
        EsrVnfmList esrVnfm = new EsrVnfmList();
        try {
            String esrVnfmStr = externalSystemProxy.queryVnfmList();
            esrVnfm = new Gson().fromJson(esrVnfmStr, EsrVnfmList.class);
            LOG.info("Response from AAI by query VNFM list: " + esrVnfm);
            vnfmList = getVnfmDetailList(esrVnfm);
            return Response.ok(vnfmList).build();
        } catch (ExtsysException e) {
            LOG.error("Query VNFM list failed !", e);
            return Response.ok(vnfmList).build();
        }
    }

    public Response queryVnfmById(String vnfmId) {
        VnfmRegisterInfo vnfm = queryVnfmDetail(vnfmId);
        if (vnfm != null) {
            return Response.ok(vnfm).build();
        } else {
            return Response.ok(vnfm).build();
        }
    }

    public Response delVnfm(String vnfmId) {
        String resourceVersion = getResourceVersion(vnfmId);
        try {
            externalSystemProxy.deleteVnfm(vnfmId, resourceVersion);
            return Response.noContent().build();
        } catch (ExtsysException e) {
            LOG.error("Delete VNFM from A&AI failed! VNFM ID: " + vnfmId + "resouce-version:" + resourceVersion, e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }

    private VnfmRegisterInfo queryVnfmDetail(String vnfmId) {
        VnfmRegisterInfo vnfm = new VnfmRegisterInfo();
        EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
        try {
            String esrVnfmstr = externalSystemProxy.queryVnfmDetail(vnfmId);
            LOG.info("Response from AAI by query VNFM: " + esrVnfmstr);
            esrVnfmDetail = new Gson().fromJson(esrVnfmstr, EsrVnfmDetail.class);
            vnfm = vnfmManagerUtil.esrVnfm2VnfmRegisterInfo(esrVnfmDetail);
        } catch (ExtsysException e) {
            LOG.error("Query VNFM detail failed! VNFM ID: " + vnfmId, e);
        }
        return vnfm;
    }

    private List<VnfmRegisterInfo> getVnfmDetailList(EsrVnfmList esrVnfm) {
        List<VnfmRegisterInfo> vnfmInfoList = new ArrayList<>();
        for (int i = 0; i < esrVnfm.getEsrVnfm().size(); i++) {
            String vnfmId = esrVnfm.getEsrVnfm().get(i).getVnfmId();
            VnfmRegisterInfo vnfmInfo = queryVnfmDetail(vnfmId);
            if (vnfmInfo != null) {
                vnfmInfoList.add(vnfmInfo);
            }
        }
        return vnfmInfoList;
    }

    private String getResourceVersion(String vnfmId) {
        String resourceVersion = null;
        EsrVnfmDetail esrVnfmDetail = queryEsrVnfmDetail(vnfmId);
        if (esrVnfmDetail != null && esrVnfmDetail.getResourceVersion() != null) {
            resourceVersion = esrVnfmDetail.getResourceVersion();
        }
        return resourceVersion;
    }

    private EsrVnfmDetail queryEsrVnfmDetail(String vnfmId) {
        EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
        try {
            String esrVnfmstr = externalSystemProxy.queryVnfmDetail(vnfmId);
            LOG.info("Response from AAI by query VNFM: " + esrVnfmstr);
            esrVnfmDetail = new Gson().fromJson(esrVnfmstr, EsrVnfmDetail.class);
        } catch (ExtsysException e) {
            LOG.error("Query VNFM detail failed! VNFM ID: " + vnfmId, e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
        return esrVnfmDetail;
    }
}
