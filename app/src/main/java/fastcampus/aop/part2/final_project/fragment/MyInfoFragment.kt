package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.CategoryAdapter
import fastcampus.aop.part2.final_project.databinding.BarketListItemBinding
import fastcampus.aop.part2.final_project.databinding.CateloryBinding
import fastcampus.aop.part2.final_project.databinding.MyInfoListBinding
import fastcampus.aop.part2.final_project.datas.CategoryData

class MyInfoFragment: BaseFragment() {

    lateinit var binding: MyInfoListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_info_list,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvent()
        setupValue()

    }



    override fun setupEvent() {

    }

    override fun setupValue() {




    }

}