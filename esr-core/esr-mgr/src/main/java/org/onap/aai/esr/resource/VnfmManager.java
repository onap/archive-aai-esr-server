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
import org.onap.aai.esr.entity.aai.VnfmData;
import org.onap.aai.esr.entity.rest.VnfmRestData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.handle.VnfmHandler;
import org.onap.aai.esr.util.ExtsysUtil;
import org.onap.aai.esr.util.RestResponseUtil;
import org.onap.aai.esr.wrapper.VnfmManagerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
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

@Path("/vnfms")
@Api(tags = {" vnfm Management "})
public class VnfmManager {

  VnfmHandler handler = new VnfmHandler();
  private static final Logger LOGGER = LoggerFactory.getLogger(VnfmManager.class);

  /**
   * query all vnfm.
   */
  @Path("")
  @GET
  @ApiOperation(value = "get  all vnfm ")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryVnfmList() {
    LOGGER.info("start query all vnfm!");
    return VnfmManagerWrapper.getInstance().queryVnfmList();
  }
  
  /**
   * query  vnfm by id.
   */
  @Path("/{vnfmId}")
  @GET
  @ApiOperation(value = "get vnfm by id")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryVnfmById(@ApiParam(value = "vnfm id") @PathParam("vnfmId") String vnfmId) {
    LOGGER.info("start query  vnfm by id." + vnfmId);
    return VnfmManagerWrapper.getInstance().queryVnfmById(vnfmId);
  }
  
  /**
   * delete  vnfm by id.
   */
  @Path("/{vnfmId}")
  @DELETE
  @ApiOperation(value = "delete a vnfm")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response delVnfm(@ApiParam(value = "vnfm id") @PathParam("vnfmId") String vnfmId) {
    LOGGER.info("start delete vnfm .id:" + vnfmId);
    return VnfmManagerWrapper.getInstance().delVnfm(vnfmId);
  }
  
  /**
   * update  vnfm by id.
   */
  @PUT
  @Path("/{vnfmId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "update a vnfm")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response updateVnfm(@ApiParam(value = "vnfm", required = true) VnfmRestData vnfm,
      @ApiParam(value = "vnfm id", required = true) @PathParam("vnfmId") String vnfmId) {
    LOGGER.info("start update vnfm .id:" + vnfmId + " info:" + ExtsysUtil.objectToString(vnfm));
    return VnfmManagerWrapper.getInstance().updateVnfm(vnfm, vnfmId);
  }
  
  /**
   * add  vnfm .
   */
  @POST
  @Path("")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "create a vnfm")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response registerVnfm(@ApiParam(value = "vnfm", required = true) VnfmRestData vnfm) {
    return VnfmManagerWrapper.getInstance().registerVnfm(vnfm);
  }
}
