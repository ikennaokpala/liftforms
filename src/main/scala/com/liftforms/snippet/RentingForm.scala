package com.liftforms.snippet


import _root_.scala.xml._
import _root_.net.liftweb.util._
import Helpers._
import _root_.net.liftweb.common.{Full, Empty}
import net.liftweb.http.{StatefulSnippet, S, SHtml}

class RentingForm extends StatefulSnippet { // RentingForm snippet extending StatefulSnippet
  var dispatch: DispatchIt = {
    case "rent" => rentForm _ 				// call rentForm function
  }
  var (name, address, email, movieType, dateOfHire, numberOfDays, discount) = ("", "", "", "", "", "", "") // Tuple of form variables
  val movieSeq = Seq("" -> "Select Movie Type", "Sci-fi" -> "Sci-fi", "Horror" -> "Horror", "Comedy" -> "Comedy", "Suspense" -> "Suspense", "Romance" -> "Romance") // Sequence of movie types
  val discountMap = Map("Regular Customer" -> "Regular Customer", "New Customer" -> "New Customer") // Map of customer types

	// definition for rentForm
  def rentForm(xhtml: NodeSeq): NodeSeq = {
    dispatch = {
      case name if name != "" => showDetails _                  // call show details function if name is not equal to ""
    }

    val discounts = SHtml.radio(discountMap.keys.toList, Full("New Customer"), discount = _, "class" -> "required") // radio button

    val submitLabel = "Confirm"
	// Binding Form fields and components to template
    bind("v", xhtml,
         "name" -> SHtml.text(name, name = _, "class" -> "required"), // text field
         "address" -> SHtml.textarea(address, address = _, "rows" -> "5", "class" -> "required"), // text area
         "email" -> SHtml.text(email, email = _, "class" -> "required email"), // text field
         "movie" -> SHtml.select(movieSeq, Empty, movieType = _, "class" -> "required"), //  select options combo box
         "date" -> SHtml.text(dateOfHire, dateOfHire = _, "class" -> "required date", "id" -> "datePicker"), // text field
         "numberOfDays" -> SHtml.text(numberOfDays, numberOfDays = _, "class" -> "required"), // text field
         "regular" -> discounts(0), // radio buttons 0
         "new" -> discounts(1), // radio button 1
         "rtxt1" -> (discountMap.head.key), //radio button label 0
         "rtxt2" -> (discountMap.last.key), // radio button label 1
         "submit" -> SHtml.submit(submitLabel, () => {}, "id" -> "submit")) // submit button

  }
// definition for showDetails
  def showDetails(xhtml: NodeSeq): NodeSeq = {

    val submitLabel = "Edit"
// Binding Form fields and components to template
    bind("v", xhtml,
         "name" -> Text(name), // plain text rendering form variable to template
         "address" -> Text(address), // plain text rendering form variable to template
         "email" -> Text(email), // plain text rendering form variable to template
         "movie" -> Text(movieType), // plain text rendering form variable to template
         "date" -> Text(dateOfHire), // plain text rendering form variable to template
         "numberOfDays" -> Text(numberOfDays), // plain text rendering form variable to template
         "regular" -> Text(discount), // plain text rendering form variable to template
         "new" -> Text(""), // plain text rendering "" to template
         "rtxt1" -> Text(""), // plain text rendering "" to template
         "rtxt2" -> Text(""), // plain text rendering "" to template
         "submit" -> SHtml.submit(submitLabel, () => S.mapSnippet("RentingForm.rent", editDetails _), "id" -> "submit")) // submit button

  }

// definition for editDetails
  def editDetails(xhtml: NodeSeq): NodeSeq = {
    val discounts = SHtml.radio(discountMap.keys.toList, Full(discount), discount = _, "class" -> "required") // radio button
// Binding Form fields and components to template
    bind("v", xhtml,
         "name" -> SHtml.text(name, name = _, "class" -> "required"), // text field
         "address" -> SHtml.textarea(address, address = _, "rows" -> "5", "class" -> "required"), // text area
         "email" -> SHtml.text(email, email = _, "class" -> "required email"), // text field
         "movie" -> SHtml.select(movieSeq, Full(movieType), movieType = _, "class" -> "required"), // select option combo box
         "date" -> SHtml.text(dateOfHire, dateOfHire = _, "class" -> "required date", "id" -> "datePicker"), // text field
         "numberOfDays" -> SHtml.text(numberOfDays, numberOfDays = _, "class" -> "required"), // text field
         "regular" -> discounts(0), // radio button 0
         "new" -> discounts(1), // radio button 1
         "rtxt1" -> (discountMap.head.key),  // radio button label 0
         "rtxt2" -> (discountMap.last.key), // radio button label 1
         "submit" -> SHtml.submit("Save", () => S.redirectTo("/view", () => S.mapSnippet("RentingForm.rent", thankYou _)), "id" -> "submit")) // submit button

  }

// definition for thankYou
  def thankYou(xhtml: NodeSeq): NodeSeq = {
    Log.info(name, address, email, movieType, dateOfHire, numberOfDays, discount) // logging the form variables
// Binding Form fields and components to template
    bind("v", xhtml,
         "name" -> (name))
  }

}