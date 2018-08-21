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
package org.onap.aai.esr.entity.rest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PnfRegisterInfoTest {

    @Test
    public void getterAndSetter4pnfId() {
        final String pnfId = "PNF1";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setPnfId(pnfId);
        assertEquals(pnfRegisterInfo.getPnfId(), pnfId);
    }
    
    @Test
    public void getterAndSetter4userLabel() {
        final String userLabel = "PNF test";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setUserLabel(userLabel);
        assertEquals(pnfRegisterInfo.getUserLabel(), userLabel);
    }
    
    @Test
    public void getterAndSetter4subnetId() {
        final String subnetId = "subnetId1";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setSubnetId(subnetId);
        assertEquals(pnfRegisterInfo.getSubnetId(), subnetId);
    }
    
    @Test
    public void getterAndSetter4neId() {
        final String neId = "neId1";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setNeId(neId);
        assertEquals(pnfRegisterInfo.getNeId(), neId);
    }
    
    @Test
    public void getterAndSetter4managementType() {
        final String managementType = "test";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setManagementType(managementType);
        assertEquals(pnfRegisterInfo.getManagementType(), managementType);
    }
    
    @Test
    public void getterAndSetter4vendor() {
        final String vendor = "ZTE";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setVendor(vendor);
        assertEquals(pnfRegisterInfo.getVendor(), vendor);
    }
    
    @Test
    public void getterAndSetter4pnfdId() {
        final String pnfdId = "PNFDID1";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setPnfdId(pnfdId);
        assertEquals(pnfRegisterInfo.getPnfdId(), pnfdId);
    }
    
    @Test
    public void getterAndSetter4emsId() {
        final String emsId = "emsId1";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setEmsId(emsId);
        assertEquals(pnfRegisterInfo.getEmsId(), emsId);
    }
    
    @Test
    public void getterAndSetter4lattitude() {
        final String lattitude = "124.12";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setLattitude(lattitude);
        assertEquals(pnfRegisterInfo.getLattitude(), lattitude);
    }
    
    @Test
    public void getterAndSetter4longitude() {
        final String longitude = "145.21";
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setLongitude(longitude);
        assertEquals(pnfRegisterInfo.getLongitude(), longitude);
    }
}
