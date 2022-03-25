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

        mProductData = intent.getSerializableExtra("name") as ProductData
        mProductData = intent.getSerializableExtra("original_price") as ProductData
        mProductData = intent.getSerializableExtra("image_url") as ProductData




        setupEvent()
        setupValues()

    }

    override fun setupEvent() {



    }

    override fun setupValues() {








    }
}