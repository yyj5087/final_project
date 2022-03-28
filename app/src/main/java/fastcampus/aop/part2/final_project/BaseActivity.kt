package fastcampus.aop.part2.final_project

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aop.part2.final_project.api.APIList
import fastcampus.aop.part2.final_project.api.ServerAPI

abstract class BaseActivity: AppCompatActivity() {

    lateinit var mContext: Context

    lateinit var apiList: APIList
    lateinit var txtTitle: TextView
    lateinit var btnAdd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)
    }

    override fun setTitle(title: CharSequence?) {
//        super.setTitle(title)

        txtTitle.text = title
    }

    abstract fun setupEvent()
    abstract fun setupValues()
}