/**
 * Copyright 2019  Verizon. All Rights Reserved.
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
import org.onap.aai.esr.entity.aai.EsrNfvoDetail;
import org.onap.aai.esr.entity.aai.EsrNfvoList;
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.onap.aai.esr.entity.rest.NfvoRegisterInfo;
import org.onap.aai.esr.exception.ExceptionUtil;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.NfvoManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

public class NfvoManagerWrapper {
    private static NfvoManagerWrapper NfvoManagerWrapper;
    private static final Logger LOG = LoggerFactory.getLogger(NfvoManagerWrapper.class);

    private static NfvoManagerUtil nfvoManagerUtil = new NfvoManagerUtil();
    private static ExternalSystemProxy externalSystemProxy = new ExternalSystemProxy();

    /**
     * get NfvoManagerWrapper instance.
     * 
     * @return nfvo manager wrapper instance
     */
    public static NfvoManagerWrapper getInstance() {
        if (NfvoManagerWrapper == null) {
            NfvoManagerWrapper = new NfvoManagerWrapper(externalSystemProxy);
        }
        return NfvoManagerWrapper;
    }
    
    public NfvoManagerWrapper(ExternalSystemProxy externalSystemProxy){
        NfvoManagerWrapper.externalSystemProxy = externalSystemProxy;
    }

    public Response registerNfvo(NfvoRegisterInfo nfvo) {
        CommonRegisterResponse result = new CommonRegisterResponse();
        EsrNfvoDetail esrNfvoDetail = nfvoManagerUtil.nfvoRegisterInfo2EsrNfvo(nfvo);
        String nfvoId = esrNfvoDetail.getNfvoId();
        try {
            externalSystemProxy.registerNfvo(nfvoId, esrNfvoDetail);
            result.setId(nfvoId);
            return Response.ok(result).build();
        } catch (ExtsysException e) {
            LOG.error("Register NFVO failed !", e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }

    public Response updateNfvo(NfvoRegisterInfo nfvo, String nfvoId) {
        CommonRegisterResponse result = new CommonRegisterResponse();
        EsrNfvoDetail originalEsrNfvoDetail = queryEsrNfvoDetail(nfvoId);
        EsrNfvoDetail esrNfvoDetail = nfvoManagerUtil.nfvoRegisterInfo2EsrNfvo(nfvo);
        String resourceVersion = getResourceVersion(nfvoId);
        esrNfvoDetail.setResourceVersion(resourceVersion);
        esrNfvoDetail.setNfvoId(nfvoId);
        EsrSystemInfo originalEsrSystemInfo = originalEsrNfvoDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0);
        esrNfvoDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
                .setEsrSystemInfoId(originalEsrSystemInfo.getEsrSystemInfoId());
        esrNfvoDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
                .setResouceVersion(originalEsrSystemInfo.getResouceVersion());
        try {
            externalSystemProxy.registerNfvo(nfvoId, esrNfvoDetail);
            result.setId(nfvoId);
            return Response.ok(result).build();
        } catch (ExtsysException e) {
            LOG.error("Update NFVO failed !", e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }

    public Response queryNfvoList() {
        List<NfvoRegisterInfo> nfvoList = new ArrayList<>();
        EsrNfvoList esrNfvo = new EsrNfvoList();
        try {
            String esrNfvoStr = externalSystemProxy.queryNfvoList();
            esrNfvo = new Gson().fromJson(esrNfvoStr, EsrNfvoList.class);
            LOG.info("Response from AAI by query NFVO list: " + esrNfvo);
            nfvoList = getNfvoDetailList(esrNfvo);
            return Response.ok(nfvoList).build();
        } catch (ExtsysException e) {
            LOG.error("Query NFVO list failed !", e);
            return Response.ok(nfvoList).build();
        }
    }

    public Response queryNfvoById(String nfvoId) {
        NfvoRegisterInfo nfvo = queryNfvoDetail(nfvoId);
        if (nfvo != null) {
            return Response.ok(nfvo).build();
        } else {
            return Response.ok(nfvo).build();
        }
    }

    public Response delNfvo(String nfvoId) {
        String resourceVersion = getResourceVersion(nfvoId);
        try {
            externalSystemProxy.deleteNfvo(nfvoId, resourceVersion);
            return Response.noContent().build();
        } catch (ExtsysException e) {
            LOG.error("Delete NFVO from A&AI failed! NFVO ID: " + nfvoId + "resouce-version:" + resourceVersion, e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }

    private NfvoRegisterInfo queryNfvoDetail(String nfvoId) {
        NfvoRegisterInfo nfvo = new NfvoRegisterInfo();
        EsrNfvoDetail esrNfvoDetail = new EsrNfvoDetail();
        try {
            String esrNfvostr = externalSystemProxy.queryNfvoDetail(nfvoId);
            LOG.info("Response from AAI by query NFVO: " + esrNfvostr);
            esrNfvoDetail = new Gson().fromJson(esrNfvostr, EsrNfvoDetail.class);
            nfvo = nfvoManagerUtil.esrNfvo2NfvoRegisterInfo(esrNfvoDetail);
        } catch (ExtsysException e) {
            LOG.error("Query NFVO detail failed! NFVO ID: " + nfvoId, e);
        }
        return nfvo;
    }

    private List<NfvoRegisterInfo> getNfvoDetailList(EsrNfvoList esrNfvo) {
        List<NfvoRegisterInfo> nfvoInfoList = new ArrayList<>();
        for (int i = 0; i < esrNfvo.getEsrNfvo().size(); i++) {
            String nfvoId = esrNfvo.getEsrNfvo().get(i).getNfvoId();
            NfvoRegisterInfo nfvoInfo = queryNfvoDetail(nfvoId);
            if (nfvoInfo != null) {
                nfvoInfoList.add(nfvoInfo);
            }
        }
        return nfvoInfoList;
    }

    private String getResourceVersion(String nfvoId) {
        String resourceVersion = null;
        EsrNfvoDetail esrNfvoDetail = queryEsrNfvoDetail(nfvoId);
        if (esrNfvoDetail != null && esrNfvoDetail.getResourceVersion() != null) {
            resourceVersion = esrNfvoDetail.getResourceVersion();
        }
        return resourceVersion;
    }

    private EsrNfvoDetail queryEsrNfvoDetail(String nfvoId) {
        EsrNfvoDetail esrNfvoDetail = new EsrNfvoDetail();
        try {
            String esrNfvostr = externalSystemProxy.queryNfvoDetail(nfvoId);
            LOG.info("Response from AAI by query NFVO: " + esrNfvostr);
            esrNfvoDetail = new Gson().fromJson(esrNfvostr, EsrNfvoDetail.class);
        } catch (ExtsysException e) {
            LOG.error("Query NFVO detail failed! NFVO ID: " + nfvoId, e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
        return esrNfvoDetail;
    }
}
