/**
 * Copyright 2016 ZTE Corporation.
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
import org.onap.aai.esr.entity.db.SdncData;
import org.onap.aai.esr.entity.rest.SdncRestData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.handle.SdncHandler;
import org.onap.aai.esr.util.ExtsysDbUtil;
import org.onap.aai.esr.util.RestResponseUtil;
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

@Path("/sdncontrollers")
@Api(tags = {" sdnc Management     "})
public class SdncManager {

  SdncHandler handler = new SdncHandler();
  private static final Logger LOGGER = LoggerFactory.getLogger(SdncManager.class);

  /**
   *query all sdnc.
   */
  @Path("")
  @GET
  @ApiOperation(value = "get  all sdnc ")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response querysdncList() {
    LOGGER.info("start query all sdnc!");
    List<SdncData> list;
    try {
      list = handler.getAll();
    } catch (ExtsysException error) {
      LOGGER.error("query all sdnc failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    if (list == null || list.size() <= 0) {
      LOGGER.info("query all sdnc end.no match condition record");
      return RestResponseUtil.getSuccessResponse(new ArrayList<SdncRestData>());
    } else {
      LOGGER.info("query all sdnc end.size:" + list.size());
      ArrayList<SdncRestData> restList = new ArrayList<SdncRestData>();
      for (int i = 0; i < list.size(); i++) {
        restList.add(new SdncRestData(list.get(i)));
      }
      return RestResponseUtil.getSuccessResponse(restList);
    }

  }
  
  /**
   *query  sdnc by id.
   */
  @Path("/{sdncId}")
  @GET
  @ApiOperation(value = "get sdnc by id")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response querysdncById(@ApiParam(value = "sdnc id") @PathParam("sdncId") String sdncId) {
    LOGGER.info("start query  sdnc by id." + sdncId);
    List<SdncData> list;
    try {
      list = handler.getSdncById(sdncId);
    } catch (ExtsysException error) {
      LOGGER.error("query  sdnc failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    if (list == null || list.size() <= 0) {
      LOGGER.info("query  sdnc end.no match condition record");
      return RestResponseUtil.getSuccessResponse(null);
    } else {
      LOGGER.info("query  sdnc end.info:" + ExtsysDbUtil.objectToString(list));
      return RestResponseUtil.getSuccessResponse(new SdncRestData(list.get(0)));
    }
  }
  
  /**
   *delete  sdnc by id.
   */
  @Path("/{sdncId}")
  @DELETE
  @ApiOperation(value = "delete a sdnc")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response delsdnc(@ApiParam(value = "sdnc id") @PathParam("sdncId") String sdncId) {
    LOGGER.info("start delete sdnc .id:" + sdncId);
    try {
      handler.delete(sdncId);
    } catch (ExtsysException error) {
      LOGGER.error("delete sdnc failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" delete sdnc end !");
    return Response.noContent().build();
  }
  
  /**
   *update sdnc by id.
   */
  @PUT
  @Path("/{sdncId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "update a sdnc")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response updatesdncs(@ApiParam(value = "sdnc", required = true) SdncData sdnc,
      @ApiParam(value = "sdnc id", required = true) @PathParam("sdncId") String sdncId) {
    LOGGER.info("start update sdnc .id:" + sdncId + " info:" + ExtsysDbUtil.objectToString(sdnc));
    SdncData newData;
    try {
      newData = handler.update(sdnc, sdncId);
    } catch (ExtsysException error) {
      LOGGER.error("update sdnc failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" update sdnc end !");
    return RestResponseUtil.getSuccessResponse(new SdncRestData(newData));
  }
  
  /**
   *add  sdnc.
   */
  @POST
  @Path("")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "create a sdnc")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response addsdncs(@ApiParam(value = "sdnc", required = true) SdncData sdnc) {
    LOGGER.info("start add sdnc" + " info:" + ExtsysDbUtil.objectToString(sdnc));
    SdncData sdncData;
    try {
      sdncData = handler.add(sdnc);
    } catch (ExtsysException error) {
      LOGGER.error("add sdnc failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" add sdnc end !");
    return RestResponseUtil.getCreateSussceeResponse(new SdncRestData(sdncData));
  }
}
