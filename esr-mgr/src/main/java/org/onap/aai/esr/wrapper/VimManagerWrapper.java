/**
 * Copyright 2017-2018 ZTE Corporation.
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
import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.entity.aai.CloudRegionList;
import org.onap.aai.esr.entity.aai.Complex;
import org.onap.aai.esr.entity.aai.ComplexList;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.Relationship;
import org.onap.aai.esr.entity.aai.RelationshipData;
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

    private static VimManagerUtil vimManagerUtil = new VimManagerUtil();

    private static CloudRegionProxy cloudRegionProxy = new CloudRegionProxy();
    
    private static VimManagerProxy vimManagerProxy = new VimManagerProxy();

    private static ExtsysUtil extsysUtil = new ExtsysUtil();

    /**
     * get VimManagerWrapper instance.
     * 
     * @return vim manager wrapper instance
     */
    public static VimManagerWrapper getInstance() {
        if (vimManagerWrapper == null) {
            vimManagerWrapper = new VimManagerWrapper(cloudRegionProxy, vimManagerProxy);
        }
        return vimManagerWrapper;
    }
    
    public VimManagerWrapper(CloudRegionProxy cloudRegionProxy, VimManagerProxy vimManagerProxy) {
        VimManagerWrapper.cloudRegionProxy = cloudRegionProxy;
        VimManagerWrapper.vimManagerProxy = vimManagerProxy;
    }

    public Response registerVim(VimRegisterInfo vimRegisterInfo) {
        LOG.info("Start register VIM, input VIM info is: " + extsysUtil.objectToString(vimRegisterInfo));
        VimRegisterResponse result = new VimRegisterResponse();
        CloudRegionDetail cloudRegion = vimManagerUtil.vimRegisterInfo2CloudRegion(vimRegisterInfo);
        String cloudOwner = vimRegisterInfo.getCloudOwner();
        String cloudRegionId = vimRegisterInfo.getCloudRegionId();
        String physicalLocationId = vimRegisterInfo.getPhysicalLocationId();
        String complexName = getComplexName(physicalLocationId);
        cloudRegion.setComplexName(complexName);
        try {
            cloudRegionProxy.registerVim(cloudOwner, cloudRegionId, cloudRegion);
            result.setCloudOwner(cloudOwner);
            result.setCloudRegionId(cloudRegionId);
            createRelationship(cloudOwner, cloudRegionId, physicalLocationId);
            updateVimWithMultiCloud(cloudRegion, cloudOwner, cloudRegionId);
            return Response.ok(result).build();
        } catch (ExtsysException error) {
            LOG.error("Register VIM failed !", error);
            throw ExceptionUtil.buildExceptionResponse(error.getMessage());
        }
    }

    private void updateVimWithMultiCloud(CloudRegionDetail cloudRegion, String cloudOwner, String cloudRegionId) {
        Tenant tenant = new Tenant();
        tenant.setDefaultTenant(cloudRegion.getEsrSystemInfoList().getEsrSystemInfo().get(0).getDefaultTenant());
        try {
            vimManagerProxy.updateVim(cloudOwner, cloudRegionId, tenant);
        } catch (ExtsysException e) {
            LOG.error("Update VIM by Multi-cloud failed !", e);
        }
    }
    
    private void createRelationship(String cloudOwner, String cloudRegionId, String physicalLocationId) {
        Relationship relationship = new Relationship();
        RelationshipData relationshipData = new RelationshipData();
        List<RelationshipData> relationshipDatas = new ArrayList<>();
        String relatedLink = "/aai/v11/cloud-infrastructure/complexes/complex/" + physicalLocationId;
        relationship.setRelatedTo("complex");
        relationship.setRelatedLink(relatedLink);
        relationshipData.setRelationshipKey("complex.physical-location-id");
        relationshipData.setRelationshipValue(physicalLocationId);
        relationshipDatas.add(relationshipData);
        relationship.setRelationshipData(relationshipDatas);
        try {
            cloudRegionProxy.createCloudRegionRelationShip(cloudOwner, cloudRegionId, relationship);
        } catch (ExtsysException e) {
            LOG.error("Create relationship between cloudRegion and complex failed !", e);
        }
    }

    public Response updateVim(String cloudOwner, String cloudRegionId, VimRegisterInfo vimRegisterInfo) {
        LOG.info("Start update VIM, input VIM info is: " + extsysUtil.objectToString(vimRegisterInfo));
        VimRegisterResponse result = new VimRegisterResponse();

        CloudRegionDetail cloudRegionDetail = getVimUpdateInfo(vimRegisterInfo);
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
        List<VimRegisterInfo> vimRegisterInfos = new ArrayList<>();
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

    private List<VimRegisterInfo> getVimDetailList(CloudRegionList cloudRegionList) {
        List<VimRegisterInfo> vimRegisterInfos = new ArrayList<>();
        int cloudRegionNum = cloudRegionList.getCloudRegion().size();
        for (int i = 0; i < cloudRegionNum; i++) {
            String cloudOwner = cloudRegionList.getCloudRegion().get(i).getCloudOwner();
            String cloudRegionId = cloudRegionList.getCloudRegion().get(i).getCloudRegionId();
            VimRegisterInfo vimRegisterInfo = getVimDetail(cloudOwner, cloudRegionId);
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
            LOG.error("query VIM detail failed ! cloud-owner = " + cloudOwner + ", cloud-region-id = " + cloudRegionId,
                    error);
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
            LOG.error("query VIM detail failed ! cloud-owner = " + cloudOwner + ", cloud-region-id = " + cloudRegionId,
                    error);
            return null;
        }
    }

    private CloudRegionDetail getVimUpdateInfo(VimRegisterInfo vimRegisterInfo) {
        String cloudOwner = vimRegisterInfo.getCloudOwner();
        String cloudRegionId = vimRegisterInfo.getCloudRegionId();
        String resourceVersion = null;
        CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
        CloudRegionDetail originalCloudRegionDetail = getOriginalCloudRegion(cloudOwner, cloudRegionId);
        if (originalCloudRegionDetail != null) {
            resourceVersion = originalCloudRegionDetail.getResourceVersion();
            cloudRegionDetail = vimManagerUtil.vimRegisterInfo2CloudRegion(vimRegisterInfo);
            if (resourceVersion != null) {
                cloudRegionDetail.setResourceVersion(resourceVersion);
                EsrSystemInfo originalSystemInfo = originalCloudRegionDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0);
                cloudRegionDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
                .setEsrSystemInfoId(originalSystemInfo.getEsrSystemInfoId());
                cloudRegionDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0)
                .setResouceVersion(originalSystemInfo.getResouceVersion());
            }
        }
        return cloudRegionDetail;
    }

    public Response delVim(String cloudOwner, String cloudRegionId) {
        CloudRegionDetail cloudRegionDetail = queryCloudRegionDetail(cloudOwner, cloudRegionId);
        String resourceVersion = cloudRegionDetail.getResourceVersion();
        try {
            cloudRegionProxy.deleteVim(cloudOwner, cloudRegionId, resourceVersion);
            return Response.noContent().build();
        } catch (ExtsysException e) {
            LOG.error("Delete cloud region from A&AI failed! cloud-owner = " + cloudOwner + ", cloud-region-id = "
                    + cloudRegionId + "resouce-version:" + resourceVersion, e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }

    private CloudRegionDetail queryCloudRegionDetail(String cloudOwner, String cloudRegionId) {
        CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
        try {
            String cloudRegionStr = cloudRegionProxy.queryVimDetail(cloudOwner, cloudRegionId);
            LOG.info("Response from AAI by query cloud region: " + cloudRegionStr);
            cloudRegionDetail = new Gson().fromJson(cloudRegionStr, CloudRegionDetail.class);
        } catch (ExtsysException e) {
            LOG.error("Query EMS detail failed! cloud-owner = " + cloudOwner + ", cloud-region-id = " + cloudRegionId,
                    e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
        return cloudRegionDetail;
    }
    
    private String getComplexName(String physicalLocationId) {
        Complex complex = new Complex();
        try {
            String complexStr = cloudRegionProxy.queryComplex(physicalLocationId);
            LOG.info("The complexes query result is: " + complexStr);
            complex = new Gson().fromJson(complexStr, Complex.class);
            return complex.getComplexName();
        } catch (ExtsysException e) {
            LOG.error("Query complex by physical location Id failed !", e);
            return null;
        }
    }

    public Response queryComplexes() {
        ComplexList complexList = new ComplexList();
        List<String> physicalLocationIdList = new ArrayList<>();
        try {
            String complexesString = cloudRegionProxy.qureyComplexes();
            LOG.info("The complexes query result is: " + complexesString);
            complexList = new Gson().fromJson(complexesString, ComplexList.class);
            for (int i=0; i<complexList.getComplex().size(); i++) {
                physicalLocationIdList.add(complexList.getComplex().get(i).getPhysicalLocationId());
            }
        } catch (ExtsysException e) {
            LOG.error("Query complexes failed !", e);
        }
        return Response.ok(physicalLocationIdList).build();
    }
}
