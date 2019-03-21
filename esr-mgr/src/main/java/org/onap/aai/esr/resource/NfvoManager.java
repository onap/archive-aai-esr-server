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
package org.onap.aai.esr.resource;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.jetty.http.HttpStatus;
import org.onap.aai.esr.entity.rest.NfvoRegisterInfo;
import org.onap.aai.esr.util.ExtsysUtil;
import org.onap.aai.esr.wrapper.NfvoManagerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Path("/nfvos")
@Api(tags = {" nfvo Management "})
public class NfvoManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(NfvoManager.class);

    private static ExtsysUtil extsysUtil = new ExtsysUtil();

    /**
     * query all nfvo.
     */
    @GET
    @ApiOperation(value = "get  all nfvo ")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response queryNfvoList() {
        LOGGER.info("start query all nfvo!");
        return NfvoManagerWrapper.getInstance().queryNfvoList();
    }

    /**
     * query nfvo by id.
     */
    @Path("/{nfvoId}")
    @GET
    @ApiOperation(value = "get nfvo by id")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response queryNfvoById(@ApiParam(value = "nfvo id") @PathParam("nfvoId") String nfvoId) {
        LOGGER.info("start query  nfvo by id." + nfvoId);
        return NfvoManagerWrapper.getInstance().queryNfvoById(nfvoId);
    }

    /**
     * delete nfvo by id.
     */
    @Path("/{nfvoId}")
    @DELETE
    @ApiOperation(value = "delete a nfvo")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response delNfvo(@ApiParam(value = "nfvo id") @PathParam("nfvoId") String nfvoId) {
        LOGGER.info("start delete nfvo .id:" + nfvoId);
        return NfvoManagerWrapper.getInstance().delNfvo(nfvoId);
    }

    /**
     * update nfvo by id.
     */
    @PUT
    @Path("/{nfvoId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @ApiOperation(value = "update a nfvo")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response updateNfvo(@ApiParam(value = "nfvo", required = true) NfvoRegisterInfo nfvo,
            @ApiParam(value = "nfvo id", required = true) @PathParam("nfvoId") String nfvoId) {
        LOGGER.info("start update nfvo .id:" + nfvoId + " info:" + extsysUtil.objectToString(nfvo));
        return NfvoManagerWrapper.getInstance().updateNfvo(nfvo, nfvoId);
    }

    /**
     * add nfvo .
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @ApiOperation(value = "create a nfvo")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found", response = String.class),
            @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
                    message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
                    response = String.class)})
    @Timed
    public Response registerNfvo(@ApiParam(value = "nfvo", required = true) NfvoRegisterInfo nfvo) {
        return NfvoManagerWrapper.getInstance().registerNfvo(nfvo);
    }
}
