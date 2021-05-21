# Chat-App-Kafka
A Realtime Chat Application with help of Kafka as Message Broker and React as Frontend Framework

# Before Running the Project

> Start Zookeeper
zookeeper-server-start .\config\zookeeper.properties

>Start Kafka
kafka-server-start .\config\server.properties

>Create a Topic
kafka-topics --create --topic kafka-chat --zookeeper localhost:2181 --replication-factor 1 --partitions 1

>Start Backend
run main method as Spring or Java Application

>Start Frontend
npm start
