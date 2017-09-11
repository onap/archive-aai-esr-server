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

import org.onap.aai.esr.entity.rest.RegisterResponse;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class VimManagerWrapper {

  private static VimManagerWrapper vimManagerWrapper;
//  private static final Logger LOG = LoggerFactory.getLogger(VimManagerWrapper.class);

  /**
   * get VimManagerWrapper instance.
   * @return vim manager wrapper instance
   */
  public static VimManagerWrapper getInstance() {
    if (vimManagerWrapper == null) {
      vimManagerWrapper = new VimManagerWrapper();
    }
    return vimManagerWrapper;
  }
  
  public Response registerVim(VimRegisterInfo vim) {
    //TODO
    RegisterResponse result = null;
    return Response.ok(result).build();
  }

  public Response updateVim(VimRegisterInfo vim) {
    //TODO
    return Response.ok().build();
  }
  
  public Response queryVimList() {
    //TODO
    ArrayList<VimRegisterInfo> vimList = new ArrayList<VimRegisterInfo>();
    return Response.ok(vimList).build();
  }
  
  public Response queryVimById(String vimId) {
    VimRegisterInfo vim = new VimRegisterInfo();
    //TODO
    return Response.ok(vim).build();
  }
  
  public Response delVim(String vimId) {
    //TODO
    return Response.noContent().build();
  }
}
