# flarify-api
This showcase project runs a small Java based API which in it's place collects and processes data from NASA's DONKI API.

See: https://api.nasa.gov/api.html#donkiIPS

### Requirements
* Java (version 8 up until 11)
* Maven 3.3+
* MongoDB 

### Configuration
The application requires minimal configuration. The following options can be configured inside the applications 
properties file, found under `src/main/resources/application.properties`.

##### flarify.api-key
Set this value to the API key used to access NASAs APIs. If you do not have a key for this, don't worry. You can obtain 
one for free at https://api.nasa.gov/index.html#apply-for-an-api-key

```flarify.api-key=abcdef1234567890``` 

This allows you to make 1000 API calls for free every hour. The application updates it's local database in the backend 
every 5 minutes so no worries that you will run out of calls.

##### flarify.mongodb.database-name=flarify
The name of your MongoDB database. Defaults to `flarify`, but can be changed if needed.

```flarify.mongodb.database-name=flarify```

##### spring.data.mongodb.uri
This setting configures the complete URI to your MongoDB database. This URI must include the database name as well. 
Defaults to `mongodb://localhost:27017/flarify` but can be changed if needed.

```spring.data.mongodb.uri=mongodb://localhost:27017/flarify```

### Run
First you will need to compile the project with Maven. To do so, run the following command inside the project root 
directory:

```mvn compile```

After the compilation has been done, you can run the application by simply entering the following command inside your 
terminal:

```java -jar target/flarify-1.0.0-SNAPSHOT.jar```

Once the application is running you can check it's status via the following URL:
http://localhost:8081/

This should return something like:
```
{
    "status":"OK",
    "solarFlares":571
}
```

### Stack
The project is setup with the following stack:
* Spring Boot
* Reactor Core
* MongoDB

### Roadmap
* Add tests
* Add more API functionality
    * Listing of most active regions
    * Listing of most common class types
    * Top listing of most powerful solar flares
* Security
    * Usage of API tokens
    * Proper implementation of CORS
* Code improvements and cleanup
    * Grouping of reactive code blocks
    * Ability to use environment variables instead of application properties
    * Removal of unused dependencies and code blocks