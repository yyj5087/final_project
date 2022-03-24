package fastcampus.aop.part2.final_project

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.adapters.BarketRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.ActivityManageCartBinding
import fastcampus.aop.part2.final_project.datas.BarketData
import fastcampus.aop.part2.final_project.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageCartActivity : BaseActivity() {

    lateinit var binding: ActivityManageCartBinding

    val mCartList = ArrayList<BarketData>()
    lateinit var mCartAdapter: BarketRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_manage_cart)

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

    }

    override fun setupValues() {

    }
    fun getRequestCartFromServer(){
        apiList.getRequestCartList().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val br = response.body()!!

                    mCartList.clear()


                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}