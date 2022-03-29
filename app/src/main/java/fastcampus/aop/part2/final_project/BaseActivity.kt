package fastcampus.aop.part2.final_project

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

        supportActionBar?.let {
            setCustomActionBar()
        }
    }



    override fun setTitle(title: CharSequence?) {

        txtTitle.text = title

    }

    abstract fun setupEvent()
    abstract fun setupValues()

    private fun setCustomActionBar(){
        val CustomActionBar = supportActionBar!!
        CustomActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        CustomActionBar.setCustomView(R.layout.custom_action_bar)
        val toolbar = CustomActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)

        val customBar = CustomActionBar.customView
        txtTitle = customBar.findViewById(R.id.txtTitle)
        btnAdd = customBar.findViewById(R.id.btnAdd)
    }
}