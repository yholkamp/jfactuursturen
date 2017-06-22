# jFactuurSturen

Unofficial Java/Kotlin/JVM library to interact with the FactuurSturen.nl API, currently at v1.


## Usage

[![Javadocs](http://www.javadoc.io/badge/net.nextpulse/jfactuursturen.svg)](http://www.javadoc.io/doc/net.nextpulse/jfactuursturen) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.nextpulse/jfactuursturen/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.nextpulse/jfactuursturen/)

Add the library to your Java 8+/Scala/Kotlin/Groovy application:
     
For Maven users:

     <dependency>
         <groupId>net.nextpulse</groupId>
         <artifactId>jfactuursturen</artifactId>
         <version>(version)</version>
     </dependency>
     
Call the code as follows:

    String username = "YOUR_FS_USER_NAME";
    String apiToken = "YOUR_API_TOKEN";
    FactuurSturenClient client = new FactuurSturenClient(username, apiToken);
    List<Invoice> invoices = client.getInvoices();

## Supported APIs

* Invoices: get all (with/without filters), get one, create, delete


## To be implemented

* All other API methods