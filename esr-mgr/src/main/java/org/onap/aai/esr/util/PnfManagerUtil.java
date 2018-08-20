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
    public static Pnf pnfRegisterInfo2pnf(PnfRegisterInfo pnfRegisterInfo) {
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

}
