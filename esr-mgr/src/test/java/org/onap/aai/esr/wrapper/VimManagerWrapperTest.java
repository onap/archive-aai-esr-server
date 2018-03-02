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

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.onap.aai.esr.common.MsbConfig;
import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.entity.aai.Relationship;
import org.onap.aai.esr.entity.rest.VimAuthInfo;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.CloudRegionProxy;
import org.onap.aai.esr.externalservice.cloud.Tenant;
import org.onap.aai.esr.externalservice.cloud.VimManagerProxy;

public class VimManagerWrapperTest {

    static {
        MsbConfig.setMsbServerAddr("http://127.0.0.1:80");
    }

    @Test
    public void test_registerVim() throws ExtsysException {
        VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
        List<VimAuthInfo> vimAuthInfos = new ArrayList<>();
        vimRegisterInfo.setCloudExtraInfo("{}");
        vimRegisterInfo.setCloudOwner("zte");
        vimRegisterInfo.setCloudRegionId("RegionOne");
        vimRegisterInfo.setCloudRegionVersion("mitaca");
        vimRegisterInfo.setCloudType("openstack");
        vimRegisterInfo.setCloudZone("default");
        vimRegisterInfo.setComplexName("complex");
        vimRegisterInfo.setPhysicalLocationId("complex");
        vimRegisterInfo.setOwnerDefinedType("test");
        vimRegisterInfo.setStatus("normal");
        VimAuthInfo vimAuthInfo = new VimAuthInfo();
        vimAuthInfo.setAuthUrl("http://10.11.22.33:5000/v3");
        vimAuthInfo.setCloudDomain("default");
        vimAuthInfo.setPassword("123456");
        vimAuthInfo.setSslCacert("test");
        vimAuthInfo.setSslInsecure(true);
        vimAuthInfo.setUserName("onap");
        vimAuthInfo.setDefaultTenant("admin");
        vimAuthInfos.add(vimAuthInfo);
        vimRegisterInfo.setVimAuthInfos(vimAuthInfos);
        String complexStr = "{\"physical-location-id\": \"complex\", \"data-center-code\": \"test\", \"complex-name\": \"complex\"}";
        CloudRegionProxy mockCloudRegionProxy = Mockito.mock(CloudRegionProxy.class);
        VimManagerProxy mockVimManagerProxy = Mockito.mock(VimManagerProxy.class);
        Mockito.doNothing().when(mockCloudRegionProxy).registerVim(Mockito.anyString(), Mockito.anyString(),
                (CloudRegionDetail) Mockito.anyObject());
        Mockito.doNothing().when(mockCloudRegionProxy).createCloudRegionRelationShip(Mockito.anyString(), Mockito.anyString(),
                (Relationship) Mockito.anyObject());
        Mockito.when(mockCloudRegionProxy.queryComplex(Mockito.anyString())).thenReturn(complexStr);
        Mockito.doNothing().when(mockVimManagerProxy).updateVim(Mockito.anyString(), Mockito.anyString(), (Tenant) Mockito.anyObject());
        VimManagerWrapper vimManagerWrapper = new VimManagerWrapper(mockCloudRegionProxy, mockVimManagerProxy);
        Response response = vimManagerWrapper.registerVim(vimRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_queryVimById() throws ExtsysException {
        CloudRegionProxy mockCloudRegionProxy = Mockito.mock(CloudRegionProxy.class);
        VimManagerProxy mockVimManagerProxy = Mockito.mock(VimManagerProxy.class);
        String vimdetail = "{\"cloud-owner\":\"zte\"," + "\"cloud-region-id\":\"RegionOne\","
                + "\"cloud-type\":\"openstack\"," + "\"cloud-region-version\":\"mitaca\","
                + "\"owner-defined-type\":\"test\"," + "\"cloud-zone\":\"default\"," + "\"complex-name\":\"complex\","
                + "\"cloud-extra-info\":\"{}\"," + "\"esr-system-info-list\":{" + "\"esr-system-info\":[{"
                + "\"esr-system-info-id\":\"123456\"," + "\"service-url\":\"http://10.11.22.33:5000/v3\","
                + "\"user-name\":\"onap\"," + "\"password\":\"123456\"," + "\"system-type\":\"VIM\","
                + "\"ssl-cassert\":\"test\"," + "\"ssl-insecure\":true," + "\"cloud-domain\":\"default\","
                + "\"default-tenant\":\"admin\"," + "\"system-status\":\"normal\"}]}}";
        Mockito.when(mockCloudRegionProxy.queryVimDetail(Mockito.anyString(),Mockito.anyString())).thenReturn(vimdetail);
        VimManagerWrapper vimManagerWrapper = new VimManagerWrapper(mockCloudRegionProxy, mockVimManagerProxy);
        Response response = vimManagerWrapper.queryVimById("zte", "RegionOne");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_queryVimListDetails() throws ExtsysException {
        CloudRegionProxy mockCloudRegionProxy = Mockito.mock(CloudRegionProxy.class);
        VimManagerProxy mockVimManagerProxy = Mockito.mock(VimManagerProxy.class);
        String vimdetail = "{\"cloud-owner\":\"zte\"," + "\"cloud-region-id\":\"RegionOne\","
                + "\"cloud-type\":\"openstack\"," + "\"cloud-region-version\":\"mitaca\","
                + "\"owner-defined-type\":\"test\"," + "\"cloud-zone\":\"default\"," + "\"complex-name\":\"complex\","
                + "\"cloud-extra-info\":\"{}\"," + "\"esr-system-info-list\":{" + "\"esr-system-info\":[{"
                + "\"esr-system-info-id\":\"123456\"," + "\"service-url\":\"http://10.11.22.33:5000/v3\","
                + "\"user-name\":\"onap\"," + "\"password\":\"123456\"," + "\"system-type\":\"VIM\","
                + "\"ssl-cassert\":\"test\"," + "\"ssl-insecure\":true," + "\"cloud-domain\":\"default\","
                + "\"default-tenant\":\"admin\"," + "\"system-status\":\"normal\"}]}}";
        String vimListStr = "{\"cloud-region\": [{\"cloud-owner\": \"zte\"," + "\"cloud-region-id\": \"RegionOne\","
                + "\"cloud-type\": \"openstack\"," + "\"owner-defined-type\": \"test\","
                + "\"cloud-region-version\": \"mitaca\"," + "\"identity-url\": \"http://10.11.22.33:5000/v3\","
                + "\"cloud-zone\": \"default\"," + "\"complex-name\": \"complex\"," + "\"sriov-automation\": true,"
                + "\"cloud-extra-info\": \"{}\"," + "\"resource-version\": \"123456\"}]}";
        Mockito.when(mockCloudRegionProxy.queryVimDetail(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(vimdetail);
        Mockito.when(mockCloudRegionProxy.qureyVimList()).thenReturn(vimListStr);
        VimManagerWrapper vimManagerWrapper = new VimManagerWrapper(mockCloudRegionProxy, mockVimManagerProxy);
        Response response = vimManagerWrapper.queryVimListDetails();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_updateVim() throws ExtsysException {
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
        vimRegisterInfo.setPhysicalLocationId("complex");
        vimRegisterInfo.setOwnerDefinedType("test");
        vimRegisterInfo.setStatus("normal");
        vimAuthInfos.add(vimAuthInfo);
        vimRegisterInfo.setVimAuthInfos(vimAuthInfos);
        CloudRegionProxy mockCloudRegionProxy = Mockito.mock(CloudRegionProxy.class);
        VimManagerProxy mockVimManagerProxy = Mockito.mock(VimManagerProxy.class);
        String vimdetail = "{\"cloud-owner\":\"zte\"," + "\"cloud-region-id\":\"RegionOne\","
                + "\"cloud-type\":\"openstack\"," + "\"cloud-region-version\":\"mitaca\","
                + "\"owner-defined-type\":\"test\"," + "\"cloud-zone\":\"default\"," + "\"complex-name\":\"complex\","
                + "\"cloud-extra-info\":\"{}\"," + "\"esr-system-info-list\":{" + "\"esr-system-info\":[{"
                + "\"esr-system-info-id\":\"123456\"," + "\"service-url\":\"http://10.11.22.33:5000/v3\","
                + "\"user-name\":\"onap\"," + "\"password\":\"123456\"," + "\"system-type\":\"VIM\","
                + "\"ssl-cassert\":\"test\"," + "\"ssl-insecure\":true," + "\"cloud-domain\":\"default\","
                + "\"default-tenant\":\"admin\"," + "\"system-status\":\"normal\"}]}}";
        Mockito.when(mockCloudRegionProxy.queryVimDetail(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(vimdetail);
        Mockito.doNothing().when(mockCloudRegionProxy).registerVim(Mockito.anyString(), Mockito.anyString(),
                (CloudRegionDetail) Mockito.anyObject());
        VimManagerWrapper vimManagerWrapper = new VimManagerWrapper(mockCloudRegionProxy, mockVimManagerProxy);
        Response response = vimManagerWrapper.updateVim("zte", "RegionOne", vimRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
    
    @Test
    public void test_delVim() throws ExtsysException {
        CloudRegionProxy mockCloudRegionProxy = Mockito.mock(CloudRegionProxy.class);
        VimManagerProxy mockVimManagerProxy = Mockito.mock(VimManagerProxy.class);
        String vimdetail = "{\"cloud-owner\":\"zte\"," + "\"cloud-region-id\":\"RegionOne\","
                + "\"cloud-type\":\"openstack\"," + "\"cloud-region-version\":\"mitaca\","
                + "\"owner-defined-type\":\"test\"," + "\"cloud-zone\":\"default\"," + "\"complex-name\":\"complex\","
                + "\"cloud-extra-info\":\"{}\"," + "\"esr-system-info-list\":{" + "\"esr-system-info\":[{"
                + "\"esr-system-info-id\":\"123456\"," + "\"service-url\":\"http://10.11.22.33:5000/v3\","
                + "\"user-name\":\"onap\"," + "\"password\":\"123456\"," + "\"system-type\":\"VIM\","
                + "\"ssl-cassert\":\"test\"," + "\"ssl-insecure\":true," + "\"cloud-domain\":\"default\","
                + "\"default-tenant\":\"admin\"," + "\"system-status\":\"normal\"}]}}";
        Mockito.when(mockCloudRegionProxy.queryVimDetail(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(vimdetail);
        Mockito.doNothing().when(mockCloudRegionProxy).deleteVim(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        VimManagerWrapper vimManagerWrapper = new VimManagerWrapper(mockCloudRegionProxy, mockVimManagerProxy);
        Response response = vimManagerWrapper.delVim("zte", "RegionOne");
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 204);
        }
    }
    
    @Test
    public void test_queryComplexes() throws ExtsysException {
        CloudRegionProxy mockCloudRegionProxy = Mockito.mock(CloudRegionProxy.class);
        VimManagerProxy mockVimManagerProxy = Mockito.mock(VimManagerProxy.class);
        String complexListStr =
                "{\"complex\": [{\"physical-location-id\": \"123\",\"complex-name\": \"complex1\"},"
                + "{\"physical-location-id\": \"test\",\"complex-name\": \"complex\"}]}";
        Mockito.when(mockCloudRegionProxy.qureyComplexes()).thenReturn(complexListStr);
        VimManagerWrapper vimManagerWrapper = new VimManagerWrapper(mockCloudRegionProxy, mockVimManagerProxy);
        Response response = vimManagerWrapper.queryComplexes();
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
}
