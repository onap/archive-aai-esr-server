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
import org.onap.aai.esr.entity.rest.VnfmRegisterInfo;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ExtsysUtil;

public class VnfmManagerWrapperTest {

    private static VnfmManagerWrapper vnfmManagerWrapper;
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
        vnfmManagerWrapper = VnfmManagerWrapper.getInstance();
    }

    @Test
    public void test_registerVnfm() {
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://10.11.22.33:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://11.22.33.44:5000/v3");
        Response response = vnfmManagerWrapper.registerVnfm(vnfmRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }

    @Test
    public void test_queryVnfmById() {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://10.11.22.33:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://11.22.33.44:5000/v3");
        vnfmRegisterInfo.setVnfmId("123456");
        Response response = vnfmManagerWrapper.queryVnfmById("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(vnfmRegisterInfo), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_queryVnfmList() {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        List<VnfmRegisterInfo> vnfmList = new ArrayList<>();
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://10.11.22.33:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://11.22.33.44:5000/v3");
        vnfmRegisterInfo.setVnfmId("123456");
        vnfmList.add(vnfmRegisterInfo);
        Response response = vnfmManagerWrapper.queryVnfmList();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(vnfmList), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_delVnfm() {
        Response response = vnfmManagerWrapper.delVnfm("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }

    @Test
    public void test_updateVnfm() {
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://10.11.22.33:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://11.22.33.44:5000/v3");
        vnfmRegisterInfo.setVnfmId("123456");
        Response response = vnfmManagerWrapper.updateVnfm(vnfmRegisterInfo, "123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
}
