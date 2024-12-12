package com.bangkit.hansai.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "profile")

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val idKey = stringPreferencesKey("userId")
    private val displayNameKey = stringPreferencesKey("userDisplayName")
    private val tokenKey = stringPreferencesKey("userToken")
    private val sleepQualityKey = stringPreferencesKey("userSleepQuality")
    private val stressLevelKey = stringPreferencesKey("userStressLevel")
    private val physicalActivityLevelKey = stringPreferencesKey("userPhysicalActivityLevel")
    private val genderKey = stringPreferencesKey("userGender")
    private val currentWeightKey = intPreferencesKey("userCurrentWeight")
    private val targetWeightKey = intPreferencesKey("userTargetWeight")
    private val ageKey = intPreferencesKey("userAge")

    fun getUserId(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[idKey] ?: ""
        }
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[idKey] = userId
        }
    }

    fun getUserDisplayName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[displayNameKey] ?: ""
        }
    }

    suspend fun saveUserDisplayName(displayName: String) {
        dataStore.edit { preferences ->
            preferences[displayNameKey] = displayName
        }
    }

    fun getUserToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[tokenKey] ?: ""
        }
    }

    suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    fun getUserSleepQuality(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[sleepQualityKey] ?: ""
        }
    }

    suspend fun saveUserSleepQuality(sleepQuality: String) {
        dataStore.edit { preferences ->
            preferences[sleepQualityKey] = sleepQuality
        }
    }

    fun getUserStressLevel(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[stressLevelKey] ?: ""
        }
    }

    suspend fun saveUserStressLevel(stressLevel: String) {
        dataStore.edit { preferences ->
            preferences[stressLevelKey] = stressLevel
        }
    }

    fun getUserPhysicalActivityLevel(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[physicalActivityLevelKey] ?: ""
        }
    }

    suspend fun saveUserPhysicalActivityLevel(physicalActivityLevel: String) {
        dataStore.edit { preferences ->
            preferences[physicalActivityLevelKey] = physicalActivityLevel
        }
    }

    fun getUserGender(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[genderKey] ?: ""
        }
    }

    suspend fun saveUserGender(gender: String) {
        dataStore.edit { preferences ->
            preferences[genderKey] = gender
        }
    }

    fun getUserCurrentWeight(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[currentWeightKey] ?: 0
        }
    }

    suspend fun saveUserCurrentWeight(currentWeight: Int) {
        dataStore.edit { preferences ->
            preferences[currentWeightKey] = currentWeight
        }
    }

    fun getUserTargetWeight(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[targetWeightKey] ?: 0
        }
    }

    suspend fun saveUserTargetWeight(targetWeight: Int) {
        dataStore.edit { preferences ->
            preferences[targetWeightKey] = targetWeight
        }
    }

    fun getUserAge(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[ageKey] ?: 19
        }
    }

    suspend fun saveUserAge(age: Int) {
        dataStore.edit { preferences ->
            preferences[ageKey] = age
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}