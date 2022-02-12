package com.yarenyarsilikal.movieland.data.remote

import com.yarenyarsilikal.movieland.util.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by yarenyarsilikal on 11.02.2022.
 */
abstract class RetrofitClient() {
    companion object {
        private var INSTANCE: Retrofit? = null

        fun getRetrofitClient(): Retrofit {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}