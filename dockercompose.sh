



docker run -d -p 8086:8086 -v /home/ec2-user/logs:/logs -e CONSUL=consul -e kafka.url=kafka:9092 -e topic.errorsIn=RaiseIncTopic  --name incident-management --link=consul --link=kafka incident-management:1.0

docker network connect bunit incident-management

docker ps

