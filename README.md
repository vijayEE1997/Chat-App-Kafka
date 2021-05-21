# Chat-App-Kafka
A Realtime Chat Application with help of Kafka as Message Broker and React as Frontend Framework

# Before Running the Project

1 Start Zookeeper
>>zookeeper-server-start .\config\zookeeper.properties

2 Start Kafka
>>kafka-server-start .\config\server.properties

3 Create a Topic
>>kafka-topics --create --topic kafka-chat --zookeeper localhost:2181 --replication-factor 1 --partitions 1

4 Start Backend
>>run main method as Spring or Java Application

5 Start Frontend
>>npm start
