package fastcampus.aop.part2.final_project.api

import fastcampus.aop.part2.final_project.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface APIList {

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("name") name: String,
        @Field("phone") phone: String
    ) : Call<BasicResponse>

    @GET("/user")
    fun getRequestMyInfo(
        @Header("X-Http-Token") token: String,
    ) : Call<BasicResponse>
}