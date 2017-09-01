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
package org.onap.aai.esr.wrapper;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.onap.aai.esr.entity.rest.RegisterResponse;
import org.onap.aai.esr.entity.rest.ThirdPartySdncRestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThirdpatySdncWrapper {

  private static ThirdpatySdncWrapper thirdpatySdncWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(ThirdpatySdncWrapper.class);

  /**
   * get ThirdpatySdncWrapper instance.
   * @return ThirdpatySdnc manager wrapper instance
   */
  public static ThirdpatySdncWrapper getInstance() {
    if (thirdpatySdncWrapper == null) {
      thirdpatySdncWrapper = new ThirdpatySdncWrapper();
    }
    return thirdpatySdncWrapper;
  }
  
  public Response registerThirdpartySdnc(ThirdPartySdncRestData thirdpartySdnc) {
    //TODO
    RegisterResponse result = null;
    return Response.ok(result).build();
  }

  public Response updateThirdpartySdnc(ThirdPartySdncRestData thirdpartySdnc) {
    //TODO
    return Response.ok().build();
  }
  
  public Response queryThirdpartySdncList() {
    //TODO
    ArrayList<ThirdPartySdncRestData> thirdpartySdncList = new ArrayList<ThirdPartySdncRestData>();
    return Response.ok(thirdpartySdncList).build();
  }
  
  public Response queryThirdpartySdncById(String thirdpartySdncId) {
    ThirdPartySdncRestData thirdpartySdnc = new ThirdPartySdncRestData();
    //TODO
    return Response.ok(thirdpartySdnc).build();
  }
  
  public Response delThirdpartySdnc(String thirdpartySdncId) {
    //TODO
    return Response.noContent().build();
  }
}
