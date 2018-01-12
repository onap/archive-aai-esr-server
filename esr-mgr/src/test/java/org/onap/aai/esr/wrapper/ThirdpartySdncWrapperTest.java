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
import org.onap.aai.esr.entity.rest.ThirdpartySdncRegisterInfo;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ExtsysUtil;

public class ThirdpartySdncWrapperTest {

    private static ThirdpartySdncWrapper thirdpartySdncWrapper;
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
        thirdpartySdncWrapper = ThirdpartySdncWrapper.getInstance();
    }

    @Test
    public void test_registerThirdpartySdnc() {
        ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
        sdncRegisterInfo.setLocation("edge");
        sdncRegisterInfo.setName("SDNC_TEST");
        sdncRegisterInfo.setPassword("123987");
        sdncRegisterInfo.setProductName("thirdparty SDNC");
        sdncRegisterInfo.setProtocol("protocol");
        sdncRegisterInfo.setThirdpartySdncId("123456");
        sdncRegisterInfo.setType("SDNC");
        sdncRegisterInfo.setUrl("http://127.0.0.1:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        Response response = thirdpartySdncWrapper.registerThirdpartySdnc(sdncRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }

    @Test
    public void test_delThirdpartySdnc() {
        Response response = thirdpartySdncWrapper.delThirdpartySdnc("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }

    @Test
    public void test_queryThirdpartySdncById() {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
        sdncRegisterInfo.setLocation("edge");
        sdncRegisterInfo.setName("SDNC_TEST");
        sdncRegisterInfo.setPassword("123987");
        sdncRegisterInfo.setProductName("thirdparty SDNC");
        sdncRegisterInfo.setProtocol("protocol");
        sdncRegisterInfo.setThirdpartySdncId("123456");
        sdncRegisterInfo.setType("SDNC");
        sdncRegisterInfo.setUrl("http://127.0.0.1:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        Response response = thirdpartySdncWrapper.queryThirdpartySdncById("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(sdncRegisterInfo), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_queryThirdpartySdncList() {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        List<ThirdpartySdncRegisterInfo> sdncList = new ArrayList<>();
        ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
        sdncRegisterInfo.setLocation("edge");
        sdncRegisterInfo.setName("SDNC_TEST");
        sdncRegisterInfo.setPassword("123987");
        sdncRegisterInfo.setProductName("thirdparty SDNC");
        sdncRegisterInfo.setProtocol("protocol");
        sdncRegisterInfo.setThirdpartySdncId("123456");
        sdncRegisterInfo.setType("SDNC");
        sdncRegisterInfo.setUrl("http://127.0.0.1:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        sdncList.add(sdncRegisterInfo);
        Response response = thirdpartySdncWrapper.queryThirdpartySdncList();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(sdncList), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_updateThirdpartySdnc() {
        ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
        sdncRegisterInfo.setLocation("edge");
        sdncRegisterInfo.setName("SDNC_TEST");
        sdncRegisterInfo.setPassword("123987");
        sdncRegisterInfo.setProductName("thirdparty SDNC");
        sdncRegisterInfo.setProtocol("protocol");
        sdncRegisterInfo.setThirdpartySdncId("123456");
        sdncRegisterInfo.setType("SDNC");
        sdncRegisterInfo.setUrl("http://127.0.0.1:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        Response response = thirdpartySdncWrapper.updateThirdpartySdnc(sdncRegisterInfo, "123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
}
