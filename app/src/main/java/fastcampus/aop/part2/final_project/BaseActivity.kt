package fastcampus.aop.part2.final_project

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aop.part2.final_project.api.APIList
import fastcampus.aop.part2.final_project.api.ServerAPI

abstract class BaseActivity: AppCompatActivity() {

    lateinit var mContext: Context

    lateinit var apiList: APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiList = retrofit.create(APIList::class.java)
    }

    abstract fun setupEvent()
    abstract fun setupValues()
}