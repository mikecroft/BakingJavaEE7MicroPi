# Baking a Java EE 7 Micro Pi

This repository is based heavily off [another Raspberry Pi demo from Steve Millidge](https://github.com/smillidge/PiyaraMicroDemo).

This demo is comprised of two microservices: *StockTicker* and *StockWeb*. The StockTicker generates random Stock objects with an EJB timer and fires a CDI event over the [Hazelcast powered Event Bus](https://docs.payara.fish/documentation/extended-documentation/cdi-events.html). When the StockWeb service is running, it listens for Stock events and charts the price in a JavaScript chart, updated via WebSockets.


#### To Build:
```
mvn clean install
```

#### To start the ticker service:
```
java -jar StockTicker/target/StockTicker-1.0-SNAPSHOT-microbundle.jar --autobindhttp
```

#### To start the UI service:
```
java -jar StockWeb/target/StockWeb-1.0-SNAPSHOT-microbundle.jar --autobindhttp
```
