/**
 * Copyright 2019  Verizon. All Rights Reserved.
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
import org.onap.aai.esr.entity.aai.EsrNfvoDetail;
import org.onap.aai.esr.entity.rest.NfvoRegisterInfo;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.ExternalSystemProxy;
import org.onap.aai.esr.util.ExtsysUtil;

public class NfvoManagerWrapperTest {

    static {
        MsbConfig.setMsbServerAddr("http://127.0.0.1:80");
    }

    @Test
    public void test_registerNfvo() throws ExtsysException {
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setApiroot("abcd/");
        nfvoRegisterInfo.setVersion("v1");
        nfvoRegisterInfo.setVendor("zte");
        nfvoRegisterInfo.setUserName("onap");
        nfvoRegisterInfo.setUrl("http://ip:8000");
        nfvoRegisterInfo.setPassword("987654");
        nfvoRegisterInfo.setName("ONAP NFVO");
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).registerNfvo(Mockito.anyString(), (EsrNfvoDetail)Mockito.anyObject());
        NfvoManagerWrapper nfvoManagerWrapper = new NfvoManagerWrapper(mockExternalSystemProxy);
        Response response = nfvoManagerWrapper.registerNfvo(nfvoRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }

    @Test
    public void test_queryNfvoById() throws ExtsysException {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setApiroot("abcd/");
        nfvoRegisterInfo.setVersion("v1");
        nfvoRegisterInfo.setVendor("zte");
        nfvoRegisterInfo.setUserName("onap");
        nfvoRegisterInfo.setUrl("http://ip:8000");
        nfvoRegisterInfo.setPassword("987654");
        nfvoRegisterInfo.setName("ONAP NFVO");
        nfvoRegisterInfo.setNfvoId("123456");
        String esrNfvoDetailStr = "{\"nfvo-id\":\"123456\",\"api-root\":\"abcd/\","
                + "\"esr-system-info-list\":{"
                + "\"esr-system-info\":[{\"esr-system-info-id\":\"qwerty\",\"system-name\":\"ONAP NFVO\","
                + "\"vendor\":\"zte\",\"version\":\"v1\","
                + "\"service-url\":\"http://ip:8000\",\"user-name\":\"onap\","
                + "\"password\":\"987654\",\"system-type\":\"NFVO\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryNfvoDetail(Mockito.anyString())).thenReturn(esrNfvoDetailStr);
        NfvoManagerWrapper nfvoManagerWrapper = new NfvoManagerWrapper(mockExternalSystemProxy);
        Response response = nfvoManagerWrapper.queryNfvoById("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(nfvoRegisterInfo), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_queryNfvoList() throws ExtsysException {
        ExtsysUtil extsysUtil = new ExtsysUtil();
        List<NfvoRegisterInfo> nfvoList = new ArrayList<>();
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setApiroot("abcd/");
        nfvoRegisterInfo.setVersion("v1");
        nfvoRegisterInfo.setVendor("zte");
        nfvoRegisterInfo.setUserName("onap");
        nfvoRegisterInfo.setUrl("http://ip:8000");
        nfvoRegisterInfo.setPassword("987654");
        nfvoRegisterInfo.setName("ONAP NFVO");
        nfvoRegisterInfo.setNfvoId("123456");
        nfvoList.add(nfvoRegisterInfo);
        String nfvoListStr = "{\"esr-nfvo\": [{\"nfvo-id\": \"123456\",\"api-root\": \"abcd/\","
                + "\"resource-version\": \"1\"}]}";
        String esrNfvoDetailStr = "{\"nfvo-id\":\"123456\",\"api-root\":\"abcd/\","
                + "\"esr-system-info-list\":{"
                + "\"esr-system-info\":[{\"esr-system-info-id\":\"qwerty\",\"system-name\":\"ONAP NFVO\","
                + "\"vendor\":\"zte\",\"version\":\"v1\","
                + "\"service-url\":\"http://ip:8000\",\"user-name\":\"onap\","
                + "\"password\":\"987654\",\"system-type\":\"NFVO\"}]}}";
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.when(mockExternalSystemProxy.queryNfvoList()).thenReturn(nfvoListStr);
        Mockito.when(mockExternalSystemProxy.queryNfvoDetail(Mockito.anyString())).thenReturn(esrNfvoDetailStr);
        NfvoManagerWrapper nfvoManagerWrapper = new NfvoManagerWrapper(mockExternalSystemProxy);
        Response response = nfvoManagerWrapper.queryNfvoList();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
            assertEquals(extsysUtil.objectToString(nfvoList), extsysUtil.objectToString(response.getEntity()));
        }
    }

    @Test
    public void test_delNfvo() throws ExtsysException {
        ExternalSystemProxy mockExternalSystemProxy = Mockito.mock(ExternalSystemProxy.class);
        Mockito.doNothing().when(mockExternalSystemProxy).deleteNfvo(Mockito.anyString(), Mockito.anyString());
        NfvoManagerWrapper nfvoManagerWrapper = new NfvoManagerWrapper(mockExternalSystemProxy);
        Response response = nfvoManagerWrapper.delNfvo("123456");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }
}
