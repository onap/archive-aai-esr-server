/**
 * Copyright 2016-2017 ZTE Corporation.
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
package org.onap.aai.esr.common;

import org.junit.Assert;
import org.junit.Test;
import org.onap.aai.esr.ExtsysAppConfiguration;

public class MsbConfigTest {

    @Test
    public void testAllMethods(){
        MsbConfig.setMsbServerAddr("msb-server");
        Assert.assertEquals(MsbConfig.getMsbServerAddr(), "msb-server");

        MsbConfig.setCloudInfrastructureAddr("cloud-1");
        Assert.assertEquals(MsbConfig.cloudInfrastructureAddr, "cloud-1");
        Assert.assertEquals(MsbConfig.getCloudInfrastructureAddr(), "msb-server/api/aai-cloudInfrastructure/v11");

        MsbConfig.setExternalSystemAddr("external-addr");
        Assert.assertEquals(MsbConfig.externalSystemAddr, "external-addr");
        Assert.assertEquals(MsbConfig.getExternalSystemAddr(), "msb-server/api/aai-externalSystem/v11");

        MsbConfig.setMultiCloudAddr("multicloud-address");
        Assert.assertEquals(MsbConfig.multiCloudAddr, "multicloud-address");
        Assert.assertEquals(MsbConfig.getMultiCloudAddr(), "msb-server/api/multicloud/v0");

        MsbConfig.setMsbDiscoveryIp("discovery://1111");
        Assert.assertEquals(MsbConfig.getMsbDiscoveryIp(), "discovery://1111");

        MsbConfig.setMsbDiscoveryPort("4040");
        Assert.assertEquals(MsbConfig.getMsbDiscoveryPort(), "4040");
    }

    @Test
    public void testConfigClass(){
        ExtsysAppConfiguration configur = new ExtsysAppConfiguration();
        Config.setConfigration(configur);
        Assert.assertEquals(Config.getConfigration(), configur);
    }
}
