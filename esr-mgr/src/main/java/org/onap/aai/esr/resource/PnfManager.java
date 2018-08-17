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
package org.onap.aai.esr.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.onap.aai.esr.entity.rest.PnfRegisterInfo;
import org.onap.aai.esr.util.ExtsysUtil;
import org.onap.aai.esr.wrapper.PnfManagerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/pnfs")
@Api(tags = {" PNF Management "})
public class PnfManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PnfManager.class);

    private static ExtsysUtil extsysUtil = new ExtsysUtil();
    
    /**
     * query all pnf.
     */
    @GET
    @ApiOperation(value = "get  all pnf ")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response queryPnfList() {
        LOGGER.info("start query all pnf!");
        return PnfManagerWrapper.getInstance().queryPnfList();
    }
    
    /**
     * query pnf by id.
     */
    @Path("/{pnfId}")
    @GET
    @ApiOperation(value = "get pnf by id")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response queryPnfById(@ApiParam(value = "pnf id") @PathParam("pnfId") String pnfId) {
        LOGGER.info("start query  pnf by id." + pnfId);
        return PnfManagerWrapper.getInstance().queryPnfById(pnfId);
    }
    
    /**
     * delete pnf by id.
     */
    @Path("/{pnfId}")
    @DELETE
    @ApiOperation(value = "delete a pnf")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response delPnf(@ApiParam(value = "pnf id") @PathParam("pnfId") String pnfId) {
        LOGGER.info("start delete pnf .id:" + pnfId);
        return PnfManagerWrapper.getInstance().delPnf(pnfId);
    }
    
    /**
     * update pnf by id.
     */
    @PUT
    @Path("/{pnfId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @ApiOperation(value = "update a pnf")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response updatePnf(@ApiParam(value = "pnf", required = true) PnfRegisterInfo pnf,
            @ApiParam(value = "pnf id", required = true) @PathParam("pnfId") String pnfId) {
        LOGGER.info("start update pnf .id:" + pnfId + " info:" + extsysUtil.objectToString(pnf));
        return PnfManagerWrapper.getInstance().updatePnf(pnf, pnfId);
    }
    
    /**
     * add pnf .
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @ApiOperation(value = "create a pnf")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response registerPnf(@ApiParam(value = "pnf", required = true) PnfRegisterInfo pnf) {
        return PnfManagerWrapper.getInstance().registerPnf(pnf);
    }
}
