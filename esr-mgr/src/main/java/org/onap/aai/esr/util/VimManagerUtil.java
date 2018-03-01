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
package org.onap.aai.esr.util;


import java.util.ArrayList;
import java.util.List;
import org.onap.aai.esr.common.SystemType;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.entity.aai.EsrSystemInfoList;
import org.onap.aai.esr.entity.rest.VimAuthInfo;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;

public class VimManagerUtil {
    private static ExtsysUtil extsysUtil = new ExtsysUtil();

    public CloudRegionDetail vimRegisterInfo2CloudRegion(VimRegisterInfo vimRegisterInfo) {
        CloudRegionDetail cloudRegion = new CloudRegionDetail();

        cloudRegion.setCloudOwner(vimRegisterInfo.getCloudOwner());
        cloudRegion.setCloudRegionId(vimRegisterInfo.getCloudRegionId());
        cloudRegion.setCloudType(vimRegisterInfo.getCloudType());
        cloudRegion.setCloudRegionVersion(vimRegisterInfo.getCloudRegionVersion());
        cloudRegion.setCloudZone(vimRegisterInfo.getCloudZone());
        cloudRegion.setComplexName(vimRegisterInfo.getComplexName());
        cloudRegion.setOwnerDefinedType(vimRegisterInfo.getOwnerDefinedType());
        cloudRegion.setCloudExtraInfo(vimRegisterInfo.getCloudExtraInfo());

        EsrSystemInfo esrSystemInfoObj = vimAuthInfo2EsrSystemInfoObj(vimRegisterInfo.getVimAuthInfos());
        esrSystemInfoObj.setSystemStatus(vimRegisterInfo.getStatus());
        EsrSystemInfoList esrSystemInfoList = extsysUtil.getEsrSystemInfoListFromAuthInfo(esrSystemInfoObj);
        cloudRegion.setEsrSystemInfoList(esrSystemInfoList);
        return cloudRegion;
    }

    private EsrSystemInfo vimAuthInfo2EsrSystemInfoObj(List<VimAuthInfo> vimAuthInfos) {
        EsrSystemInfo esrSystemInfoObj = new EsrSystemInfo();
        VimAuthInfo vimAuthInfo = vimAuthInfos.get(0);
        esrSystemInfoObj.setCloudDomain(vimAuthInfo.getCloudDomain());
        esrSystemInfoObj.setUserName(vimAuthInfo.getUserName());
        esrSystemInfoObj.setPassword(vimAuthInfo.getPassword());
        esrSystemInfoObj.setServiceUrl(vimAuthInfo.getAuthUrl());
        esrSystemInfoObj.setSslCassert(vimAuthInfo.getSslCacert());
        esrSystemInfoObj.setSslInsecure(vimAuthInfo.getSslInsecure());
        esrSystemInfoObj.setDefaultTenant(vimAuthInfo.getDefaultTenant());
        esrSystemInfoObj.setEsrSystemInfoId(extsysUtil.generateId());
        esrSystemInfoObj.setSystemType(SystemType.VIM.toString());
        // esrSystemInfoObj.setSystemStatus(SystemStatus.normal.toString());
        return esrSystemInfoObj;
    }

    private VimAuthInfo authInfo2VimAuthInfo(EsrSystemInfo authInfo) {
        VimAuthInfo vimAuthInfo = new VimAuthInfo();
        vimAuthInfo.setAuthUrl(authInfo.getServiceUrl());
        vimAuthInfo.setCloudDomain(authInfo.getCloudDomain());
        vimAuthInfo.setPassword(authInfo.getPassword());
        vimAuthInfo.setSslCacert(authInfo.getSslCassert());
        vimAuthInfo.setSslInsecure(authInfo.getSslInsecure());
        vimAuthInfo.setUserName(authInfo.getUserName());
        vimAuthInfo.setDefaultTenant(authInfo.getDefaultTenant());
        return vimAuthInfo;
    }

    public VimRegisterInfo cloudRegion2VimRegisterInfo(CloudRegionDetail cloudRegion) {
        VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
        List<VimAuthInfo> vimAuthInfos = new ArrayList<>();
        if (cloudRegion.getEsrSystemInfoList() != null) {
            VimAuthInfo vimAuthInfo =
                    authInfo2VimAuthInfo(cloudRegion.getEsrSystemInfoList().getEsrSystemInfo().get(0));
            vimAuthInfos.add(vimAuthInfo);
            vimRegisterInfo.setStatus(cloudRegion.getEsrSystemInfoList().getEsrSystemInfo().get(0).getSystemStatus());
        }
        vimRegisterInfo.setVimAuthInfos(vimAuthInfos);
        vimRegisterInfo.setCloudExtraInfo(cloudRegion.getCloudExtraInfo());
        vimRegisterInfo.setCloudOwner(cloudRegion.getCloudOwner());
        vimRegisterInfo.setCloudRegionId(cloudRegion.getCloudRegionId());
        vimRegisterInfo.setCloudType(cloudRegion.getCloudType());
        vimRegisterInfo.setCloudZone(cloudRegion.getCloudZone());
        vimRegisterInfo.setComplexName(cloudRegion.getComplexName());
        vimRegisterInfo.setCloudRegionVersion(cloudRegion.getCloudRegionVersion());
        vimRegisterInfo.setOwnerDefinedType(cloudRegion.getOwnerDefinedType());
        return vimRegisterInfo;
    }
}
