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

import org.onap.aai.esr.entity.aai.CloudRegion;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;
import org.onap.aai.esr.entity.rest.VimRegisterResponse;
import org.onap.aai.esr.externalservice.aai.CloudRegionProxy;
import org.onap.aai.esr.util.ExtsysUtil;
import org.onap.aai.esr.util.VimManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


public class VimManagerWrapper {

  private static VimManagerWrapper vimManagerWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(VimManagerWrapper.class);

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
  
  public Response registerVim(VimRegisterInfo vimRegisterInfo) {
    LOG.info("Start register VIM, input VIM info is: " + ExtsysUtil.objectToString(vimRegisterInfo));
    CloudRegion cloudRegion = new CloudRegion();
    VimRegisterResponse result = new VimRegisterResponse();
    cloudRegion = VimManagerUtil.vimRegisterInfo2CloudRegion(vimRegisterInfo);
    String cloud_owner = vimRegisterInfo.getCloudOwner();
    String cloud_region_id = vimRegisterInfo.getCloudRegionId();

    try {
      CloudRegionProxy.registerVim(cloud_owner, cloud_region_id, cloudRegion);
      result.setCloudOwner(cloud_owner);
      result.setCloudRegionId(cloud_region_id);
      return Response.ok(result).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.serverError().build();
    }
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
  
  public Response queryVimById(String cloudOwner, String cloudRegionId) {
    VimRegisterInfo vim = new VimRegisterInfo();
    CloudRegion cloudRegion = new CloudRegion();
    try {
      String cloudRegionstr = CloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      LOG.info("Response from AAI by query VIM: " + cloudRegionstr);
      cloudRegion = new Gson().fromJson(cloudRegionstr, CloudRegion.class);
      vim = VimManagerUtil.cloudRegion2VimRegisterInfo(cloudRegion);
      return Response.ok(vim).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.serverError().build();
    }
    
  }
  
  public Response delVim(String vimId) {
    //TODO
    return Response.noContent().build();
  }
}
