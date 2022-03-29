package fastcampus.aop.part2.final_project.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.ViewDetailItemInfoActivity
import fastcampus.aop.part2.final_project.adapters.HomeRecyclerAdapter
import fastcampus.aop.part2.final_project.adapters.ProductRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.FragmentHomeBinding
import fastcampus.aop.part2.final_project.datas.BannersData
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryListFragment: BaseFragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var mHomeRecyclerAdapter: HomeRecyclerAdapter
    val mBannerList = ArrayList<BannersData>()
    val mTodayHotList = ArrayList<ProductData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvent()
        setupValue()

    }



    override fun setupEvent() {

        mHomeRecyclerAdapter = HomeRecyclerAdapter(mContext, mBannerList, mTodayHotList)
        mHomeRecyclerAdapter.oic = object : HomeRecyclerAdapter.OnItemClick {
            override fun onItemClick(position: Int) {
                val clickTodayHotProduct = mTodayHotList[position]

                val myIntent = Intent(mContext, ViewDetailItemInfoActivity::class.java)
                myIntent.putExtra("product", clickTodayHotProduct)
                startActivity(myIntent)
            }

        }



    }

    override fun setupValue() {


        binding.homeRecyclerView.adapter = mHomeRecyclerAdapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(mContext)
        getItemFromServer()

    }
    fun getItemFromServer(){
        apiList.getRequestItem().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){
                    val br = response.body()!!

                    mTodayHotList.clear()
                    mTodayHotList.addAll(br.data.todays_hot_lists)

                    mHomeRecyclerAdapter.notifyDataSetChanged()

                    apiList.getRequestBanner().enqueue(object : Callback<BasicResponse>{
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if (response.isSuccessful){
                                mBannerList.clear()
                                mBannerList.addAll(response.body()!!.data.banners)
                                mHomeRecyclerAdapter.notifyDataSetChanged()
                            }
                        }

                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                        }

                    })




                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}