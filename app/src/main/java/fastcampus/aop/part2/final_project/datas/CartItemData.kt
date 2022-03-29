package fastcampus.aop.part2.final_project.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class CartItemData(
    val id: Int,
    val product_id: Int,
    val user_id: Int,
    var quantity: Int,
    var isBuy: Boolean = false,
    val product_info: ProductData,
    val option_info: List<CaryOptionData>,






) : Serializable{

    fun getFormattedPrice(): String{
        if(this.product_info.original_price < 100000000){
            return NumberFormat.getNumberInstance(Locale.KOREA).format(this.product_info.original_price)
        }
        else{

            val rest = this.product_info.original_price % 100000000

            return "${NumberFormat.getInstance(Locale.KOREA).format(rest)}"
        }
    }

}