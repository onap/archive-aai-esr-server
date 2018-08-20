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

import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.onap.aai.esr.common.MsbConfig;
import org.onap.aai.esr.entity.aai.Pnf;
import org.onap.aai.esr.entity.rest.PnfRegisterInfo;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.NetworkProxy;

public class PnfManagerWrapperTest {

    static {
        MsbConfig.setMsbServerAddr("http://127.0.0.1:80");
    }

    @Test
    public void test_registerPnf() throws ExtsysException {
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setPnfId("pnf1");
        pnfRegisterInfo.setUserLabel("PNF-test");
        pnfRegisterInfo.setSubnetId("subnetId1");
        pnfRegisterInfo.setNeId("neId1");
        pnfRegisterInfo.setManagementType("Test");
        pnfRegisterInfo.setVendor("ZTE");
        pnfRegisterInfo.setPnfdId("pnfdId1");
        pnfRegisterInfo.setEmsId("emsId1");
        pnfRegisterInfo.setLattitude("121.546");
        pnfRegisterInfo.setLongitude("14.22");
        NetworkProxy mockNetworkProxy = Mockito.mock(NetworkProxy.class);
        Mockito.doNothing().when(mockNetworkProxy).registerPnf(Mockito.anyString(), (Pnf)Mockito.anyObject());
        PnfManagerWrapper pnfManagerWrapper = new PnfManagerWrapper(mockNetworkProxy);
        Response response = pnfManagerWrapper.registerPnf(pnfRegisterInfo);
        if (response != null) {
            Assert.assertTrue(response.getStatus() == 200);
        }
    }
}
