package fastcampus.aop.part2.final_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import fastcampus.aop.part2.final_project.databinding.ActivityViewDetailItemInfoBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.ProductData
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
                        val customView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cart_bottom_sheet, null)
                        val dialog = BottomSheetDialog(mContext)

                        val customImg = customView.findViewById<ImageView>(R.id.imgProductThumbnail)
                        val imgClose = customView.findViewById<ImageView>(R.id.imgClose)
                        val btnGoCart = customView.findViewById<TextView>(R.id.btnGoCart)

                        if (mProductData.product_main_images.isNotEmpty()) {
                            Glide.with(mContext).load(mProductData.product_main_images[0].image_url).into(customImg)
                        }
                        dialog.setContentView(customView)
                        dialog.show()

                        imgClose.setOnClickListener {
                            dialog.dismiss()
                        }
                        btnGoCart.setOnClickListener {
                            val myIntent = Intent(mContext, MainActivity::class.java)
                            myIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            myIntent.putExtra("startFragmentIndex", 2)
                            startActivity(myIntent)
                        }
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