package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.ProductRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.CateloryBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryListFragment: BaseFragment() {

    lateinit var binding: CateloryBinding
    lateinit var mProductRecyclerAdapter: ProductRecyclerAdapter
    val mCategoryList = ArrayList<ProductData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.catelory,container, false)
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
        getItemFromServer()
        mProductRecyclerAdapter = ProductRecyclerAdapter(mContext, mCategoryList)
        binding.categoryListView.adapter = mProductRecyclerAdapter
        binding.categoryListView.layoutManager = LinearLayoutManager(mContext)


    }
    fun getItemFromServer(){
        apiList.getRequestItem().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){
                    val br = response.body()!!


                    mCategoryList.addAll(br.data.todays_hot_lists)

                    mProductRecyclerAdapter.notifyDataSetChanged()





                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}