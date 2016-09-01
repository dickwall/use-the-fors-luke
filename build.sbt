name := """use-the-fors"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

resolvers += "bintray-djspiewak-maven" at "https://dl.bintray.com/djspiewak/maven"

val EmmVersion = "0.2.1"

//libraryDependencies += "com.codecommit" %% "emm-core" % EmmVersion

libraryDependencies += "com.codecommit" %% "emm-cats" % EmmVersion