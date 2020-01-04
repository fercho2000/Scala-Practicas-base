name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies += jdbc
libraryDependencies += cache
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test
libraryDependencies +=  "com.typesafe.slick"          %% "slick-extensions"             % "3.1.0"
libraryDependencies += "com.typesafe.slick"          %% "slick-codegen"                % "3.1.0"
libraryDependencies += "com.typesafe.play"           %% "play-slick"                   % "2.0.0"
libraryDependencies += "com.typesafe.play"           %% "play-slick-evolutions"        % "2.0.0"
libraryDependencies +=   "org.postgresql"              % "postgresql"                    %  "9.4.1212"
val  appDependencies :  Seq [ ModuleID ] = Seq (
  "com.typesafe"  %  "slick_2.10.0-RC1"  %  "0.11.2" ,
  "play"  %%  "play-jdbc"  %  "2.1-SNAPSHOT"
)
