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
package org.onap.aai.esr.entity.rest;

public class VimAuthInfo {

    private String cloudDomain;

    private String userName;

    private String password;

    private String authUrl;

    private String defaultTenant;

    private String sslCacert;

    private Boolean sslInsecure;

    public String getCloudDomain() {
        return cloudDomain;
    }

    public void setCloudDomain(String cloudDomain) {
        this.cloudDomain = cloudDomain;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getSslCacert() {
        return sslCacert;
    }

    public void setSslCacert(String sslCacert) {
        this.sslCacert = sslCacert;
    }

    public Boolean getSslInsecure() {
        return sslInsecure;
    }

    public void setSslInsecure(Boolean sslInsecure) {
        this.sslInsecure = sslInsecure;
    }

    public String getDefaultTenant() {
        return defaultTenant;
    }

    public void setDefaultTenant(String defaultTenant) {
        this.defaultTenant = defaultTenant;
    }

}
