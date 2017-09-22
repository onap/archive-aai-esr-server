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

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.rest.AlarmAddr;
import org.onap.aai.esr.entity.rest.EmsRegisterInfo;
import org.onap.aai.esr.entity.rest.FtpAddr;

import com.google.gson.Gson;

public class EmsManagerUtilTest {
  
  @Test
  public void getEsrSystemInfoListFromAuthInfoTest() {
    EmsManagerUtil emsManagerUtil = new EmsManagerUtil();
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
    EsrEmsDetail esrEms = new EsrEmsDetail();
    AlarmAddr alarmAddr = new AlarmAddr();
    FtpAddr resourceAddr = new FtpAddr();
    FtpAddr performanceAddr = new FtpAddr();
    alarmAddr.setIp("127.0.0.1");
    alarmAddr.setPassword("987654");
    alarmAddr.setPort("5000");
    alarmAddr.setUser("nancy");
    resourceAddr.setFtptype("sftp");
    resourceAddr.setIp("127.0.0.1");
    resourceAddr.setPassive(true);
    resourceAddr.setPassword("asdf");
    resourceAddr.setPort("5000");
    resourceAddr.setRemotepath("/home/per");
    resourceAddr.setUser("nancy");
    performanceAddr.setFtptype("sftp");
    performanceAddr.setIp("127.0.0.1");
    performanceAddr.setPassive(true);
    performanceAddr.setPassword("asdf");
    performanceAddr.setPort("5000");
    performanceAddr.setRemotepath("/home/per");
    performanceAddr.setUser("nancy");
    emsRegisterInfo.setName("EMS_TEST");
    emsRegisterInfo.setVendor("ZTE");
    emsRegisterInfo.setVersion("V1");
    emsRegisterInfo.setAlarmAddr(alarmAddr);
    emsRegisterInfo.setResourceAddr(resourceAddr);
    emsRegisterInfo.setPerformanceAddr(performanceAddr);
    esrEms = emsManagerUtil.emsRegisterInfo2EsrEms(emsRegisterInfo);
    esrEms.setEmsId("123456");
    esrEms.getEsrSystemInfoList().getEsrSystemInfo().get(0).setEsrSystemInfoId("234567");
    esrEms.getEsrSystemInfoList().getEsrSystemInfo().get(1).setEsrSystemInfoId("345678");
    esrEms.getEsrSystemInfoList().getEsrSystemInfo().get(2).setEsrSystemInfoId("456789");
    String esremsStr = new ExtsysUtil().objectToString(esrEms);
    String expect = "{\"ems-id\":\"123456\","
        + "\"esr-system-info-list\":"
        + "{\"esr-system-info\":"
        + "[{\"esr-system-info-id\":\"234567\","
        + "\"system-name\":\"EMS_TEST\","
        + "\"type\":\"sftp\","
        + "\"vendor\":\"ZTE\","
        + "\"version\":\"V1\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"asdf\","
        + "\"system-type\":\"EMS_RESOUCE\","
        + "\"ip-address\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"passive\":true,"
        + "\"remote-path\":\"/home/per\"},"
        + "{\"esr-system-info-id\":\"345678\","
        + "\"system-name\":\"EMS_TEST\","
        + "\"type\":\"sftp\","
        + "\"vendor\":\"ZTE\","
        + "\"version\":\"V1\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"asdf\","
        + "\"system-type\":\"EMS_PERFORMANCE\","
        + "\"ip-address\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"passive\":true,"
        + "\"remote-path\":\"/home/per\"},"
        + "{\"esr-system-info-id\":\"456789\","
        + "\"system-name\":\"EMS_TEST\","
        + "\"vendor\":\"ZTE\","
        + "\"version\":\"V1\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"987654\","
        + "\"system-type\":\"EMS_ALARM\","
        + "\"ip-address\":\"127.0.0.1\","
        + "\"port\":\"5000\"}]}}";    
    assertEquals(expect, esremsStr);
  }
  
  @Test
  public void EsrEms2EmsRegisterInfoTest() {
    EsrEmsDetail esrEms = new EsrEmsDetail();
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
    EmsManagerUtil emsManagerUtil = new EmsManagerUtil();
    String esrEmsStr = "{\"ems-id\":\"123456\","
        + "\"esr-system-info-list\":"
        + "{\"esr-system-info\":"
        + "[{\"esr-system-info-id\":\"234567\","
        + "\"system-name\":\"EMS_TEST\","
        + "\"type\":\"sftp\","
        + "\"vendor\":\"ZTE\","
        + "\"version\":\"V1\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"asdf\","
        + "\"system-type\":\"EMS_RESOUCE\","
        + "\"ip-address\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"passive\":true,"
        + "\"remote-path\":\"/home/per\"},"
        + "{\"esr-system-info-id\":\"345678\","
        + "\"system-name\":\"EMS_TEST\","
        + "\"type\":\"sftp\","
        + "\"vendor\":\"ZTE\","
        + "\"version\":\"V1\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"asdf\","
        + "\"system-type\":\"EMS_PERFORMANCE\","
        + "\"ip-address\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"passive\":true,"
        + "\"remote-path\":\"/home/per\"},"
        + "{\"esr-system-info-id\":\"456789\","
        + "\"system-name\":\"EMS_TEST\","
        + "\"vendor\":\"ZTE\","
        + "\"version\":\"V1\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"987654\","
        + "\"system-type\":\"EMS_ALARM\","
        + "\"ip-address\":\"127.0.0.1\","
        + "\"port\":\"5000\"}]}}";
    esrEms = new Gson().fromJson(esrEmsStr, EsrEmsDetail.class);
    emsRegisterInfo = emsManagerUtil.EsrEms2EmsRegisterInfo(esrEms);
    String registerInfoStr = new ExtsysUtil().objectToString(emsRegisterInfo);
    String expectRegisterInfo = "{\"emsId\":\"123456\","
        + "\"name\":\"EMS_TEST\","
        + "\"vendor\":\"ZTE\","
        + "\"version\":\"V1\","
        + "\"resourceAddr\":{"
        + "\"ftptype\":\"sftp\","
        + "\"ip\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"user\":\"nancy\","
        + "\"password\":\"asdf\","
        + "\"remotepath\":\"/home/per\","
        + "\"passive\":true},"
        + "\"performanceAddr\":{"
        + "\"ftptype\":\"sftp\","
        + "\"ip\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"user\":\"nancy\","
        + "\"password\":\"asdf\","
        + "\"remotepath\":\"/home/per\","
        + "\"passive\":true},"
        + "\"alarmAddr\":{"
        + "\"ip\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"user\":\"nancy\","
        + "\"password\":\"987654\"}}";
    assertEquals(registerInfoStr, expectRegisterInfo);
  }
}
