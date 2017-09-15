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

import org.onap.aai.esr.common.SystemType;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.EsrSystemInfoList;
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.rest.ThirdpartySdncRegisterInfo;

public class ThirdpartySdncManagerUtil {
  
  public EsrThirdpartySdncDetail sdncRegisterInfo2EsrSdnc(ThirdpartySdncRegisterInfo sdncRegisterInfo) {
    EsrThirdpartySdncDetail esrThirdpartySdnc = new EsrThirdpartySdncDetail();
    sdncRegisterInfo.setThirdpartySdncId(ExtsysUtil.generateId());
    esrThirdpartySdnc.setThirdpartySdncId(sdncRegisterInfo.getThirdpartySdncId());
    esrThirdpartySdnc.setLocation(sdncRegisterInfo.getLocation());
    esrThirdpartySdnc.setProductName(sdncRegisterInfo.getProductName());
    esrThirdpartySdnc.setEsrSystemInfoList(getEsrSystemInfoList(sdncRegisterInfo));
    return esrThirdpartySdnc;
  }
  
  private EsrSystemInfoList getEsrSystemInfoList(ThirdpartySdncRegisterInfo sdncRegisterInfo) {
    EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
    ArrayList<EsrSystemInfo> esrSystemInfo = new ArrayList<EsrSystemInfo>();
    EsrSystemInfo authInfo = new EsrSystemInfo();
    authInfo.setResouceVersion(sdncRegisterInfo.getVersion());
    authInfo.setSystemName(sdncRegisterInfo.getName());
    authInfo.setServiceUrl(sdncRegisterInfo.getUrl());
    authInfo.setVendor(sdncRegisterInfo.getVendor());
    authInfo.setType(sdncRegisterInfo.getType());
    authInfo.setUserName(sdncRegisterInfo.getUserName());
    authInfo.setPassword(sdncRegisterInfo.getPassword());
    authInfo.setProtocol(sdncRegisterInfo.getProtocol());
    authInfo.setSystemType(SystemType.thirdparty_SDNC.toString());
    esrSystemInfo.add(authInfo);
    esrSystemInfoList.setEsrSystemInfo(esrSystemInfo);
    return esrSystemInfoList;
  }

  public ThirdpartySdncRegisterInfo esrSdnc2SdncRegisterInfo(EsrThirdpartySdncDetail esrSdnc) {
    ThirdpartySdncRegisterInfo registerSdncInfo = new ThirdpartySdncRegisterInfo();
    EsrSystemInfo esrSystemInfo = esrSdnc.getEsrSystemInfoList().getEsrSystemInfo().get(0);
    registerSdncInfo.setThirdpartySdncId(esrSdnc.getThirdpartySdncId());
    registerSdncInfo.setLocation(esrSdnc.getLocation());
    registerSdncInfo.setProductName(esrSdnc.getProductName());
    registerSdncInfo.setName(esrSystemInfo.getSystemName());
    registerSdncInfo.setPassword(esrSystemInfo.getPassword());
    registerSdncInfo.setProtocol(esrSystemInfo.getProtocol());
    registerSdncInfo.setType(esrSystemInfo.getType());
    registerSdncInfo.setUrl(esrSystemInfo.getServiceUrl());
    registerSdncInfo.setUserName(esrSystemInfo.getUserName());
    registerSdncInfo.setVendor(esrSystemInfo.getVendor());
    registerSdncInfo.setVersion(esrSystemInfo.getVersion());
    return registerSdncInfo;
  }
}
