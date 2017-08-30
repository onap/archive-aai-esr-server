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
import org.onap.aai.esr.entity.aai.EmsData;
import org.onap.aai.esr.entity.rest.EmsRestData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.handle.EmsHandler;
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

@Path("/emses")
@Api(tags = {" ems Management "})
public class EmsManager {

  EmsHandler handler = new EmsHandler();
  private static final Logger LOGGER = LoggerFactory.getLogger(EmsManager.class);

  /**
   * query all ems.
   */
  @Path("")
  @GET
  @ApiOperation(value = "get  all ems ")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryEmsList() {
    LOGGER.info("start query all ems!");
    List<EmsData> list;
    try {
      list = handler.getAll();
    } catch (ExtsysException error) {
      LOGGER.error("query all ems failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    if (list == null || list.size() <= 0) {
      LOGGER.info("query all ems end.no match condition record");
      return RestResponseUtil.getSuccessResponse(new ArrayList<EmsRestData>());
    } else {
      LOGGER.info("query all ems end.size:" + list.size());
      ArrayList<EmsRestData> restList = new ArrayList<EmsRestData>();
      for (int i = 0; i < list.size(); i++) {
//        restList.add(new EmsRestData(list.get(i)));
        //TODO
      }
      return RestResponseUtil.getSuccessResponse(restList);
    }

  }
  
  /**
   * query  ems info by id.
   */
  @Path("/{emsId}")
  @GET
  @ApiOperation(value = "get ems by id")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response queryemsById(@ApiParam(value = "ems id") @PathParam("emsId") String emsId) {
    LOGGER.info("start query  ems by id." + emsId);
    List<EmsData> list;
    try {
      list = handler.getEmsById(emsId);
    } catch (ExtsysException error) {
      LOGGER.error("query  ems failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    if (list == null || list.size() <= 0) {
      LOGGER.info("query  ems end.no match condition record");
      return RestResponseUtil.getSuccessResponse(null);
    } else {
      LOGGER.info("query  ems end.info:" + ExtsysDbUtil.objectToString(list));
//      return RestResponseUtil.getSuccessResponse(new EmsRestData(list.get(0)));
      return RestResponseUtil.getSuccessResponse(new EmsRestData());
    }
  }
  
  /**
   * delete ems by id.
   */
  @Path("/{emsId}")
  @DELETE
  @ApiOperation(value = "delete a ems")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response delems(@ApiParam(value = "ems id") @PathParam("emsId") String emsId) {
    LOGGER.info("start delete ems .id:" + emsId);
    try {
      handler.delete(emsId);
    } catch (ExtsysException error) {
      LOGGER.error("delete ems failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" delete ems end !");
    return Response.noContent().build();
  }
  
  /**
   * update ems by id.
   */
  @PUT
  @Path("/{emsId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "update a ems")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response updateemss(@ApiParam(value = "ems", required = true) EmsData ems,
      @ApiParam(value = "ems id", required = true) @PathParam("emsId") String emsId) {
    LOGGER.info("start update ems .id:" + emsId + " info:" + ExtsysDbUtil.objectToString(ems));
    EmsData newData;
    try {
      newData = handler.update(ems, emsId);
    } catch (ExtsysException error) {
      LOGGER.error("update ems failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" update ems end !");
//    return RestResponseUtil.getSuccessResponse(new EmsRestData(newData));
    return RestResponseUtil.getSuccessResponse(new EmsRestData());
  }
  
  /**
   * add ems.
   */
  @POST
  @Path("")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
  @ApiOperation(value = "create a ems")
  @ApiResponses(value = {
      @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "microservice not found",
          response = String.class),
      @ApiResponse(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE_415,
          message = "Unprocessable MicroServiceInfo Entity ", response = String.class),
      @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "internal server error",
          response = String.class)})
  @Timed
  public Response addemss(@ApiParam(value = "ems", required = true) EmsData ems) {
    LOGGER.info("start add ems" + " info:" + ExtsysDbUtil.objectToString(ems));
    EmsData emsData;
    try {
      emsData = handler.add(ems);
    } catch (ExtsysException error) {
      LOGGER.error("add ems failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" add ems end !");
//    return RestResponseUtil.getCreateSussceeResponse(new EmsRestData(emsData));
    return RestResponseUtil.getCreateSussceeResponse(new EmsRestData());
  }
}
