name := "scalatra_demo"

version := "1.0"

scalaVersion := "2.11.7"

val activateVersion = "1.7"
val hikaricpVersion = "2.3.6"
val scalatraVersion = "2.3.1"

val macwireVersion = "1.0.7"
val json4sVersion = "3.2.11"

enablePlugins(XwpJetty)

jetty(port = 9000)

libraryDependencies ++= Seq(
  "net.fwbrasil" %% "activate-jdbc-async" % activateVersion exclude("log4j", "log4j") exclude("com.zaxxer", "HikariCP"),
  "net.fwbrasil" %% "activate-slick" % activateVersion exclude("log4j", "log4j"),
  "net.fwbrasil" %% "activate-test" % activateVersion % "test" excludeAll (ExclusionRule("org.slf4j"))
  , "com.zaxxer" % "HikariCP-java6" % hikaricpVersion excludeAll (ExclusionRule("org.slf4j")),
  "com.softwaremill.macwire" %% "macros" % macwireVersion,
  "com.softwaremill.macwire" %% "runtime" % macwireVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "org.scalatra" %% "scalatra" % scalatraVersion excludeAll (ExclusionRule("org.slf4j")),
  "org.scalatra" %% "scalatra-auth" % scalatraVersion,
  "org.scalatra" %% "scalatra-json" % scalatraVersion,
  "org.scalatra" %% "scalatra-swagger" % scalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % scalatraVersion
)
