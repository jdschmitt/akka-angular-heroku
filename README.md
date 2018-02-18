## spray-angular-heroku

Seed project for spray back-end with angular front-end (currently Angular 4) and configured for heroku

This project is a starting point to build a full-stack web application using Scala / Spray as the backend and Angular 4 as the front-end. It is configured in such a way as to allow for deployment to Heroku in a single dyno by following the steps below.

Follow these steps to get started:

1. Git-clone this repository.

   `git clone git://github.com/jdschmitt/spray-angular-heroku.git my-project`

2. Change directory into your clone:

   `cd my-project`

3. Start the application:

   `sbt compile run`

4. Browse to [http://localhost:8080](http://localhost:8080/)

5. Start hacking on `src/main/scala/com/example/MyService.scala` for new APIs

6. Start hacking in `client` folder for Angular app

## Heroku

1. `heroku create my-project`

2. `heroku buildpacks:set heroku/nodejs`

3. `heroku buildpacks:add heroku/scala`

4. `git push heroku master`

5. `heroku open`

Note: The order you add the buildpacks is important. NodeJS buildpack must be added first.

## Other useful info

* See [Angular CLI docs](https://github.com/angular/angular-cli/wiki) for adding more components etc.
* If you update the name in [build.sbt](build.sbt) then you must also update the file name of the target in [Procfile](Procfile) to match
* [build.sbt](build.sbt) adds a dependency to the Compile task which is responsible for building the Angular application.
* Basic Specs2 tests included. Execute with `sbt test`
* For Angular 2 front-end, check the [angular2](https://github.com/jdschmitt/spray-angular-heroku/tree/angular2) branch.