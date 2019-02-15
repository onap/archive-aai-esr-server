.. This work is licensed under a Creative Commons Attribution 4.0 International License.

New Features
------------

ONAP AAI-ESR items for: (Last Updated: 10/12/2017)

*    **Version**: Amsterdam Release
*    **Release Date**: 02 November, 2017
*    **Description**: R1
  
Story
^^^^^

* `[AAI-64] <https://jira.onap.org/browse/AAI-64>`_ - Move the seed code from Open-O to ONAP
* `[AAI-65] <https://jira.onap.org/browse/AAI-65>`_ - Realize the EMS register portal
* `[AAI-66] <https://jira.onap.org/browse/AAI-66>`_ - Change mysql data store backend to AAI
* `[AAI-77] <https://jira.onap.org/browse/AAI-77>`_ - Update POM to inherit from oparent
* `[AAI-187] <https://jira.onap.org/browse/AAI-187>`_ - Add docker build function for esr-server
* `[AAI-188] <https://jira.onap.org/browse/AAI-188>`_ - Add docker build function for esr-gui
* `[AAI-204] <https://jira.onap.org/browse/AAI-204>`_ - Add EMS/VNFM/SDNC register schema to A&AI
* `[AAI-282] <https://jira.onap.org/browse/AAI-282>`_ - Fix the critical sonar issue about esr-server
* `[AAI-284] <https://jira.onap.org/browse/AAI-284>`_ - Realize the data transaction between ESR rest and A&AI API
* `[AAI-303] <https://jira.onap.org/browse/AAI-303>`_ - Realize the VNFM management API
* `[AAI-304] <https://jira.onap.org/browse/AAI-304>`_ - Realize the EMS management API
* `[AAI-305] <https://jira.onap.org/browse/AAI-305>`_ - Realize the Thirdparty SDNC management API
* `[AAI-330] <https://jira.onap.org/browse/AAI-330>`_ - Add CSIT usecase for esr-server
* `[AAI-331] <https://jira.onap.org/browse/AAI-331>`_ - Add UNIT test for esr-server
* `[AAI-345] <https://jira.onap.org/browse/AAI-345>`_ - Integrate esr-server with esr-gui
* `[AAI-378] <https://jira.onap.org/browse/AAI-378>`_ - Add the document files for ESR
* `[AAI-427] <https://jira.onap.org/browse/AAI-427>`_ - Add document details for ESR

Task
^^^^

* `[AAI-67] <https://jira.onap.org/browse/AAI-67>`_ - Upload the server-end seed code to ONAP repository
* `[AAI-68] <https://jira.onap.org/browse/AAI-68>`_ - Upload the ESR gui seed code to ONAP repository
* `[AAI-69] <https://jira.onap.org/browse/AAI-69>`_ - Remove the original SDNC related function from Amsterdan release.
* `[AAI-70] <https://jira.onap.org/browse/AAI-70>`_ - Collect the schema of ESR for ONAP in Amsterdam release
* `[AAI-137] <https://jira.onap.org/browse/AAI-137>`_ - Setup A&AI Developer Environment
* `[AAI-138] <https://jira.onap.org/browse/AAI-138>`_ - Add CI task for ESR
* `[AAI-143] <https://jira.onap.org/browse/AAI-143>`_ - Remove the openo tag from esr-gui
* `[AAI-144] <https://jira.onap.org/browse/AAI-144>`_ - remove the openo tag from esr-server repository
* `[AAI-145] <https://jira.onap.org/browse/AAI-145>`_ - Fix the license of esr-server according to the ONAP license rule
* `[AAI-166] <https://jira.onap.org/browse/AAI-166>`_ - Add auth-info node for cloud-region to support VoLTE
* `[AAI-181] <https://jira.onap.org/browse/AAI-181>`_ - Add verification for the input data of SDNC portal
* `[AAI-184] <https://jira.onap.org/browse/AAI-184>`_ - Modify the VIM register card and property name
* `[AAI-185] <https://jira.onap.org/browse/AAI-185>`_ - Change the way of input cloud-region and version
* `[AAI-186] <https://jira.onap.org/browse/AAI-186>`_ - Add check function for cloud-owner and cloud-region-id
* `[AAI-234] <https://jira.onap.org/browse/AAI-234>`_ - Remove the lombok dependency
* `[AAI-235] <https://jira.onap.org/browse/AAI-235>`_ - remove the mysql-connector-java dependency
* `[AAI-240] <https://jira.onap.org/browse/AAI-240>`_ - Modify the properties of ESR rest API
* `[AAI-243] <https://jira.onap.org/browse/AAI-243>`_ - Define the REST API of EMS according to the new data model
* `[AAI-250] <https://jira.onap.org/browse/AAI-250>`_ - Define the Rest API of thirdparty sdnc according to the new data model
* `[AAI-251] <https://jira.onap.org/browse/AAI-251>`_ - Define the Rest API of VIM register according to the new data model
* `[AAI-256] <https://jira.onap.org/browse/AAI-256>`_ - Remove the database related code
* `[AAI-261] <https://jira.onap.org/browse/AAI-261>`_ - Add the API definition of A&AI to esr
* `[AAI-262] <https://jira.onap.org/browse/AAI-262>`_ - clean the esr-server parent pom
* `[AAI-263] <https://jira.onap.org/browse/AAI-263>`_ - Define the A&AI VIM register API in esr-server
* `[AAI-269] <https://jira.onap.org/browse/AAI-269>`_ - Simplify esr-server project level
* `[AAI-275] <https://jira.onap.org/browse/AAI-275>`_ - Add a switch for register to MSB by java-sdk
* `[AAI-283] <https://jira.onap.org/browse/AAI-283>`_ - Define the entities of external system.
* `[AAI-285] <https://jira.onap.org/browse/AAI-285>`_ - Realize the data transaction between ESR rest and A&AI API of VIM
* `[AAI-286] <https://jira.onap.org/browse/AAI-286>`_ - Realize the data transaction between ESR rest and A&AI API of VNFM
* `[AAI-287] <https://jira.onap.org/browse/AAI-287>`_ - Realize the data transaction between ESR rest and A&AI API of EMS
* `[AAI-288] <https://jira.onap.org/browse/AAI-288>`_ - Adjust the entity used in esr-server API
* `[AAI-289] <https://jira.onap.org/browse/AAI-289>`_ - There is some issue about external system schema
* `[AAI-291] <https://jira.onap.org/browse/AAI-291>`_ - Add tomcat as dependency of esr-gui
* `[AAI-294] <https://jira.onap.org/browse/AAI-294>`_ - Realize the data transaction between ESR rest and A&AI API of VNFM
* `[AAI-299] <https://jira.onap.org/browse/AAI-299>`_ - Fix the esr-server register to MSB issue
* `[AAI-300] <https://jira.onap.org/browse/AAI-300>`_ - Realize the function of register VIM and query VIM detail
* `[AAI-301] <https://jira.onap.org/browse/AAI-301>`_ - Realize the function of query VIM information list
* `[AAI-302] <https://jira.onap.org/browse/AAI-302>`_ - Realize update registered VIM
* `[AAI-310] <https://jira.onap.org/browse/AAI-310>`_ - Realize VNFM register API
* `[AAI-311] <https://jira.onap.org/browse/AAI-311>`_ - Realize the VNFM detail query API
* `[AAI-312] <https://jira.onap.org/browse/AAI-312>`_ - Realize the VNFM list query API
* `[AAI-313] <https://jira.onap.org/browse/AAI-313>`_ - Realize the VNFM delete API
* `[AAI-314] <https://jira.onap.org/browse/AAI-314>`_ - Realize the update VNFM API
* `[AAI-315] <https://jira.onap.org/browse/AAI-315>`_ - Realize register thirdparty sdnc API
* `[AAI-316] <https://jira.onap.org/browse/AAI-316>`_ - Realize query thirdparty SDNC detail API
* `[AAI-317] <https://jira.onap.org/browse/AAI-317>`_ - Realize query thirdparty SDNC list API
* `[AAI-318] <https://jira.onap.org/browse/AAI-318>`_ - Realize update thirdparty SDNC API
* `[AAI-319] <https://jira.onap.org/browse/AAI-319>`_ - Realize delete thirdparty SDNC API
* `[AAI-320] <https://jira.onap.org/browse/AAI-320>`_ - Realize EMS register API
* `[AAI-321] <https://jira.onap.org/browse/AAI-321>`_ - Realize query EMS details API
* `[AAI-322] <https://jira.onap.org/browse/AAI-322>`_ - Realize query all EMS details API
* `[AAI-323] <https://jira.onap.org/browse/AAI-323>`_ - Realize update EMS API
* `[AAI-324] <https://jira.onap.org/browse/AAI-324>`_ - Realize delete EMS API
* `[AAI-332] <https://jira.onap.org/browse/AAI-332>`_ - Fix the data structure of vim register data in esr-server
* `[AAI-333] <https://jira.onap.org/browse/AAI-333>`_ - Fix the issue "Can not add esr portal file to tomcat"
* `[AAI-334] <https://jira.onap.org/browse/AAI-334>`_ - Add VIM status to VIM management
* `[AAI-335] <https://jira.onap.org/browse/AAI-335>`_ - Add a testcase to test whether the service is reachable.
* `[AAI-336] <https://jira.onap.org/browse/AAI-336>`_ - Add the setup service CSIT test case
* `[AAI-337] <https://jira.onap.org/browse/AAI-337>`_ - Add jjb to add the esr-server CSIT task
* `[AAI-340] <https://jira.onap.org/browse/AAI-340>`_ - Add unit test function for ExtsysApp class
* `[AAI-341] <https://jira.onap.org/browse/AAI-341>`_ - Add unit test for cloudRegion bean class
* `[AAI-343] <https://jira.onap.org/browse/AAI-343>`_ - Add unit test for ems bean class
* `[AAI-344] <https://jira.onap.org/browse/AAI-344>`_ - Add unit test for esr system info bean
* `[AAI-346] <https://jira.onap.org/browse/AAI-346>`_ - Call the VIM API from esr-server
* `[AAI-347] <https://jira.onap.org/browse/AAI-347>`_ - Integrate esr-gui with server end for EMS
* `[AAI-348] <https://jira.onap.org/browse/AAI-348>`_ - Integrate esr-gui with server end for VNFM
* `[AAI-349] <https://jira.onap.org/browse/AAI-349>`_ - Integrate esr-gui with server end for thirdparty SDNC
* `[AAI-350] <https://jira.onap.org/browse/AAI-350>`_ - Add unit test for thirdparty sdnc bean
* `[AAI-351] <https://jira.onap.org/browse/AAI-351>`_ - Add unit test for vnfm bean class
* `[AAI-352] <https://jira.onap.org/browse/AAI-352>`_ - Add unit test for vnfm register info
* `[AAI-353] <https://jira.onap.org/browse/AAI-353>`_ - Add unit test for ems register info
* `[AAI-354] <https://jira.onap.org/browse/AAI-354>`_ - Add unit test for vim register info
* `[AAI-355] <https://jira.onap.org/browse/AAI-355>`_ - Add unit test for thirdparty sdnc register info
* `[AAI-358] <https://jira.onap.org/browse/AAI-358>`_ - Change util method from static to unstatic
* `[AAI-359] <https://jira.onap.org/browse/AAI-359>`_ - Fix the return data of del and query list API
* `[AAI-360] <https://jira.onap.org/browse/AAI-360>`_ - Realize delete vim API
* `[AAI-361] <https://jira.onap.org/browse/AAI-361>`_ - Update VIM after register finished.
* `[AAI-362] <https://jira.onap.org/browse/AAI-362>`_ - Clean the Config files
* `[AAI-363] <https://jira.onap.org/browse/AAI-363>`_ - Add unit test for app configuration
* `[AAI-365] <https://jira.onap.org/browse/AAI-365>`_ - change the objectToString method to un-static
* `[AAI-366] <https://jira.onap.org/browse/AAI-366>`_ - Add unit test for ExtsysUtil
* `[AAI-367] <https://jira.onap.org/browse/AAI-367>`_ - Add unit test case for EmsManagerUtil
* `[AAI-368] <https://jira.onap.org/browse/AAI-368>`_ - Add unit test case for SDNC register util
* `[AAI-369] <https://jira.onap.org/browse/AAI-369>`_ - Fix the VNFM register issue
* `[AAI-370] <https://jira.onap.org/browse/AAI-370>`_ - Change the static method in VIM to un-static
* `[AAI-371] <https://jira.onap.org/browse/AAI-371>`_ - Change the exception deal way for VIM register
* `[AAI-375] <https://jira.onap.org/browse/AAI-375>`_ - Add unit test case for VIM register utils
* `[AAI-376] <https://jira.onap.org/browse/AAI-376>`_ - Add unit test for vnfm register utils
* `[AAI-379] <https://jira.onap.org/browse/AAI-379>`_ - Add the document files for esr-server
* `[AAI-385] <https://jira.onap.org/browse/AAI-385>`_ - Add document files for esr-gui
* `[AAI-391] <https://jira.onap.org/browse/AAI-391>`_ - The esr related file should be packed in one category.
* `[AAI-392] <https://jira.onap.org/browse/AAI-392>`_ - Fix the parameter name of external system register in portal
* `[AAI-399] <https://jira.onap.org/browse/AAI-399>`_ - Add csit usecase for external system vnfm
* `[AAI-402] <https://jira.onap.org/browse/AAI-402>`_ - Remove the unused thirdparty code
* `[AAI-404] <https://jira.onap.org/browse/AAI-404>`_ - add csit uscase for thirdparty sdnc operation
* `[AAI-405] <https://jira.onap.org/browse/AAI-405>`_ - Add csit usecase for external system ems
* `[AAI-406] <https://jira.onap.org/browse/AAI-406>`_ - add csit usecase for external system (VIM)
* `[AAI-421] <https://jira.onap.org/browse/AAI-421>`_ - Add esr vm init script in demo project
* `[AAI-429] <https://jira.onap.org/browse/AAI-429>`_ - Increase Junit coverage
* `[AAI-430] <https://jira.onap.org/browse/AAI-430>`_ - Add swagger.json to main/resources

