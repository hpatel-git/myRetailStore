#!/bin/bash
 
if [ -z "$PRICE_LOOKUP_HOST" ]; then
    export PRICE_LOOKUP_HOST=localhost
fi

if [ -z "$PRICE_LOOKUP_PORT" ]; then
    export PRICE_LOOKUP_PORT=8183
fi
 
echo '................................Starting myRetail API Gateway Server.............................'

echo Price Lookup Host : $PRICE_LOOKUP_HOST
echo Price Lookup Port : $PRICE_LOOKUP_PORT

java -jar /app.jar

exec "$@"