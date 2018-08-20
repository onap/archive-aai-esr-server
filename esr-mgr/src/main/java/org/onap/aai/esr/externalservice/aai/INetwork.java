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
package org.onap.aai.esr.externalservice.aai;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.onap.aai.esr.entity.aai.Pnf;
import org.onap.aai.esr.exception.ExtsysException;

@Path("/")
public interface INetwork {
    @PUT
    @Path("/pnfs/pnf/{pnfName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void registerPnfService(@HeaderParam("X-TransactionId") String transactionId,
            @HeaderParam("X-FromAppId") String fromApp, @HeaderParam("Authorization") String authorization,
            @PathParam("pnfName") String pnfName, Pnf pnf) throws ExtsysException;
}
