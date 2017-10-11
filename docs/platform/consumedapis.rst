.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0

Consumed APIs
-------------

In the Amsterdam release, Holmes does not consume any APIs provided by other ONAP components.

A&AI
^^^^

ESR stored external system data to A&AI. EMS/VNFM/Third-party SDNC operation API included in external system namespace. VIM operation API included in CloudInfrastructure namespace.

#. VIM management API:

   ``PUT /cloud-infrastructure/cloud-regions/cloud-region/{cloud-owner}/{cloud-region-id}``
   
   ``GET /cloud-infrastructure/cloud-regions/cloud-region/{cloud-owner}/{cloud-region-id}``

   ``GET /cloud-infrastructure/cloud-regions``
   
   ``DELETE /cloud-infrastructure/cloud-regions/cloud-region/{cloud-owner}/{cloud-region-id}``
   
#. VNFM management API:

   ``PUT /external-system/esr-vnfm-list/esr-vnfm/{vnfm-id}``
   
   ``GET /external-system/esr-vnfm-list/esr-vnfm/{vnfm-id}``

   ``GET /external-system/esr-vnfm-list``
   
   ``DELETE /external-system/esr-vnfm-list/esr-vnfm/{vnfm-id}``
   
#. EMS management API:

   ``PUT /external-system/esr-ems-list/esr-ems/{ems-id}``
   
   ``GET /external-system/esr-ems-list/esr-ems/{ems-id}``

   ``GET /external-system/esr-ems-list``
   
   ``DELETE /external-system/esr-ems-list/esr-ems/{ems-id}``
   
#. Thirdparty SDNC management API:

   ``PUT /external-system/esr-thirdparty-sdnc-list/esr-thirdparty-sdnc/{thirdparty-sdnc-id}``
   
   ``GET /external-system/esr-thirdparty-sdnc-list/esr-thirdparty-sdnc/{thirdparty-sdnc-id}``

   ``GET /external-system/esr-thirdparty-sdnc-list``
   
   ``DELETE /external-system/esr-thirdparty-sdnc-list/esr-thirdparty-sdnc/{thirdparty-sdnc-id}``   

More details could be found at `A&AI APIs <https://wiki.onap.org/pages/viewpage.action?pageId=13598793>`_. 
   
Multi-Cloud
^^^^^^^^^^^

ESR will call the API from Multi-Cloud to update the cloud-region infomation after put the VIM data to A&AI.

#. Update VIM Info:

   ``POST /api/multicloud /v0/{cloud-owner}_{cloud-region}/registry``

More details could be found at `Multi-Cloud APIs <https://wiki.onap.org/download/attachments/13599038/MultiVIM-onap-draft-r1-0822.doc?version=1&modificationDate=1503406573000&api=v2>`_.
   
MSB
^^^

Both esr-server and esr-gui need to registered to MSB.

Service Registration: ``/api/microservices/v1/services``

More details could be found at `MSB APIs <https://wiki.onap.org/display/DW/Microservice+Bus+API+Documentation>`_.