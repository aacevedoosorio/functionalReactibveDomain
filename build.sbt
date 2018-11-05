name := "frDomainMine"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % Versions.scalazVer,
  "org.scalatest" % "scalatest_2.12" % Versions.scalaTestVer % Test,
  "org.scalacheck" %% "scalacheck" % Versions.scalaCheckVer % Test
)