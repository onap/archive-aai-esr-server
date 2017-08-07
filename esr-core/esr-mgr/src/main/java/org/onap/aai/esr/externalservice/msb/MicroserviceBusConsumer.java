/**
 * Copyright 2016 ZTE Corporation.
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

package org.onap.aai.esr.externalservice.msb;

import com.eclipsesource.jaxrs.consumer.ConsumerFactory;

import org.glassfish.jersey.client.ClientConfig;
import org.onap.aai.esr.common.Config;
import org.onap.aai.esr.externalservice.entity.ServiceRegisterEntity;
import org.onap.aai.esr.util.ExtsysDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MicroserviceBusConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(MicroserviceBusConsumer.class);

  /**
   * @param entity service entity
   * @return register service to msb success return true, else return false.
   */
  public static boolean registerService(ServiceRegisterEntity entity) {
    ClientConfig config = new ClientConfig();
    LOG.info("microservice register body:" + ExtsysDbUtil.objectToString(entity));
    try {
      MicroserviceBusRest resourceserviceproxy = ConsumerFactory.createConsumer(
          Config.getConfigration().getMsbServerAddr(), config, MicroserviceBusRest.class);
      resourceserviceproxy.registerServce("false", entity);
    } catch (Exception error) {
      LOG.error("microservice register failed!" + error.getMessage());
      return false;
    }
    return true;
  }
}
