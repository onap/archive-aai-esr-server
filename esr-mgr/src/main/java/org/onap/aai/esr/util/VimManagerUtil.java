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
package org.onap.aai.esr.util;

import java.util.ArrayList;

import org.onap.aai.esr.common.SystemStatus;
import org.onap.aai.esr.common.SystemType;
import org.onap.aai.esr.entity.aai.AuthInfo;
import org.onap.aai.esr.entity.aai.CloudRegion;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.EsrSystemInfoList;
import org.onap.aai.esr.entity.rest.VimAuthInfo;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;


public class VimManagerUtil {
  
  public static CloudRegion vimRegisterInfo2CloudRegion(VimRegisterInfo vimRegisterInfo) {
    CloudRegion cloudRegion = new CloudRegion();
    EsrSystemInfoList esrSystemInfo = new EsrSystemInfoList();
    AuthInfo authInfo = new AuthInfo();
    
    cloudRegion.setCloudOwner(vimRegisterInfo.getCloudOwner());
    cloudRegion.setCloudRegionId(vimRegisterInfo.getCloudRegionId());
    cloudRegion.setCloudType(vimRegisterInfo.getCloudType());
    cloudRegion.setCloudRegionVersion(vimRegisterInfo.getCloudRegionVersion());
    cloudRegion.setCloudZone(vimRegisterInfo.getCloudZone());
    cloudRegion.setComplexName(vimRegisterInfo.getComplexName());
    cloudRegion.setOwnerDefinedType(vimRegisterInfo.getOwnerDefinedType());
    cloudRegion.setCloudExtraInfo(vimRegisterInfo.getCloudExtraInfo());
    
    authInfo = vimAuthInfo2AuthInfo(vimRegisterInfo.getVimAuthInfo());
    esrSystemInfo = ExtsysUtil.getEsrSystemInfoListFromAuthInfo(authInfo);
    cloudRegion.setEsrSystemInfoList(esrSystemInfo);
    return cloudRegion;
  }

  private static AuthInfo vimAuthInfo2AuthInfo(VimAuthInfo vimAuthInfo) {
    AuthInfo authInfo = new AuthInfo();
    authInfo.setCloudDomain(vimAuthInfo.getCloudDomain());
    authInfo.setUserName(vimAuthInfo.getUserName());
    authInfo.setPassword(vimAuthInfo.getPassword());
    authInfo.setServiceUrl(vimAuthInfo.getAuthUrl());
    authInfo.setSslCassert(vimAuthInfo.getSslCacert());
    authInfo.setSslInsecure(vimAuthInfo.getSslInsecure());
    authInfo.setEsrSystemInfoId(ExtsysUtil.generateId());
    authInfo.setSystemType(SystemType.VIM.toString());
    authInfo.setSystemStatus(SystemStatus.normal.toString());
    return authInfo;
  }
  
  private static VimAuthInfo authInfo2VimAuthInfo(AuthInfo authInfo) {
    VimAuthInfo vimAuthInfo = new VimAuthInfo();
    vimAuthInfo.setAuthUrl(authInfo.getServiceUrl());
    vimAuthInfo.setCloudDomain(authInfo.getCloudDomain());
    vimAuthInfo.setPassword(authInfo.getPassword());
    vimAuthInfo.setSslCacert(authInfo.getSslCassert());
    vimAuthInfo.setSslInsecure(authInfo.getSslInsecure());
    vimAuthInfo.setUserName(authInfo.getUserName());
    return vimAuthInfo;
  }
  
  public static VimRegisterInfo cloudRegion2VimRegisterInfo(CloudRegion cloudRegion) {
    VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
    VimAuthInfo vimAuthInfo = new VimAuthInfo();
    vimAuthInfo = authInfo2VimAuthInfo(cloudRegion.getEsrSystemInfoList().getEsrSystemInfo().getEsrSystemInfo().get(0));
    vimRegisterInfo.setVimAuthInfo(vimAuthInfo);
    vimRegisterInfo.setCloudExtraInfo(cloudRegion.getCloudExtraInfo());
    vimRegisterInfo.setCloudOwner(cloudRegion.getCloudOwner());
    vimRegisterInfo.setCloudRegionId(cloudRegion.getCloudRegionId());
    vimRegisterInfo.setCloudType(cloudRegion.getCloudType());
    vimRegisterInfo.setCloudZone(cloudRegion.getCloudZone());
    vimRegisterInfo.setComplexName(cloudRegion.getComplexName());
    vimRegisterInfo.setOwnerDefinedType(cloudRegion.getOwnerDefinedType());
    return vimRegisterInfo;
  }
}
