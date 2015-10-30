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
  * Required information for making connection to database:
  1.  Username.
  2.  Password.
  3.  URL to oracle database.
  4.  sid: is a site identifier.
  5.  Name of the schema to read rows from, at least 5 characters long.
  
  * Other parameters in property file:
  1.  poll interval: has value in milliseconds, can be adjusted to suit users needs.
  2.  reference_id.tags: this parameter takes id-number of all maschines user want to monitor, multiple id-numbers must be 
      comma-separated.
  3.  object_id.tags: these values represent column OBJECT_ID in the schema. The values user want to extract must be listed        here in the form they are present in the database and separated with comma..
  4.  object_id.mapping: Values for this parameter maps to tags of the previouse parameter, these values must be presented         in the same sequence as the object_id.tags to avoid missmathch. Second property required is the type of value this is, 
      like STRING, DOUBLE, BOOLEAN or BLOB. Syntaxt for this should look like this: 
      cycleTime:DOUBLE, multiple values must be separeted by comma.
  
# 

* There are are three folders for this adapter, only two of them are meant to be executed. The folders are:
  1.  oracle-imm
  2.  oracle.scrap

* Run the program
  start Oracle server.
  cd to folder you want to execute, eks oracle-imm
  type "mvn exec:java" in command prompt.

# Test data
  There is test-data for all three modules located in test folder of the modules.
  A program has been developed to generate sample schema with necessary values.
  Program is located in test.net.modelbased.proasense.adapter.imm, this program will generate 101 row for the IMM schema       located in oracle database.
