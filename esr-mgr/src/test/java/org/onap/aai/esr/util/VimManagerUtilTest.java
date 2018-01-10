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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.entity.rest.VimAuthInfo;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;

import com.google.gson.Gson;

public class VimManagerUtilTest {
  
  @Test
  public void vimRegisterInfo2CloudRegionTest() {
    VimManagerUtil vimManagerUtil = new VimManagerUtil();
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
    List<VimAuthInfo> vimAuthInfos = new ArrayList<>();
    VimAuthInfo vimAuthInfo = new VimAuthInfo();
    vimAuthInfo.setAuthUrl("http://10.11.22.33:5000/v3");
    vimAuthInfo.setCloudDomain("default");
    vimAuthInfo.setPassword("123456");
    vimAuthInfo.setSslCacert("test");
    vimAuthInfo.setSslInsecure(true);
    vimAuthInfo.setUserName("onap");
    vimAuthInfo.setDefaultTenant("admin");
    vimRegisterInfo.setCloudExtraInfo("{}");
    vimRegisterInfo.setCloudOwner("zte");
    vimRegisterInfo.setCloudRegionId("RegionOne");
    vimRegisterInfo.setCloudRegionVersion("mitaca");
    vimRegisterInfo.setCloudType("openstack");
    vimRegisterInfo.setCloudZone("default");
    vimRegisterInfo.setComplexName("complex");
    vimRegisterInfo.setOwnerDefinedType("test");
    vimRegisterInfo.setStatus("normal");
    vimAuthInfos.add(vimAuthInfo);
    vimRegisterInfo.setVimAuthInfos(vimAuthInfos);
    cloudRegionDetail = vimManagerUtil.vimRegisterInfo2CloudRegion(vimRegisterInfo);
    cloudRegionDetail.getEsrSystemInfoList().getEsrSystemInfo().get(0).setEsrSystemInfoId("123456");
    String cloudRegionDetailStr = new ExtsysUtil().objectToString(cloudRegionDetail);
    String expectResult = "{\"cloud-owner\":\"zte\","
        + "\"cloud-region-id\":\"RegionOne\","
        + "\"cloud-type\":\"openstack\","
        + "\"cloud-region-version\":\"mitaca\","
        + "\"owner-defined-type\":\"test\","
        + "\"cloud-zone\":\"default\","
        + "\"complex-name\":\"complex\","
        + "\"cloud-extra-info\":\"{}\","
        + "\"esr-system-info-list\":{"
        + "\"esr-system-info\":[{"
        + "\"esr-system-info-id\":\"123456\","
        + "\"service-url\":\"http://10.11.22.33:5000/v3\","
        + "\"user-name\":\"onap\","
        + "\"password\":\"123456\","
        + "\"system-type\":\"VIM\","
        + "\"ssl-cassert\":\"test\","
        + "\"ssl-insecure\":true,"
        + "\"cloud-domain\":\"default\","
        + "\"default-tenant\":\"admin\","
        + "\"system-status\":\"normal\"}]}}";
    assertEquals(expectResult, cloudRegionDetailStr);
  }
  
  @Test
  public void cloudRegion2VimRegisterInfoTest() {
    VimManagerUtil vimManagerUtil = new VimManagerUtil();
    CloudRegionDetail cloudRegionDetail = new CloudRegionDetail();
    VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
    String cloudRegionDetailStr = "{\"cloud-owner\":\"zte\","
        + "\"cloud-region-id\":\"RegionOne\","
        + "\"cloud-type\":\"openstack\","
        + "\"cloud-region-version\":\"mitaca\","
        + "\"owner-defined-type\":\"test\","
        + "\"cloud-zone\":\"default\","
        + "\"complex-name\":\"complex\","
        + "\"cloud-extra-info\":\"{}\","
        + "\"esr-system-info-list\":{"
        + "\"esr-system-info\":[{"
        + "\"esr-system-info-id\":\"123456\","
        + "\"service-url\":\"http://10.11.22.33:5000/v3\","
        + "\"user-name\":\"onap\","
        + "\"password\":\"123456\","
        + "\"default-tenant\":\"admin\","
        + "\"system-type\":\"VIM\","
        + "\"ssl-cassert\":\"test\","
        + "\"ssl-insecure\":true,"
        + "\"cloud-domain\":\"default\","
        + "\"system-status\":\"normal\"}]}}";
    cloudRegionDetail = new Gson().fromJson(cloudRegionDetailStr, CloudRegionDetail.class);
    vimRegisterInfo = vimManagerUtil.cloudRegion2VimRegisterInfo(cloudRegionDetail);
    String vimRegisterInfoStr = new ExtsysUtil().objectToString(vimRegisterInfo);
    String expectResult = "{\"cloudOwner\":\"zte\","
        + "\"cloudRegionId\":\"RegionOne\","
        + "\"cloudType\":\"openstack\","
        + "\"cloudRegionVersion\":\"mitaca\","
        + "\"ownerDefinedType\":\"test\","
        + "\"cloudZone\":\"default\","
        + "\"complexName\":\"complex\","
        + "\"cloudExtraInfo\":\"{}\","
        + "\"status\":\"normal\","
        + "\"vimAuthInfos\":[{"
        + "\"cloudDomain\":\"default\","
        + "\"userName\":\"onap\","
        + "\"password\":\"123456\","
        + "\"authUrl\":\"http://10.11.22.33:5000/v3\","
        + "\"defaultTenant\":\"admin\","
        + "\"sslCacert\":\"test\","
        + "\"sslInsecure\":true}]}";
    assertEquals(expectResult, vimRegisterInfoStr);
  }
}
