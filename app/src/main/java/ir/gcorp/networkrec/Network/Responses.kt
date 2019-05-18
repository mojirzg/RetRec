package ir.gcorp.networkrec.Network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Responses {

    inner class GetEmployeeResponse {

        var page: Long? = null
        @SerializedName("per_page")
        @Expose
        var perPage: Long? = null
        @SerializedName("total")
        @Expose
        var total: Long? = null
        @SerializedName("total_pages")
        @Expose
        var totalPages: Long? = null
        @SerializedName("data")
        @Expose
        var data: List<Datum>? = null


        inner class Datum() {

            @SerializedName("id")
            @Expose
            var id: Long? = null
            @SerializedName("email")
            @Expose
            var email: String? = null
            @SerializedName("first_name")
            @Expose
            var firstName: String? = null
            @SerializedName("last_name")
            @Expose
            var lastName: String? = null
            @SerializedName("avatar")
            @Expose
            var avatar: String? = null


        }

    }

    inner class SignUpResponse(
        var id: Long,
        var token : String
        )


}