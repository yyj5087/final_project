package fastcampus.aop.part2.final_project.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

    companion object{

        private var retrofit : Retrofit? = null
        private val BASE_URL = "https://api.copang.xyz"

        fun getRetrofit() : Retrofit {

            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}