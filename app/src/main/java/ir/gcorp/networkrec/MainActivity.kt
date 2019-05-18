package ir.gcorp.networkrec

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import ir.gcorp.networkrec.Network.ApiAdapter
import ir.gcorp.networkrec.Network.Responses
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cardview_main.view.*
import retrofit2.Response
import rx.Subscriber

class MainActivity : AppCompatActivity() {


    //http://www.jsonschema2pojo.org/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ApiAdapter().getEmployees()!!.subscribe(
            object : Subscriber<Response<Responses.GetEmployeeResponse>>() {
                override fun onNext(t: Response<Responses.GetEmployeeResponse>?) {
                    val code = t?.code()
                    val response = t?.body()!!

                    initiateRecyclerView(response.data!!)


                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    Log.e("ir.gcorp.networkrec", e.message)
                    e.printStackTrace()
                }

            })


//        ok.setOnClickListener {
//
//
//            ApiAdapter().signUp(username.text.toString(),"pistol")!!
//                .subscribe(
//                    object : Subscriber<Response<Responses.SignUpResponse>>() {
//                        override fun onNext(t: Response<Responses.SignUpResponse>?) {
//                            val code = t?.code()
//                            val response = t?.body()!!
//
//                            when(code) {
//                                200 -> toast(response.token)
//                                else -> toast("Errored")
//                            }
//
//                        }
//
//                        override fun onCompleted() {
//
//                        }
//
//                        override fun onError(e: Throwable) {
//                            Log.e("ir.gcorp.networkrec",e.message)
//                            e.printStackTrace()
//                        }
//
//                    })
//
//
//
//
//
//
//
//        }


    }

    private fun initiateRecyclerView(data: List<Responses.GetEmployeeResponse.Datum>) {

        recyclerView.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = RecyclerViewAdapter(data)
        }


    }

    inner class RecyclerViewAdapter(private val recData: List<Responses.GetEmployeeResponse.Datum>) :
        RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
            val view = layoutInflater.inflate(R.layout.cardview_main, p0, false)
            return CustomViewHolder(view)
        }

        override fun getItemCount(): Int = recData.size

        override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) = p0.onBind()


        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val name : TextView = itemView.fName
            val lName : TextView = itemView.lName
            val email : TextView = itemView.email
            val image : ImageView = itemView.image

            fun onBind() {
                 val data = recData[layoutPosition]

                name.text = data.firstName
                lName.text = data.lastName
                email.text = data.email

                Glide.with(itemView)
                    .load(data.avatar)
                    .into(image)

            }
        }
    }


    fun toast(test: String) {

        Toast.makeText(this@MainActivity, test, Toast.LENGTH_SHORT).show()
    }
}
