package ir.gcorp.networkrec.Network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable
import java.util.*

interface ApiInterface {



    @GET("users?page=2")
    fun getEmployees() : Observable<Response<Responses.GetEmployeeResponse>>

    @POST("register")
    fun signUp(@Body signUpRequest: Requests.SignUpRequest): Observable<Response<Responses.SignUpResponse>>
}