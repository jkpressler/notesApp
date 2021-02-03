package com.example.notesapp.models

import android.provider.ContactsContract
import com.google.firebase.database.DataSnapshot
import java.lang.Exception

class Note (snapshot: DataSnapshot?){
    lateinit var id : String
    lateinit var title : String
    //lateinit var subtitle : String
    lateinit var body : String
    lateinit var dateTime : String
    //lateinit var webLink : String
    //lateinit var color : String

    init{
        if(snapshot != null){
            createNoteFromSnapshot(snapshot)
        }
    }

    private fun createNoteFromSnapshot(snapshot: DataSnapshot){
        try {
            val data: HashMap<String, Any> = snapshot.value as HashMap<String, Any>
            id = snapshot.key.toString()
            title = data["title"] as String
            //subtitle = data["subtitle"] as String
            body = data["body"] as String
            dateTime = data["dateTime"] as String
            //webLink = data["webLink"] as String
            //color = data["color"] as String
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun toMap(): HashMap<String, Any>{
        val map: HashMap<String, Any> = HashMap()
        map["title"] = title
        map["body"] = body
        map["dateTime"] = dateTime

        return map
    }

}


