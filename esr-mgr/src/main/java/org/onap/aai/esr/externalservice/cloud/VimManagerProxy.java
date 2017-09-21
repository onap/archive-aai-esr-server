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
package org.onap.aai.esr.externalservice.cloud;

import org.glassfish.jersey.client.ClientConfig;
import org.onap.aai.esr.common.MsbConfig;

import com.eclipsesource.jaxrs.consumer.ConsumerFactory;

public class VimManagerProxy {
  
  public static void updateVim(String cloudOwner, String cloudRegionId,
      Tenant tenant) throws Exception {
    ClientConfig config = new ClientConfig(new VimUpdateProvider());
    IVimManage updateVimServiceproxy = ConsumerFactory
        .createConsumer(MsbConfig.getCloudInfrastructureAddr(), config, IVimManage.class);
    updateVimServiceproxy.updateVIM(cloudOwner, cloudRegionId, tenant);
  }
}
