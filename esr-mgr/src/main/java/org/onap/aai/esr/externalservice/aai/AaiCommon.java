/**
 * Copyright 2017 ZTE Corporation.
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

public class AaiCommon {

    private static String AAI_AUTHENTICATION_USER = "AAI";
    private static String AAI_AUTHENTICATION_PAASWORD = "AAI";

    public static String getAuthenticationCredentials() {
        String usernameAndPassword = AAI_AUTHENTICATION_USER + ":" + AAI_AUTHENTICATION_PAASWORD;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(usernameAndPassword.getBytes());
    }

}
