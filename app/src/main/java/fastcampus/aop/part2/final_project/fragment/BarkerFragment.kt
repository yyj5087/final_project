package fastcampus.aop.part2.final_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.RequestCartRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.MyAddRecyclerviewBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.prodictionfos.productinfosData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BarkerFragment: BaseFragment() {

    lateinit var binding: MyAddRecyclerviewBinding
    lateinit var mBarketAdapter: RequestCartRecyclerAdapter
    lateinit var mProductData: productinfosData
    val mCartItemList = ArrayList<productinfosData>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_add_recyclerview,container, false)
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

        getDataFromViewDetailItemInfoActivity()
        mBarketAdapter = RequestCartRecyclerAdapter(mContext, mCartItemList)
        binding.CartListView.adapter = mBarketAdapter
        binding.CartListView.layoutManager = LinearLayoutManager(mContext)



    }
    override fun onResume() {
        super.onResume()

        getDataFromViewDetailItemInfoActivity()
    }


    fun getDataFromViewDetailItemInfoActivity(){
        apiList.getRequestAddItemCheck().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful){

                    mCartItemList.clear()
                    val br = response.body()!!
                    mCartItemList.addAll(br.data.carts)
                    mBarketAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }



}