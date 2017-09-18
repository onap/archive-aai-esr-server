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

import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.onap.aai.esr.entity.rest.ThirdpartySdncRegisterInfo;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ThirdpartySdncManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  public Response updateThirdpartySdnc(ThirdpartySdncRegisterInfo thirdpartySdnc) {
    //TODO
    return Response.ok().build();
  }
  
  public Response queryThirdpartySdncList() {
    //TODO
    ArrayList<ThirdpartySdncRegisterInfo> thirdpartySdncList = new ArrayList<ThirdpartySdncRegisterInfo>();
    return Response.ok(thirdpartySdncList).build();
  }
  
  public Response queryThirdpartySdncById(String thirdpartySdncId) {
    ThirdpartySdncRegisterInfo thirdpartySdnc = new ThirdpartySdncRegisterInfo();
    //TODO
    return Response.ok(thirdpartySdnc).build();
  }
  
  public Response delThirdpartySdnc(String thirdpartySdncId) {
    //TODO
    return Response.noContent().build();
  }
}
