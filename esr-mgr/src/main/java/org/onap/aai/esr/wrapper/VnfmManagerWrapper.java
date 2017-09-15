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

import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
import org.onap.aai.esr.entity.rest.VnfmRegisterInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class VnfmManagerWrapper {
  private static VnfmManagerWrapper vnfmManagerWrapper;
//  private static final Logger LOG = LoggerFactory.getLogger(VnfmManagerWrapper.class);

  /**
   * get VnfmManagerWrapper instance.
   * @return vnfm manager wrapper instance
   */
  public static VnfmManagerWrapper getInstance() {
    if (vnfmManagerWrapper == null) {
      vnfmManagerWrapper = new VnfmManagerWrapper();
    }
    return vnfmManagerWrapper;
  }

  public Response registerVnfm(VnfmRegisterInfo vnfm) {
    //TODO
    CommonRegisterResponse result = null;
    return Response.ok(result).build();
  }
  
  public Response updateVnfm(VnfmRegisterInfo vnfm, String vnfmId) {
    //TODO
    return Response.ok().build();
  }
  
  public Response queryVnfmList() {
    ArrayList<VnfmRegisterInfo> vnfmList = new ArrayList<VnfmRegisterInfo>();
    //TODO
    return Response.ok(vnfmList).build();
  }
  
  public Response queryVnfmById(String vnfmId) {
    VnfmRegisterInfo vnfm = new VnfmRegisterInfo();
    //TODO
    return Response.ok(vnfm).build();
  }
  
  public Response delVnfm(String vnfmId) {
    //TODO
    return Response.noContent().build();
  }
}
