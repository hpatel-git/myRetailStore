#!/bin/bash



MYSQL=`which mysql`

if [ -z "$MYSQL_HOST" ]; then
    export MYSQL_HOST=localhost
    echo '.............................Using Container MySQL database......................'
    /usr/bin/mysql_install_db --user=mysql
  	
  	/usr/share/mysql/mysql.server start
 
	ADD_DB_QUERY="CREATE DATABASE IF NOT EXISTS eui_service_dev;"
	ADD_USER_QUERY="CREATE USER 'euidev'@'localhost' IDENTIFIED BY 'euidev';"
	ADD_GRANT="GRANT ALL PRIVILEGES ON eui_service_dev.* TO 'euidev'@'localhost';"


	echo 'Schema and User creation start'

	SQL="${ADD_DB_QUERY}${ADD_USER_QUERY}${ADD_GRANT}"

	$MYSQL -e "$SQL"

	echo 'Schema has been created successfully'

	$MYSQL eui_service_dev < /init-data.sql

	echo 'Data imported successfully'

	 echo '.............................Done with Container MySQL database......................'
fi

if [ -z "$MYSQL_PORT" ]; then
    export MYSQL_PORT=3306
fi

if [ -z "$MYSQL_USER" ]; then
    export MYSQL_USER=euidev
fi

if [ -z "$MYSQL_PASSWORD" ]; then
    export MYSQL_PASSWORD=euidev
fi

if [ -z "$MYSQL_DATABASE" ]; then
    export MYSQL_DATABASE=eui_service_dev
fi
 
echo My Sql Host : $MYSQL_HOST Port : $MYSQL_PORT User Name : $MYSQL_USER Password : $MYSQL_PASSWORD Default Database : $MYSQL_DATABASE 
  
echo '................................Starting EUI Dev Micro Service.............................'
java -jar /app.jar

exec "$@"