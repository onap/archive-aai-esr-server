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

public class EsrVnfmDetailTest {
    @Test
    public void getterAndSetter4vnfmId() {
        final String vnfmId = "vnfmId-test";
        EsrVnfmDetail esrVnfm = new EsrVnfmDetail();
        esrVnfm.setVnfmId(vnfmId);
        assertEquals(esrVnfm.getVnfmId(), vnfmId);
    }

    @Test
    public void getterAndSetter4resourceVersion() {
        final String resourceVersion = "resourceVersion-test";
        EsrVnfmDetail esrVnfm = new EsrVnfmDetail();
        esrVnfm.setResourceVersion(resourceVersion);
        assertEquals(esrVnfm.getResourceVersion(), resourceVersion);
    }

    @Test
    public void getterAndSetter4vimId() {
        final String vimId = "vimId-test";
        EsrVnfmDetail esrVnfm = new EsrVnfmDetail();
        esrVnfm.setVimId(vimId);
        assertEquals(esrVnfm.getVimId(), vimId);
    }

    @Test
    public void getterAndSetter4certificateUrl() {
        final String certificateUrl = "certificateUrl-test";
        EsrVnfmDetail esrVnfm = new EsrVnfmDetail();
        esrVnfm.setCertificateUrl(certificateUrl);
        assertEquals(esrVnfm.getCertificateUrl(), certificateUrl);
    }

    @Test
    public void getterAndSetter4esrSystemInfoList() {
        final EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
        List<EsrSystemInfo> esrSystemInfo = new ArrayList<>();
        EsrSystemInfo esrSystemInfoObj = new EsrSystemInfo();
        esrSystemInfoObj.setEsrSystemInfoId("123");
        esrSystemInfo.add(esrSystemInfoObj);
        esrSystemInfoList.setEsrSystemInfo(esrSystemInfo);
        EsrVnfmDetail esrVnfmDetail = new EsrVnfmDetail();
        esrVnfmDetail.setEsrSystemInfoList(esrSystemInfoList);
        assertEquals(esrVnfmDetail.getEsrSystemInfoList(), esrSystemInfoList);
    }
}
