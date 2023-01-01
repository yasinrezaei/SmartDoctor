package com.example.smartdoctor.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SaveData(private val context: Context?) {

    //to make sure there is only one instance
   companion object {
       private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="datastore")
       val TOKEN = stringPreferencesKey("TOKEN")
       val USER_ID = intPreferencesKey("USER_ID")
       val PROFILE_ID = intPreferencesKey("PROFILE_ID")
    }

    //save data
    suspend fun saveToken(token: String) {
        context?.dataStore?.edit {
            it[TOKEN] = token
        }
    }

    suspend fun saveUserId(userId: Int){
        context?.dataStore?.edit {
            it[USER_ID] = userId
        }
    }

    suspend fun saveProfileId(profileId: Int){
        context?.dataStore?.edit {
            it[PROFILE_ID] = profileId
        }
    }

    //get data
    val getToken:Flow<String?> = context!!.dataStore.data
        .map { preferences ->
            preferences[TOKEN] ?:""
        }

    val getUserId:Flow<Int?> = context!!.dataStore.data
        .map { preferences ->
            preferences[USER_ID] ?:0 }

    val getProfileId:Flow<Int?> = context!!.dataStore.data
        .map { preferences ->
            preferences[PROFILE_ID] ?:0 }

    suspend fun cleanData(){
        context!!.dataStore.edit {
            it.clear()
        }
    }

}