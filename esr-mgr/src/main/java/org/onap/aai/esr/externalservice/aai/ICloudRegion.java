/**
 * Copyright 2017-2018 ZTE Corporation.
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
import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.entity.aai.Relationship;
import org.onap.aai.esr.exception.ExtsysException;

public interface ICloudRegion {

    @PUT
    @Path("/cloud-regions/cloud-region/{cloud_owner}/{cloud_region_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void registerVIMService(@HeaderParam("X-TransactionId") String transactionId,
            @HeaderParam("X-FromAppId") String fromApp, @HeaderParam("Authorization") String authorization,
            @PathParam("cloud_owner") String cloud_owner, @PathParam("cloud_region_id") String cloud_region_id,
            CloudRegionDetail cloudRegion) throws ExtsysException;

    @GET
    @Path("/cloud-regions/cloud-region/{cloud_owner}/{cloud_region_id}?depth=all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String queryVIMDetail(@HeaderParam("X-TransactionId") String transactionId,
            @HeaderParam("X-FromAppId") String fromApp, @HeaderParam("Authorization") String authorization,
            @PathParam("cloud_owner") String cloud_owner, @PathParam("cloud_region_id") String cloud_region_id)
            throws ExtsysException;

    @GET
    @Path("/cloud-regions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String queryVIMList(@HeaderParam("X-TransactionId") String transactionId,
            @HeaderParam("X-FromAppId") String fromApp, @HeaderParam("Authorization") String authorization)
            throws ExtsysException;

    @DELETE
    @Path("/cloud-regions/cloud-region/{cloud_owner}/{cloud_region_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteVim(@HeaderParam("X-TransactionId") String transactionId,
            @HeaderParam("X-FromAppId") String fromApp, @HeaderParam("Authorization") String authorization,
            @PathParam("cloud_owner") String cloud_owner, @PathParam("cloud_region_id") String cloud_region_id,
            @QueryParam("resource-version") String resourceVersion) throws ExtsysException;
    
    @PUT
    @Path("/cloud-regions/cloud-region/{cloud_owner}/{cloud_region_id}/relationship-list/relationship")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void putRelationship(@HeaderParam("X-TransactionId") String transactionId,
            @HeaderParam("X-FromAppId") String fromApp, @HeaderParam("Authorization") String authorization,
            @PathParam("cloud_owner") String cloud_owner, @PathParam("cloud_region_id") String cloud_region_id,
            Relationship relationship) throws ExtsysException;
    
    @GET
    @Path("/complexes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String queryComplexList(@HeaderParam("X-TransactionId") String transactionId,
            @HeaderParam("X-FromAppId") String fromApp, @HeaderParam("Authorization") String authorization)
            throws ExtsysException;
}
