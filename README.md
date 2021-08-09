# Booking-System-using-Saga-Orchestration
A Booking system with airline microservice, hotel microservice and saga orchestration

# Installation
- Clone the project git reository in your desired folder using the following command on the terminal
```
git clone https://github.com/srujanavanama/Booking-System-using-Saga-Orchestration.git
```

- Run the below command for each microservice 
```
mvn clean install -Dmaven.test.skip=true
```

- Install Active MQ from http://activemq.apache.org/activemq-5015005-release and save it in your local. Go to the location of apache-activemq-5.15.5/bin in your local and run the command, active MQ should be up and running at http://127.0.0.1:8161/admin/queues.jsp
```
activemq start
```

- Run each microservice as Java Application

- To check the application, give the command 
```
curl -X POST -v -H "Content-Type: application/json" http://localhost:8083/orders -d "{\"sourceLocation\":\"New Delhi\", \"destinationLocation\":\"Mumbai\"}"
```
