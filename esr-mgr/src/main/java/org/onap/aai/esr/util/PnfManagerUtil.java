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

import org.onap.aai.esr.entity.aai.Pnf;
import org.onap.aai.esr.entity.rest.PnfRegisterInfo;

public class PnfManagerUtil {

    /**
     * @param pnfRegisterInfo
     * @return
     */
    public Pnf pnfRegisterInfo2pnf(PnfRegisterInfo pnfRegisterInfo) {
        Pnf pnf = new Pnf();
        pnf.setPnfName(pnfRegisterInfo.getPnfId());
        pnf.setPnfName2(pnfRegisterInfo.getUserLabel());
        String pnfId = pnfRegisterInfo.getSubnetId() + "-" +pnfRegisterInfo.getNeId();
        pnf.setPnfId(pnfId);
        pnf.setEquipType(pnfRegisterInfo.getManagementType());
        pnf.setEquipVendor(pnfRegisterInfo.getVendor());
        pnf.setEquipModel(pnfRegisterInfo.getPnfdId());
        pnf.setManagementOption(pnfRegisterInfo.getEmsId());
        String frameId = pnfRegisterInfo.getLattitude() + "-" + pnfRegisterInfo.getLongitude();
        pnf.setFrameId(frameId);
        return pnf;
    }

    /**
     * @param pnf
     * @return
     */
    public PnfRegisterInfo pnf2PnfRegisterInfo(Pnf pnf) {
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        pnfRegisterInfo.setPnfId(pnf.getPnfName());
        pnfRegisterInfo.setUserLabel(pnf.getPnfName2());
        String subnetNeId = pnf.getPnfId();
        
        String[] ids = subnetNeId.split("-");
        pnfRegisterInfo.setSubnetId(ids[0].toString());
        pnfRegisterInfo.setNeId(ids[1].toString());
        
        pnfRegisterInfo.setManagementType(pnf.getEquipType());
        pnfRegisterInfo.setVendor(pnf.getEquipVendor());
        pnfRegisterInfo.setPnfdId(pnf.getEquipModel());
        pnfRegisterInfo.setEmsId(pnf.getManagementOption());
        
        String location = pnf.getFrameId();
        String[] locates = location.split("-");
        pnfRegisterInfo.setLattitude(locates[0].toString());
        pnfRegisterInfo.setLongitude(locates[1].toString());
        
        return pnfRegisterInfo;
    }

}
