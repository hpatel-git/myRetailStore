#!/bin/bash
 
echo '................................Starting myRetail API Gateway Server.............................'
java -jar /app.jar

exec "$@"