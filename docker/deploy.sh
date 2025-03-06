#!/bin/sh

# Usage instructions, used to prompt input parameters
usage() {
	echo "Usage: sh execute script.sh [port|base|modules|stop|rm]"
	exit 1
}

# Open required ports
port(){
	firewall-cmd --add-port=80/tcp --permanent
	firewall-cmd --add-port=8080/tcp --permanent
	firewall-cmd --add-port=8848/tcp --permanent
	firewall-cmd --add-port=9848/tcp --permanent
	firewall-cmd --add-port=9849/tcp --permanent
	firewall-cmd --add-port=6379/tcp --permanent
	firewall-cmd --add-port=3306/tcp --permanent
	firewall-cmd --add-port=9100/tcp --permanent
	firewall-cmd --add-port=9200/tcp --permanent
	firewall-cmd --add-port=9201/tcp --permanent
	firewall-cmd --add-port=9202/tcp --permanent
	firewall-cmd --add-port=9203/tcp --permanent
	firewall-cmd --add-port=9300/tcp --permanent
	service firewalld restart
}

# Start the basic environment (required)
base(){
	docker-compose up -d ruoyi-mysql ruoyi-redis ruoyi-nacos
}

# Start program modules (required)
modules(){
	docker-compose up -d ruoyi-nginx ruoyi-gateway ruoyi-auth ruoyi-modules-system
}

# Stop all environments/modules
stop(){
	docker-compose stop
}

# Remove all environments/modules
rm(){
	docker-compose rm
}

# Execute the corresponding method based on input parameters, if not input, execute usage instructions
case "$1" in
"port")
	port
;;
"base")
	base
;;
"modules")
	modules
;;
"stop")
	stop
;;
"rm")
	rm
;;
*)
	usage
;;
esac
