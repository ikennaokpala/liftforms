package bootstrap.liftweb


import _root_.net.liftweb.http._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {

    // where to search snippet
    LiftRules.addToPackages("com.liftforms")

    // Build SiteMap
    val entries = Menu(Loc("Home", List("index"), "Home", Hidden)) :: 
		  Menu(Loc("View", List("view"), "View", Hidden)) :: Nil
   
    // Setting the sitemap
    LiftRules.setSiteMap(SiteMap(entries: _*))

    /*LiftRules.dispatch.append{
      case Req("view" :: _, _, GetRequest) => S.redirectTo("/")
    }*/
      
    // Allowing access to css, images etc.. folders located in the resources source folder
    ResourceServer.allow {
      case "jquery.js" :: Nil => true
      case "js" :: _ => true
      case "css" :: _ => true
      case "images" :: _ => true
     
     
    }

  }

}
