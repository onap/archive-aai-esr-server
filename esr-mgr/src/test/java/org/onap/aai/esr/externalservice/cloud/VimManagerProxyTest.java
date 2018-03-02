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
package org.onap.aai.esr.externalservice.cloud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.onap.aai.esr.common.MsbConfig;
import org.onap.aai.esr.exception.ExtsysException;

public class VimManagerProxyTest {

    VimManagerProxy proxy;
    
    @Before
    public void init() {
        MsbConfig.setMsbServerAddr("http://msb-server");
        proxy = new VimManagerProxy();
    }

    @Test(expected = ExtsysException.class)
    public void testUpdateVim() throws ExtsysException {
        Tenant tenant = new Tenant();
        tenant.setDefaultTenant("defaultTenant");
        Assert.assertEquals(tenant.getDefaultTenant(), "defaultTenant");
        proxy.updateVim("owner-1", "region-1", tenant);
    }
}
