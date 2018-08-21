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
package org.onap.aai.esr.entity.aai;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PnfTest {

    @Test
    public void getterAndSetter4pnfName() {
        final String pnfName = "pnf1";
        Pnf pnf = new Pnf();
        pnf.setPnfName(pnfName);
        assertEquals(pnf.getPnfName(), pnfName);
    }
    
    @Test
    public void getterAndSetter4pnfName2() {
        final String pnfName2 = "pnf2";
        Pnf pnf = new Pnf();
        pnf.setPnfName2(pnfName2);
        assertEquals(pnf.getPnfName2(), pnfName2);
    }
    
    @Test
    public void getterAndSetter4pnfId() {
        final String pnfId = "123";
        Pnf pnf = new Pnf();
        pnf.setPnfId(pnfId);
        assertEquals(pnf.getPnfId(), pnfId);
    }
    
    @Test
    public void getterAndSetter4equipType() {
        final String equipType = "pnf1";
        Pnf pnf = new Pnf();
        pnf.setEquipType(equipType);
        assertEquals(pnf.getEquipType(), equipType);
    }
    
    @Test
    public void getterAndSetter4equipVendor() {
        final String equipVendor = "ZTE";
        Pnf pnf = new Pnf();
        pnf.setEquipVendor(equipVendor);
        assertEquals(pnf.getEquipVendor(), equipVendor);
    }
    
    @Test
    public void getterAndSetter4equipModel() {
        final String equipModel = "pnfdId1";
        Pnf pnf = new Pnf();
        pnf.setEquipModel(equipModel);
        assertEquals(pnf.getEquipModel(), equipModel);
    }
    
    @Test
    public void getterAndSetter4managementOption() {
        final String managementOption = "emsId1";
        Pnf pnf = new Pnf();
        pnf.setManagementOption(managementOption);
        assertEquals(pnf.getManagementOption(), managementOption);
    }
    
    @Test
    public void getterAndSetter4inMaint() {
        final boolean inMaint = false;
        Pnf pnf = new Pnf();
        pnf.setInMaint(inMaint);
        assertEquals(pnf.isInMaint(), inMaint);
    }
    
    @Test
    public void getterAndSetter4frameId() {
        final String frameId = "123.14-41.25";
        Pnf pnf = new Pnf();
        pnf.setFrameId(frameId);
        assertEquals(pnf.getFrameId(), frameId);
    }
    
    @Test
    public void getterAndSetter4resourceVersion() {
        final String resourceVersion = "1234";
        Pnf pnf = new Pnf();
        pnf.setResourceVersion(resourceVersion);
        assertEquals(pnf.getResourceVersion(), resourceVersion);
    }
}
