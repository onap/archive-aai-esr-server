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

package org.onap.aai.esr.externalservice.aai;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientResponse;
import org.onap.aai.esr.externalservice.entity.CloudRegion;

import retrofit2.http.Header;
import retrofit2.http.Headers;


@Path("/aai/v11/cloud-infrastructure/cloud-regions")
public interface IEsrVimRest {

  @Headers({  
    "X-TransactionId: 9999",
    "Accept: application/json",  
    "X-FromAppId: aai-esr"
  })
  @Path("/cloud-region/{cloud_owner}/{cloud_region_id}")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)

  public ClientResponse registerVIMServce(@Header("Authorization") String authorization, @PathParam("cloud_owner") String cloud_owner,
      CloudRegion entity) throws Exception;

  @Headers({  
    "X-TransactionId: 9999",
    "Accept: application/json",  
    "X-FromAppId: aai-esr"
  })
  @Path("/cloud-region/{cloud_owner}/{cloud_region_id}?depth=all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ClientResponse queryVIMDetail(@Header("Authorization") String authorization, @PathParam("cloud_owner") String cloud_owner,
      @PathParam("cloud_region_id") String cloud_region_id) throws Exception;
  
  @Headers({  
    "X-TransactionId: 9999",
    "Accept: application/json",  
    "X-FromAppId: aai-esr"
  })
  @Path("/")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ClientResponse queryVIMList(@Header("Authorization") String authorization) throws Exception;
  

  @Headers({  
    "X-TransactionId: 9999",
    "Accept: application/json",  
    "X-FromAppId: aai-esr"
  })
  @Path("/cloud-region/{cloud_owner}/{cloud_region_id}/esr-system-info/{esr_system_info_id}")
  @GET
  public ClientResponse delVIMAuthInfo(@Header("Authorization") String authorization, @PathParam("cloud_owner") String cloud_owner,
      @PathParam("cloud_region_id") String cloud_region_id, @PathParam("esr_system_info_id") String esr_system_info_id) throws Exception;
  
}
