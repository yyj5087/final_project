package fastcampus.aop.part2.final_project.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.adapters.RequestCartRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.FragmentCartBinding
import fastcampus.aop.part2.final_project.databinding.MyAddRecyclerviewBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.datas.CartItemData
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat

class BarkerFragment: BaseFragment() {

    lateinit var binding: FragmentCartBinding
    val mCartItemList = ArrayList<CartItemData>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart,container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvent()
        setupValue()

    }



    override fun setupEvent() {
        binding.selectAllCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->

          if(isChecked){
              for (i in 0 until binding.cartListLayout.childCount){
                  val row = binding.cartListLayout.getChildAt(i)
                  val checkBox = row.findViewById<CheckBox>(R.id.productCheckBox)
                  checkBox.isChecked = true
              }

          }
            else{
                for (i in 0 until  binding.cartListLayout.childCount){
                    val row = binding.cartListLayout.getChildAt(i)
                    val checkBox = row.findViewById<CheckBox>(R.id.productCheckBox)
                    checkBox.isChecked = false
                }
            }
            calculateTotalPrice()
        }
        binding.btnBuy.setOnClickListener {
            val buyCartListJsonArr = JSONArray()

            var sum = 0

            for(CartItemData in mCartItemList){
                if (CartItemData.isBuy){
                    val cartJson = JSONObject()
                    cartJson.put("product_id", CartItemData.product_info.id)
                    cartJson.put("quantity", CartItemData.quantity)
                    cartJson.put("sale_price", CartItemData.product_info.sale_price)

                    val optionJsonArr = JSONArray()
                    for (option in CartItemData.option_info){
                        val optionJsonObj = JSONObject()
                        optionJsonObj.put("option_id", option.option_id)
                        optionJsonObj.put("value_id", option.value_id)
                        optionJsonArr.put(optionJsonObj)
                    }
                    cartJson.put("option_infos", optionJsonArr)

                    sum += CartItemData.product_info.sale_price * CartItemData.quantity

                    buyCartListJsonArr.put(cartJson)

                }

            }
            var shipmentFee = 0

            if (sum < 30000){
                shipmentFee = 3000

            }
            sum += shipmentFee


        }
    }





    override fun setupValue() {

        getDataFromViewDetailItemInfoActivity()



    }


    fun getDataFromViewDetailItemInfoActivity(){
        apiList.getRequestAddItemCheck().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful){
                    binding.cartListLayout.removeAllViews()
                    mCartItemList.clear()

                    val br = response.body()!!
                    if(br.data.carts.isEmpty()){
                        binding.emptyCartLayout.visibility = View.VISIBLE
                        binding.notEmptyLayout.visibility = View.GONE
                        binding.putchaseLayout.visibility = View.GONE
                    }
                    else{
                        binding.emptyCartLayout.visibility = View.GONE
                        binding.notEmptyLayout.visibility = View.VISIBLE
                        binding.putchaseLayout.visibility = View.VISIBLE

                        mCartItemList.addAll(br.data.carts)

                        br.data.carts.forEach {
                            val row = makeCartRow(it)

                            binding.cartListLayout.addView(row)

                    }


                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

    private fun calculateTotalPrice(){

        var sum = 0
        var count = 0

        for (data in mCartItemList){
            if (data.isBuy){
                sum += data.quantity * data.product_info.sale_price
                count++
            }
        }
        binding.txtProductPrice.text = NumberFormat.getNumberInstance().format(sum)

        var shipmentFee = 0

        if (sum < 30000){
            shipmentFee = 3000
        }
        binding.txtShipmentFee.text = NumberFormat.getNumberInstance().format(shipmentFee)

        val totalPrice = sum+shipmentFee
        binding.txtTotalPrice.text = NumberFormat.getNumberInstance().format(totalPrice)


        if (count == 0){
            binding.btnBuy.isEnabled = false
            binding.btnBuy.text = "구매할 상품을 선택해주세요."
        }
        else{
            binding.btnBuy.text = "구매하기 (${count}개)"
            binding.btnBuy.isEnabled = true
        }


    }

    fun makeCartRow(data: CartItemData) : View {
        val row = LayoutInflater.from(mContext).inflate(R.layout.cart_list_item, null)

        val productCheckBox = row.findViewById<CheckBox>(R.id.productCheckBox)
        val txtSalePrice = row.findViewById<TextView>(R.id.txtSalePrice)
        val txtItemTotalPrice = row.findViewById<TextView>(R.id.txtItemTotalPrice)
        val btnDelete = row.findViewById<Button>(R.id.btnDelete)
        val cartCountSpinner = row.findViewById<Spinner>(R.id.cartCountSpinner)
        val imgProductThumbnail = row.findViewById<ImageView>(R.id.imgProductThumbnail)
        val selectedOptionsLayout = row.findViewById<LinearLayout>(R.id.selectedOptionsLayout)

        productCheckBox.text = data.product_info.name
        txtSalePrice.text = NumberFormat.getNumberInstance().format(data.product_info.sale_price)

        cartCountSpinner.setSelection(data.quantity - 1)

        if (data.product_info.product_main_images.isNotEmpty()) {
            Glide.with(mContext).load(data.product_info.product_main_images[0].image_url)
                .into(imgProductThumbnail)
        }
        for (option in data.option_info) {
            val optionRow =
                LayoutInflater.from(mContext).inflate(R.layout.selected_option_list_item, null)

            val txtSelectedOptionValue =
                optionRow.findViewById<TextView>(R.id.txtSelectedOptionValue)

            txtSelectedOptionValue.text = "- ${option.option.name} : ${option.value.name}"

            selectedOptionsLayout.addView(optionRow)
        }
        fun getItemTotalPrice(): Int {

            return if (productCheckBox.isChecked) {
                data.quantity = cartCountSpinner.selectedItemPosition + 1
                data.quantity * data.product_info.sale_price
            } else {
                0
            }


        }

        productCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->

            data.isBuy = isChecked

            txtItemTotalPrice.text = NumberFormat.getNumberInstance().format(getItemTotalPrice())
            calculateTotalPrice()
        }
        if (binding.selectAllCheckBox.isChecked){
            productCheckBox.isChecked = true
        }
        cartCountSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positon: Int, p3: Long) {

                data.quantity = positon + 1
                txtItemTotalPrice.text = NumberFormat.getNumberInstance().format(getItemTotalPrice())
                calculateTotalPrice()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        btnDelete.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("장바구니 삭제")
            alert.setMessage("정말 삭제 하겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

                apiList.getRequestDeleteItem(
                    data.id
                ).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(mContext, "장바구니에서 삭제했습니다.", Toast.LENGTH_SHORT).show()
                            getDataFromViewDetailItemInfoActivity()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })


            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }



        return row
    }


}