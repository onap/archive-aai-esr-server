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
import org.onap.aai.esr.entity.aai.VimData;
import org.onap.aai.esr.entity.rest.VimRestData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.handle.VimHandler;
import org.onap.aai.esr.util.ExtsysUtil;
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

@Path("/vims")
@Api(tags = {" vim Management "})
public class VimManager {

  VimHandler handler = new VimHandler();
  private static final Logger LOGGER = LoggerFactory.getLogger(VimManager.class);

  /**
   * query all VIM.
   */
  @Path("")
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
  public Response queryvimList() {
    LOGGER.info("start query all vim!");
    List<VimData> list;
    try {
      list = handler.getAll();
    } catch (ExtsysException error) {
      LOGGER.error("query all vim failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    if (list == null || list.size() <= 0) {
      LOGGER.info("query all vim end.no match condition record");
      return RestResponseUtil.getSuccessResponse(new ArrayList<VimRestData>());
    } else {
      LOGGER.info("query all vim end.size:" + list.size());
      ArrayList<VimRestData> restList = new ArrayList<VimRestData>();
      for (int i = 0; i < list.size(); i++) {
//        restList.add(new VimRestData(list.get(i)));
        restList.add(new VimRestData());
      }
      return RestResponseUtil.getSuccessResponse(restList);
    }

  }

  /**
   * query vim by id.
   */
  @Path("/{vimId}")
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
  public Response queryvimById(@ApiParam(value = "vim id") @PathParam("vimId") String vimId) {
    LOGGER.info("start query  vim by id." + vimId);
    List<VimData> list;
    try {
      list = handler.getVimById(vimId);
    } catch (ExtsysException error) {
      LOGGER.error("query  vim failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    if (list == null || list.size() <= 0) {
      LOGGER.info("query  vim end.no match condition record");
      return RestResponseUtil.getSuccessResponse(null);
    } else {
      LOGGER.info("query  vim end.info:" + ExtsysUtil.objectToString(list));
//      return RestResponseUtil.getSuccessResponse(new VimRestData(list.get(0)));
      return RestResponseUtil.getSuccessResponse(new VimRestData());
    }
  }
  
  /**
   * delete vim by id.
   */
  @Path("/{vimId}")
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
  public Response delvim(@ApiParam(value = "vim id") @PathParam("vimId") String vimId) {
    LOGGER.info("start delete vim .id:" + vimId);
    try {
      handler.delete(vimId);
    } catch (ExtsysException error) {
      LOGGER.error("delete vim failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" delete vim end !");
    return Response.noContent().build();
  }
  
  /**
   * update vim by id.
   */
  @PUT
  @Path("/{vimId}")
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
  public Response updatevims(@ApiParam(value = "vim", required = true) VimData vim,
      @ApiParam(value = "vim id", required = true) @PathParam("vimId") String vimId) {
    LOGGER.info("start update vim .id:" + vimId + " info:" + ExtsysUtil.objectToString(vim));
    VimData newData;
    try {
      newData = handler.update(vim, vimId);
    } catch (ExtsysException error) {
      LOGGER.error("update vim failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" update vim end !");
//    return RestResponseUtil.getSuccessResponse(new VimRestData(newData));
    return RestResponseUtil.getSuccessResponse(new VimRestData());
  }
  
  /**
   * add vim .
   */
  @POST
  @Path("")
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
  public Response addvims(@ApiParam(value = "vim", required = true) VimData vim) {
    LOGGER.info("start add vim" + " info:" + ExtsysUtil.objectToString(vim));
    VimData vimData;
    try {
      vimData = handler.add(vim);
    } catch (ExtsysException error) {
      LOGGER.error("add vim failed.errorMsg:" + error.getErrorMsg());
      return RestResponseUtil.getErrorResponse(error);
    }
    LOGGER.info(" add vim end !");
//    return RestResponseUtil.getCreateSussceeResponse(new VimRestData(vimData));
    return RestResponseUtil.getCreateSussceeResponse(new VimRestData());
  }
}
