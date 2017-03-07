## Seed project for spray back-end with angular 2 front-end and configured for heroku

This project is a starting point to build a full-stack web application using Scala / Spray.io as the backend and Angular 2 as the front-end. It is configured in such a way as to allow for deployment to Heroku in a single dyno by following the steps below.

Follow these steps to get started:

1. Git-clone this repository.

        $ git clone git://github.com/spray/spray-template.git my-project

2. Change directory into your clone:

        $ cd my-project

3. Launch SBT:

        $ sbt

4. Compile everything and run all tests:

        > test

5. Start the application:

        > re-start

6. Browse to [http://localhost:8080](http://localhost:8080/)

7. Stop the application:

        > re-stop

8. Start hacking on `src/main/scala/com/example/MyService.scala`
