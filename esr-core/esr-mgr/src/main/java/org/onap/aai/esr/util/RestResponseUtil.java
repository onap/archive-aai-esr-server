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
package org.onap.aai.esr.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class RestResponseUtil {
  
  /**
   * get http success entity.
   */
  public static Response getSuccessResponse(Object obj) {
    if (obj != null) {
      return Response.ok(obj).build();
    } else {
      return Response.ok().build();
    }
  }

  public static Response getCreateSussceeResponse(Object obj) {
    return Response.status(Status.CREATED).entity(obj).build();
  }
  
  /**
   * get http error entity.
   */
  public static Response getErrorResponse(Object obj) {
    if (obj != null) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(obj).build();
    } else {
      return Response.serverError().build();
    }

  }
}
