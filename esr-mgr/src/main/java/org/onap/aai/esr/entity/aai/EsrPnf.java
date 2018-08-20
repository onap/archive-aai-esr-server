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

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class EsrPnf implements Serializable{

    public static final long serialVersionUID = 1L;

    @SerializedName("pnf-name")
    private String pnfName;

    @SerializedName("pnf-name2")
    private String pnfName2;

    @SerializedName("pnf-id")
    private String pnfId;
    
    @SerializedName("equip-type")
    private String equipType;

    @SerializedName("equip-vendor")
    private String equipVendor;
    
    @SerializedName("equip-model")
    private String equipModel;
    
    @SerializedName("management-option")
    private String managementOption;
    
    @SerializedName("in-maint")
    private boolean inMaint = false;
    
    @SerializedName("frame-id")
    private String frameId;
    
    @SerializedName("resource-version")
    private String resourceVersion;

    public String getPnfName() {
        return pnfName;
    }

    public void setPnfName(String pnfName) {
        this.pnfName = pnfName;
    }

    public String getPnfName2() {
        return pnfName2;
    }

    public void setPnfName2(String pnfName2) {
        this.pnfName2 = pnfName2;
    }

    public String getPnfId() {
        return pnfId;
    }

    public void setPnfId(String pnfId) {
        this.pnfId = pnfId;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipVendor() {
        return equipVendor;
    }

    public void setEquipVendor(String equipVendor) {
        this.equipVendor = equipVendor;
    }

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel;
    }

    public String getManagementOption() {
        return managementOption;
    }

    public void setManagementOption(String managementOption) {
        this.managementOption = managementOption;
    }

    public boolean isInMaint() {
        return inMaint;
    }

    public void setInMaint(boolean inMaint) {
        this.inMaint = inMaint;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }
}
