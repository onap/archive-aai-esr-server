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
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.rest.ThirdpartySdncRegisterInfo;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ExtsysUtil;

public class ThirdpartySdncWrapperTest {

    static {
        MsbConfig.setMsbServerAddr("http://127.0.0.1:80");
    }

    @Test
    public void test_registerThirdpartySdnc() throws ExtsysException {
        ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
        sdncRegisterInfo.setLocation("edge");
        sdncRegisterInfo.setName("SDNC_TEST");
        sdncRegisterInfo.setPassword("123987");
        sdncRegisterInfo.setProductName("thirdparty SDNC");
        sdncRegisterInfo.setProtocol("protocol");
        sdncRegisterInfo.setThirdpartySdncId("123456");
        sdncRegisterInfo.setType("SDNC");
        sdncRegisterInfo.setUrl("http://ip:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).registerSdnc(Mockito.anyString(), (EsrThirdpartySdncDetail)Mockito.anyObject());
        ThirdpartySdncWrapper thirdpartySdncWrapper = new ThirdpartySdncWrapper(mockExternalSystemProxy);
        Response response = thirdpartySdncWrapper.registerThirdpartySdnc(sdncRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }

    @Test
    public void test_delThirdpartySdnc() throws ExtsysException {
        String sdncDetail = "{\"thirdparty-sdnc-id\":\"123456\",\"location\":\"edge\","
                + "\"product-name\":\"thirdparty SDNC\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"987654\",\"system-name\":\"SDNC_TEST\",\"type\":\"SDNC\","
                + "\"vendor\":\"zte\",\"version\":\"v1\",\"service-url\":\"http://ip:8000\","
                + "\"user-name\":\"nancy\",\"password\":\"123987\",\"system-type\":\"thirdparty_SDNC\","
                + "\"protocol\":\"protocol\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).deleteThirdpartySdnc(Mockito.anyString(), Mockito.anyString());
        Mockito.when(mockExternalSystemProxy.queryThirdpartySdncDetail(Mockito.anyString())).thenReturn(sdncDetail);
        ThirdpartySdncWrapper thirdpartySdncWrapper = new ThirdpartySdncWrapper(mockExternalSystemProxy);
        Response response = thirdpartySdncWrapper.delThirdpartySdnc("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }

    @Test
    public void test_queryThirdpartySdncById() throws ExtsysException {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
        sdncRegisterInfo.setLocation("edge");
        sdncRegisterInfo.setName("SDNC_TEST");
        sdncRegisterInfo.setPassword("123987");
        sdncRegisterInfo.setProductName("thirdparty SDNC");
        sdncRegisterInfo.setProtocol("protocol");
        sdncRegisterInfo.setThirdpartySdncId("123456");
        sdncRegisterInfo.setType("SDNC");
        sdncRegisterInfo.setUrl("http://ip:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        String sdncDetail = "{\"thirdparty-sdnc-id\":\"123456\",\"location\":\"edge\","
                + "\"product-name\":\"thirdparty SDNC\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"987654\",\"system-name\":\"SDNC_TEST\",\"type\":\"SDNC\","
                + "\"vendor\":\"zte\",\"version\":\"v1\",\"service-url\":\"http://ip:8000\","
                + "\"user-name\":\"nancy\",\"password\":\"123987\",\"system-type\":\"thirdparty_SDNC\","
                + "\"protocol\":\"protocol\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryThirdpartySdncDetail(Mockito.anyString())).thenReturn(sdncDetail);
        ThirdpartySdncWrapper thirdpartySdncWrapper = new ThirdpartySdncWrapper(mockExternalSystemProxy);
        Response response = thirdpartySdncWrapper.queryThirdpartySdncById("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(sdncRegisterInfo), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_queryThirdpartySdncList() throws ExtsysException {
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
        sdncRegisterInfo.setUrl("http://ip:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        sdncList.add(sdncRegisterInfo);
        String sdncDetail = "{\"thirdparty-sdnc-id\":\"123456\",\"location\":\"edge\","
                + "\"product-name\":\"thirdparty SDNC\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"987654\",\"system-name\":\"SDNC_TEST\",\"type\":\"SDNC\","
                + "\"vendor\":\"zte\",\"version\":\"v1\",\"service-url\":\"http://ip:8000\","
                + "\"user-name\":\"nancy\",\"password\":\"123987\",\"system-type\":\"thirdparty_SDNC\","
                + "\"protocol\":\"protocol\"}]}}";
        String sdncListStr =
                "{\"esr-thirdparty-sdnc\": [{\"thirdparty-sdnc-id\": \"123456\",\"location\": \"edge\","
                        + "\"product-name\": \"thirdparty SDNC\",\"resource-version\": \"1\"}]}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryThirdpartySdncDetail(Mockito.anyString())).thenReturn(sdncDetail);
        Mockito.when(mockExternalSystemProxy.querySdncList()).thenReturn(sdncListStr);
        ThirdpartySdncWrapper thirdpartySdncWrapper = new ThirdpartySdncWrapper(mockExternalSystemProxy);
        Response response = thirdpartySdncWrapper.queryThirdpartySdncList();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(sdncList), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_updateThirdpartySdnc() throws ExtsysException {
        ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
        sdncRegisterInfo.setLocation("edge");
        sdncRegisterInfo.setName("SDNC_TEST");
        sdncRegisterInfo.setPassword("123987");
        sdncRegisterInfo.setProductName("thirdparty SDNC");
        sdncRegisterInfo.setProtocol("protocol");
        sdncRegisterInfo.setThirdpartySdncId("123456");
        sdncRegisterInfo.setType("SDNC");
        sdncRegisterInfo.setUrl("http://ip:8000");
        sdncRegisterInfo.setUserName("nancy");
        sdncRegisterInfo.setVendor("zte");
        sdncRegisterInfo.setVersion("v1");
        String sdncDetail = "{\"thirdparty-sdnc-id\":\"123456\",\"location\":\"edge\","
                + "\"product-name\":\"thirdparty SDNC\",\"esr-system-info-list\":{\"esr-system-info\":"
                + "[{\"esr-system-info-id\":\"987654\",\"system-name\":\"SDNC_TEST\",\"type\":\"SDNC\","
                + "\"vendor\":\"zte\",\"version\":\"v1\",\"service-url\":\"http://ip:8000\","
                + "\"user-name\":\"nancy\",\"password\":\"123987\",\"system-type\":\"thirdparty_SDNC\","
                + "\"protocol\":\"protocol\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryThirdpartySdncDetail(Mockito.anyString())).thenReturn(sdncDetail);
        Mockito.doNothing().when(mockExternalSystemProxy).registerSdnc(Mockito.anyString(), (EsrThirdpartySdncDetail)Mockito.anyObject());
        ThirdpartySdncWrapper thirdpartySdncWrapper = new ThirdpartySdncWrapper(mockExternalSystemProxy);
        Response response = thirdpartySdncWrapper.updateThirdpartySdnc(sdncRegisterInfo, "123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
}
