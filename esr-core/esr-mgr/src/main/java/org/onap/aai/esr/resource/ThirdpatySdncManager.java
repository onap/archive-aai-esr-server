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
import org.onap.aai.esr.entity.rest.ThirdPartySdncRestData;
import org.onap.aai.esr.handle.SdncHandler;
import org.onap.aai.esr.util.ExtsysUtil;
import org.onap.aai.esr.wrapper.ThirdpatySdncWrapper;
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

@Path("/sdncontrollers")
@Api(tags = {"ThirdParty sdnc Management     "})
public class ThirdpatySdncManager {

  SdncHandler handler = new SdncHandler();
  private static final Logger LOGGER = LoggerFactory.getLogger(ThirdpatySdncManager.class);

  /**
   *query all thirdParty sdnc.
   */
  @Path("")
  @GET
  @ApiOperation(value = "get all thirdParty sdnc ")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryThirdpartySdncList() {
    LOGGER.info("start query all thirdParty sdnc!");
    return ThirdpatySdncWrapper.getInstance().queryThirdpartySdncList();
  }
  
  /**
   *query thirdParty sdnc by id.
   */
  @Path("/{thirdPartySdncId}")
  @GET
  @ApiOperation(value = "get thirdParty sdnc by id")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryThirdpartySdncById(@ApiParam(value = "thirdparty sdnc id") @PathParam("thirdPartySdncId") String thirdPartySdncId) {
    LOGGER.info("start query thirdparty sdnc by id." + thirdPartySdncId);
    return ThirdpatySdncWrapper.getInstance().queryThirdpartySdncById(thirdPartySdncId);
  }
  
  /**
   *delete thirdparty sdnc by id.
   */
  @Path("/{thirdPartySdncId}")
  @DELETE
  @ApiOperation(value = "delete a thirdparty sdnc")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response delThirdpartySdnc(@ApiParam(value = "thirdparty sdnc id") @PathParam("thirdPartySdncId") String thirdPartySdncId) {
    LOGGER.info("start delete thirdparty sdnc .id:" + thirdPartySdncId);
    return ThirdpatySdncWrapper.getInstance().delThirdpartySdnc(thirdPartySdncId);
  }
  
  /**
   *update thirdParty sdnc by id.
   */
  @PUT
  @Path("/{thirdPartySdncId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "update a thirdParty Sdnc")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response updateThirdpartySdnc(@ApiParam(value = "thirdpartySdnc", required = true) ThirdPartySdncRestData thirdPartySdnc,
      @ApiParam(value = "sdnc id", required = true) @PathParam("thirdPartySdncId") String thirdPartySdncId) {
    LOGGER.info("start update sdnc .id:" + thirdPartySdncId + " info:" + ExtsysUtil.objectToString(thirdPartySdnc));
    return ThirdpatySdncWrapper.getInstance().updateThirdpartySdnc(thirdPartySdnc);
  }
  
  /**
   *thirdParty sdnc register.
   */
  @POST
  @Path("")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "register a thirdparty sdnc")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response registerThirdpatySdnc(@ApiParam(value = "thirdPartySdnc", required = true) ThirdPartySdncRestData thirdPartySdnc) {
    LOGGER.info("start register sdnc" + " info:" + ExtsysUtil.objectToString(thirdPartySdnc));
    return ThirdpatySdncWrapper.getInstance().registerThirdpartySdnc(thirdPartySdnc);
  }
}
