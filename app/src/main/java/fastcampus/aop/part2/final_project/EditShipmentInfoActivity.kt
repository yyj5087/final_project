package fastcampus.aop.part2.final_project

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part2.final_project.adapters.AddressSelectRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.ActivityEditShipmentInfoBinding
import fastcampus.aop.part2.final_project.datas.AddressData
import fastcampus.aop.part2.final_project.datas.BasicResponse
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class EditShipmentInfoActivity : BaseActivity() {

    lateinit var binding: ActivityEditShipmentInfoBinding

    var mSelectedAddressData : AddressData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_shipment_info)

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

        binding.btnSave.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("배송지 저장")
            alert.setMessage("정말 배송지를 저장 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                val inputName = binding.edtReceiverName.text.toString()
                if (inputName.length < 2) {
                    Toast.makeText(mContext, "이름은 최소 2자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }

                if (mSelectedAddressData == null) {
                    Toast.makeText(mContext, "배송지 주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }

                val inputAddress1 = binding.txtAddress1.text.toString()
                val inputAddress2 = binding.edtAddress2.text.toString()

                val inputPhone = binding.edtPhone.text.toString()

                if (inputPhone.length < 5) {
                    Toast.makeText(mContext, "연락처는 최소 5자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                val isBasicShipmentInfo = binding.basicAddressCheckBox.isChecked

                val addressData = mSelectedAddressData!!
                apiList.postRequestAddShipmentInfo(
                    inputName,
                    inputPhone,
                    addressData.zipCode,
                    inputAddress1,
                    inputAddress2,
                    isBasicShipmentInfo,
                    ""
                ).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(mContext, "배송지 저장에 성공했습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })

            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }

        binding.btnSearchAddress.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            val customView = LayoutInflater.from(mContext)
                .inflate(R.layout.my_custom_alert_select_address, null)
            val addressSelectRecyclerView =
                customView.findViewById<RecyclerView>(R.id.addressSelectRecyclerView)

            val addressList = ArrayList<AddressData>()
            val addressAdapter = AddressSelectRecyclerAdapter(mContext, addressList)
            addressSelectRecyclerView.adapter = addressAdapter
            addressSelectRecyclerView.layoutManager = LinearLayoutManager(mContext)


            val edtAddress1Keyword = customView.findViewById<EditText>(R.id.edtAddress1Keyword)
            val btnSearch = customView.findViewById<Button>(R.id.btnSearch)

            btnSearch.setOnClickListener {

                val inputKeyword = edtAddress1Keyword.text.toString()


                val urlBuilder = "https://dapi.kakao.com/v2/local/search/address.json".toHttpUrlOrNull()!!.newBuilder()
                urlBuilder.addEncodedQueryParameter("query", inputKeyword)
                urlBuilder.addEncodedQueryParameter("size", 30.toString())

                val request = Request.Builder()
                    .url(urlBuilder.toString())
                    .get()
                    .header("Authorization", "KakaoAK 74c28e8fb2324f6c693626ce6972288f")
                    .build()

                val client = OkHttpClient()
                client.newCall(request).enqueue(object : okhttp3.Callback {
                    override fun onFailure(call: okhttp3.Call, e: IOException) {

                    }

                    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                        if (response.isSuccessful) {

                            val jsonObj = JSONObject(response.body!!.string())
                            Log.d("jsonObj", jsonObj.toString())

                            addressList.clear()
                            val documentsArr = jsonObj.getJSONArray("documents")
                            for (i in  0 until  documentsArr.length()) {

                                val documentObj = documentsArr.getJSONObject(i)
                                val addressData = AddressData()
                                if (!documentObj.isNull("address")) {
                                    val addressObj = documentObj.getJSONObject("address")
                                    addressData.oldAddress = addressObj.getString("address_name")
                                }
                                else {
                                    addressData.oldAddress = "지번 주소 없음"
                                }

                                val roadAddressObj = documentObj.getJSONObject("road_address")

                                addressData.roadAddress = roadAddressObj.getString("address_name")

                                if (roadAddressObj.getString("building_name") != "") {
                                    addressData.roadAddress += (" " + roadAddressObj.getString("building_name"))
                                }

                                addressData.zipCode = roadAddressObj.getString("zone_no")

                                if (addressData.zipCode != "") {
                                    addressList.add(addressData)
                                }



                            }

                            runOnUiThread {
                                if (addressList.isEmpty()) {
                                    Toast.makeText(mContext, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                                }
                                addressAdapter.notifyDataSetChanged()
                            }


                        }
                    }

                })

            }

            alert.setView(customView)

            val dialog = alert.show()


            addressAdapter.onItemClickListener = object : AddressSelectRecyclerAdapter.OnItemClickListener {
                override fun onItemClick(data: AddressData) {

                    mSelectedAddressData = data
                    binding.address2Layout.visibility = View.VISIBLE
                    binding.txtAddress1.text = data.roadAddress
                    binding.txtAddress1.setTextColor(ContextCompat.getColor(mContext, R.color.black))

                    dialog.dismiss()
                }

            }


        }
        }

    override fun setupValues() {

    }
}