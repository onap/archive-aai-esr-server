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
package org.onap.aai.esr.entity.aai;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EsrSystemInfoTest {

    @Test
    public void getterAndSetter4esrSystemInfoId() {
        final String esrSystemInfoId = "esrSystemInfoId-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setEsrSystemInfoId(esrSystemInfoId);
        assertEquals(esrSystemInfo.getEsrSystemInfoId(), esrSystemInfoId);
    }

    @Test
    public void getterAndSetter4systemName() {
        final String systemName = "systemName-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setSystemName(systemName);
        assertEquals(esrSystemInfo.getSystemName(), systemName);
    }

    @Test
    public void getterAndSetter4type() {
        final String type = "type-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setType(type);
        assertEquals(esrSystemInfo.getType(), type);
    }

    @Test
    public void getterAndSetter4vendor() {
        final String vendor = "vendor-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setVendor(vendor);
        assertEquals(esrSystemInfo.getVendor(), vendor);
    }

    @Test
    public void getterAndSetter4version() {
        final String version = "version-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setVersion(version);
        assertEquals(esrSystemInfo.getVersion(), version);
    }

    @Test
    public void getterAndSetter4serviceUrl() {
        final String serviceUrl = "serviceUrl-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setServiceUrl(serviceUrl);
        assertEquals(esrSystemInfo.getServiceUrl(), serviceUrl);
    }

    @Test
    public void getterAndSetter4userName() {
        final String userName = "userName-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setUserName(userName);
        assertEquals(esrSystemInfo.getUserName(), userName);
    }

    @Test
    public void getterAndSetter4password() {
        final String password = "password-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setPassword(password);
        assertEquals(esrSystemInfo.getPassword(), password);
    }

    @Test
    public void getterAndSetter4systemType() {
        final String systemType = "systemType-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setSystemType(systemType);
        assertEquals(esrSystemInfo.getSystemType(), systemType);
    }

    @Test
    public void getterAndSetter4protocol() {
        final String protocol = "protocol-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setProtocol(protocol);
        assertEquals(esrSystemInfo.getProtocol(), protocol);
    }

    @Test
    public void getterAndSetter4sslCassert() {
        final String sslCassert = "sslCassert-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setSslCassert(sslCassert);
        assertEquals(esrSystemInfo.getSslCassert(), sslCassert);
    }

    @Test
    public void getterAndSetter4sslInsecure() {
        final Boolean sslInsecure = true;
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setSslInsecure(sslInsecure);
        assertEquals(esrSystemInfo.getSslInsecure(), sslInsecure);
    }

    @Test
    public void getterAndSetter4ipAddress() {
        final String ipAddress = "ipAddress-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setIpAddress(ipAddress);
        assertEquals(esrSystemInfo.getIpAddress(), ipAddress);
    }

    @Test
    public void getterAndSetter4port() {
        final String port = "port-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setIpAddress(port);
        assertEquals(esrSystemInfo.getIpAddress(), port);
    }

    @Test
    public void getterAndSetter4cloudDomain() {
        final String cloudDomain = "cloudDomain-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setCloudDomain(cloudDomain);
        assertEquals(esrSystemInfo.getCloudDomain(), cloudDomain);
    }

    @Test
    public void getterAndSetter4defaultTenant() {
        final String defaultTenant = "defaultTenant-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setDefaultTenant(defaultTenant);
        assertEquals(esrSystemInfo.getDefaultTenant(), defaultTenant);
    }

    @Test
    public void getterAndSetter4passive() {
        final Boolean passive = true;
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setPassive(passive);
        assertEquals(esrSystemInfo.getPassive(), passive);
    }

    @Test
    public void getterAndSetter4remotePath() {
        final String remotePath = "remotePath-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setRemotePath(remotePath);
        assertEquals(esrSystemInfo.getRemotePath(), remotePath);
    }

    @Test
    public void getterAndSetter4systemStatus() {
        final String systemStatus = "defaultTenant-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setSystemStatus(systemStatus);
        assertEquals(esrSystemInfo.getSystemStatus(), systemStatus);
    }

    @Test
    public void getterAndSetter4resourceVersion() {
        final String resourceVersion = "resourceVersion-test";
        EsrSystemInfo esrSystemInfo = new EsrSystemInfo();
        esrSystemInfo.setResouceVersion(resourceVersion);
        assertEquals(esrSystemInfo.getResouceVersion(), resourceVersion);
    }
}
