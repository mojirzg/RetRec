package ir.gcorp.networkrec.Network

import retrofit2.Response
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiAdapter {

    private val retrofit = RetrofitClient.client
    private val apiInterface = retrofit.create(ApiInterface::class.java)


    fun getEmployees(): Observable<Response<Responses.GetEmployeeResponse>>? {

        return apiInterface.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    fun signUp(email : String, password : String): Observable<Response<Responses.SignUpResponse>>? {


        return apiInterface.signUp(Requests().SignUpRequest(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }



}