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
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.rest.ThirdpartySdncRegisterInfo;

import com.google.gson.Gson;

public class ThirdpartySdncManagerUtilTest {
  
  @Test
  public void sdncRegisterInfo2EsrSdncTest() {
    ThirdpartySdncManagerUtil thirdpartySdncManagerUtil = new ThirdpartySdncManagerUtil();
    ThirdpartySdncRegisterInfo sdncRegisterInfo = new ThirdpartySdncRegisterInfo();
    EsrThirdpartySdncDetail esrSdncDetail = new EsrThirdpartySdncDetail();
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
    esrSdncDetail = thirdpartySdncManagerUtil.sdncRegisterInfo2EsrSdnc(sdncRegisterInfo);
    esrSdncDetail.setThirdpartySdncId("123456");
    esrSdncDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0).setEsrSystemInfoId("987654");
    String esrSdncDetailStr = new ExtsysUtil().objectToString(esrSdncDetail);
    String expectResult = "{\"thirdparty-sdnc-id\":\"123456\","
        + "\"location\":\"edge\","
        + "\"product-name\":\"thirdparty SDNC\","
        + "\"esr-system-info-list\":{"
        + "\"esr-system-info\":"
        + "[{\"esr-system-info-id\":\"987654\","
        + "\"system-name\":\"SDNC_TEST\","
        + "\"type\":\"SDNC\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1\","
        + "\"service-url\":\"http://127.0.0.1:8000\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"123987\","
        + "\"system-type\":\"thirdparty_SDNC\","
        + "\"protocol\":\"protocol\"}]}}";
    assertEquals(expectResult, esrSdncDetailStr);
  }
  
  @Test
  public void esrSdnc2SdncRegisterInfoTest() {
    EsrThirdpartySdncDetail esrSdnc = new EsrThirdpartySdncDetail();
    String esrSdncStr = "{\"thirdparty-sdnc-id\":\"123456\","
        + "\"location\":\"edge\","
        + "\"product-name\":\"thirdparty SDNC\","
        + "\"esr-system-info-list\":{"
        + "\"esr-system-info\":"
        + "[{\"esr-system-info-id\":\"987654\","
        + "\"system-name\":\"SDNC_TEST\","
        + "\"type\":\"SDNC\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1\","
        + "\"service-url\":\"http://127.0.0.1:8000\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"123987\","
        + "\"system-type\":\"thirdparty_SDNC\","
        + "\"protocol\":\"protocol\"}]}}";
    esrSdnc = new Gson().fromJson(esrSdncStr, EsrThirdpartySdncDetail.class);
    ThirdpartySdncRegisterInfo registerInfo = new ThirdpartySdncManagerUtil().esrSdnc2SdncRegisterInfo(esrSdnc);
    String registerInfoStr = new ExtsysUtil().objectToString(registerInfo);
    String expectResult = "{\"thirdpartySdncId\":\"123456\","
        + "\"name\":\"SDNC_TEST\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1\","
        + "\"type\":\"SDNC\","
        + "\"location\":\"edge\","
        + "\"url\":\"http://127.0.0.1:8000\","
        + "\"userName\":\"nancy\","
        + "\"password\":\"123987\","
        + "\"productName\":\"thirdparty SDNC\","
        + "\"protocol\":\"protocol\"}";
    assertEquals(expectResult, registerInfoStr);
  }
}
