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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.onap.aai.esr.common.MsbConfig;
import org.onap.aai.esr.entity.rest.AlarmAddr;
import org.onap.aai.esr.entity.rest.EmsRegisterInfo;
import org.onap.aai.esr.entity.rest.FtpAddr;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ExtsysUtil;

public class EmsManagerWrapperTest {
    private static EmsManagerWrapper emsManagerWrapper;
    static {
        MsbConfig.setMsbServerAddr("http://127.0.0.1:80");
    }

    @BeforeClass  
    public static void beforeClass() {  
        ExternalSystemProxy.isTest = true;
    };  
    
    @AfterClass  
    public static void afterClass() {  
        ExternalSystemProxy.isTest = false;
    };
    
    @Before
    public void setUp() throws Exception {
        emsManagerWrapper = EmsManagerWrapper.getInstance();
    }

    @Test
    public void test_registerEms() {
        
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
        Response response = emsManagerWrapper.registerEms(emsRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_queryEmsById() {
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
        Response response = emsManagerWrapper.queryEmsById("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(emsRegisterInfo), extsysUtil.objectToString(response.getEntity()));
        }
    }
    
    @Test
    public void test_queryEmsList() {
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
        Response response = emsManagerWrapper.queryEmsList();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(emsList), extsysUtil.objectToString(response.getEntity()));
        }
    }
    
    @Test
    public void test_updateEms() {
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
        Response response = emsManagerWrapper.updateEms(emsRegisterInfo, "123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_delEms() {
        Response response = emsManagerWrapper.delEms("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }
}
