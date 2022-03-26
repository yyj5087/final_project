package fastcampus.aop.part2.final_project

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.databinding.ActivityViewDetailItemInfoBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.ProductData
import fastcampus.aop.part2.final_project.fragment.BarkerFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            ).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(mContext, "장바구니에 물건이 담겼습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })



        }



    }

    override fun setupValues() {





        Glide.with(mContext).load(mProductData.product_main_images[0].image_url).into(binding.categoryImg)
        binding.categoryName.text = mProductData.name
        binding.productPrice.text = mProductData.original_price.toString()
        binding.productPrice.text = mProductData.getFormattedPrice()



    }
}