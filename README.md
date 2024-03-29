# kprog-fortune

## General

This is a simple web service that can deliver "fortunes" similar to the old
Unix program `fortune`.

It is also a simple web server capable of delivering static file contents making
it possible to deliver web pages from the same domain as the web service thus
removing all possible problems with same-origin-policy.

## Running from the repo

Run with Java 11+ using Gradle. Execute `gradle run` and the contents of the
webroot directory should be served along with the API.

By default it uses the port 8080. To change this use the `-p <port>` argument
to the application. To instead use port 9999 when running with Gradle use the 
command `gradle run --args="-p 9999"`.

## Running from the release zip

Run with Java 11+ using the fat jar. The program takes two optional parameters
`-d <webroot>` and `-p <port>` to control where static content is located as
well as setting the web server port.

By default it uses the port 8080 and the directory webroot in the current 
directory.

`java -jar kprog-fortune-<version>-all.jar`

The release contains a sample application so run the server and visit:
`http://localhost:8080/index.html`.

## Building from the repo

Build the repo using Gradle by running the command `gradle build`. The
jar-files will be found under `build\libs`.

## API

The API is very simple and has five endpoints that return JSON data.

### GET /api/fortune

Returns a random fortune as a json-object.

{ fortune: '...' }

### GET /api/short

Returns a short random fortune (160 characters or less) as a json-object.
Same format as for `/api/fortune`.

### GET /api/showerthought

Returns a shower thought as a json-object.
Same format as for `/api/fortune`.

### GET /api/traditional

Returns a traditional (Unix) fortune as a json-object.
Same format as for `/api/fortune`.

### GET /api/query?q=<query>

Query all fortunes for matching fortunes. Will return a list of all fortunes
that contains the sent string as a substring (case sensitive). The query must
contain at least three characters.

Will return a list of fortunes in the same format as `/api/fortune`.