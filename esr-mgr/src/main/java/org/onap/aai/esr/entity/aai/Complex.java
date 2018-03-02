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

public class Complex implements Serializable {
    public static final long serialVersionUID = 1L;
    
    @SerializedName("physical-location-id")
    private String physicalLocationId;
    
    
    @SerializedName("complex-name")
    private String complexName;

    public String getPhysicalLocationId() {
        return physicalLocationId;
    }

    public void setPhysicalLocationId(String physicalLocationId) {
        this.physicalLocationId = physicalLocationId;
    }

    public String getComplexName() {
        return complexName;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }
}
