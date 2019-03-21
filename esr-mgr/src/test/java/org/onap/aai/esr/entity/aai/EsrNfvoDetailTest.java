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

public class EsrNfvoDetailTest {
    @Test
    public void getterAndSetter4nfvoId() {
        final String nfvoId = "nfvoId-test";
        EsrNfvoDetail esrNfvo = new EsrNfvoDetail();
        esrNfvo.setNfvoId(nfvoId);
        assertEquals(esrNfvo.getNfvoId(), nfvoId);
    }

    @Test
    public void getterAndSetter4resourceVersion() {
        final String resourceVersion = "resourceVersion-test";
        EsrNfvoDetail esrNfvo = new EsrNfvoDetail();
        esrNfvo.setResourceVersion(resourceVersion);
        assertEquals(esrNfvo.getResourceVersion(), resourceVersion);
    }

    @Test
    public void getterAndSetter4apiRoot() {
        final String apiRoot = "apiRoot-test";
        EsrNfvoDetail esrNfvo = new EsrNfvoDetail();
        esrNfvo.setApiroot(apiRoot);
        assertEquals(esrNfvo.getApiroot(), apiRoot);
    }

    @Test
    public void getterAndSetter4esrSystemInfoList() {
        final EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
        List<EsrSystemInfo> esrSystemInfo = new ArrayList<>();
        EsrSystemInfo esrSystemInfoObj = new EsrSystemInfo();
        esrSystemInfoObj.setEsrSystemInfoId("123");
        esrSystemInfo.add(esrSystemInfoObj);
        esrSystemInfoList.setEsrSystemInfo(esrSystemInfo);
        EsrNfvoDetail esrNfvoDetail = new EsrNfvoDetail();
        esrNfvoDetail.setEsrSystemInfoList(esrSystemInfoList);
        assertEquals(esrNfvoDetail.getEsrSystemInfoList(), esrSystemInfoList);
    }
}
