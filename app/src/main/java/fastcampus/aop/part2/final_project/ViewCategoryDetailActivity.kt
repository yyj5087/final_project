package fastcampus.aop.part2.final_project

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import fastcampus.aop.part2.final_project.adapters.ImageSlideAdapter
import fastcampus.aop.part2.final_project.adapters.OptionValuesSpinnerAdapter
import fastcampus.aop.part2.final_project.adapters.ProductAdapter
import fastcampus.aop.part2.final_project.adapters.SmallCategorySpinnerAdapter
import fastcampus.aop.part2.final_project.databinding.ActivityViewCategoryDetailBinding
import fastcampus.aop.part2.final_project.databinding.ActivityViewDetailItemInfoBinding
import fastcampus.aop.part2.final_project.datas.*
import fastcampus.aop.part2.final_project.utils.WonFormatUtil
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewCategoryDetailActivity : BaseActivity() {

    lateinit var binding: ActivityViewCategoryDetailBinding
    lateinit var mLargeCategoryData: LargeCategoryData

    val mSmallCategoryList = ArrayList<Smallcategories>()
    lateinit var mSmallCategorySpinnerAdapter: SmallCategorySpinnerAdapter

    val mProductList = ArrayList<ProductData>()
    lateinit var mProductsAdapter: ProductAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_category_detail)
        mLargeCategoryData = intent.getSerializableExtra("category") as LargeCategoryData

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {
        mProductsAdapter = ProductAdapter(mContext, mProductList)
        mProductsAdapter.setOnItemClick(object : ProductAdapter.OnItemClick{
            override fun onItemClick(position: Int) {
                val clickedProduct = mProductList[position]
                val myIntent = Intent(mContext, ViewDetailItemInfoActivity::class.java)
                myIntent.putExtra("product", clickedProduct)
                startActivity(myIntent)
            }

        })

        binding.smallCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedSmallCategoryData = mSmallCategoryList[position]
                apiList.getRequestProductsBySmallCategory(
                    selectedSmallCategoryData.id,
                    mLargeCategoryData.id
                ).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        mProductList.clear()
                        if (response.isSuccessful){
                            val br = response.body()!!
                            mProductList.addAll(br.data.products)
                        }
                        else{

                        }
                        mProductsAdapter.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }


    override fun setupValues() {
        setTitle(mLargeCategoryData.name)

        mSmallCategorySpinnerAdapter = SmallCategorySpinnerAdapter(mContext, mSmallCategoryList)
        binding.smallCategorySpinner.adapter = mSmallCategorySpinnerAdapter

        binding.productRecyclerView.adapter = mProductsAdapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(mContext)

        getSmallCategortList()
    }
    private fun getSmallCategortList(){
        apiList.getRequestSmallCategory(
            mLargeCategoryData.id
        ).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    mSmallCategoryList.clear()
                    mSmallCategoryList.add(Smallcategories(0,"전체",0))
                    mSmallCategoryList.addAll(br.data.small_categories)

                    mSmallCategorySpinnerAdapter.notifyDataSetChanged()
                }
                else{
                    Toast.makeText(mContext, "준비중인 카테고리 입니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}