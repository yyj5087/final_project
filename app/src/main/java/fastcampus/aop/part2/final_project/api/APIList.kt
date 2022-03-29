package fastcampus.aop.part2.final_project.api

import fastcampus.aop.part2.final_project.datas.BasicResponse
import okhttp3.MultipartBody
import retrofit2.Call
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
    fun getRequestMyInfo() : Call<BasicResponse>

    @Multipart
    @PUT("/user/profile")
    fun putRequestProfileImg(
        @Part img: MultipartBody.Part
    ) : Call<BasicResponse>

    @GET("/cart")
    fun getRequestCartList(
    ) : Call<BasicResponse>

    @GET("/todayshot")
    fun getRequestItem(
    ) : Call<BasicResponse>

    @GET("/largecategory")
    fun getRequestLargeCategory(
    ) : Call<BasicResponse>

    @GET("/largecategory/{large_category_id}")
    fun getRequestLargeCategoryId(
        @Field("large_category_id") largeCategoryId: Int,
    ) :  Call<BasicResponse>

    @FormUrlEncoded
    @POST("/cart")
    fun getRequestAddItem(
        @Field("product_id") product_ai: Int,
        @Field("quantity") quantity: Int,
        @Field("option_info_str") option_info_str: String,
    ) : Call<BasicResponse>

    @GET("/cart")
    fun getRequestAddItemCheck(
    ) : Call<BasicResponse>

    @GET("/product/{product_id}")
    fun getRequestProductDetail(
        @Path("product_id") id: Int,
    ): Call<BasicResponse>

    @DELETE("/cart")
    fun getRequestDeleteItem(
        @Query("cart_id") cardId: Int,
    ) : Call<BasicResponse>

    @GET("/smallcategory/{small_category_id}/product")
    fun getRequestProductsBySmallCategory(
        @Path("small_category_id") id: Int,
        @Query("large_category_id") largeCategoryId: Int,
    ) : Call<BasicResponse>

    @GET("/largecategory/{large_category_id}/smallcategory")
    fun getRequestSmallCategory(
        @Path("large_category_id") id: Int,
    ) : Call<BasicResponse>



}