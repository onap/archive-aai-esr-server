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
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.EmsManagerUtil;
import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class EmsManagerWrapper {
  private static EmsManagerWrapper emsManagerWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(EmsManagerWrapper.class);

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
    esrEmsDetail = EmsManagerUtil.emsRegisterInfo2EsrEms(emsRegisterInfo);
    String emsId = esrEmsDetail.getEmsId();
    try {
      ExternalSystemProxy.registerEms(emsId, esrEmsDetail);
      result.setId(emsId);
      return Response.ok(result).build();
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Register EMS failed !" + e.getMessage());
      return Response.serverError().build();
    }
  }

  public Response updateEms(EmsRegisterInfo ems) {
    //TODO
    return Response.ok().build();
  }
  
  public Response queryEmsList() {
    //TODO
    ArrayList<EmsRegisterInfo> emsList = new ArrayList<EmsRegisterInfo>();
    return Response.ok(emsList).build();
  }
  
  public Response queryEmsById(String emsId) {
    EmsRegisterInfo ems = new EmsRegisterInfo();
    ems = queryEmsDetail(emsId);
    if (ems != null) {
      return Response.ok(ems).build();
    } else {
      return Response.ok().build();
    }
  }
  
  public Response delEms(String emsId) {
    //TODO
    return Response.noContent().build();
  }
  
  private EmsRegisterInfo queryEmsDetail(String emsId) {
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
    EsrEmsDetail esrEmsDetail = new EsrEmsDetail();
    try {
      String esrEmsStr = ExternalSystemProxy.queryEmsDetail(emsId);
      LOG.info("Response from AAI by query EMS: " + esrEmsStr);
      esrEmsDetail = new Gson().fromJson(esrEmsStr, EsrEmsDetail.class);
      emsRegisterInfo = EmsManagerUtil.EsrEms2EmsRegisterInfo(esrEmsDetail);
      return emsRegisterInfo;
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Query VNFM detail failed! EMS ID: " + emsId, e.getMessage());
      return null;
    }
  }
}
