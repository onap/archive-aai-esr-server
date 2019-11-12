/**
 * Copyright 2019 ZTE Corporation.
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
 * Submit by HePeng 10064135
 */
package org.onap.aai.esr.util;

import com.google.gson.Gson;
import org.junit.Test;
import org.onap.aai.esr.entity.aai.EsrNfvoDetail;
import org.onap.aai.esr.entity.rest.NfvoRegisterInfo;

import static org.junit.Assert.assertEquals;

public class NfvoManagerUtilTest {
    @Test
    public void nfvoRegisterInfo2EsrNfvoTest()
    {
        NfvoManagerUtil nfvoManagerUtil=new NfvoManagerUtil();
        NfvoRegisterInfo nfvoRegisterInfo=new NfvoRegisterInfo();
        ExtsysUtil extsysUtil=new ExtsysUtil();
        nfvoRegisterInfo.setNfvoId("123456");
        nfvoRegisterInfo.setApiroot("/api/v1/test");
        nfvoRegisterInfo.setName("NFVO_TEST");
        nfvoRegisterInfo.setVendor("ZTE");
        nfvoRegisterInfo.setVersion("v1");
        nfvoRegisterInfo.setUrl("127.0.0.1");
        nfvoRegisterInfo.setUserName("root");
        nfvoRegisterInfo.setPassword("root");
        EsrNfvoDetail esrNfvoDetail=nfvoManagerUtil.nfvoRegisterInfo2EsrNfvo(nfvoRegisterInfo);
        esrNfvoDetail.setNfvoId("123456");
        esrNfvoDetail.setResourceVersion("v2");
        esrNfvoDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0).setEsrSystemInfoId("654321");
        String esrnfvoStr = extsysUtil.objectToString(esrNfvoDetail);
        String expect = "{\"nfvo-id\":\"123456\"," + "\"resource-version\":\"v2\"," +"\"api-root\":\"/api/v1/test\","
                + "\"esr-system-info-list\":" + "{\"esr-system-info\":" + "[{\"esr-system-info-id\":\"654321\","
                + "\"system-name\":\"NFVO_TEST\"," + "\"vendor\":\"ZTE\"," + "\"version\":\"v1\"," + "\"service-url\":\"127.0.0.1\","
                + "\"user-name\":\"root\"," + "\"password\":\"root\","+ "\"system-type\":\"NFVO\"}]}}";
        assertEquals(expect,esrnfvoStr);

    }
    @Test
    public void esrNfvo2NfvoRegisterInfoTest()
    {
        EsrNfvoDetail esrNfvoDetail=new EsrNfvoDetail();
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        NfvoManagerUtil nfvoManagerUtil = new NfvoManagerUtil();
        String esrnfvoStr = "{\"nfvo-id\":\"123456\"," + "\"resource-version\":\"v2\"," +"\"api-root\":\"/api/v1/test\","
                + "\"esr-system-info-list\":" + "{\"esr-system-info\":" + "[{\"esr-system-info-id\":\"654321\","
                + "\"system-name\":\"NFVO_TEST\"," + "\"vendor\":\"ZTE\","+ "\"version\":\"v1\"," + "\"service-url\":\"127.0.0.1\","
                + "\"user-name\":\"root\"," + "\"password\":\"root\","+ "\"system-type\":\"NFVO\"}]}}";
       esrNfvoDetail=new Gson().fromJson(esrnfvoStr,EsrNfvoDetail.class);
       nfvoRegisterInfo=nfvoManagerUtil.esrNfvo2NfvoRegisterInfo(esrNfvoDetail);
       String registerInfoStr = new ExtsysUtil().objectToString(nfvoRegisterInfo);
       String expect= "{\"nfvoId\":\"123456\"," + "\"name\":\"NFVO_TEST\"," + "\"apiroot\":\"/api/v1/test\","
               + "\"vendor\":\"ZTE\"," + "\"version\":\"v1\"," +"\"url\":\"127.0.0.1\","+"\"userName\":\"root\","
               + "\"password\":\"root\"}";
       assertEquals(registerInfoStr, expect);
    }
}
