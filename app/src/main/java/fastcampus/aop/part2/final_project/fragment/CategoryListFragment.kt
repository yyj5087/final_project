package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.CategoryAdapter
import fastcampus.aop.part2.final_project.databinding.CateloryBinding
import fastcampus.aop.part2.final_project.datas.CategoryData

class CategoryListFragment: BaseFragment() {

    lateinit var binding: CateloryBinding
    lateinit var mCategoryAdapter: CategoryAdapter
    val mCategoryList = ArrayList<CategoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catelory,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvent()
        setupValue()

    }



    override fun setupEvent() {

    }

    override fun setupValue() {

        mCategoryList.add(CategoryData("플레이스테이션4", 300000, 5.0, "https://search.naver.com/search.naver?where=image&sm=tab_jum&query=%ED%94%8C%EB%A0%88%EC%9D%B4%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%85%984#imgId=image_sas%3Awebhttp%3A%2F%2Fwww.enuri.com%2Fdetail.jsp%3Fmodelno%3D12276201_80870606"))

        mCategoryAdapter = CategoryAdapter(requireContext(), R.layout.view_category, mCategoryList)
        binding.categoryListView.adapter = mCategoryAdapter


    }

}