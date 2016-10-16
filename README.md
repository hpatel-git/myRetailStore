# myRetailStore
myRetailStore to retrieve product information and update product price online using REST API. Product information is available
from third party product store and myRetail Price Service will resposible for retrieval of Product price.

myRetailStore has used Micro Service Architecture. Below are list of micro services availabe into system
* myRetailAuth Server
* myRetail API Server
* myRetail Pricing Server

## myRetailAuth Server
   myRetailAuth Server uses `MySQL` database to store user information. myRetailAuth Server is Oauth2 Server. All product retrieval 
APIs are open API means no authetnication is required. In order to update product price , update product price API requires Oauth2
token with Administrator privileges

myRetailAuth Server Docker image is available on Docker Hub.

myRetailAuth Server is running on 8181 port with 'store-auth-server' as context. By default following users are available 
* User Name : **sysadmin** , Password : **sysadmin** , Role : ROLE_ADMIN
* User Name : **sysuser** , Password : **sysuser** , Role : ROLE_USER

Please find following URL to generate Administrator token to update product price in system :

```sh
curl acme:acmesecret@**<HOST_IP_ADDRESS>**:8181/store-auth-server/oauth/token -d grant_type=password -d username=sysadmin -d password=sysadmin
```
Sample Response : 

```sh
{
   "access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsic3RvcmUtb2F1dGgyLXJlc291cmNlIl0sInVzZXJfbmFtZSI6InN5c2FkbWluIiwic2NvcGUiOlsib3BlbmlkIl0sImV4cCI6MTQ3NjY4NjU4OCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlODNhOGYxNS01YzJiLTQ5ZmQtODgwYi1mYmYxZjA0Zjc3ZTUiLCJjbGllbnRfaWQiOiJhY21lIn0.mAG6A8oQex3okyJmiwy7TQwRJ1TG3JYbOThX3qpWcSJRgd-tNUYtSMuk0N2fS_khGQ08fHEfNePb7DoivQsPDk7ioHz7ROuyMAVbnf9pGPeuScXkxy6A3DmTHn7-LzIlMlv7rgBLAsHiSMm966VH427IJcmGYew7h3Lf3X4UVP_zvZSoqvxgKEfK7fBYG5ntHqHa7xWRfICygedWul-cLAZQvo3JU89Nt87foO0U0uacpHqBK8yeHXWuZAVOHnkb84NeE_FiN5lxVHKcE8d4J2xeCcaqY5ECS3AtgaPG24GPSdaXfH6RiJkeDOQXmHAVzgsvmQTaKVRk_Crrx9XqXw",
   "token_type":"bearer",
   "refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsic3RvcmUtb2F1dGgyLXJlc291cmNlIl0sInVzZXJfbmFtZSI6InN5c2FkbWluIiwic2NvcGUiOlsib3BlbmlkIl0sImF0aSI6ImU4M2E4ZjE1LTVjMmItNDlmZC04ODBiLWZiZjFmMDRmNzdlNSIsImV4cCI6MTQ3OTA1OTM1OCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlYjlkYmU5OC03M2E0LTQxNDUtODNhZi02Y2EyNmY2Zjc1ZjAiLCJjbGllbnRfaWQiOiJhY21lIn0.UibOlJ79qP_Qo20cyvHJE8mxxjaT_n1QyFRitt9il8uvVlvZFkxpz4u_1Rzb77RsbDbUNHp4DYrKMw-6ccghYW0t49MF7tKhjPHspsViCd0xbsepOBh_y8AYjurKGjrhxb4en6tctFY0bjIiFJzAeG19bV_HynRUMDUHkBRGzHD30vh5cjfubLxl0tKnFDQvFiaAIJI5thnOw0KMt_N8Wlz_OuwRcqdupZ9QK-IjHHO5eNHwpRGKmI3eLGxDsrQZ0emyLP3liY_1B-edFhHBAHEZ451y5zSbOTtSXLRTsY010UbGzLVZUG986CpdE4XZEkCYrLpDIRLp4YX8mMEfoA",
   "expires_in":43199,
   "scope":"openid",
   "jti":"e83a8f15-5c2b-49fd-880b-fbf1f04f77e5"
}
```

## myRetail API Server
  myRetail API Server is based on API gateway pattern. It's entry point for myRetail Product data. myRetail API micro service 
  calls Thirdparty Product Detail API and Internal Pricing API to get product data. This service also aggregate data from both 
  Product Detail API and Pricing API and returns to caller.
  
  By default this service runs on 8182 port with context 'store-api'. 
  
**Few Design Decisions** :
* myRetail API will not return partial response means if any one of the servers from Product Detail or Price Details are down , then Product retrieval API will fail. 
* Product Detail Server is down and only Price Details Service is up and running then , Price Update API will result 
  into success
  
  

## Architecture Diagram 
![myimage-alt-tag](https://github.com/hpatel-git/myRetailStore/blob/develop/docs/architecture_diagram.png)
