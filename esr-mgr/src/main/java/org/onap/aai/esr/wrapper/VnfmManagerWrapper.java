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
  
  /**
   * get VnfmManagerWrapper instance.
   * 
   * @return vnfm manager wrapper instance
   */
  public static VnfmManagerWrapper getInstance() {
    if (vnfmManagerWrapper == null) {
      vnfmManagerWrapper = new VnfmManagerWrapper();
    }
    return vnfmManagerWrapper;
  }

  public Response registerVnfm(VnfmRegisterInfo vnfm) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
    esrVnfmDetail = vnfmManagerUtil.vnfmRegisterInfo2EsrVnfm(vnfm);
    String vnfmId = esrVnfmDetail.getVnfmId();
    try {
      ExternalSystemProxy.registerVnfm(vnfmId, esrVnfmDetail);
      result.setId(vnfmId);
      return Response.ok(result).build();
    } catch (ExtsysException e) {
      LOG.error("Register VNFM failed !" , e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }

  public Response updateVnfm(VnfmRegisterInfo vnfm, String vnfmId) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
    EsrVnfmDetail originalEsrVnfmDetail = new EsrVnfmDetail();
    EsrSystemInfo originalEsrSystemInfo = new EsrSystemInfo();
    originalEsrVnfmDetail = queryEsrVnfmDetail(vnfmId);
    esrVnfmDetail = vnfmManagerUtil.vnfmRegisterInfo2EsrVnfm(vnfm);
    String resourceVersion = getResourceVersion(vnfmId);
    esrVnfmDetail.setResourceVersion(resourceVersion);
    esrVnfmDetail.setVnfmId(vnfmId);
    originalEsrSystemInfo = originalEsrVnfmDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0);
    esrVnfmDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
        .setEsrSystemInfoId(originalEsrSystemInfo.getEsrSystemInfoId());
    esrVnfmDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
        .setResouceVersion(originalEsrSystemInfo.getResouceVersion());
    try {
      ExternalSystemProxy.registerVnfm(vnfmId, esrVnfmDetail);
      result.setId(vnfmId);
      return Response.ok(result).build();
    } catch (ExtsysException e) {
      LOG.error("Update VNFM failed !", e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }

  public Response queryVnfmList() {
    ArrayList<VnfmRegisterInfo> vnfmList = new ArrayList<VnfmRegisterInfo>();
    EsrVnfmList esrVnfm = new EsrVnfmList();
    try {
      String esrVnfmStr = ExternalSystemProxy.queryVnfmList();
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
    VnfmRegisterInfo vnfm = new VnfmRegisterInfo();
    vnfm = queryVnfmDetail(vnfmId);
    if (vnfm != null) {
      return Response.ok(vnfm).build();
    } else {
      return Response.ok(vnfm).build();
    }
  }

  public Response delVnfm(String vnfmId) {
    String resourceVersion = getResourceVersion(vnfmId);
    try {
      ExternalSystemProxy.deleteVnfm(vnfmId, resourceVersion);
      return Response.noContent().build();
    } catch (ExtsysException e) {
      LOG.error(
          "Delete VNFM from A&AI failed! VNFM ID: " + vnfmId + "resouce-version:" + resourceVersion,
          e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }

  private VnfmRegisterInfo queryVnfmDetail(String vnfmId) {
    VnfmRegisterInfo vnfm = new VnfmRegisterInfo();
    EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
    try {
      String esrVnfmstr = ExternalSystemProxy.queryVnfmDetail(vnfmId);
      LOG.info("Response from AAI by query VNFM: " + esrVnfmstr);
      esrVnfmDetail = new Gson().fromJson(esrVnfmstr, EsrVnfmDetail.class);
      vnfm = vnfmManagerUtil.esrVnfm2VnfmRegisterInfo(esrVnfmDetail);
    } catch (ExtsysException e) {
      LOG.error("Query VNFM detail failed! VNFM ID: " + vnfmId, e);
    }
    return vnfm;
  }

  private ArrayList<VnfmRegisterInfo> getVnfmDetailList(EsrVnfmList esrVnfm) {
    ArrayList<VnfmRegisterInfo> vnfmInfoList = new ArrayList<VnfmRegisterInfo>();
    VnfmRegisterInfo vnfmInfo = new VnfmRegisterInfo();
    for (int i = 0; i < esrVnfm.getEsrVnfm().size(); i++) {
      String vnfmId = esrVnfm.getEsrVnfm().get(i).getVnfmId();
      vnfmInfo = queryVnfmDetail(vnfmId);
      if (vnfmInfo != null) {
        vnfmInfoList.add(vnfmInfo);
      }
    }
    return vnfmInfoList;
  }
  
  private String getResourceVersion (String vnfmId) {
    EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
    String resourceVersion = null;
    esrVnfmDetail = queryEsrVnfmDetail(vnfmId);
    if (esrVnfmDetail != null && esrVnfmDetail.getResourceVersion() != null) {
      resourceVersion = esrVnfmDetail.getResourceVersion();
    }
    return resourceVersion;
  }
  
  private EsrVnfmDetail queryEsrVnfmDetail (String vnfmId) {
    EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
    try {
      String esrVnfmstr = ExternalSystemProxy.queryVnfmDetail(vnfmId);
      LOG.info("Response from AAI by query VNFM: " + esrVnfmstr);
      esrVnfmDetail = new Gson().fromJson(esrVnfmstr, EsrVnfmDetail.class);
    } catch (ExtsysException e) {
      LOG.error("Query VNFM detail failed! VNFM ID: " + vnfmId, e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
    return esrVnfmDetail;
  }
}
