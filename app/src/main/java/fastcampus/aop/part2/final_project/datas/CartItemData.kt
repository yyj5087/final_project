package fastcampus.aop.part2.final_project.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class CartItemData(
    val id: Int,
    val name: String,
    val original_price: Int,
    val product_id: Int,
    val quantity: Int,
    val option_info_str: String,





) : Serializable{

    fun getFormattedPrice(): String{
        if(this.original_price < 100000000){
            return NumberFormat.getNumberInstance(Locale.KOREA).format(this.original_price)
        }
        else{

            val rest = this.original_price % 100000000

            return "${NumberFormat.getInstance(Locale.KOREA).format(rest)}"
        }
    }

}