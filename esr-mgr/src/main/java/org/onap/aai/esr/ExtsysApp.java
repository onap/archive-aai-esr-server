/**
 * Copyright 2016-2017 ZTE Corporation.
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
package org.onap.aai.esr;


import org.onap.aai.esr.externalservice.aai.AaiAdapterConfig;
import org.onap.aai.esr.externalservice.msb.MsbHelper;
import org.onap.aai.esr.externalservice.msb.MsbInfoConfig;
import org.onap.aai.esr.resource.EmsManager;
import org.onap.aai.esr.resource.ThirdpatySdncManager;
import org.onap.aai.esr.resource.VimManager;
import org.onap.aai.esr.resource.VnfmManager;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ExtsysApp extends Application<ExtsysAppConfiguration> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExtsysApp.class);
  
  public static void main(String[] args) throws Exception {
    new ExtsysApp().run(args);
  }

  @Override
  public String getName() {
    return "ONAP-ESR";
  }

  @Override
  public void run(ExtsysAppConfiguration configuration, Environment environment) {
    LOGGER.info("Start to initialize esr.");
    AaiAdapterConfig.setCloudInfrastructureAddr(configuration.getCloudInfrastructureAddr());
    AaiAdapterConfig.setExternalSystemAddr(configuration.getExternalSystemAddr());
    MsbInfoConfig.setMsbDiscoveryIp(configuration.getMsbDiscoveryIp());
    MsbInfoConfig.setMsbDiscoveryPort(configuration.getMsbDiscoveryPort());
    environment.jersey().register(new EmsManager());
    environment.jersey().register(new ThirdpatySdncManager());
    environment.jersey().register(new VimManager());
    environment.jersey().register(new VnfmManager());
    
    if (configuration.getRegistByHand().endsWith("true")){
      String MSB_IP=configuration.getMsbDiscoveryIp();
      Integer MSB_Port= Integer.valueOf(configuration.getMsbDiscoveryPort());
      MSBServiceClient msbClient = new MSBServiceClient(MSB_IP, MSB_Port);
      MsbHelper helper = new MsbHelper(msbClient);
      try {
        helper.registerMsb();
      } catch (Exception e) {
        LOGGER.error("Register esr-server to msb by java-sdk failed", e);
      }
    }
    LOGGER.info("Initialize extsys finished.");
  }

}
