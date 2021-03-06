package fastcampus.aop.part2.final_project.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.ViewCategoryDetailActivity
import fastcampus.aop.part2.final_project.ViewDetailItemInfoActivity
import fastcampus.aop.part2.final_project.adapters.LargeCategoryRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.LargecategoryItemListBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.LargeCategoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LargeCategoryFragment: BaseFragment() {

    lateinit var binding: LargecategoryItemListBinding
    lateinit var mLargeAdapter: LargeCategoryRecyclerAdapter
    val mLargeCategoryList = ArrayList<LargeCategoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.largecategory_item_list,container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvent()
        setupValue()

    }



    override fun setupEvent() {

        mLargeAdapter = LargeCategoryRecyclerAdapter(mContext, mLargeCategoryList)
        mLargeAdapter.setOnItemClick(object : LargeCategoryRecyclerAdapter.OnItemClick{
            override fun onItemClick(position: Int) {
                val clickedCategoryData = mLargeCategoryList[position]

                val myIntent = Intent(mContext, ViewCategoryDetailActivity::class.java)
                myIntent.putExtra("category", clickedCategoryData)
                startActivity(myIntent)
            }

        })
    }

    override fun setupValue() {
        getRequestLargeCategory()

        binding.largeCategoryListView.adapter = mLargeAdapter
        binding.largeCategoryListView.layoutManager = LinearLayoutManager(mContext)

    }
    fun getRequestLargeCategory(){
        apiList.getRequestLargeCategory().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    mLargeCategoryList.addAll(br.data.large_categories)
                    mLargeAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}