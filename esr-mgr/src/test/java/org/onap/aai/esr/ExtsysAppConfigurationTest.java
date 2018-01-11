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
package org.onap.aai.esr;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ExtsysAppConfigurationTest {

    @Test
    public void getterAndSetter4template() {
        final String template = "Hello ESR";
        ExtsysAppConfiguration appConfiguration = new ExtsysAppConfiguration();
        appConfiguration.setTemplate(template);
        assertEquals(appConfiguration.getTemplate(), template);
    }

    @Test
    public void getterAndSetter4defaultName() {
        final String defaultName = "ONAP-A&AI-ESR";
        ExtsysAppConfiguration appConfiguration = new ExtsysAppConfiguration();
        appConfiguration.setDefaultName(defaultName);
        assertEquals(appConfiguration.getDefaultName(), defaultName);
    }

    @Test
    public void getterAndSetter4msbDiscoveryIp() {
        final String msbDiscoveryIp = "127.0.0.1";
        ExtsysAppConfiguration appConfiguration = new ExtsysAppConfiguration();
        appConfiguration.setMsbDiscoveryIp(msbDiscoveryIp);
        assertEquals(appConfiguration.getMsbDiscoveryIp(), msbDiscoveryIp);
    }

    @Test
    public void getterAndSetter4msbDiscoveryPort() {
        final String msbDiscoveryPort = "10081";
        ExtsysAppConfiguration appConfiguration = new ExtsysAppConfiguration();
        appConfiguration.setMsbDiscoveryPort(msbDiscoveryPort);
        assertEquals(appConfiguration.getMsbDiscoveryPort(), msbDiscoveryPort);
    }

    @Test
    public void getterAndSetter4registByHand() {
        final String registByHand = "true";
        ExtsysAppConfiguration appConfiguration = new ExtsysAppConfiguration();
        appConfiguration.setRegistByHand(registByHand);
        assertEquals(appConfiguration.getRegistByHand(), registByHand);
    }

    @Test
    public void getterAndSetter4msbServerAddr() {
        final String msbServerAddr = "http://127.0.0.1:80";
        ExtsysAppConfiguration appConfiguration = new ExtsysAppConfiguration();
        appConfiguration.setMsbServerAddr(msbServerAddr);
        assertEquals(appConfiguration.getMsbServerAddr(), msbServerAddr);
    }

    @Test
    public void getterAndSetter4serviceIp() {
        final String serviceIp = "true";
        ExtsysAppConfiguration appConfiguration = new ExtsysAppConfiguration();
        appConfiguration.setServiceIp(serviceIp);
        assertEquals(appConfiguration.getServiceIp(), serviceIp);
    }
}
