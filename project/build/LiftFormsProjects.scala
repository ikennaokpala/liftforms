import sbt._

class LiftFormsProject(info: ProjectInfo) extends DefaultWebProject(info)
{
  val snapshots = ScalaToolsSnapshots
  val lift = "net.liftweb" %% "lift-mapper" % "2.1-SNAPSHOT" % "compile"
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.14" % "test"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided"
  
  // required because Ivy doesn't pull repositories from poms
  val smackRepo = "m2-repository-smack" at "http://maven.reucon.com/public"
  val nexusRepo = "nexus" at "https://nexus.griddynamics.net/nexus/content/groups/public"
  val scalaToolsRepo = "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/"

}
