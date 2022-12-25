package com.example.smartdoctor.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.smartdoctor.data.model.UserInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SaveData(private val context: Context?) {

    //to make sure there is only one instance
   companion object {
       private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="datastore")
       val TOKEN = stringPreferencesKey("TOKEN")
       val USER_ID = intPreferencesKey("USER_ID")
    }

    val getToken:Flow<String?> = context!!.dataStore.data
        .map { preferences ->
        preferences[TOKEN] ?:""
    }

    val getUserid:Flow<Int?> = context!!.dataStore.data
        .map { preferences ->
            preferences[USER_ID] ?:0 }


    suspend fun saveToDataStore(token: String,userId:Int) {
        context?.dataStore?.edit {
            it[TOKEN] = token
            it[USER_ID] = userId

        }
    }

    suspend fun cleanData(){
        context!!.dataStore.edit {
            it.clear()
        }
    }

}