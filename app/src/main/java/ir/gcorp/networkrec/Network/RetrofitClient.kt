package ir.gcorp.networkrec.Network

import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitClient {

    val baseUrl = "https://reqres.in/api/"

    private var retrofit: Retrofit? = null


    val client: Retrofit
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY


            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(provideAjax())
                .build()

            val gSonBuilder = GsonBuilder()
                .setLenient()
                .create()


            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gSonBuilder))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build()


            return retrofit!!
        }


    private fun provideAjax() = Interceptor {
        val builder = it.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("client", "android")

        it.proceed(builder.build())

    }


}