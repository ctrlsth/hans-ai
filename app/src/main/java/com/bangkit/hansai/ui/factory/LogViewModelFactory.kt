package com.bangkit.hansai.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.hansai.data.repository.LogRepository
import com.bangkit.hansai.di.Injection
import com.bangkit.hansai.ui.track.TrackViewModel

class LogViewModelFactory private constructor(private val logRepository: LogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
            return TrackViewModel(logRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: LogViewModelFactory? = null
        fun getInstance(context: Context): LogViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: LogViewModelFactory(Injection.provideLogRepository(context))
            }.also { instance = it }
    }
}