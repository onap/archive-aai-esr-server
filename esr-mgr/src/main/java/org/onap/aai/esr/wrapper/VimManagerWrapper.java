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

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.entity.aai.CloudRegionList;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;
import org.onap.aai.esr.entity.rest.VimRegisterResponse;
import org.onap.aai.esr.exception.ExceptionUtil;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.CloudRegionProxy;
import org.onap.aai.esr.externalservice.cloud.Tenant;
import org.onap.aai.esr.externalservice.cloud.VimManagerProxy;
import org.onap.aai.esr.util.ExtsysUtil;
import org.onap.aai.esr.util.VimManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


public class VimManagerWrapper {

  private static VimManagerWrapper vimManagerWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(VimManagerWrapper.class);
  
  @Inject
  private VimManagerUtil vimManagerUtil;
  
  @Inject
  private CloudRegionProxy cloudRegionProxy;
  
  @Inject
  private ExtsysUtil extsysUtil;

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
        "Start register VIM, input VIM info is: " + extsysUtil.objectToString(vimRegisterInfo));
    CloudRegionDetail cloudRegion = new CloudRegionDetail();
    VimRegisterResponse result = new VimRegisterResponse();
    cloudRegion = vimManagerUtil.vimRegisterInfo2CloudRegion(vimRegisterInfo);
    String cloudOwner = vimRegisterInfo.getCloudOwner();
    String cloudRegionId = vimRegisterInfo.getCloudRegionId();
    try {
      cloudRegionProxy.registerVim(cloudOwner, cloudRegionId, cloudRegion);
      result.setCloudOwner(cloudOwner);
      result.setCloudRegionId(cloudRegionId);
      Tenant tenant = new Tenant();
      tenant.setDefaultTenant(cloudRegion.getEsrSystemInfoList().getEsrSystemInfo().get(0).getDefaultTenant());
      try {
        VimManagerProxy.updateVim(cloudOwner, cloudRegionId, tenant);
      } catch (ExtsysException e) {
        LOG.error("Update VIM by Multi-cloud failed !", e);
        throw ExceptionUtil.buildExceptionResponse(e.getMessage());
      }
      return Response.ok(result).build();
    } catch (ExtsysException error) {
      LOG.error("Register VIM failed !", error);
      throw ExceptionUtil.buildExceptionResponse(error.getMessage());
    }
  }

  public Response updateVim(String cloudOwner, String cloudRegionId,VimRegisterInfo vimRegisterInfo) {
    LOG.info("Start update VIM, input VIM info is: " + extsysUtil.objectToString(vimRegisterInfo));
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    VimRegisterResponse result = new VimRegisterResponse();

    cloudRegionDetail = getVimUpdateInfo(vimRegisterInfo);
    try {
        cloudRegionProxy.registerVim(cloudOwner, cloudRegionId, cloudRegionDetail);
      result.setCloudOwner(cloudOwner);
      result.setCloudRegionId(cloudRegionId);
      return Response.ok(result).build();
    } catch (ExtsysException e) {
      LOG.error("Update VIM failed !", e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }

  public Response queryVimListDetails() {
    ArrayList<VimRegisterInfo> vimRegisterInfos = new ArrayList<VimRegisterInfo>();
    CloudRegionList cloudRegionList = new CloudRegionList();
    try {
      String aaiVimList = cloudRegionProxy.qureyVimList();
      cloudRegionList = new Gson().fromJson(aaiVimList, CloudRegionList.class);
      vimRegisterInfos = getVimDetailList(cloudRegionList);
      return Response.ok(vimRegisterInfos).build();
    } catch (ExtsysException error) {
      LOG.error("Query vim list details failed !", error);
      return Response.ok(vimRegisterInfos).build();
    }

  }

  public Response queryVimById(String cloudOwner, String cloudRegionId) {
    VimRegisterInfo vim = new VimRegisterInfo();
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    try {
      String cloudRegionstr = cloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      LOG.info("Response from AAI by query VIM: " + cloudRegionstr);
      cloudRegionDetail = new Gson().fromJson(cloudRegionstr, CloudRegionDetail.class);
      vim = vimManagerUtil.cloudRegion2VimRegisterInfo(cloudRegionDetail);
      return Response.ok(vim).build();
    } catch (ExtsysException e) {
      LOG.error("Query vim details by ID failed !", e);
      return Response.ok(vim).build();
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
      String cloudRegionstr = cloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      cloudRegionDetail = new Gson().fromJson(cloudRegionstr, CloudRegionDetail.class);
      registeredVimInfo = vimManagerUtil.cloudRegion2VimRegisterInfo(cloudRegionDetail);
    } catch (ExtsysException error) {
      LOG.error("query VIM detail failed ! cloud-owner = " + cloudOwner + ", cloud-region-id = "
          + cloudRegionId, error);
    }
    return registeredVimInfo;
  }

  private CloudRegionDetail getOriginalCloudRegion(String cloudOwner, String cloudRegionId) {
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    try {
      String cloudRegionstr = cloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      cloudRegionDetail = new Gson().fromJson(cloudRegionstr, CloudRegionDetail.class);
      return cloudRegionDetail;
    } catch (ExtsysException error) {
      LOG.error("query VIM detail failed ! cloud-owner = " + cloudOwner + ", cloud-region-id = "
          + cloudRegionId, error);
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
    try {
      cloudRegionProxy.deleteVim(cloudOwner, cloudRegionId, resourceVersion);
      return Response.noContent().build();
    } catch (ExtsysException e) {
      LOG.error("Delete cloud region from A&AI failed! cloud-owner = " + cloudOwner
          + ", cloud-region-id = " + cloudRegionId + "resouce-version:" + resourceVersion, e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
  }

  private CloudRegionDetail queryCloudRegionDetail (String cloudOwner, String cloudRegionId) {
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    try {
      String cloudRegionStr = cloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
      LOG.info("Response from AAI by query cloud region: " + cloudRegionStr);
      cloudRegionDetail = new Gson().fromJson(cloudRegionStr, CloudRegionDetail.class);
    } catch (ExtsysException e) {
      LOG.error("Query EMS detail failed! cloud-owner = " + cloudOwner
                + ", cloud-region-id = " + cloudRegionId , e);
      throw ExceptionUtil.buildExceptionResponse(e.getMessage());
    }
    return cloudRegionDetail;
  }
}
