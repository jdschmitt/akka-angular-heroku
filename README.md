## spray-angular2-heroku

Seed project for spray back-end with angular 2 front-end and configured for heroku

This project is a starting point to build a full-stack web application using Scala / Spray as the backend and Angular 2 as the front-end. It is configured in such a way as to allow for deployment to Heroku in a single dyno by following the steps below.

Follow these steps to get started:

1. Git-clone this repository.

   `git clone git://github.com/jdschmitt/spray-angular2-heroku.git my-project`

2. Change directory into your clone:

   `cd my-project`

3. Start the application:

   `sbt compile run`

4. Browse to [http://localhost:8080](http://localhost:8080/)

5. Start hacking on `src/main/scala/com/example/MyService.scala` for new APIs

6. Start hacking in `client` folder for Angular 2 app

## Heroku

### Deploying to Heroku
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

**or**

1. `heroku create spray-angular-seed`

2. `git push heroku master`

3. `heroku open`
