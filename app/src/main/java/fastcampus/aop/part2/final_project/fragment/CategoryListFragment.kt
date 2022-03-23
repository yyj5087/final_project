package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        binding = DataBindingUtil.inflate(inflater, R.layout.catelory,container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvent()
        setupValue()

    }



    override fun setupEvent() {

    }

    override fun setupValue() {

        mCategoryList.add(CategoryData("소니 본체 슬림 2218B 1TB MEGA 패키지 게임 3종 + PSN3 PS4", 679000, 5.0, "https://cpng.io/wp-content/uploads/32222939613a1da7dbded36a8f4d9213db8670c55d54563f875869ea019b-1200x1200.jpg"))
        mCategoryList.add(CategoryData("휘슬주전자", 16000, 4.5, "https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/rs_quotation_api/uq5ppala/de4c63bc29fb428b941fcec3349f4ce9.jpg"))
        mCategoryList.add(CategoryData("남성 봄 자켓", 56000, 3.5, "https://m.min2i.com/web/product/big/20200210/2ef31dbdc57001def88c08bc2677f7f3.jpg"))
        mCategoryList.add(CategoryData("게이밍 노트북", 1000000, 5.0, "https://www.thegear.kr/news/photo/202006/24237_35171_5919.jpg"))






        mCategoryAdapter = CategoryAdapter(requireContext(), R.layout.view_category, mCategoryList)
        binding.categoryListView.adapter = mCategoryAdapter


    }

}