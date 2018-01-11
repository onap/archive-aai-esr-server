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
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class EsrThirdpartySdncDetailTest {
    @Test
    public void getterAndSetter4thirdpartySdncId() {
        final String thirdpartySdncId = "thirdpartySdncId-test";
        EsrThirdpartySdncDetail esrThirdpartySdncDetail = new EsrThirdpartySdncDetail();
        esrThirdpartySdncDetail.setThirdpartySdncId(thirdpartySdncId);
        assertEquals(esrThirdpartySdncDetail.getThirdpartySdncId(), thirdpartySdncId);
    }

    @Test
    public void getterAndSetter4resourceVersion() {
        final String resourceVersion = "resourceVersion-test";
        EsrThirdpartySdncDetail esrThirdpartySdncDetail = new EsrThirdpartySdncDetail();
        esrThirdpartySdncDetail.setResourceVersion(resourceVersion);
        assertEquals(esrThirdpartySdncDetail.getResourceVersion(), resourceVersion);
    }

    @Test
    public void getterAndSetter4location() {
        final String location = "location-test";
        EsrThirdpartySdncDetail esrThirdpartySdncDetail = new EsrThirdpartySdncDetail();
        esrThirdpartySdncDetail.setLocation(location);
        assertEquals(esrThirdpartySdncDetail.getLocation(), location);
    }

    @Test
    public void getterAndSetter4productName() {
        final String productName = "productName-test";
        EsrThirdpartySdncDetail esrThirdpartySdncDetail = new EsrThirdpartySdncDetail();
        esrThirdpartySdncDetail.setProductName(productName);
        assertEquals(esrThirdpartySdncDetail.getProductName(), productName);
    }

    @Test
    public void getterAndSetter4esrSystemInfoList() {
        final EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
        List<EsrSystemInfo> esrSystemInfo = new ArrayList<>();
        EsrSystemInfo esrSystemInfoObj = new EsrSystemInfo();
        esrSystemInfoObj.setEsrSystemInfoId("123");
        esrSystemInfo.add(esrSystemInfoObj);
        esrSystemInfoList.setEsrSystemInfo(esrSystemInfo);
        EsrThirdpartySdncDetail esrThirdpartySdncDetail = new EsrThirdpartySdncDetail();
        esrThirdpartySdncDetail.setEsrSystemInfoList(esrSystemInfoList);
        assertEquals(esrThirdpartySdncDetail.getEsrSystemInfoList(), esrSystemInfoList);
    }
}
