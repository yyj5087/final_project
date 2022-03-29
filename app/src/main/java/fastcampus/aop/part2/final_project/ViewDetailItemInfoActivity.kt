package fastcampus.aop.part2.final_project

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import fastcampus.aop.part2.final_project.adapters.ImageSlideAdapter
import fastcampus.aop.part2.final_project.adapters.OptionValuesSpinnerAdapter
import fastcampus.aop.part2.final_project.databinding.ActivityViewDetailItemInfoBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.ProductData
import fastcampus.aop.part2.final_project.datas.ProductOptionValueData
import fastcampus.aop.part2.final_project.utils.WonFormatUtil
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewDetailItemInfoActivity : BaseActivity() {

    lateinit var binding: ActivityViewDetailItemInfoBinding
    lateinit var mProductData: ProductData

    val mFragmentList = ArrayList<Fragment>()

    var buyQuantity = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_detail_item_info)
        mProductData = intent.getSerializableExtra("product") as ProductData
        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

        val selectedOptionJsonArr = JSONArray()

        for(view in binding.optionListLayout.children){
            val txtOptionName = view.findViewById<TextView>(R.id.txtOptionName)
            val optionsValuesListSpinner = view.findViewById<Spinner>(R.id.optionValuesListSpinner)

            val selectedValue = optionsValuesListSpinner.selectedItem as ProductOptionValueData
            Log.d("선택한옵션값", selectedValue.id.toString())

            val jsonObj = JSONObject()
            jsonObj.put("option_id", txtOptionName.tag.toString().toInt())
            jsonObj.put("selected_value_id", selectedValue.id)

            selectedOptionJsonArr.put(jsonObj)
        }
        Log.d("앱에서만든 JSON Str", selectedOptionJsonArr.toString())


        binding.btnAddItem.setOnClickListener {

//            선택된 갯수를 Int로
            val quantity = binding.txtQuantity.text.toString().toInt()
            apiList.getRequestAddItem(
                mProductData.id,
                buyQuantity,
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
                    else{
                        Toast.makeText(mContext, "장바구니 등록에 실패했습니다. 관리자에게 문의해주세요.", Toast.LENGTH_SHORT).show()
                    }


                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })



        }
        binding.plus.setOnClickListener {
            if(buyQuantity < 5) {
                buyQuantity += 1
                setQuantityAndPriceTxt()
            }
            else{
                Toast.makeText(mContext, "최대 5개까지만 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.minus.setOnClickListener {
            if(buyQuantity > 1) {
                buyQuantity -= 1
                setQuantityAndPriceTxt()
            }
            else{
                Toast.makeText(mContext, "최소 1개 이상은 구매해야 합니다.", Toast.LENGTH_SHORT).show()
            }
        }



    }
    private fun setQuantityAndPriceTxt() {
        binding.txtOriginalPrice.text = WonFormatUtil.getWonFormat(mProductData.original_price * buyQuantity)
        binding.txtOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG or binding.txtOriginalPrice.paintFlags
        binding.txtSalePrice.text = WonFormatUtil.getWonFormat(mProductData.sale_price * buyQuantity)
        val salePercent = (((mProductData.original_price - mProductData.sale_price).toDouble() / mProductData.original_price.toDouble()) * 100).toInt()

        binding.txtSalePercent.text = "${salePercent}%"

        binding.txtQuantity.text = buyQuantity.toString()
    }

    override fun setupValues() {




        binding.txtProductName.text = mProductData.name
        setProductDataToUI()


        getProductDetailFromServer()
    }
    private fun setProductDataToUI(){
        setQuantityAndPriceTxt()

        val imageAdapter = ImageSlideAdapter(mContext, mProductData.product_main_images)
        binding.productThumbnailViewPager.adapter = imageAdapter

        for (option in mProductData.product_options){

            val row = LayoutInflater.from(mContext).inflate(R.layout.option_list_item, null)
            val txtOptionName = row.findViewById<TextView>(R.id.txtOptionName)
            val optionsValuesListSpinner = row.findViewById<Spinner>(R.id.optionValuesListSpinner)

            txtOptionName.text = option.name
            txtOptionName.tag = option.id
            optionsValuesListSpinner.adapter = OptionValuesSpinnerAdapter(mContext, option.option_values)

            binding.optionListLayout.addView(row)
        }


    }
    private fun getProductDetailFromServer(){
        apiList.getRequestProductDetail(
            mProductData.id
        ).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    mProductData = response.body()!!.data.product

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
    private inner class PagerAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc){
        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            return mFragmentList[position]
        }

    }
}