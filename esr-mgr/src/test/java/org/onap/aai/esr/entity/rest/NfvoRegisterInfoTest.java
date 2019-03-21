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
package org.onap.aai.esr.entity.rest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NfvoRegisterInfoTest {

    @Test
    public void getterAndSetter4nfvoId() {
        final String nfvoId = "dafadf";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setNfvoId(nfvoId);
        assertEquals(nfvoRegisterInfo.getNfvoId(), nfvoId);
    }

    @Test
    public void getterAndSetter4name() {
        final String name = "name-test";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setName(name);
        assertEquals(nfvoRegisterInfo.getName(), name);
    }
    @Test
    public void getterAndSetter4apiRoot() {
        final String apiRoot = "dafadf";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setApiroot(apiRoot);
        assertEquals(nfvoRegisterInfo.getApiroot(), apiRoot);
    }

    @Test
    public void getterAndSetter4vendor() {
        final String vendor = "zte";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setVendor(vendor);
        assertEquals(nfvoRegisterInfo.getVendor(), vendor);
    }

    @Test
    public void getterAndSetter4version() {
        final String version = "v1.0";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setVersion(version);
        assertEquals(nfvoRegisterInfo.getVersion(), version);
    }
    @Test
    public void getterAndSetter4url() {
        final String url = "/home";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setUrl(url);
        assertEquals(nfvoRegisterInfo.getUrl(), url);
    }

    @Test
    public void getterAndSetter4userName() {
        final String userName = "li";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setUserName(userName);
        assertEquals(nfvoRegisterInfo.getUserName(), userName);
    }

    @Test
    public void getterAndSetter4password() {
        final String password = "dfaaf";
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setPassword(password);
        assertEquals(nfvoRegisterInfo.getPassword(), password);
    }
}
