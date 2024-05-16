package com.example.firebaseproject.DataModel

data class ItemData(
    var name: String = "",
    var age: Int = 0
) {
    // Default constructor
    constructor() : this("", 0)
}
