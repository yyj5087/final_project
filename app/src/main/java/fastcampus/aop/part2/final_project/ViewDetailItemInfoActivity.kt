package fastcampus.aop.part2.final_project

import android.os.Bundle
import android.widget.Toast
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

        binding.plus.setOnClickListener {
            val quantity = binding.number.text.toString().toInt()

            binding.number.text = "${quantity+1}"

        }
        binding.minus.setOnClickListener {
            val quantity = binding.number.text.toString().toInt()

            binding.number.text = "${quantity-1}"
        }


        binding.btnAddItem.setOnClickListener {

//            선택된 갯수를 Int로
            val quantity = binding.number.text.toString().toInt()
            apiList.getRequestAddItem(
                mProductData.id,
                quantity,
                "[]" // 아직 옵션 관련된 UI가 없으니, 우선 비어있는 []로


            )
            Toast.makeText(mContext, "장바구니에 물건이 담겼습니다.", Toast.LENGTH_SHORT).show()
        }



    }

    override fun setupValues() {


        Glide.with(mContext).load(mProductData.product_main_images[0].image_url).into(binding.categoryImg)
        binding.categoryName.text = mProductData.name
        binding.productPrice.text = mProductData.original_price.toString()
        binding.productPrice.text = mProductData.getFormattedPrice()








    }
}