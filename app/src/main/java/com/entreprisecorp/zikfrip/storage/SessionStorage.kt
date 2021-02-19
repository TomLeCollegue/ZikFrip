package com.entreprisecorp.zikfrip.storage

import android.content.Context
import android.content.res.Resources
import android.provider.Settings.Global.getString
import com.entreprisecorp.zikfrip.MainActivity
import com.entreprisecorp.zikfrip.R
import com.entreprisecorp.zikfrip.R.*
import java.lang.Integer.getInteger

class SessionStorage {

    object Singleton{

        var nameSession : String = ""
        var firstnameSession : String = ""

    }

    companion object{

        fun putInfoSession(mainActivity: MainActivity, name : String, firstname : String){
            val sharedPref = mainActivity?.getPreferences(Context.MODE_PRIVATE)?:return
            with (sharedPref.edit()) {
                putString("name", name)
                putString("firstname", firstname)
                apply()
            }
        }

        fun getInfoSession(mainActivity: MainActivity){
            val sharedPref = mainActivity?.getPreferences(Context.MODE_PRIVATE)?:return
            Singleton.nameSession = sharedPref.getString("name","lele").toString()
            Singleton.firstnameSession = sharedPref.getString("firstname","lele").toString()
        }

    }

}