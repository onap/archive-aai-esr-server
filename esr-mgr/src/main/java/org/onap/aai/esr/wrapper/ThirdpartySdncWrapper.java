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
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncList;
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.onap.aai.esr.entity.rest.ThirdpartySdncRegisterInfo;
import org.onap.aai.esr.exception.ExceptionUtil;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ThirdpartySdncManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class ThirdpartySdncWrapper {

  private static ThirdpartySdncWrapper thirdpatySdncWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(ThirdpartySdncWrapper.class);
  private static ThirdpartySdncManagerUtil thirdpartySdncManagerUtil = new ThirdpartySdncManagerUtil();

  /**
   * get ThirdpatySdncWrapper instance.
   * @return ThirdpatySdnc manager wrapper instance
   */
  public static ThirdpartySdncWrapper getInstance() {
    if (thirdpatySdncWrapper == null) {
      thirdpatySdncWrapper = new ThirdpartySdncWrapper();
    }
    return thirdpatySdncWrapper;
  }
  
  public Response registerThirdpartySdnc(ThirdpartySdncRegisterInfo thirdpartySdnc) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrThirdpartySdncDetail esrSdncDetail = thirdpartySdncManagerUtil.sdncRegisterInfo2EsrSdnc(thirdpartySdnc);
    String sdncId = esrSdncDetail.getThirdpartySdncId();
    try {
      ExternalSystemProxy.registerSdnc(sdncId, esrSdncDetail);
      result.setId(sdncId);
      return Response.ok(result).build();
    } catch (ExtsysException e) {
      LOG.error("Register thirdParty SDNC failed !" , e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
    
  }

  public Response updateThirdpartySdnc(ThirdpartySdncRegisterInfo thirdpartySdnc, String sdncId) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrThirdpartySdncDetail originalEsrSdncDetail = queryEsrThirdpartySdncDetail(sdncId);
    EsrThirdpartySdncDetail esrSdncDetail = thirdpartySdncManagerUtil.sdncRegisterInfo2EsrSdnc(thirdpartySdnc);
    String resourceVersion = originalEsrSdncDetail.getResourceVersion();
    esrSdncDetail.setResourceVersion(resourceVersion);
    esrSdncDetail.setThirdpartySdncId(sdncId);
    EsrSystemInfo originalEsrSystemInfo = originalEsrSdncDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0);
    esrSdncDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
        .setEsrSystemInfoId(originalEsrSystemInfo.getEsrSystemInfoId());
    esrSdncDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
        .setResouceVersion(originalEsrSystemInfo.getResouceVersion());
    try {
      ExternalSystemProxy.registerSdnc(sdncId, esrSdncDetail);
      result.setId(sdncId);
      return Response.ok(result).build();
    } catch (ExtsysException e) {
      LOG.error("Update VNFM failed !" , e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }
  
  public Response queryThirdpartySdncList() {
    List<ThirdpartySdncRegisterInfo> sdncList = new ArrayList<>();
    EsrThirdpartySdncList esrSdnc = new EsrThirdpartySdncList();
    try {
      String esrSdncStr = ExternalSystemProxy.querySdncList();
      esrSdnc = new Gson().fromJson(esrSdncStr, EsrThirdpartySdncList.class);
      LOG.info("Response from AAI by query thirdparty SDNC list: " + esrSdnc);
      sdncList = getSdncDetailList(esrSdnc);
    } catch (ExtsysException e) {
      LOG.error("Query thirdparty SDNC list failed !", e);
    }
    return Response.ok(sdncList).build();
  }
  
  public Response queryThirdpartySdncById(String thirdpartySdncId) {
    ThirdpartySdncRegisterInfo thirdpartySdnc = querySdncDetail(thirdpartySdncId);
    return Response.ok(thirdpartySdnc).build();
  }
  
  public Response delThirdpartySdnc(String thirdpartySdncId) {
    EsrThirdpartySdncDetail thirdpartySdncDetail = queryEsrThirdpartySdncDetail(thirdpartySdncId);
    String resourceVersion = thirdpartySdncDetail.getResourceVersion();
    try {
      ExternalSystemProxy.deleteThirdpartySdnc(thirdpartySdncId, resourceVersion);
      return Response.noContent().build();
    } catch (ExtsysException e) {
      LOG.error("Delete VNFM from A&AI failed! thirdparty SDNC ID: " + thirdpartySdncId
          + "resouce-version:" + resourceVersion, e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }
  
  private ThirdpartySdncRegisterInfo querySdncDetail(String sdncId) {
    ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
    EsrThirdpartySdncDetail esrSdncDetail = new EsrThirdpartySdncDetail();
    try {
      String esrSdncStr = ExternalSystemProxy.queryThirdpartySdncDetail(sdncId);
      LOG.info("Response from AAI by query thirdparty SDNC: " + esrSdncStr);
      esrSdncDetail = new Gson().fromJson(esrSdncStr, EsrThirdpartySdncDetail.class);
      sdncRegisterInfo = thirdpartySdncManagerUtil.esrSdnc2SdncRegisterInfo(esrSdncDetail);
      return sdncRegisterInfo;
    } catch (ExtsysException e) {
      LOG.error("Query VNFM detail failed! thirdpaty SDNC ID: " + sdncId, e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }
  
  private List<ThirdpartySdncRegisterInfo> getSdncDetailList(EsrThirdpartySdncList esrThirdPartySdnc) {
    List<ThirdpartySdncRegisterInfo> sdncInfoList = new ArrayList<>();
    for (int i = 0; i < esrThirdPartySdnc.getEsrThirdpartySdnc().size(); i++) {
      String sdncId = esrThirdPartySdnc.getEsrThirdpartySdnc().get(i).getThirdpartySdncId();
      ThirdpartySdncRegisterInfo sdncInfo = querySdncDetail(sdncId);
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
    } catch (ExtsysException e) {
      LOG.error("Query VNFM detail failed! VNFM ID: " + sdncId, e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
    return esrSdncDetail;
  }
  
}
