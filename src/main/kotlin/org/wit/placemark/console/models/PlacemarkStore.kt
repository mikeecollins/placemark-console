package org.wit.placemark.console.models



var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
/* This class implements the PlacemarkStore interface, so it must
   provide function bodies for the abstract methods in it.

   This class manages an ArrayList of PlacemarkModel e.g. adding,
   updating, searching, etc.
 */

class PlacemarkMemStore : PlacemarkStore {

    private val placemarks = ArrayList<PlacemarkModel>()

    override fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    override fun findOne(id: Long) : PlacemarkModel? {
        return placemarks.find { p -> p.id == id }
    }

    override fun create(placemark: PlacemarkModel) {
        placemark.id = getId()
        placemarks.add(placemark)
    }

    override fun update(placemark: PlacemarkModel) {
        val foundPlacemark = findOne(placemark.id)
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
        }
    }

}