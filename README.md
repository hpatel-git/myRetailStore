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

myRetailAuth Server Docker image is available on Docker Hub. Install on local using following commands

```sh
$ docker pull hpatel511/store-auth-server:0.0.1
$ docker run -p 8181:8181 hpatel511/store-auth-server:0.0.1
```

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
  
  By default this service runs on 8182 port with context 'store-api'. Download API server image from Docker Hub as follow

```sh
$ docker pull hpatel511/store-api:0.0.1
$ docker run -p 8182:8182 -e PRICE_LOOKUP_HOST=192.168.99.100 -e PRICE_LOOKUP_PORT=8183 hpatel511/store-api:0.0.1
```

**Few Design Decisions** :
* myRetail API will not return partial response means if any one of the servers from Product Detail or Price Details are down , then Product retrieval API will fail. 
* Product Detail Server is down and only Price Details Service is up and running then , Price Update API will result 
  into success
* While updating price , there is no check for Product in Thirdparty server. Assuming that using event based architecture ,
consistency of distrubuted data has been handled.

APIs are tested using folloing: 
* **15117729** - Available in Thirdparty server and Pricing server
* 16483589 - Not available in Thirdparty product server and available in pricing server 
* **16696652** - Available in Thirdparty server and Pricing server
* 16752456 - Not available in Thirdparty product server and available in pricing server  
* 15643793 - Not available in Thirdparty product server and available in pricing server 


**Available APIs** <br>
* Get Product Details <br>
**Path :** store-api/v1/products/<PRODUCT_ID> <br>
**HTTP Method:** GET<br>
**HTTP Response :** HTTP 200 OK<br>

**Sample API Request :**
```sh
   curl -i curl -i http://192.168.99.100:8182/store-api/v1/products/15117729
```
**Sample API Response :** 
```sh
{
   "success":true,
   "data":{
      "productId":15117729,
      "productName":"Apple iPad Air 2 Wi-Fi 16GB, Gold",
      "priceDetail":{
         "price":520.23,
         "currencyCode":"INR"
      }
   }
}
```
* Update Price API <br>
**Path :** store-api/v1/products/<PRODUCT_ID>/price<br>
**HTTP Method :** PUT<br>
**Response Type :** HTTP 302 No Content<br>

**Sample API Request :**
```sh

$ curl -XPUT -H 'Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsic3RvcmUtb2F1dGgyLXJlc291cmNlIl0sInVzZXJfbmFtZSI6InN5c2FkbWluIiwic2NvcGUiOlsib3BlbmlkIl0sImV4cCI6MTQ3NjY4NjU4OCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlODNhOGYxNS01YzJiLTQ5ZmQtODgwYi1mYmYxZjA0Zjc3ZTUiLCJjbGllbnRfaWQiOiJhY21lIn0.mAG6A8oQex3okyJmiwy7TQwRJ1TG3JYbOThX3qpWcSJRgd-tNUYtSMuk0N2fS_khGQ08fHEfNePb7DoivQsPDk7ioHz7ROuyMAVbnf9pGPeuScXkxy6A3DmTHn7-LzIlMlv7rgBLAsHiSMm966VH427IJcmGYew7h3Lf3X4UVP_zvZSoqvxgKEfK7fBYG5ntHqHa7xWRfICygedWul-cLAZQvo3JU89Nt87foO0U0uacpHqBK8yeHXWuZAVOHnkb84NeE_FiN5lxVHKcE8d4J2xeCcaqY5ECS3AtgaPG24GPSdaXfH6RiJkeDOQXmHAVzgsvmQTaKVRk_Crrx9XqXw' -H "Content-type: application/json" -d '{"productId":15117729,"price":520.23,"currencyCode":"INR"}' 'http://192.168.99.100:8182/store-api/v1/products/15117729/price'
 
```

## myRetail Pricing Server

myRetail Pricing Server stores product price data into MongoDB(noSQL) server. Pricing Server is internal service. It will not be exposed out side network. Only myRetail API Gateway server can call this service. By default this service run on 8183 port
with context 'pricing-api'
 
myRetail Pricing Server documer image available on Docker Hub. 

```sh
$ docker pull hpatel511/store-pricing-api:0.0.1
$ docker run -p 8183:8183 -e MONGODB_HOST=<MONGO_DB_HOST> -e MONGODB_PORT=27017 -e MONGODB_DATABASE=my_retail_product_price_db hpatel511/store-pricing-api:0.0.1
```

**Available APIs** <br>
* Get Price Details  By Product ID<br>
**Path :** pricing-api/v1/products/<PRODUCT_ID>/price <br>
**HTTP Method:** GET<br>
**HTTP Response :** HTTP 200 OK<br>

**Sample API Request :**
```sh
   curl -i http://192.168.99.100:8183/pricing-api/v1/products/15117729/price
```
**Sample API Response :** 
```sh
{
   "productId":15117729,
   "price":520.23,
   "currencyCode":"USD"
} 
```
* Update Price API <br>
**Path :** pricing-api/v1/products/<PRODUCT_ID>/price<br>
**HTTP Method :** PUT<br>
**Response Type :** HTTP 302 No Content<br>

**Sample API Request :**

```sh

$ curl -XPUT -H "Content-type: application/json" -d '{"productId":15117729,"price":45.23,"currencyCode":"INR"}' 'http://192.168.99.100:8183/pricing-api/v1/products/15117729/price'
 
```

## Architecture Diagram 
![myimage-alt-tag](https://github.com/hpatel-git/myRetailStore/blob/develop/docs/architecture_diagram.png)
