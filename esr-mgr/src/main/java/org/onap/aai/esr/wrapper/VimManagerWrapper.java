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

import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.entity.aai.CloudRegionList;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
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
  private static VimManagerUtil vimManagerUtil = new VimManagerUtil();

  /**
   * get VimManagerWrapper instance.
   * 
   * @return vim manager wrapper instance
   */
  public static VimManagerWrapper getInstance() {
    if (vimManagerWrapper == null) {
      vimManagerWrapper = new VimManagerWrapper();
    }
    return vimManagerWrapper;

  }

  public Response registerVim(VimRegisterInfo vimRegisterInfo) {
    LOG.info(
        "Start register VIM, input VIM info is: " + ExtsysUtil.objectToString(vimRegisterInfo));
    CloudRegionDetail cloudRegion = new CloudRegionDetail();
    VimRegisterResponse result = new VimRegisterResponse();
    cloudRegion = vimManagerUtil.vimRegisterInfo2CloudRegion(vimRegisterInfo);
    String cloudOwner = vimRegisterInfo.getCloudOwner();
    String cloudRegionId = vimRegisterInfo.getCloudRegionId();
    try {
      CloudRegionProxy.registerVim(cloudOwner, cloudRegionId, cloudRegion);
      result.setCloudOwner(cloudOwner);
      result.setCloudRegionId(cloudRegionId);
      return Response.ok(result).build();
    } catch (Exception error) {
      error.printStackTrace();
      LOG.error("Register VIM failed !" + error.getMessage());
      return Response.serverError().build();
    }
  }

  public Response updateVim(String cloudOwner, String cloudRegionId,VimRegisterInfo vimRegisterInfo) {
    LOG.info("Start update VIM, input VIM info is: " + ExtsysUtil.objectToString(vimRegisterInfo));
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    VimRegisterResponse result = new VimRegisterResponse();

    cloudRegionDetail = getVimUpdateInfo(vimRegisterInfo);
    try {
      CloudRegionProxy.registerVim(cloudOwner, cloudRegionId, cloudRegionDetail);
      result.setCloudOwner(cloudOwner);
      result.setCloudRegionId(cloudRegionId);
      return Response.ok(result).build();
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Update VIM failed !" + e.getMessage());
      return Response.serverError().build();
    }
  }

  public Response queryVimListDetails() {
    ArrayList<VimRegisterInfo> vimRegisterInfos = new ArrayList<VimRegisterInfo>();
    CloudRegionList cloudRegionList = new CloudRegionList();
    try {
      String aaiVimList = CloudRegionProxy.qureyVimList();
      cloudRegionList = new Gson().fromJson(aaiVimList, CloudRegionList.class);
      vimRegisterInfos = getVimDetailList(cloudRegionList);
      return Response.ok(vimRegisterInfos).build();
    } catch (Exception error) {
      error.printStackTrace();
      LOG.error("Query vim list details failed !" + error.getMessage());
      return Response.ok().build();
    }

  }

  public Response queryVimById(String cloudOwner, String cloudRegionId) {
    VimRegisterInfo vim = new VimRegisterInfo();
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    try {
      String cloudRegionstr = CloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      LOG.info("Response from AAI by query VIM: " + cloudRegionstr);
      cloudRegionDetail = new Gson().fromJson(cloudRegionstr, CloudRegionDetail.class);
      vim = vimManagerUtil.cloudRegion2VimRegisterInfo(cloudRegionDetail);
      return Response.ok(vim).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.serverError().build();
    }

  }

  private ArrayList<VimRegisterInfo> getVimDetailList(CloudRegionList cloudRegionList) {
    ArrayList<VimRegisterInfo> vimRegisterInfos = new ArrayList<VimRegisterInfo>();
    VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
    int cloudRegionNum = cloudRegionList.getCloudRegion().size();
    for (int i = 0; i < cloudRegionNum; i++) {
      String cloudOwner = cloudRegionList.getCloudRegion().get(i).getCloudOwner();
      String cloudRegionId = cloudRegionList.getCloudRegion().get(i).getCloudRegionId();
      vimRegisterInfo = getVimDetail(cloudOwner, cloudRegionId);
      vimRegisterInfos.add(vimRegisterInfo);
    }
    return vimRegisterInfos;
  }

  private VimRegisterInfo getVimDetail(String cloudOwner, String cloudRegionId) {
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    VimRegisterInfo registeredVimInfo = new VimRegisterInfo();
    try {
      String cloudRegionstr = CloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      cloudRegionDetail = new Gson().fromJson(cloudRegionstr, CloudRegionDetail.class);
      registeredVimInfo = vimManagerUtil.cloudRegion2VimRegisterInfo(cloudRegionDetail);
    } catch (Exception error) {
      error.printStackTrace();
      LOG.error("query VIM detail failed ! cloud-owner = " + cloudOwner + ", cloud-region-id = "
          + cloudRegionId + error.getMessage());
    }
    return registeredVimInfo;
  }

  private CloudRegionDetail getOriginalCloudRegion(String cloudOwner, String cloudRegionId) {
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    try {
      String cloudRegionstr = CloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      cloudRegionDetail = new Gson().fromJson(cloudRegionstr, CloudRegionDetail.class);
      return cloudRegionDetail;
    } catch (Exception error) {
      error.printStackTrace();
      LOG.error("query VIM detail failed ! cloud-owner = " + cloudOwner + ", cloud-region-id = "
          + cloudRegionId + error.getMessage());
      return null;
    }
  }
  
  private CloudRegionDetail getVimUpdateInfo(VimRegisterInfo vimRegisterInfo) {
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    CloudRegionDetail originalCloudRegionDetail = new CloudRegionDetail();
    EsrSystemInfo originalSystemInfo = new EsrSystemInfo();
    String cloudOwner = vimRegisterInfo.getCloudOwner();
    String cloudRegionId = vimRegisterInfo.getCloudRegionId();
    originalCloudRegionDetail = getOriginalCloudRegion(cloudOwner, cloudRegionId);
    String resourceVersion = originalCloudRegionDetail.getResourceVersion();
    cloudRegionDetail = vimManagerUtil.vimRegisterInfo2CloudRegion(vimRegisterInfo);
    if(resourceVersion != null) {
      cloudRegionDetail.setResourceVersion(resourceVersion);
      originalSystemInfo = originalCloudRegionDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0);
      cloudRegionDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0).setEsrSystemInfoId(originalSystemInfo.getEsrSystemInfoId());
      cloudRegionDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0).setResouceVersion(originalSystemInfo.getResouceVersion());
    }
    return cloudRegionDetail;
  }

  public Response delVim(String cloudOwner, String cloudRegionId) {
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    cloudRegionDetail = queryCloudRegionDetail(cloudOwner, cloudRegionId);
    String resourceVersion = cloudRegionDetail.getResourceVersion();
    if (resourceVersion != null) {
      try {
        CloudRegionProxy.deleteVim(cloudOwner, cloudRegionId, resourceVersion);
        return Response.noContent().build();
      } catch (Exception e) {
        e.printStackTrace();
        LOG.error(
            "Delete cloud region from A&AI failed! cloud-owner = " + cloudOwner
                + ", cloud-region-id = " + cloudRegionId + "resouce-version:" + resourceVersion,
            e.getMessage());
        return Response.noContent().build();
      }
    } else {
      LOG.error("resouce-version is null ! Can not delete resouce from A&AI. ");
      return Response.noContent().build();
    }
  }
  
  private CloudRegionDetail queryCloudRegionDetail (String cloudOwner, String cloudRegionId) {
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    try {
      String cloudRegionStr = CloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      LOG.info("Response from AAI by query cloud region: " + cloudRegionStr);
      cloudRegionDetail = new Gson().fromJson(cloudRegionStr, CloudRegionDetail.class);
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Query EMS detail failed! cloud-owner = " + cloudOwner
                + ", cloud-region-id = " + cloudRegionId , e.getMessage());
    }
    return cloudRegionDetail;
  }
}
