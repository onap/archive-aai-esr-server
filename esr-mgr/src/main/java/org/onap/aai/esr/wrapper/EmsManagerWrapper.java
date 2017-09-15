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
import org.onap.aai.esr.entity.rest.CommonRegisterResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class EmsManagerWrapper {
  private static EmsManagerWrapper emsManagerWrapper;
//  private static final Logger LOG = LoggerFactory.getLogger(EmsManagerWrapper.class);

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
  
  public Response registerEms(EmsRegisterInfo ems) {
    //TODO
    CommonRegisterResponse result = null;
    return Response.ok(result).build();
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
    //TODO
    return Response.ok(ems).build();
  }
  
  public Response delEms(String emsId) {
    //TODO
    return Response.noContent().build();
  }
}
