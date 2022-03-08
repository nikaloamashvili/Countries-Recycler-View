package com.example.ep.myapplication.Activitys.Services

import android.os.NetworkOnMainThreadException
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.example.myapplicationrecyclerview.models.State
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

/**
 * Created by EP on 18/07/2017.
 */
class DataService {
    companion object {

        private val arrState: ArrayList<State> = ArrayList<State>()
    fun getArrState(): ArrayList<State> {
        val sURL =
            "https://restcountries.com/v2/all?access_key=4b97e2377af02c4b8a4ffe3a1df5381d" // get all states

        // Connect to the URL using java's native library
        var url: URL? = null
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            url = URL(sURL)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            val request = url!!.openConnection() as HttpURLConnection
            request.connect()

            // Convert to a JSON object to print data
            var id1 :Long
            id1=0
            val jp = JsonParser() //from gson
            val root: JsonElement =
                jp.parse(InputStreamReader(request.content as InputStream)) //Convert the input stream to a json element
            val rootobj: JsonArray = root.getAsJsonArray() //May be an array, may be an object.
            for (je in rootobj) {
                val obj: JsonObject = je.getAsJsonObject() //since you know it's a JsonObject
                val entriesname: JsonElement = obj.get("name") //will return members of your object
                val entriesnative: JsonElement = obj.get("nativeName")
//                val entriesborders: JsonElement = obj.getAsJsonArray("borders")
                val entriesflag: JsonObject? = obj.getAsJsonObject("flags")
                val png: JsonElement? = entriesflag?.get("png")
                val name: String = entriesname.toString().replace("\"", "")
                println(name)

                val nativen: String = entriesnative.toString().replace("\"", "")
                val flag: String = png.toString().replace("\"", "")
                val arrBorders = ArrayList<String>()
//                val entriesbordersArray: JsonArray = entriesborders.getAsJsonArray()
//                for (j in entriesbordersArray) {
//                    val s: String = j.toString().replace("\"", "")
//                    arrBorders.add(s)
//                }
                arrState.add(State(name, nativen, flag,id1))
                id1=id1+1
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NetworkOnMainThreadException) {
            e.printStackTrace()
        }
        return arrState
    }}
}