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

import org.onap.aai.esr.common.SystemType;
import org.onap.aai.esr.entity.aai.AuthInfo;
import org.onap.aai.esr.entity.aai.EsrSystemInfoList;
import org.onap.aai.esr.entity.aai.EsrVnfm;
import org.onap.aai.esr.entity.rest.VnfmRegisterInfo;

public class VnfmManagerUtil {
  
  public EsrVnfm vnfmRegisterInfo2EsrVnfm(VnfmRegisterInfo vnfmRegisterInfo) {
    EsrVnfm esrVnfm = new EsrVnfm();
    EsrSystemInfoList esrSystemInfo = new EsrSystemInfoList();
    AuthInfo authInfo = new AuthInfo();
    esrVnfm.setCertificateUrl(vnfmRegisterInfo.getCertificateUrl());
    esrVnfm.setResouceVersion(vnfmRegisterInfo.getVersion());
    esrVnfm.setVimId(vnfmRegisterInfo.getVimId());
    esrVnfm.setVnfmId(ExtsysUtil.generateId());
    authInfo = getAuthInfoFromVnfmRegisterInfo(vnfmRegisterInfo);
    esrSystemInfo = ExtsysUtil.getEsrSystemInfoListFromAuthInfo(authInfo);
    esrVnfm.setEsrSystemInfoList(esrSystemInfo);
    return esrVnfm;
  }

  /**
   * @param vnfmRegisterInfo vnfm register informantion from portal
   * @return
   */
  private AuthInfo getAuthInfoFromVnfmRegisterInfo(VnfmRegisterInfo vnfmRegisterInfo) {
    AuthInfo authInfo = new AuthInfo();
    authInfo.setEsrSystemInfoId(ExtsysUtil.generateId());
    authInfo.setSystemName(vnfmRegisterInfo.getName());
    authInfo.setType(vnfmRegisterInfo.getType());
    authInfo.setVendor(vnfmRegisterInfo.getVendor());
    authInfo.setVersion(vnfmRegisterInfo.getVersion());
    authInfo.setServiceUrl(vnfmRegisterInfo.getUrl());
    authInfo.setUserName(vnfmRegisterInfo.getUserName());
    authInfo.setPassword(vnfmRegisterInfo.getPassword());
    authInfo.setSystemType(SystemType.VNFM.toString());
    return authInfo;
  }
  
  public VnfmRegisterInfo esrVnfm2VnfmRegisterInfo(EsrVnfm esrVnfm) {
    VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
    AuthInfo authInfo = new AuthInfo();
    vnfmRegisterInfo.setVnfmId(esrVnfm.getVnfmId());
    vnfmRegisterInfo.setCertificateUrl(esrVnfm.getCertificateUrl());
    vnfmRegisterInfo.setVimId(esrVnfm.getVimId());
    authInfo = esrVnfm.getEsrSystemInfoList().getEsrSystemInfo().getEsrSystemInfo().get(0);
    vnfmRegisterInfo.setName(authInfo.getSystemName());
    vnfmRegisterInfo.setPassword(authInfo.getPassword());
    vnfmRegisterInfo.setType(authInfo.getType());
    vnfmRegisterInfo.setUrl(authInfo.getServiceUrl());
    vnfmRegisterInfo.setUserName(authInfo.getUserName());
    vnfmRegisterInfo.setVendor(authInfo.getVendor());
    vnfmRegisterInfo.setVersion(authInfo.getVersion());
    return vnfmRegisterInfo;
  }

}
