package org.wit.placemark.console.main.kt

import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkModel

val placemarks = ArrayList<PlacemarkModel>()



private val logger = KotlinLogging.logger {}

var placemark = PlacemarkModel()

var title = ""
var description = ""

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 2.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlacemarks()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}


fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println("-1. Exit")
    println()
    print("Enter an option : ")
    input = readLine()
    option = if (input?.toIntOrNull() != null && !input.isNullOrEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addPlacemark() {
    var aPlacemark = PlacemarkModel()
    println("Add Placemark")
    println()
    print("Enter a Title : ")
    title = readLine()!!
    print("Enter a Description : ")
    description = readLine()!!

    if (title.isNotEmpty() && description.isNotEmpty()) {
        placemark.title = title
        placemark.description = description
        placemarks.add(placemark.copy())
        logger.info("Placemark Added : [ $placemark ]")
        placemark.id++
    }
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    listAllPlacemarks()
    var searchId = getId()
    val aPlacemark = search(searchId)
    var tempTitle : String?
    var tempDescription : String?

    if(aPlacemark != null) {
        print("Enter a new Title for [ " + aPlacemark.title + " ] : ")
        tempTitle = readLine()!!
        print("Enter a new Description for [ " + aPlacemark.description + " ] : ")
        tempDescription = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            aPlacemark.title = tempTitle
            aPlacemark.description = tempDescription
            println(
                "You updated [ " + aPlacemark.title + " ] for title " +
                        "and [ " + aPlacemark.description + " ] for description")
            logger.info("Placemark Updated : [ $aPlacemark ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Placemark Not Updated...")
}

fun listAllPlacemarks(){
    println("List All Placemarks")
    println()
    placemarks.forEach { logger.info("${it}") }
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && strId.isNotEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark

}

fun searchPlacemarks() {

    var searchId = getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark Not Found...")
}

fun dummyData() {
    placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}