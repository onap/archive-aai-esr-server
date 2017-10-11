/**
 * Copyright 2017 ZTE Corporation.
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

import java.util.ArrayList;

import org.junit.Test;

public class EsrThirdpartySdncListTest {
  @Test
  public void getterAndSetter4EsrThirdpartySdncList() {
    EsrThirdpartySdncList esrThirdpartySdncList = new EsrThirdpartySdncList();
    ArrayList<EsrThirdpartySdnc> esrThirdpartySdncs = new ArrayList<EsrThirdpartySdnc>();
    EsrThirdpartySdnc esrThirdpartySdnc = new EsrThirdpartySdnc();
    esrThirdpartySdnc.setThirdpartySdncId("123");
    esrThirdpartySdncs.add(esrThirdpartySdnc);
    esrThirdpartySdncList.setEsrThirdpartySdnc(esrThirdpartySdncs);
    assertEquals(esrThirdpartySdncList.getEsrThirdpartySdnc(), esrThirdpartySdncs);
  }
}