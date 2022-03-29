package fastcampus.aop.part2.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part2.final_project.adapters.ShipmentInfoAdapter
import fastcampus.aop.part2.final_project.databinding.ActivityShipmentInfoListBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.ShipmentInfoData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShipmentInfoListActivity : BaseActivity() {
    lateinit var binding: ActivityShipmentInfoListBinding
    val mShipmentInfoList = ArrayList<ShipmentInfoData>()
    lateinit var mAdapter: ShipmentInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_shipment_info_list)

        setupEvent()
        setupValues()
    }

    override fun setupEvent() {
        mAdapter = ShipmentInfoAdapter(mContext, mShipmentInfoList)
        mAdapter.setOnItemClick(object : ShipmentInfoAdapter.OnItemClick {
            override fun onItemClick(position: Int) {
                val clickedShipmentInfo = mShipmentInfoList[position]

                val resultIntent = Intent()

            }

        })
        btnAdd.setOnClickListener {
            val myIntent = Intent(mContext, EditShipmentInfoActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setupValues() {
        binding.shipmentInfoRecyclerView.adapter = mAdapter
        binding.shipmentInfoRecyclerView.layoutManager = LinearLayoutManager(mContext)

        btnAdd.visibility = View.VISIBLE
    }
    override fun onResume() {
        super.onResume()

        getMyShipmentInfoList()

    }
    private fun getMyShipmentInfoList() {
        apiList.getRequestShimentInfoList().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {
                    mShipmentInfoList.clear()
                    mShipmentInfoList.addAll(response.body()!!.data.user_all_address)
                    mAdapter.notifyDataSetChanged()
                }
                else {

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}