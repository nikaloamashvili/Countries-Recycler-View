package com.example.myapplicationrecyclerview.models

import java.io.Serializable

/**
 * Created by EP on 18/07/2017.
 */
class State : Serializable {
    var name: String
        private set
    var borders: List<String>? = null
        private set
    var nativeName: String
        private set
    var flag: String? = null
        private set
    var id: Long=0

    constructor(name: String, nativeName: String) {
        this.name = name
        this.nativeName = nativeName
    }

    constructor(name: String,  nativeName: String, flag: String?,id:Long) {
        this.name = name
        this.borders = borders
        this.nativeName = nativeName
        this.flag = flag
        this.id = id
    }
}