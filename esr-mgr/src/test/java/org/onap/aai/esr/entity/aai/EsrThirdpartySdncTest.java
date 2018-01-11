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

public class EsrThirdpartySdncTest {
    @Test
    public void getterAndSetter4thirdpartySdncId() {
        final String thirdpartySdncId = "thirdpartySdncId-test";
        EsrThirdpartySdnc esrThirdpartySdnc = new EsrThirdpartySdnc();
        esrThirdpartySdnc.setThirdpartySdncId(thirdpartySdncId);
        assertEquals(esrThirdpartySdnc.getThirdpartySdncId(), thirdpartySdncId);
    }

    @Test
    public void getterAndSetter4resourceVersion() {
        final String resourceVersion = "resourceVersion-test";
        EsrThirdpartySdnc esrThirdpartySdnc = new EsrThirdpartySdnc();
        esrThirdpartySdnc.setResourceVersion(resourceVersion);
        assertEquals(esrThirdpartySdnc.getResourceVersion(), resourceVersion);
    }

    @Test
    public void getterAndSetter4location() {
        final String location = "location-test";
        EsrThirdpartySdnc esrThirdpartySdnc = new EsrThirdpartySdnc();
        esrThirdpartySdnc.setLocation(location);
        assertEquals(esrThirdpartySdnc.getLocation(), location);
    }

    @Test
    public void getterAndSetter4productName() {
        final String productName = "productName-test";
        EsrThirdpartySdnc esrThirdpartySdnc = new EsrThirdpartySdnc();
        esrThirdpartySdnc.setProductName(productName);
        assertEquals(esrThirdpartySdnc.getProductName(), productName);
    }
}
