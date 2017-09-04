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

import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.onap.aai.esr.common.Config;
import org.onap.aai.esr.externalservice.msb.MsbHelper;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  public void initialize(Bootstrap<ExtsysAppConfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/api-doc", "/api-doc", "index.html", "api-doc"));
  }

  @Override
  public void run(ExtsysAppConfiguration configuration, Environment environment) {
    LOGGER.info("Start to initialize esr.");    
    environment.jersey().packages("org.onap.aai.esr.resource");
    environment.jersey().register(MultiPartFeature.class);
    String MSB_IP=configuration.getMsbIp();
    int MSB_Port=configuration.getMsbPort();
    
    initSwaggerConfig(environment, configuration);
    Config.setConfigration(configuration);
    MSBServiceClient msbClient = new MSBServiceClient(MSB_IP, MSB_Port);

    MsbHelper helper = new MsbHelper(msbClient);
    try {
      helper.registerMsb();
    } catch (Exception e) {
      LOGGER.error("Register ESR to msb failed", e);
    }
    
    LOGGER.info("Initialize extsys finished.");
  }

  /**
   * initialize swagger configuration.
   * 
   * @param environment environment information
   * @param configuration configuration
   */
  private void initSwaggerConfig(Environment environment, ExtsysAppConfiguration configuration) {
    environment.jersey().register(new ApiListingResource());
    environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    BeanConfig config = new BeanConfig();
    config.setTitle("Open-o ExtSys Service rest API");
    config.setVersion("1.0.0");
    config.setResourcePackage("org.onap.aai.esr.resource");
    // set rest api basepath in swagger
    SimpleServerFactory simpleServerFactory =
        (SimpleServerFactory) configuration.getServerFactory();
    String basePath = simpleServerFactory.getApplicationContextPath();
    String rootPath = simpleServerFactory.getJerseyRootPath();
    rootPath = rootPath.substring(0, rootPath.indexOf("/*"));
    basePath = basePath.equals("/") ? rootPath
        : (new StringBuilder()).append(basePath).append(rootPath).toString();
    config.setBasePath(basePath);
    config.setScan(true);
  }

}
