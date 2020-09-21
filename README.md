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

### Version 0.3.0

**Work log:**

* Implemented a "side" use case to illustrate the **overall code structure**, based on ideas from the hexagonal architecture, the onion architecture, the clean architecture and domain driven design. The user can visit the `/jokes` page, which runs a **query** to get a list of jokes. On this page, he can also send a **command** to propose a new joke.
* Code has been added in all layers of the application:
  * In the **domain** layer, there is now a **Joke entity** and a **JokeId** identifier for it.
  * Also in the domain, there is an **IJokeRepository** interface that defines the contract to be fulfilled by **Joke Repositories**.
  * In the **infrastructure / persistence** layer, there is an **in-memory implementation** of the IJokeRepository interface.
  * In the **application** layer, there is a facade that **handles commands and queries** built in the user interface layer. There is one command to propose a new Joke. There is a query to retrieve a list of Jokes.
  * In the **ui / web** layer, there are endpoints that react to incoming HTTP request.
    * The first endpoint reacts to GET requests when users visit the jokes page. The endpoint prepares a **query object**, invokes the facade, receives a DTO from the facade and finally delegates the rendering of the page to a JSP.
    * The second endpoint reacts to POST requests when users have filled the form and clicked on the submit button. The endpoint prepares a **command object**, invokes the facade and goes back to the Jokes page.
* We also have **unit tests**, **one integration test** (which tests the collaboration between the facade and in-memory repository) and **one e2e test** (which tests the use case from the end user point of view).

**WIth this version, we can:**

* Visit /jokes, write jokes and see them in a list.
* Jokes are stored in an in-memory "data store" (i.e. a map)
* Run an end-to-end test with CodeceptJS

