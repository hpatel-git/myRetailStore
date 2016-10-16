#!/bin/bash
 
if [ -z "$MONGODB_HOST" ]; then
    export MONGODB_HOST=localhost
fi

if [ -z "$MONGODB_PORT" ]; then
    export MONGODB_PORT=27017
fi

if [ -z "$MONGODB_DATABASE" ]; then
    export MONGODB_DATABASE=my_retail_product_price_db
fi


 
echo '................................Starting myRetail Pricing Server.............................'

echo MongoDB Host : $MONGODB_HOST
echo MongoDB Port : $MONGODB_PORT
echo MongoDB Database : $MONGODB_DATABASE
 
java -jar /app.jar

exec "$@"