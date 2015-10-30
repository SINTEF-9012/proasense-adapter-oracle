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

# Technical user guide
  * Required information for making connection to database:
  1.  Username.
  2.  Password.
  3.  URL to oracle database.
  4.  sid: is a site identifier.
  5.  Name of the schema to read rows from, at least 5 characters long.
  
# Other parameters in the property files of oracle-imm and oracle-scrap:
* IMM adapter:
  1.  poll interval: has value in milliseconds, can be adjusted to suit users needs.
  2.  reference_id.tags: this parameter takes id-number of all maschines user want to monitor, multiple id-numbers must be 
      comma-separated.
  3.  object_id.tags: these values represent column OBJECT_ID in the schema. The values user want to extract must be listed        here in the form they are present in the database and separated with comma..
  4.  object_id.mapping: Values for this parameter maps to tags of the previouse parameter, these values must be presented         in the same sequence as the object_id.tags to avoid missmathch. Second property required is the type of value this is, 
      like STRING, DOUBLE, BOOLEAN or BLOB. Syntaxt for this should look like this: 
      cycleTime:DOUBLE, multiple values must be separeted by comma.
  5.  proasense.adapter.oracle.imm.reference_id.mapping: this defines a default string; machineId.
* Scrap adapter:
  5.  proasense.adapter.scrap.config.firstDelay = 60: polls row from database 60 minutes back in time.
  6.  proasense.adapter.oracle.poll.interval = 3600000: this defines poll-interval to 3600000 ms.
  
# Folder structure
* There are three folders for this adapter, only two of them are meant to be executed. These folders are:
  1.  oracle-imm
  2.  oracle-scrap

* Run the program
  start Oracle server.
  cd to folder you want to execute, in this case proasense-adapter-oracle.
  type "mvn clean install".
  cd to the one of the folders mentioned above.
  type "mvn exec:java" in the command prompt.

# End-users guide
  Steps above are needed to accomplish to start any of the two adapters, this section will keep focus on how to use these      adapters, namely oracle-imm and orecle-scrap. In the end will tests for both of the adapters be highligted.
* Oracle-imm: This adapter will take the latest table created on that day, if there is no table on that day that have the      given prefix, the loop will continue with certain time-delay. If the table exists then it will be accessed and it's row      will be processed as well as new row that are entered into the table.
* Oracle-scrap: This adapter is meant to have a pollinterval and a firstDelay that will poll rows from database from a         specific time from the past, like 60 min or any other value. Both of these properties are to be defined in the property      file. When running the adapter, row from example 60 min back in time will start to be processed with a certain interval      defined in the property file. 

# Test data
  There is test-data for all three modules located in test folder of the modules.
  A program has been developed to generate sample schema with necessary values.
  Program is located in test.net.modelbased.proasense.adapter.imm, this program will generate 101 row for the IMM schema       located in oracle database.
