package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.CategoryAdapter
import fastcampus.aop.part2.final_project.databinding.CategoryListItemBinding
import fastcampus.aop.part2.final_project.databinding.CateloryBinding
import fastcampus.aop.part2.final_project.datas.CategoryData

class CategoryItemListFragment: BaseFragment() {

    lateinit var binding: CategoryListItemBinding
    lateinit var mCategoryAdapter: CategoryAdapter
    val mCategoryList = ArrayList<CategoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_list_item,container, false)
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