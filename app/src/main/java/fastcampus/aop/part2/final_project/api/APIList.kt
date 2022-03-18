package fastcampus.aop.part2.final_project.api

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface APIList {

    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String
    ) : Call<JSONObject>
}