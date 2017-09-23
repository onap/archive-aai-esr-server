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
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;
import org.onap.aai.esr.entity.rest.VnfmRegisterInfo;

import com.google.gson.Gson;

public class VnfmManagerUtilTest {

  @Test
  public void vnfmRegisterInfo2EsrVnfmTest() {
    VnfmManagerUtil vnfmManagerUtil = new VnfmManagerUtil();
    VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
    EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
    vnfmRegisterInfo.setVimId("987654");
    vnfmRegisterInfo.setVersion("v1");
    vnfmRegisterInfo.setVendor("zte");
    vnfmRegisterInfo.setUserName("onap");
    vnfmRegisterInfo.setUrl("http://10.11.22.33:8000");
    vnfmRegisterInfo.setType("vnfm");
    vnfmRegisterInfo.setPassword("987654");
    vnfmRegisterInfo.setName("ONAP VNFM");
    vnfmRegisterInfo.setCertificateUrl("http://11.22.33.44:5000/v3");
    esrVnfmDetail = vnfmManagerUtil.vnfmRegisterInfo2EsrVnfm(vnfmRegisterInfo);
    esrVnfmDetail.setVnfmId("123456");
    esrVnfmDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0).setEsrSystemInfoId("qwerty");
    String esrVnfmDetailStr = new ExtsysUtil().objectToString(esrVnfmDetail);
    String expectResult = "{\"vnfm-id\":\"123456\","
        + "\"vim-id\":\"987654\","
        + "\"certificate-url\":\"http://11.22.33.44:5000/v3\","
        + "\"esr-system-info-list\":{"
        + "\"esr-system-info\":[{"
        + "\"esr-system-info-id\":\"qwerty\","
        + "\"system-name\":\"ONAP VNFM\","
        + "\"type\":\"vnfm\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1\","
        + "\"service-url\":\"http://10.11.22.33:8000\","
        + "\"user-name\":\"onap\","
        + "\"password\":\"987654\","
        + "\"system-type\":\"VNFM\"}]}}";
    assertEquals(expectResult, esrVnfmDetailStr);
  }
  
  @Test
  public void esrVnfm2VnfmRegisterInfoTest() {
    VnfmManagerUtil vnfmManagerUtil = new VnfmManagerUtil();
    VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
    EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
    String esrVnfmDetailStr = "{\"vnfm-id\":\"123456\","
        + "\"vim-id\":\"987654\","
        + "\"certificate-url\":\"http://11.22.33.44:5000/v3\","
        + "\"esr-system-info-list\":{"
        + "\"esr-system-info\":[{"
        + "\"esr-system-info-id\":\"qwerty\","
        + "\"system-name\":\"ONAP VNFM\","
        + "\"type\":\"vnfm\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1\","
        + "\"service-url\":\"http://10.11.22.33:8000\","
        + "\"user-name\":\"onap\","
        + "\"password\":\"987654\","
        + "\"system-type\":\"VNFM\"}]}}";
    esrVnfmDetail = new Gson().fromJson(esrVnfmDetailStr, EsrVnfmDetail.class);
    vnfmRegisterInfo = vnfmManagerUtil.esrVnfm2VnfmRegisterInfo(esrVnfmDetail);
    String vnfmRegisterInfoStr = new ExtsysUtil().objectToString(vnfmRegisterInfo);
    String expectResult = "{\"vnfmId\":\"123456\","
        + "\"name\":\"ONAP VNFM\","
        + "\"type\":\"vnfm\","
        + "\"vimId\":\"987654\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1\","
        + "\"certificateUrl\":\"http://11.22.33.44:5000/v3\","
        + "\"url\":\"http://10.11.22.33:8000\","
        + "\"userName\":\"onap\","
        + "\"password\":\"987654\"}";
    assertEquals(expectResult, vnfmRegisterInfoStr);
  }
}
