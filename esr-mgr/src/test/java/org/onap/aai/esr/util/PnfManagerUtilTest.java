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
package org.onap.aai.esr.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.onap.aai.esr.entity.aai.Pnf;
import org.onap.aai.esr.entity.rest.PnfRegisterInfo;
import com.google.gson.Gson;

public class PnfManagerUtilTest {

    @Test
    public void pnf2pnfRegisterInfoTest() {
        PnfManagerUtil pnfManagerUtil = new PnfManagerUtil();
        String pnfStr = "{\"pnf-name\": \"pnf1\","
                + "\"pnf-name2\": \"PNF test\","
                + "\"pnf-id\": \"subnetId1-neId1\","
                + "\"equip-type\": \"Test\","
                + "\"equip-vendor\": \"ZTE\","
                + "\"equip-model\": \"pnfdId1\","
                + "\"management-option\": \"emsId1\","
                + "\"in-maint\": false,"
                + "\"frame-id\": \"121.546-14.22\"}";
        Pnf pnf = new Gson().fromJson(pnfStr, Pnf.class);
        PnfRegisterInfo pnfRegisterInfo = pnfManagerUtil.pnf2PnfRegisterInfo(pnf);
        String pnfRegisterInfoStr = new ExtsysUtil().objectToString(pnfRegisterInfo);
        String expectResult = "{\"pnfId\":\"pnf1\","
                + "\"userLabel\":\"PNF test\","
                + "\"subnetId\":\"subnetId1\","
                + "\"neId\":\"neId1\","
                + "\"managementType\":\"Test\","
                + "\"vendor\":\"ZTE\","
                + "\"pnfdId\":\"pnfdId1\","
                + "\"emsId\":\"emsId1\","
                + "\"lattitude\":\"121.546\","
                + "\"longitude\":\"14.22\"}";
        assertEquals(expectResult, pnfRegisterInfoStr);
    }
    
    @Test
    public void pnfRegisterInfo2pnfTest() {
        PnfManagerUtil pnfManagerUtil = new PnfManagerUtil();
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setPnfId("pnf1");
        pnfRegisterInfo.setUserLabel("PNF test");
        pnfRegisterInfo.setSubnetId("subnetId1");
        pnfRegisterInfo.setNeId("neId1");
        pnfRegisterInfo.setManagementType("Test");
        pnfRegisterInfo.setVendor("ZTE");
        pnfRegisterInfo.setPnfdId("pnfdId1");
        pnfRegisterInfo.setEmsId("emsId1");
        pnfRegisterInfo.setLattitude("121.546");
        pnfRegisterInfo.setLongitude("14.22");
        Pnf pnf = pnfManagerUtil.pnfRegisterInfo2pnf(pnfRegisterInfo);
        String expectResult = "{\"pnf-name\":\"pnf1\","
                + "\"pnf-name2\":\"PNF test\","
                + "\"pnf-id\":\"subnetId1-neId1\","
                + "\"equip-type\":\"Test\","
                + "\"equip-vendor\":\"ZTE\","
                + "\"equip-model\":\"pnfdId1\","
                + "\"management-option\":\"emsId1\","
                + "\"in-maint\":false,"
                + "\"frame-id\":\"121.546-14.22\"}";
        String pnfStr = new ExtsysUtil().objectToString(pnf);
        assertEquals(expectResult, pnfStr);
    }
}
