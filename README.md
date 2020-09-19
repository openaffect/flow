# Open Affect Flow
Open Affect Flow is very simple application inspired by Stack Overflow, created to illustrate architecture principles.

## History

### Version 0.1.0

Work log:

* Created a project based on https://openliberty.io/guides/getting-started.html. 
* Did a number of small adjustments in the `pom.xml` and `Dockerfile`
* Used Jakarta EE 8 Web Profile instead of Micro Profile
* Got rid of the Java code
* Got rid of the static web assets and created basic html and css assets.

With this version, we can:

* We can run the project in dev mode with `mvn liberty:dev`
* We can build a Docker image with `./build-image.sh` and run it with `./run-image.sh`
* We can visit a page showing the "Open Affect Flow" title





