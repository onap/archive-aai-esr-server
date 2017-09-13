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
import org.onap.aai.esr.entity.aai.EsrEms;
import org.onap.aai.esr.entity.rest.AlarmAddr;
import org.onap.aai.esr.entity.rest.EmsRegisterInfo;
import org.onap.aai.esr.entity.rest.FtpAddr;

public class EmsManagerUtil {
  
  public static EsrEms emsRegisterInfo2EsrEms(EmsRegisterInfo emsRegisterInfo) {
    EsrEms esrEms = new EsrEms();
    esrEms.setEmsId(ExtsysUtil.generateId());
    ArrayList<EsrSystemInfo> authInfos = new ArrayList<EsrSystemInfo>();
    authInfos = getAuthInfosFromRegisterData(emsRegisterInfo);
    esrEms.setEsrSystemInfoList(ExtsysUtil.getEsrSystemInfoListFromAuthInfoList(authInfos));
    return esrEms;
  }

  private static ArrayList<EsrSystemInfo> getAuthInfosFromRegisterData(EmsRegisterInfo emsRegisterInfo) {
    ArrayList<EsrSystemInfo> authInfos = new ArrayList<EsrSystemInfo>();
    EsrSystemInfo resouceAuthInfo = new EsrSystemInfo();
    EsrSystemInfo performanceAuthInfo = new EsrSystemInfo();
    EsrSystemInfo alarmAuthInfo = new EsrSystemInfo();
    resouceAuthInfo = getAuthInfoFromFtpAddr(emsRegisterInfo, SystemType.EMS_RESOUCE.toString());
    performanceAuthInfo = getAuthInfoFromFtpAddr(emsRegisterInfo, SystemType.EMS_PERFORMANCE.toString());
    alarmAuthInfo = getAuthInfoFromAlarmAddr(emsRegisterInfo);
    authInfos.add(resouceAuthInfo);
    authInfos.add(performanceAuthInfo);
    authInfos.add(alarmAuthInfo);
    return authInfos;
  }
  
  private static EsrSystemInfo getAuthInfoFromFtpAddr(EmsRegisterInfo emsRegisterInfo, String systemType) {
    EsrSystemInfo authInfo = new EsrSystemInfo();
    FtpAddr ftpAddr = new FtpAddr();
    if(systemType.equals(SystemType.EMS_RESOUCE.toString())) {
      ftpAddr = emsRegisterInfo.getResourceAddr();
    } else if(systemType.equals(SystemType.EMS_PERFORMANCE.toString())) {
      ftpAddr = emsRegisterInfo.getPerformanceAddr();
    }
    authInfo.setType(ftpAddr.getFtptype());
    authInfo.setIpAddress(ftpAddr.getIp());
    authInfo.setPort(ftpAddr.getPort());
    authInfo.setUserName(ftpAddr.getUser());
    authInfo.setPassword(ftpAddr.getPassword());
    authInfo.setRemotePath(ftpAddr.getRemotepath());
    authInfo.setPassive(ftpAddr.getPassive());
    authInfo.setEsrSystemInfoId(ExtsysUtil.generateId());
    authInfo.setSystemType(systemType);
    authInfo.setSystemName(emsRegisterInfo.getName());
    authInfo.setVendor(emsRegisterInfo.getVendor());
    authInfo.setVersion(emsRegisterInfo.getVersion());
    return authInfo;
  }
  
  private static EsrSystemInfo getAuthInfoFromAlarmAddr(EmsRegisterInfo emsRegisterInfo) {
    EsrSystemInfo authInfo = new EsrSystemInfo();
    AlarmAddr alarmAddr = new AlarmAddr();
    alarmAddr = emsRegisterInfo.getAlarmAddr();
    authInfo.setIpAddress(alarmAddr.getIp());
    authInfo.setPort(alarmAddr.getPort());
    authInfo.setUserName(alarmAddr.getUser());
    authInfo.setPassword(alarmAddr.getPassword());
    authInfo.setSystemType(SystemType.EMS_ALARM.toString());
    authInfo.setSystemName(emsRegisterInfo.getName());
    authInfo.setVendor(emsRegisterInfo.getVendor());
    authInfo.setVersion(emsRegisterInfo.getVersion());
    return authInfo;
  }
  
  public static EmsRegisterInfo EsrEms2EmsRegisterInfo(EsrEms esrEms) {
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
    ArrayList<EsrSystemInfo> esrSystemInfo = new ArrayList<EsrSystemInfo>();
    EsrSystemInfo authInfo = new EsrSystemInfo();
    esrSystemInfo = esrEms.getEsrSystemInfoList().getEsrSystemInfo();
    emsRegisterInfo.setEmsId(esrEms.getEmsId());
    
    for(int i=0; i<esrSystemInfo.size(); i++) {
      authInfo = esrSystemInfo.get(i);
      if(authInfo.getSystemType().equals(SystemType.EMS_RESOUCE.toString())){
        FtpAddr resourceAddr = new FtpAddr();
        resourceAddr = getFtpAddrFromAuthInfo(authInfo);
        emsRegisterInfo.setResourceAddr(resourceAddr);
      } else if(authInfo.getSystemType().equals(SystemType.EMS_PERFORMANCE.toString())) {
        FtpAddr performanceAddr = new FtpAddr();
        performanceAddr = getFtpAddrFromAuthInfo(authInfo);
        emsRegisterInfo.setResourceAddr(performanceAddr);
      } else if(authInfo.getSystemType().equals(SystemType.EMS_ALARM.toString())) {
        AlarmAddr alarmAddr = new AlarmAddr();
        alarmAddr = getAlarmAddrFromAuthInfo(authInfo);
        emsRegisterInfo.setAlarmAddr(alarmAddr);
      }
    }
    emsRegisterInfo.setName(authInfo.getSystemName());
    emsRegisterInfo.setVendor(authInfo.getVendor());
    emsRegisterInfo.setVersion(authInfo.getVersion());
    return emsRegisterInfo;
  }
  
  private static FtpAddr getFtpAddrFromAuthInfo(EsrSystemInfo authInfo) {
    FtpAddr ftpAddr = new FtpAddr();
    ftpAddr.setFtptype(authInfo.getType());
    ftpAddr.setIp(authInfo.getIpAddress());
    ftpAddr.setPassive(authInfo.getPassive());
    ftpAddr.setPassword(authInfo.getPassword());
    ftpAddr.setPort(authInfo.getPort());
    ftpAddr.setRemotepath(authInfo.getRemotePath());
    ftpAddr.setUser(authInfo.getUserName());
    return ftpAddr;
  }
  
  private static AlarmAddr getAlarmAddrFromAuthInfo(EsrSystemInfo authInfo) {
    AlarmAddr alarmAddr = new AlarmAddr();
    alarmAddr.setIp(authInfo.getIpAddress());
    alarmAddr.setPassword(authInfo.getPassword());
    alarmAddr.setPort(authInfo.getPort());
    alarmAddr.setUser(authInfo.getUserName());
    return alarmAddr;
  }
}
