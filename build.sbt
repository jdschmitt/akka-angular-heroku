organization  := "com.sprangular"

version       := "0.1"

scalaVersion  := "2.11.8"

name          := "akka-angular-heroku"

enablePlugins(JavaAppPackaging)

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

// webapp task
resourceGenerators in Compile <+= (resourceManaged, baseDirectory) map { (managedBase, base) =>
  val webappBase = base / "src" / "main" / "webapp"
  for {
    (from, to) <- webappBase ** "*" x rebase(webappBase, managedBase / "main" / "webapp")
  } yield {
    Sync.copy(from, to)
    to
  }
}

// watch webapp files
watchSources <++= baseDirectory map { path => ((path / "client") ** "*").get }

libraryDependencies ++= {
  lazy val akkaHttpVersion = "10.0.11"
  lazy val akkaVersion    = "2.5.8"
  Seq(
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit"         % akkaVersion       % Test,
    "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion   % Test,
    "org.scalatest"     %% "scalatest"            % "3.0.5"           % Test
  )
}

Revolver.settings

lazy val buildFrontEnd: TaskKey[Unit] = taskKey[Unit]("Execute the npm build command to build the ui")

buildFrontEnd := {
  val s: TaskStreams = streams.value
  s.log.info("Building front-end")
  val shell: Seq[String] = Seq("bash", "-c")
  val npmInstall: Seq[String] = shell :+ "npm install"
  val npmBuild: Seq[String] = shell :+ "npm run build"

  if ((npmInstall #&& npmBuild !) == 0) {
    s.log.success("Successfully built front-end.")
  } else {
    throw new IllegalStateException("Failed to build front-end.")
  }
}

// Executes when running within IDE
//(run in Compile) <<= (run in Compile).dependsOn(buildFrontEnd)
// Executes when running "sbt compile"
compile <<= (compile in Compile) dependsOn buildFrontEnd
