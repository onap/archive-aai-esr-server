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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.jetty.http.HttpStatus;
import org.onap.aai.esr.entity.rest.VimRegisterInfo;
import org.onap.aai.esr.util.ExtsysUtil;
import org.onap.aai.esr.wrapper.VimManagerWrapper;
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

@Path("/vims")
@Api(tags = {" vim Management "})
public class VimManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(VimManager.class);

  /**
   * query all VIM.
   */
  @GET
  @ApiOperation(value = "get  all vim ")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryVimList() {
    return VimManagerWrapper.getInstance().queryVimListDetails();
  }

  /**
   * query vim by id.
   */
  @Path("/{cloudOwner}/{cloudRegionId}")
  @GET
  @ApiOperation(value = "get vim by id")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryVimById(@PathParam("cloudOwner") String cloudOwner, @PathParam("cloudRegionId") String cloudRegionId) {
    LOGGER.info("start query vim by cloud owner and cloud region id." + cloudOwner +"," + cloudRegionId);
    return VimManagerWrapper.getInstance().queryVimById(cloudOwner, cloudRegionId);
  }
  
  /**
   * delete vim by id.
   */
  @Path("/{cloudOwner}/{cloudRegionId}")
  @DELETE
  @ApiOperation(value = "delete a vim")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response delvim(@PathParam("cloudOwner") String cloudOwner,@PathParam("cloudRegionId") String cloudRegionId) {
    LOGGER.info("start delete cloud-owner :" + cloudOwner +", cloud-region-id: " + cloudRegionId);
    return VimManagerWrapper.getInstance().delVim(cloudOwner, cloudRegionId);
  }
  
  /**
   * update vim by id.
   */
  @PUT
  @Path("/{cloudOwner}/{cloudRegionId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "update a vim")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response updatevims(@PathParam("cloudOwner") String cloudOwner, @PathParam("cloudRegionId") String cloudRegionId, VimRegisterInfo vim) {
    LOGGER.info("start update vim info:" + ExtsysUtil.objectToString(vim));
    return VimManagerWrapper.getInstance().updateVim(cloudOwner, cloudRegionId, vim);
  }
  
  /**
   * register vim .
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "create a vim")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response registerVims(VimRegisterInfo vim) {
    LOGGER.info("start add vim" + " info:" + ExtsysUtil.objectToString(vim));
    return VimManagerWrapper.getInstance().registerVim(vim);
  }
}
