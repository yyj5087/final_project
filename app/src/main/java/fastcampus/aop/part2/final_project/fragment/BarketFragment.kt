package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.CategoryAdapter
import fastcampus.aop.part2.final_project.databinding.BarketListItemBinding
import fastcampus.aop.part2.final_project.databinding.CateloryBinding
import fastcampus.aop.part2.final_project.datas.CategoryData

class BarketFragment: BaseFragment() {

    lateinit var binding: BarketListItemBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.barket_list_item,container, false)
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