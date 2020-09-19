# Open Affect Flow
Open Affect Flow is very simple application inspired by Stack Overflow, created to illustrate architecture principles.

## History

### Version 0.1.0

**Work log:**

* Created a project based on https://openliberty.io/guides/getting-started.html. 
* Did a number of small adjustments in the `pom.xml` and `Dockerfile`
* Used Jakarta EE 8 Web Profile instead of Micro Profile
* Got rid of the Java code
* Got rid of the static web assets and created basic html and css assets.

**With this version, we can:**

* We can run the project in dev mode with `mvn liberty:dev`
* We can build a Docker image with `./build-image.sh` and run it with `./run-image.sh`
* We can visit a page showing the "Open Affect Flow" title

### Version 0.2.0

**Work log:**

* Created a first CI pipeline with GitHub Actions 
* The pipeline is run when commits are pushed on master or when pull requests are opened on master (every commit to the PR then also triggers the pipeline)
* The pipeline builds the .war file with maven, build the Docker image and uploads it to the GitHub Docker Registry (at the time of writing, in beta)
* Created a personal access token and used as a secret in the project repository, so that the GitHub action can log into the GitHub Docker Registry
* Manually configured the Docker image (which is a package at the organization level) to make it publicly available

**With this version, we can**

* Build and publish a Docker image packaging our application
* Any interested user can now `docker run -p 9000:9080 ghcr.io/openaffect/flow`



