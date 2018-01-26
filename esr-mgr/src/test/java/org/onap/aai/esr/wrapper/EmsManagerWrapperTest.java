/**
 * Copyright 2018 ZTE Corporation.
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

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.onap.aai.esr.common.MsbConfig;
import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.rest.AlarmAddr;
import org.onap.aai.esr.entity.rest.EmsRegisterInfo;
import org.onap.aai.esr.entity.rest.FtpAddr;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ExtsysUtil;

public class EmsManagerWrapperTest {

    static {
        MsbConfig.setMsbServerAddr("http://127.0.0.1:80");
    }

    @Test
    public void test_registerEms() throws ExtsysException {
        
        EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
        AlarmAddr alarmAddr = new AlarmAddr();
        FtpAddr resourceAddr = new FtpAddr();
        FtpAddr performanceAddr = new FtpAddr();
        alarmAddr.setIp("ip");
        alarmAddr.setPassword("987654");
        alarmAddr.setPort("5000");
        alarmAddr.setUser("nancy");
        resourceAddr.setFtptype("sftp");
        resourceAddr.setIp("ip");
        resourceAddr.setPassive(true);
        resourceAddr.setPassword("asdf");
        resourceAddr.setPort("5000");
        resourceAddr.setRemotepath("/home/per");
        resourceAddr.setUser("nancy");
        performanceAddr.setFtptype("sftp");
        performanceAddr.setIp("ip");
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
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).registerEms(Mockito.anyString(), (EsrEmsDetail)Mockito.anyObject());
        EmsManagerWrapper emsManagerWrapper = new EmsManagerWrapper(mockExternalSystemProxy);
        Response response = emsManagerWrapper.registerEms(emsRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_queryEmsById() throws ExtsysException {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
        AlarmAddr alarmAddr = new AlarmAddr();
        FtpAddr resourceAddr = new FtpAddr();
        FtpAddr performanceAddr = new FtpAddr();
        alarmAddr.setIp("ip");
        alarmAddr.setPassword("987654");
        alarmAddr.setPort("5000");
        alarmAddr.setUser("nancy");
        resourceAddr.setFtptype("sftp");
        resourceAddr.setIp("ip");
        resourceAddr.setPassive(true);
        resourceAddr.setPassword("asdf");
        resourceAddr.setPort("5000");
        resourceAddr.setRemotepath("/home/per");
        resourceAddr.setUser("nancy");
        performanceAddr.setFtptype("sftp");
        performanceAddr.setIp("ip");
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
        emsRegisterInfo.setEmsId("123456");
        String emsDetailStr = "{\"ems-id\":\"123456\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"234567\",\"system-name\":\"EMS_TEST\",\"type\":\"sftp\","
                + "\"vendor\":\"ZTE\",\"version\":\"V1\",\"user-name\":\"nancy\",\"password\":\"asdf\","
                + "\"system-type\":\"EMS_RESOUCE\",\"ip-address\":\"ip\",\"port\":\"5000\","
                + "\"passive\":true,\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"345678\","
                + "\"system-name\":\"EMS_TEST\",\"type\":\"sftp\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"asdf\",\"system-type\":\"EMS_PERFORMANCE\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\",\"passive\":true,"
                + "\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"456789\","
                + "\"system-name\":\"EMS_TEST\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"987654\",\"system-type\":\"EMS_ALARM\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryEmsDetail(Mockito.anyString())).thenReturn(emsDetailStr);
        EmsManagerWrapper emsManagerWrapper = new EmsManagerWrapper(mockExternalSystemProxy);
        
        Response response = emsManagerWrapper.queryEmsById("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(emsRegisterInfo), extsysUtil.objectToString(response.getEntity()));
        }
    }
    
    @Test
    public void test_queryEmsList() throws ExtsysException {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        List<EmsRegisterInfo> emsList = new ArrayList<>();
        EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
        AlarmAddr alarmAddr = new AlarmAddr();
        FtpAddr resourceAddr = new FtpAddr();
        FtpAddr performanceAddr = new FtpAddr();
        alarmAddr.setIp("ip");
        alarmAddr.setPassword("987654");
        alarmAddr.setPort("5000");
        alarmAddr.setUser("nancy");
        resourceAddr.setFtptype("sftp");
        resourceAddr.setIp("ip");
        resourceAddr.setPassive(true);
        resourceAddr.setPassword("asdf");
        resourceAddr.setPort("5000");
        resourceAddr.setRemotepath("/home/per");
        resourceAddr.setUser("nancy");
        performanceAddr.setFtptype("sftp");
        performanceAddr.setIp("ip");
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
        emsRegisterInfo.setEmsId("123456");
        emsList.add(emsRegisterInfo);
        String emsDetailStr = "{\"ems-id\":\"123456\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"234567\",\"system-name\":\"EMS_TEST\",\"type\":\"sftp\","
                + "\"vendor\":\"ZTE\",\"version\":\"V1\",\"user-name\":\"nancy\",\"password\":\"asdf\","
                + "\"system-type\":\"EMS_RESOUCE\",\"ip-address\":\"ip\",\"port\":\"5000\","
                + "\"passive\":true,\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"345678\","
                + "\"system-name\":\"EMS_TEST\",\"type\":\"sftp\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"asdf\",\"system-type\":\"EMS_PERFORMANCE\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\",\"passive\":true,"
                + "\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"456789\","
                + "\"system-name\":\"EMS_TEST\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"987654\",\"system-type\":\"EMS_ALARM\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\"}]}}";
        String emsListStr = "{\"esr-ems\": [ {\"ems-id\": \"123456\",\"resource-version\": \"1\"}]}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryEmsDetail(Mockito.anyString())).thenReturn(emsDetailStr);
        Mockito.when(mockExternalSystemProxy.queryEmsList()).thenReturn(emsListStr);
        EmsManagerWrapper emsManagerWrapper = new EmsManagerWrapper(mockExternalSystemProxy);
        Response response = emsManagerWrapper.queryEmsList();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(emsList), extsysUtil.objectToString(response.getEntity()));
        }
    }
    
    @Test
    public void test_updateEms() throws ExtsysException {
        EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
        AlarmAddr alarmAddr = new AlarmAddr();
        FtpAddr resourceAddr = new FtpAddr();
        FtpAddr performanceAddr = new FtpAddr();
        alarmAddr.setIp("ip");
        alarmAddr.setPassword("987654");
        alarmAddr.setPort("5000");
        alarmAddr.setUser("nancy");
        resourceAddr.setFtptype("sftp");
        resourceAddr.setIp("ip");
        resourceAddr.setPassive(true);
        resourceAddr.setPassword("asdf");
        resourceAddr.setPort("5000");
        resourceAddr.setRemotepath("/home/per");
        resourceAddr.setUser("nancy");
        performanceAddr.setFtptype("sftp");
        performanceAddr.setIp("ip");
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
        emsRegisterInfo.setEmsId("123456");
        String emsDetailStr = "{\"ems-id\":\"123456\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"234567\",\"system-name\":\"EMS_TEST\",\"type\":\"sftp\","
                + "\"vendor\":\"ZTE\",\"version\":\"V1\",\"user-name\":\"nancy\",\"password\":\"asdf\","
                + "\"system-type\":\"EMS_RESOUCE\",\"ip-address\":\"ip\",\"port\":\"5000\","
                + "\"passive\":true,\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"345678\","
                + "\"system-name\":\"EMS_TEST\",\"type\":\"sftp\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"asdf\",\"system-type\":\"EMS_PERFORMANCE\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\",\"passive\":true,"
                + "\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"456789\","
                + "\"system-name\":\"EMS_TEST\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"987654\",\"system-type\":\"EMS_ALARM\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryEmsDetail(Mockito.anyString())).thenReturn(emsDetailStr);
        Mockito.doNothing().when(mockExternalSystemProxy).registerEms(Mockito.anyString(), (EsrEmsDetail)Mockito.anyObject());
        EmsManagerWrapper emsManagerWrapper = new EmsManagerWrapper(mockExternalSystemProxy);
        Response response = emsManagerWrapper.updateEms(emsRegisterInfo, "123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_delEms() throws ExtsysException {
        String emsDetailStr = "{\"ems-id\":\"123456\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"234567\",\"system-name\":\"EMS_TEST\",\"type\":\"sftp\","
                + "\"vendor\":\"ZTE\",\"version\":\"V1\",\"user-name\":\"nancy\",\"password\":\"asdf\","
                + "\"system-type\":\"EMS_RESOUCE\",\"ip-address\":\"ip\",\"port\":\"5000\","
                + "\"passive\":true,\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"345678\","
                + "\"system-name\":\"EMS_TEST\",\"type\":\"sftp\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"asdf\",\"system-type\":\"EMS_PERFORMANCE\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\",\"passive\":true,"
                + "\"remote-path\":\"/home/per\"},{\"esr-system-info-id\":\"456789\","
                + "\"system-name\":\"EMS_TEST\",\"vendor\":\"ZTE\",\"version\":\"V1\","
                + "\"user-name\":\"nancy\",\"password\":\"987654\",\"system-type\":\"EMS_ALARM\","
                + "\"ip-address\":\"ip\",\"port\":\"5000\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryEmsDetail(Mockito.anyString())).thenReturn(emsDetailStr);
        Mockito.doNothing().when(mockExternalSystemProxy).deleteEms(Mockito.anyString(), Mockito.anyString());
        EmsManagerWrapper emsManagerWrapper = new EmsManagerWrapper(mockExternalSystemProxy);
        Response response = emsManagerWrapper.delEms("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }
}
