# proasense-adapter-oracle
ProaSense Oracle adapter for retrieving Moulding Machine (MM) parameters and Scrap data from the HYDRA MES system

# Requirements
* Oracle JDBC drivers.
* Maven.
* Java 1.7 or above.

# Setup
* Download Oracle JDBC driver
  Unfortunately, due to the binary license restrictions there is no public repository with the Oracle JDBC driver. The Oracle
  JDBC driver must be manually downloaded and installed for the Oracle adapters to work.
  Download the ojdbc6.jar file from: 
  http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-112010-090769.html

* Install the Oracle JDBC driver:
  mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=ojdbc6.jar      -DgeneratePom=true

* Download the ProaSense Oracle adapters from GitHub
  Clone the source code repository from GitHub and build the base Oracle adapter:
  git clone https://github.com/SINTEF-9012/proasense-adapter-oracle
  cd proasense-adapter-oracle
  mvn clean install 

# User guide
  Certain information is needed to connect to the database:
  1.  Username.
  2.  Password.
  3.  URL to oracle database.

* Run the program
  mvn exec:java

# Test data
  A program has been developed to generate sample schema with necessary values.
  Program is located in test.net.modelbased.proasense.adapter.imm, this program will generate 101 row for the IMM schema       located in oracle database.
