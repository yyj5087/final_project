package fastcampus.aop.part2.final_project.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    lateinit var mContext: Context


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    abstract fun setupEvent()
    abstract fun setupValue()

}