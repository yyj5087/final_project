package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.CategoryItemAdapter
import fastcampus.aop.part2.final_project.databinding.CatecolyBinding

import fastcampus.aop.part2.final_project.datas.CategoryItemData

class CategoryItemListFragment: BaseFragment() {

    lateinit var binding: CatecolyBinding
    lateinit var mCategoryItemAdapter: CategoryItemAdapter
    val mCategoryItemList = ArrayList<CategoryItemData>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.catecoly,container, false)
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


        mCategoryItemList.add(CategoryItemData("https://cdn2.iconfinder.com/data/icons/iot-internet-of-things-line/64/camara-wireless-iot-equipment-electronics-hardware-512.png", "가전디지털"))
        mCategoryItemList.add(CategoryItemData("https://cdn0.iconfinder.com/data/icons/women-things-1/250/mascara-512.png", "뷰티"))
        mCategoryItemList.add(CategoryItemData("https://cdn2.iconfinder.com/data/icons/free-color-halloween-icons/24/Toilet-Paper-512.png", "생활용품"))
        mCategoryItemList.add(CategoryItemData("https://cdn0.iconfinder.com/data/icons/stroke-ball-icons-2/633/02_Soccer-512.png", "스포츠레저"))
        mCategoryItemList.add(CategoryItemData("https://cdn0.iconfinder.com/data/icons/fastfood-29/64/noodle-bowl-chopsticks-food-asian-ramen-spaghetti-512.png", "식품"))
        mCategoryItemList.add(CategoryItemData("https://cdn2.iconfinder.com/data/icons/kitchenware-solid-cookery/512/Kitchen_Whisk-512.png", "주방용품"))
        mCategoryItemList.add(CategoryItemData("https://cdn2.iconfinder.com/data/icons/travel-solid-world-is-beautiful/512/Camping_tent-512.png", "캠핑"))
        mCategoryItemList.add(CategoryItemData("https://cdn2.iconfinder.com/data/icons/clothes-outline-24-px/24/Clothes_full_sleeves_shirt_shirt_t_shirt_wardrobe_1-512.png", "패션의류/잡화"))
        mCategoryItemList.add(CategoryItemData("https://cdn1.iconfinder.com/data/icons/furniture-line-modern-classy/512/chandelier-512.png", "홈인테리어"))

        mCategoryItemAdapter = CategoryItemAdapter(requireContext(), R.layout.category_list_item, mCategoryItemList)
        binding.categoryColyListView.adapter = mCategoryItemAdapter





    }

}