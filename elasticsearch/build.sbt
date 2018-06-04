name := "elasticsearch-playground"

version := "1.0"

organization := "pdorobisz"

scalaVersion := "2.12.1"

libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-core" % "6.2.9"

libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-tcp" % "6.2.9"

libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-http" % "6.2.9"

libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-jackson" % "6.2.9"

libraryDependencies += "org.apache.logging.log4j" %% "log4j-core" % "2.9.0"

libraryDependencies += "io.codearte.jfairy" % "jfairy" % "0.5.7"
