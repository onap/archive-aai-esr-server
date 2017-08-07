--
-- Copyright 2016 ZTE Corporation.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

DROP TABLE IF EXISTS extsys_base_table;
CREATE TABLE extsys_base_table (
	ID                   VARCHAR(200)       NOT NULL,	
	NAME                    VARCHAR(200)       NULL,
	TYPE                    VARCHAR(200)       NULL,
	VENDOR                  VARCHAR(100)       NULL,
	VERSION                 VARCHAR(100)       NULL,
	DESCRIPTION             VARCHAR(100)       NULL,
    CREATETIME              VARCHAR(200)       NULL,
	CATAGORY                VARCHAR(200)       NULL,
    CONSTRAINT extsys_base_table PRIMARY KEY(ID)
);
DROP TABLE IF EXISTS extsys_ems_table;
CREATE TABLE extsys_ems_table (
	EMSID                   VARCHAR(200)       NOT NULL,
	URL                     VARCHAR(100)       NULL,
	PRODUCTNAME             VARCHAR(100)       NULL,
	USERNAME                VARCHAR(100)       NULL,
	PASSWORD                VARCHAR(100)       NULL,
    CONSTRAINT extsys_ems_table PRIMARY KEY(EMSID)
);

DROP TABLE IF EXISTS extsys_sdnc_table;
CREATE TABLE extsys_sdnc_table (
	SDNCONTROLLERID       VARCHAR(200)       NOT NULL,
	URL                     VARCHAR(100)       NULL,
	USERNAME                VARCHAR(100)       NULL,
	PASSWORD                VARCHAR(100)       NULL,
	PRODUCTNAME             VARCHAR(100)       NULL,
	PROTOCOL                VARCHAR(100)       NULL,
    CONSTRAINT extsys_sdnc_table PRIMARY KEY(SDNCONTROLLERID)
);

DROP TABLE IF EXISTS extsys_vim_table;
CREATE TABLE extsys_vim_table (
	VIMID                   VARCHAR(200)       NOT NULL,
	URL                     VARCHAR(100)       NULL,
	USERNAME                VARCHAR(100)       NULL,
	PASSWORD                VARCHAR(100)       NULL,
	DOMAIN                  VARCHAR(100)       NULL,
	TENANT                  VARCHAR(100)       NULL,
    CONSTRAINT extsys_vim_table PRIMARY KEY(VIMID)
);

DROP TABLE IF EXISTS extsys_vnfm_table;
CREATE TABLE extsys_vnfm_table (
	VNFMID                  VARCHAR(200)       NOT NULL,
	URL                     VARCHAR(200)       NULL,
	USERNAME                VARCHAR(100)       NULL,
	PASSWORD                VARCHAR(100)       NULL,
	VIMID                   VARCHAR(100)       NULL,
	CERTIFICATEURL          VARCHAR(200)       NULL,
    CONSTRAINT extsys_vnfm_table PRIMARY KEY(VNFMID)
);

