package fastcampus.aop.part2.final_project.api

import retrofit2.Retrofit

class ServerAPI {

    companion object{

        private var retrofit : Retrofit? = null

        fun getRetrofit() : Retrofit {

            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .build()
            }
            return retrofit!!
        }
    }
}