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
package org.onap.aai.esr.externalservice.aai;

import org.junit.Before;
import org.junit.Test;
import org.onap.aai.esr.common.MsbConfig;
import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;
import org.onap.aai.esr.exception.ExtsysException;

public class ExternalSystemProxyTest {

    ExternalSystemProxy externalSystemProxy;

    @Before
    public void init() {
        MsbConfig.setMsbServerAddr("http://msb-server");
        externalSystemProxy = new ExternalSystemProxy();
    }

    @Test(expected = ExtsysException.class)
    public void testRegisterVnfm() throws ExtsysException {
        EsrVnfmDetail detail = new EsrVnfmDetail();
        externalSystemProxy.registerVnfm("vnfm-1", detail);
    }

    @Test(expected = ExtsysException.class)
    public void testQueryVnfmDetail() throws ExtsysException {
        externalSystemProxy.queryVnfmDetail("vnfm-1");
    }

    @Test(expected = ExtsysException.class)
    public void testQueryVnfmList() throws ExtsysException {
        externalSystemProxy.queryVnfmList();
    }

    @Test(expected = ExtsysException.class)
    public void testDeleteVnfm() throws ExtsysException {
        externalSystemProxy.deleteVnfm("vnfm-1", "version-1");
    }

    @Test(expected = ExtsysException.class)
    public void testRegisterSdnc() throws ExtsysException {
        EsrThirdpartySdncDetail detail = new EsrThirdpartySdncDetail();
        externalSystemProxy.registerSdnc("sdnc-1", detail);
    }

    @Test(expected = ExtsysException.class)
    public void testQueryThirdpartySdncDetail() throws ExtsysException {
        externalSystemProxy.queryThirdpartySdncDetail("sdnc-1");
    }

    @Test(expected = ExtsysException.class)
    public void testQuerySdncList() throws ExtsysException {
        externalSystemProxy.querySdncList();
    }

    @Test(expected = ExtsysException.class)
    public void testDeleteThirdpartySdnc() throws ExtsysException {
        externalSystemProxy.deleteThirdpartySdnc("sdnc-1", "version-1");
    }

    @Test(expected = ExtsysException.class)
    public void testRegisterEms() throws ExtsysException {
        EsrEmsDetail detail = new EsrEmsDetail();
        externalSystemProxy.registerEms("ems-1", detail);
    }

    @Test(expected = ExtsysException.class)
    public void testQueryEmsDetail() throws ExtsysException {
        externalSystemProxy.queryEmsDetail("ems-1");
    }

    @Test(expected = ExtsysException.class)
    public void testQueryEmsList() throws ExtsysException {
        externalSystemProxy.queryEmsList();
    }

    @Test(expected = ExtsysException.class)
    public void testDeleteEms() throws ExtsysException {
        externalSystemProxy.deleteEms("ems-1", "version-1");
    }
}
