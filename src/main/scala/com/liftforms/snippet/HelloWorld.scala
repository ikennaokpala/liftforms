package com.liftforms {
package snippet {

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import Helpers._

class HelloWorld {

  def howdy(in: NodeSeq): NodeSeq = <span> Welcome... The Time is {new _root_.java.util.Date}</span> 

 }

}
}
