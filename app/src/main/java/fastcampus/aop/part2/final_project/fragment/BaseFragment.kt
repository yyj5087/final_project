package fastcampus.aop.part2.final_project.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import fastcampus.aop.part2.final_project.api.APIList
import fastcampus.aop.part2.final_project.api.ServerAPI

abstract class BaseFragment: Fragment() {

    lateinit var mContext: Context
    lateinit var apiList: APIList


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)

    }


    abstract fun setupEvent()
    abstract fun setupValue()

}