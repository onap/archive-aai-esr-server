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
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncList;
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.onap.aai.esr.entity.rest.ThirdpartySdncRegisterInfo;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ThirdpartySdncManagerUtil;
import org.onap.aai.esr.util.VnfmManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class ThirdpatySdncWrapper {

  private static ThirdpatySdncWrapper thirdpatySdncWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(ThirdpatySdncWrapper.class);

  /**
   * get ThirdpatySdncWrapper instance.
   * @return ThirdpatySdnc manager wrapper instance
   */
  public static ThirdpatySdncWrapper getInstance() {
    if (thirdpatySdncWrapper == null) {
      thirdpatySdncWrapper = new ThirdpatySdncWrapper();
    }
    return thirdpatySdncWrapper;
  }
  
  public Response registerThirdpartySdnc(ThirdpartySdncRegisterInfo thirdpartySdnc) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrThirdpartySdncDetail esrSdncDetail = new EsrThirdpartySdncDetail();
    esrSdncDetail = ThirdpartySdncManagerUtil.sdncRegisterInfo2EsrSdnc(thirdpartySdnc);
    String sdncId = esrSdncDetail.getThirdpartySdncId();
    try {
      ExternalSystemProxy.registerSdnc(sdncId, esrSdncDetail);
      result.setId(sdncId);
      return Response.ok(result).build();
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Register thirdParty SDNC failed !" + e.getMessage());
      return Response.serverError().build();
    }
    
  }

  public Response updateThirdpartySdnc(ThirdpartySdncRegisterInfo thirdpartySdnc, String sdncId) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrThirdpartySdncDetail esrSdncDetail = new EsrThirdpartySdncDetail();
    EsrThirdpartySdncDetail originalEsrSdncDetail = new EsrThirdpartySdncDetail();
    EsrSystemInfo originalEsrSystemInfo = new EsrSystemInfo();
    originalEsrSdncDetail = queryEsrThirdpartySdncDetail(sdncId);
    esrSdncDetail = ThirdpartySdncManagerUtil.sdncRegisterInfo2EsrSdnc(thirdpartySdnc);
    String resourceVersion = originalEsrSdncDetail.getResourceVersion();
    esrSdncDetail.setResourceVersion(resourceVersion);
    esrSdncDetail.setThirdpartySdncId(sdncId);
    originalEsrSystemInfo = originalEsrSdncDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0);
    esrSdncDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
        .setEsrSystemInfoId(originalEsrSystemInfo.getEsrSystemInfoId());
    esrSdncDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
        .setResouceVersion(originalEsrSystemInfo.getResouceVersion());
    try {
      ExternalSystemProxy.registerSdnc(sdncId, esrSdncDetail);
      result.setId(sdncId);
      return Response.ok(result).build();
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Update VNFM failed !" + e.getMessage());
      return Response.serverError().build();
    }
  }
  
  public Response queryThirdpartySdncList() {
    ArrayList<ThirdpartySdncRegisterInfo> sdncList = new ArrayList<ThirdpartySdncRegisterInfo>();
    EsrThirdpartySdncList esrSdnc = new EsrThirdpartySdncList();
    try {
      String esrSdncStr = ExternalSystemProxy.querySdncList();
      esrSdnc = new Gson().fromJson(esrSdncStr, EsrThirdpartySdncList.class);
      LOG.info("Response from AAI by query thirdparty SDNC list: " + esrSdnc);
      sdncList = getSdncDetailList(esrSdnc);
      return Response.ok(sdncList).build();
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Query thirdparty SDNC list failed !");
      return Response.serverError().build();
    }
  }
  
  public Response queryThirdpartySdncById(String thirdpartySdncId) {
    ThirdpartySdncRegisterInfo thirdpartySdnc = new ThirdpartySdncRegisterInfo();
    thirdpartySdnc = querySdncDetail(thirdpartySdncId);
    if(thirdpartySdnc != null) {
      return Response.ok(thirdpartySdnc).build();
    } else {
      return Response.ok().build();
    }
  }
  
  public Response delThirdpartySdnc(String thirdpartySdncId) {
    EsrThirdpartySdncDetail thirdpartySdncDetail = new EsrThirdpartySdncDetail();
    thirdpartySdncDetail = queryEsrThirdpartySdncDetail(thirdpartySdncId);
    String resourceVersion = thirdpartySdncDetail.getResourceVersion();
    if (resourceVersion != null) {
      try {
        ExternalSystemProxy.deleteThirdpartySdnc(thirdpartySdncId, resourceVersion);
        return Response.ok().build();
      } catch (Exception e) {
        e.printStackTrace();
        LOG.error("Delete VNFM from A&AI failed! thirdparty SDNC ID: " + thirdpartySdncId + "resouce-version:"
            + resourceVersion, e.getMessage());
        return Response.serverError().build();
      }
    } else {
      LOG.error("resouce-version is null ! Can not delete resouce from A&AI. ");
      return Response.serverError().build();
    }
  }
  
  private ThirdpartySdncRegisterInfo querySdncDetail(String sdncId) {
    ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
    EsrThirdpartySdncDetail esrSdncDetail = new EsrThirdpartySdncDetail();
    try {
      String esrSdncStr = ExternalSystemProxy.queryThirdpartySdncDetail(sdncId);
      LOG.info("Response from AAI by query thirdparty SDNC: " + esrSdncStr);
      esrSdncDetail = new Gson().fromJson(esrSdncStr, EsrThirdpartySdncDetail.class);
      sdncRegisterInfo = ThirdpartySdncManagerUtil.esrSdnc2SdncRegisterInfo(esrSdncDetail);
      return sdncRegisterInfo;
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Query VNFM detail failed! thirdpaty SDNC ID: " + sdncId, e.getMessage());
      return null;
    }
  }
  
  private ArrayList<ThirdpartySdncRegisterInfo> getSdncDetailList(EsrThirdpartySdncList esrThirdPartySdnc) {
    ArrayList<ThirdpartySdncRegisterInfo> sdncInfoList = new ArrayList<ThirdpartySdncRegisterInfo>();
    ThirdpartySdncRegisterInfo sdncInfo = new ThirdpartySdncRegisterInfo();
    for (int i = 0; i < esrThirdPartySdnc.getEsrThirdpartySdnc().size(); i++) {
      String sdncId = esrThirdPartySdnc.getEsrThirdpartySdnc().get(i).getThirdpartySdncId();
      sdncInfo = querySdncDetail(sdncId);
      if (sdncInfo != null) {
        sdncInfoList.add(sdncInfo);
      }
    }
    return sdncInfoList;
  }
  
  private EsrThirdpartySdncDetail queryEsrThirdpartySdncDetail (String sdncId) {
    EsrThirdpartySdncDetail esrSdncDetail = new EsrThirdpartySdncDetail();
    try {
      String esrThirdpartySdncStr = ExternalSystemProxy.queryThirdpartySdncDetail(sdncId);
      LOG.info("Response from AAI by query thirdparty SDNC: " + esrThirdpartySdncStr);
      esrSdncDetail = new Gson().fromJson(esrThirdpartySdncStr, EsrThirdpartySdncDetail.class);
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Query VNFM detail failed! VNFM ID: " + sdncId, e.getMessage());
    }
    return esrSdncDetail;
  }
  
}
