organization  := "com.sprangular"

version       := "0.1"

scalaVersion  := "2.11.8"

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
  val akkaV = "2.3.12"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
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
