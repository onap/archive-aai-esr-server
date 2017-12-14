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
package org.onap.aai.esr.externalservice.msb;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.MicroServiceInfo;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;

public class MsbHelperTest {

    MsbHelper helper;

    @Mock
    MSBServiceClient client;

    @Mock
    MicroServiceFullInfo serviceInfo;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        helper = new MsbHelper(client);
    }

    @Test
    public void testRegisterMsb() throws Exception {
        Mockito.when(client.registerMicroServiceInfo(Mockito.any(MicroServiceInfo.class),
                Mockito.anyBoolean())).thenReturn(serviceInfo);
        helper.registerMsb();
        Mockito.verify(client, Mockito.times(1)).
                registerMicroServiceInfo(Mockito.any(MicroServiceInfo.class), Mockito.anyBoolean());
    }
}
