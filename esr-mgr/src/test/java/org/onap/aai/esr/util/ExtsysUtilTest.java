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
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.EsrSystemInfoList;
import org.onap.aai.esr.externalservice.cloud.Tenant;

public class ExtsysUtilTest {
  
  @Test
  public void objectToStringTest() {
    ExtsysUtil extsysUtil = new ExtsysUtil();
    Tenant tenant = new Tenant();
    tenant.setDefaultTenant("admin");
    String tenantStr = "{\"defaultTenant\":\"admin\"}";
    String result = extsysUtil.objectToString(tenant);
    assertEquals(tenantStr, result);
  }
  
                      
  @Test
  public void getEsrSystemInfoListFromAuthInfoTest() {
    EsrSystemInfo esrSystemInfoObj = new EsrSystemInfo();
    ExtsysUtil extsysUtil = new ExtsysUtil();
    EsrSystemInfoList result = new EsrSystemInfoList();
    esrSystemInfoObj.setCloudDomain("cloudDomain");
    esrSystemInfoObj.setDefaultTenant("admin");
    esrSystemInfoObj.setEsrSystemInfoId("123456");
    esrSystemInfoObj.setIpAddress("127.0.0.1");
    esrSystemInfoObj.setPassive(true);
    esrSystemInfoObj.setPassword("qwelk");
    esrSystemInfoObj.setPort("5000");
    esrSystemInfoObj.setProtocol("http");
    esrSystemInfoObj.setRemotePath("/root/test");
    esrSystemInfoObj.setServiceUrl("http://127.0.0.1:8080");
    esrSystemInfoObj.setSslCassert("sslCassert");
    esrSystemInfoObj.setSslInsecure(false);
    esrSystemInfoObj.setSystemName("vnfm");
    esrSystemInfoObj.setSystemStatus("normal");
    esrSystemInfoObj.setSystemType("VNFM");
    esrSystemInfoObj.setType("test");
    esrSystemInfoObj.setUserName("root");
    esrSystemInfoObj.setVendor("zte");
    esrSystemInfoObj.setVersion("v1.0");
    result = extsysUtil.getEsrSystemInfoListFromAuthInfo(esrSystemInfoObj);
    
    String listStr = "{\"esr-system-info\":[{"
                               + "\"esr-system-info-id\":\"123456\","
                               + "\"system-name\":\"vnfm\","
                               + "\"type\":\"test\","
                               + "\"vendor\":\"zte\","
                               + "\"version\":\"v1.0\","
                               + "\"service-url\":\"http://127.0.0.1:8080\","
                               + "\"user-name\":\"root\","
                               + "\"password\":\"qwelk\","
                               + "\"system-type\":\"VNFM\","
                               + "\"protocol\":\"http\","
                               + "\"ssl-cassert\":\"sslCassert\","
                               + "\"ssl-insecure\":false,"
                               + "\"ip-address\":\"127.0.0.1\","
                               + "\"port\":\"5000\","
                               + "\"cloud-domain\":\"cloudDomain\","
                               + "\"default-tenant\":\"admin\","
                               + "\"passive\":true,"
                               + "\"remote-path\":\"/root/test\","
                               + "\"system-status\":\"normal\"}]}";
    assertEquals(extsysUtil.objectToString(result), listStr);
  }
  
  @Test
  public void getEsrSystemInfoListFromAuthInfoListTest() {
    List<EsrSystemInfo> esrSystemInfos = new ArrayList<>();
    EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
    EsrSystemInfo esrSystemInfoObj = new EsrSystemInfo();
    ExtsysUtil extsysUtil = new ExtsysUtil();
    esrSystemInfoObj.setCloudDomain("cloudDomain");
    esrSystemInfoObj.setDefaultTenant("admin");
    esrSystemInfoObj.setEsrSystemInfoId("123456");
    esrSystemInfoObj.setIpAddress("127.0.0.1");
    esrSystemInfoObj.setPassive(true);
    esrSystemInfoObj.setPassword("qwelk");
    esrSystemInfoObj.setPort("5000");
    esrSystemInfoObj.setProtocol("http");
    esrSystemInfoObj.setRemotePath("/root/test");
    esrSystemInfoObj.setServiceUrl("http://127.0.0.1:8080");
    esrSystemInfoObj.setSslCassert("sslCassert");
    esrSystemInfoObj.setSslInsecure(false);
    esrSystemInfoObj.setSystemName("vnfm");
    esrSystemInfoObj.setSystemStatus("normal");
    esrSystemInfoObj.setSystemType("VNFM");
    esrSystemInfoObj.setType("test");
    esrSystemInfoObj.setUserName("root");
    esrSystemInfoObj.setVendor("zte");
    esrSystemInfoObj.setVersion("v1.0");
    esrSystemInfos.add(esrSystemInfoObj);
    esrSystemInfoList.setEsrSystemInfo(esrSystemInfos);
    
    String listStr = "{\"esr-system-info\":[{"
        + "\"esr-system-info-id\":\"123456\","
        + "\"system-name\":\"vnfm\","
        + "\"type\":\"test\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1.0\","
        + "\"service-url\":\"http://127.0.0.1:8080\","
        + "\"user-name\":\"root\","
        + "\"password\":\"qwelk\","
        + "\"system-type\":\"VNFM\","
        + "\"protocol\":\"http\","
        + "\"ssl-cassert\":\"sslCassert\","
        + "\"ssl-insecure\":false,"
        + "\"ip-address\":\"127.0.0.1\","
        + "\"port\":\"5000\","
        + "\"cloud-domain\":\"cloudDomain\","
        + "\"default-tenant\":\"admin\","
        + "\"passive\":true,"
        + "\"remote-path\":\"/root/test\","
        + "\"system-status\":\"normal\"}]}";
    assertEquals(extsysUtil.objectToString(esrSystemInfoList), listStr);
  }
}
