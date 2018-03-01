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
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ComplexList implements Serializable {
    public static final long serialVersionUID = 1L;

    @SerializedName("complex")
    private List<Complex> complex;

    public List<Complex> getComplex() {
        return complex;
    }

    public void setComplex(List<Complex> complex) {
        this.complex = complex;
    }
    
}
