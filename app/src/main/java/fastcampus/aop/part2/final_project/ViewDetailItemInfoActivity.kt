package fastcampus.aop.part2.final_project

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.databinding.ActivityViewDetailItemInfoBinding
import fastcampus.aop.part2.final_project.datas.ProductData

class ViewDetailItemInfoActivity : BaseActivity() {

    lateinit var binding: ActivityViewDetailItemInfoBinding
    lateinit var mProductData: ProductData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_detail_item_info)

        mProductData = intent.getSerializableExtra("product") as ProductData




        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

        val inputKeyword = binding.number
        apiList.getRequestAddItem(
            mProductData.id,
            mProductData

        )

    }

    override fun setupValues() {


        Glide.with(mContext).load(mProductData.product_main_images[0].image_url).into(binding.categoryImg)
        binding.categoryName.text = mProductData.name
        binding.productPrice.text = mProductData.original_price.toString()
        binding.productPrice.text = mProductData.getFormattedPrice()








    }
}