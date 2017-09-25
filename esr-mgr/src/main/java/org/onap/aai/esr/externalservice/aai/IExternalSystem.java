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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;
import org.onap.aai.esr.exception.ExtsysException;

@Path("/")
public interface IExternalSystem {

  @PUT
  @Path("/esr-vnfm-list/esr-vnfm/{vnfm_id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void registerVNFM(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization, @PathParam("vnfm_id") String vnfmId,
      EsrVnfmDetail esrVnfmDetail) throws ExtsysException;

  @GET
  @Path("/esr-vnfm-list/esr-vnfm/{vnfm_id}?depth=all")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String queryVNFMDetail(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization, @PathParam("vnfm_id") String vnfmId)
      throws ExtsysException;

  @GET
  @Path("/esr-vnfm-list")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String queryVNFMList(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization) throws ExtsysException;

  @DELETE
  @Path("/esr-vnfm-list/esr-vnfm/{vnfm_id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteVNFM(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization,
      @PathParam("vnfm_id") String vnfmId,
      @QueryParam("resource-version") String resourceVersion) throws ExtsysException;

  @PUT
  @Path("/esr-ems-list/esr-ems/{ems_id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void registerEMS(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization, @PathParam("ems_id") String emsId,
      EsrEmsDetail esrEmsDetail) throws ExtsysException;

  @GET
  @Path("/esr-ems-list/esr-ems/{ems_id}?depth=all")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String queryEMSDetail(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization, @PathParam("ems_id") String emsId)
      throws ExtsysException;

  @GET
  @Path("/esr-ems-list")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String queryEMSList(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization) throws ExtsysException;

  @DELETE
  @Path("/esr-ems-list/esr-ems/{ems_id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteEMS(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization,
      @PathParam("ems_id") String emsId,
      @QueryParam("resource-version") String resourceVersion) throws ExtsysException;
  
  @PUT
  @Path("/esr-thirdparty-sdnc-list/esr-thirdparty-sdnc/{thirdparty-sdnc-id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void registerThirdpartySdnc(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization, @PathParam("thirdparty-sdnc-id") String thirdpartySdncId,
      EsrThirdpartySdncDetail esrThirdpartySdncDetail) throws ExtsysException;

  @GET
  @Path("/esr-thirdparty-sdnc-list/esr-thirdparty-sdnc/{thirdparty-sdnc-id}?depth=all")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String queryThirdpartySdncDetail(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization, @PathParam("thirdparty-sdnc-id") String thirdpartySdncId)
      throws ExtsysException;

  @GET
  @Path("/esr-thirdparty-sdnc-list")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String queryThirdpartySdncList(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization) throws ExtsysException;

  @DELETE
  @Path("/esr-thirdparty-sdnc-list/esr-thirdparty-sdnc/{thirdparty-sdnc-id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteThirdpartySdnc(@HeaderParam("X-TransactionId") String transactionId,
      @HeaderParam("X-FromAppId") String fromApp,
      @HeaderParam("Authorization") String authorization,
      @PathParam("thirdparty-sdnc-id") String thirdpartySdncId,
      @QueryParam("resource-version") String resourceVersion) throws ExtsysException;
}
