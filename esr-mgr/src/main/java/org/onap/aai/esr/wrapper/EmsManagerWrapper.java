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

import org.onap.aai.esr.entity.rest.EmsRegisterInfo;
import org.onap.aai.esr.exception.ExceptionUtil;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.EmsManagerUtil;
import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.aai.EsrEmsList;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class EmsManagerWrapper {
  private static EmsManagerWrapper emsManagerWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(EmsManagerWrapper.class);
  private static EmsManagerUtil emsManagerUtil = new EmsManagerUtil();

  /**
   * get VnfmManagerWrapper instance.
   * @return ems manager wrapper instance
   */
  public static EmsManagerWrapper getInstance() {
    if (emsManagerWrapper == null) {
      emsManagerWrapper = new EmsManagerWrapper();
    }
    return emsManagerWrapper;
  }
  
  public Response registerEms(EmsRegisterInfo emsRegisterInfo) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrEmsDetail esrEmsDetail = new EsrEmsDetail();
    esrEmsDetail = emsManagerUtil.emsRegisterInfo2EsrEms(emsRegisterInfo);
    String emsId = esrEmsDetail.getEmsId();
    try {
      ExternalSystemProxy.registerEms(emsId, esrEmsDetail);
      result.setId(emsId);
      return Response.ok(result).build();
    } catch (ExtsysException e) {
      LOG.error("Register EMS failed !", e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }

  public Response updateEms(EmsRegisterInfo emsRegisterInfo, String emsId) {
    CommonRegisterResponse result = new CommonRegisterResponse();
    EsrEmsDetail esrEmsDetail = new EsrEmsDetail();
    esrEmsDetail = getNewEsrEmsDetail(emsRegisterInfo, emsId);
    try {
      ExternalSystemProxy.registerEms(emsId, esrEmsDetail);
      result.setId(emsId);
      return Response.ok(result).build();
    } catch (ExtsysException e) {
      LOG.error("Update VNFM failed !", e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }
  
  public Response queryEmsList() {
    ArrayList<EmsRegisterInfo> emsList = new ArrayList<EmsRegisterInfo>();
    EsrEmsList esrEms = new EsrEmsList();
    try {
      String esrEmsStr = ExternalSystemProxy.queryEmsList();
      esrEms = new Gson().fromJson(esrEmsStr, EsrEmsList.class);
      LOG.info("Response from AAI by query EMS list: " + esrEms);
      emsList = getEmsDetailList(esrEms);
      return Response.ok(emsList).build();
    } catch (ExtsysException e) {
      LOG.error("Query EMS list failed !", e);
      return Response.ok(emsList).build();
    }
  }
  
  public Response queryEmsById(String emsId) {
    EmsRegisterInfo ems;
    ems = queryEmsDetail(emsId);
    if (ems != null) {
      return Response.ok(ems).build();
    } else {
      return Response.ok(ems).build();
    }
  }
  
  public Response delEms(String emsId) {
    EsrEmsDetail esrEmsDetail = new EsrEmsDetail();
    esrEmsDetail = queryEsrEmsDetail(emsId);
    String resourceVersion = esrEmsDetail.getResourceVersion();
    try {
      ExternalSystemProxy.deleteEms(emsId, resourceVersion);
      return Response.noContent().build();
    } catch (ExtsysException e) {
      LOG.error(
          "Delete EMS from A&AI failed! EMS ID: " + emsId + "resouce-version:" + resourceVersion,
          e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }

  private EmsRegisterInfo queryEmsDetail(String emsId) {
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
    EsrEmsDetail esrEmsDetail = new EsrEmsDetail();
    try {
      String esrEmsStr = ExternalSystemProxy.queryEmsDetail(emsId);
      LOG.info("Response from AAI by query EMS: " + esrEmsStr);
      esrEmsDetail = new Gson().fromJson(esrEmsStr, EsrEmsDetail.class);
      emsRegisterInfo = emsManagerUtil.EsrEms2EmsRegisterInfo(esrEmsDetail);
    } catch (ExtsysException e) {
      LOG.error("Query EMS detail failed! EMS ID: " + emsId, e);
    }
    return emsRegisterInfo;
  }
  
  private ArrayList<EmsRegisterInfo> getEmsDetailList(EsrEmsList esrEms) {
    ArrayList<EmsRegisterInfo> emsInfoList = new ArrayList<EmsRegisterInfo>();
    EmsRegisterInfo emsInfo = new EmsRegisterInfo();
    for (int i = 0; i < esrEms.getEsrEms().size(); i++) {
      String emsId = esrEms.getEsrEms().get(i).getEmsId();
      emsInfo = queryEmsDetail(emsId);
      if (emsInfo != null) {
        emsInfoList.add(emsInfo);
      }
    }
    return emsInfoList;
  }
  
  private EsrEmsDetail queryEsrEmsDetail (String emsId) {
    EsrEmsDetail esrEmsDetail = new EsrEmsDetail();
    try {
      String esrEmsStr = ExternalSystemProxy.queryEmsDetail(emsId);
      LOG.info("Response from AAI by query EMS: " + esrEmsStr);
      esrEmsDetail = new Gson().fromJson(esrEmsStr, EsrEmsDetail.class);
    } catch (ExtsysException e) {
      LOG.error("Query EMS detail failed! EMS ID: " + emsId, e);
    }
    return esrEmsDetail;
  }
  
  private EsrEmsDetail getNewEsrEmsDetail(EmsRegisterInfo emsRegisterInfo, String emsId) {
    EsrEmsDetail esrEmsDetail = new EsrEmsDetail();
    ArrayList<EsrSystemInfo> newEsrSysInfoList = new ArrayList<EsrSystemInfo>();
    EsrSystemInfo newEsrSystemInfo = new EsrSystemInfo();
    EsrEmsDetail oriEsrEmsDetail = new EsrEmsDetail();
    ArrayList<EsrSystemInfo> oriEsrSysInfoList = new ArrayList<EsrSystemInfo>();
    EsrSystemInfo originalEsrSystemInfo = new EsrSystemInfo();
    
    oriEsrEmsDetail = queryEsrEmsDetail(emsId);
    esrEmsDetail = emsManagerUtil.emsRegisterInfo2EsrEms(emsRegisterInfo);
    String emsResourceVersion = oriEsrEmsDetail.getResourceVersion();
    esrEmsDetail.setResourceVersion(emsResourceVersion);
    esrEmsDetail.setEmsId(emsId);
    newEsrSysInfoList = esrEmsDetail.getEsrSystemInfoList().getEsrSystemInfo();
    oriEsrSysInfoList = oriEsrEmsDetail.getEsrSystemInfoList().getEsrSystemInfo();
    for (int i = 0; i < oriEsrSysInfoList.size(); i++) {
      originalEsrSystemInfo = oriEsrSysInfoList.get(i);
      for (int j = 0; j < newEsrSysInfoList.size(); j++) {
        newEsrSystemInfo = newEsrSysInfoList.get(j);
        if (originalEsrSystemInfo.getSystemType().equals(newEsrSystemInfo.getSystemType())) {
          esrEmsDetail.getEsrSystemInfoList().getEsrSystemInfo().get(j)
              .setResouceVersion(originalEsrSystemInfo.getResouceVersion());
          esrEmsDetail.getEsrSystemInfoList().getEsrSystemInfo().get(j)
              .setEsrSystemInfoId(originalEsrSystemInfo.getEsrSystemInfoId());
          break;
        }
      }
    }
    return esrEmsDetail;
  }
}
