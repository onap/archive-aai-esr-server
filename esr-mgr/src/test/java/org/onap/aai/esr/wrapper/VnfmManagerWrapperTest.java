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
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;
import org.onap.aai.esr.entity.rest.VnfmRegisterInfo;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ExtsysUtil;

public class VnfmManagerWrapperTest {

    static {
        MsbConfig.setMsbServerAddr("http://127.0.0.1:80");
    }

    @Test
    public void test_registerVnfm() throws ExtsysException {
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://ip:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://ip:5000/v3");
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).registerVnfm(Mockito.anyString(), (EsrVnfmDetail)Mockito.anyObject());
        VnfmManagerWrapper vnfmManagerWrapper = new VnfmManagerWrapper(mockExternalSystemProxy);
        Response response = vnfmManagerWrapper.registerVnfm(vnfmRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }

    @Test
    public void test_queryVnfmById() throws ExtsysException {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://ip:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://ip:5000/v3");
        vnfmRegisterInfo.setVnfmId("123456");
        String esrVnfmDetailStr = "{\"vnfm-id\":\"123456\",\"vim-id\":\"987654\","
                + "\"certificate-url\":\"http://ip:5000/v3\",\"esr-system-info-list\":{"
                + "\"esr-system-info\":[{\"esr-system-info-id\":\"qwerty\",\"system-name\":\"ONAP VNFM\","
                + "\"type\":\"vnfm\",\"vendor\":\"zte\",\"version\":\"v1\","
                + "\"service-url\":\"http://ip:8000\",\"user-name\":\"onap\","
                + "\"password\":\"987654\",\"system-type\":\"VNFM\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryVnfmDetail(Mockito.anyString())).thenReturn(esrVnfmDetailStr);
        VnfmManagerWrapper vnfmManagerWrapper = new VnfmManagerWrapper(mockExternalSystemProxy);
        Response response = vnfmManagerWrapper.queryVnfmById("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(vnfmRegisterInfo), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_queryVnfmList() throws ExtsysException {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        List<VnfmRegisterInfo> vnfmList = new ArrayList<>();
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://ip:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://ip:5000/v3");
        vnfmRegisterInfo.setVnfmId("123456");
        vnfmList.add(vnfmRegisterInfo);
        String vnfmListStr = "{\"esr-vnfm\": [{\"vnfm-id\": \"123456\",\"vim-id\": \"987654\","
                + "\"certificate-url\": \"http://ip:5000/v3\",\"resource-version\": \"1\"}]}";
        String esrVnfmDetailStr = "{\"vnfm-id\":\"123456\",\"vim-id\":\"987654\","
                + "\"certificate-url\":\"http://ip:5000/v3\",\"esr-system-info-list\":{"
                + "\"esr-system-info\":[{\"esr-system-info-id\":\"qwerty\",\"system-name\":\"ONAP VNFM\","
                + "\"type\":\"vnfm\",\"vendor\":\"zte\",\"version\":\"v1\","
                + "\"service-url\":\"http://ip:8000\",\"user-name\":\"onap\","
                + "\"password\":\"987654\",\"system-type\":\"VNFM\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryVnfmList()).thenReturn(vnfmListStr);
        Mockito.when(mockExternalSystemProxy.queryVnfmDetail(Mockito.anyString())).thenReturn(esrVnfmDetailStr);
        VnfmManagerWrapper vnfmManagerWrapper = new VnfmManagerWrapper(mockExternalSystemProxy);
        Response response = vnfmManagerWrapper.queryVnfmList();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(vnfmList), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_delVnfm() throws ExtsysException {
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).deleteVnfm(Mockito.anyString(), Mockito.anyString());
        VnfmManagerWrapper vnfmManagerWrapper = new VnfmManagerWrapper(mockExternalSystemProxy);
        Response response = vnfmManagerWrapper.delVnfm("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }

    @Test
    public void test_updateVnfm() throws ExtsysException {
        VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
        vnfmRegisterInfo.setVimId("987654");
        vnfmRegisterInfo.setVersion("v1");
        vnfmRegisterInfo.setVendor("zte");
        vnfmRegisterInfo.setUserName("onap");
        vnfmRegisterInfo.setUrl("http://ip:8000");
        vnfmRegisterInfo.setType("vnfm");
        vnfmRegisterInfo.setPassword("987654");
        vnfmRegisterInfo.setName("ONAP VNFM");
        vnfmRegisterInfo.setCertificateUrl("http://ip:5000/v3");
        vnfmRegisterInfo.setVnfmId("123456");
        String esrVnfmDetailStr = "{\"vnfm-id\":\"123456\",\"vim-id\":\"987654\","
                + "\"certificate-url\":\"http://ip:5000/v3\",\"esr-system-info-list\":{"
                + "\"esr-system-info\":[{\"esr-system-info-id\":\"qwerty\",\"system-name\":\"ONAP VNFM\","
                + "\"type\":\"vnfm\",\"vendor\":\"zte\",\"version\":\"v1\","
                + "\"service-url\":\"http://ip:8000\",\"user-name\":\"onap\","
                + "\"password\":\"987654\",\"system-type\":\"VNFM\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).registerVnfm(Mockito.anyString(), (EsrVnfmDetail)Mockito.anyObject());
        Mockito.when(mockExternalSystemProxy.queryVnfmDetail(Mockito.anyString())).thenReturn(esrVnfmDetailStr);
        VnfmManagerWrapper vnfmManagerWrapper = new VnfmManagerWrapper(mockExternalSystemProxy);
        Response response = vnfmManagerWrapper.updateVnfm(vnfmRegisterInfo, "123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
}
