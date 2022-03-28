package fastcampus.aop.part2.final_project.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class CartItemData(
    val id: Int,
    val product_id: Int,
    val user_id: Int,
    val quantity: Int,
    val originalprice: Int,
    val saleprice: Int,
    val createdat: String,
    val icon_url: String,
    val image_url: String,
    val index: Int,
    val large_category_id: Int,
    val name: String,
    val product_info: ProductData,






) : Serializable{

    fun getFormattedPrice(): String{
        if(this.originalprice < 100000000){
            return NumberFormat.getNumberInstance(Locale.KOREA).format(this.originalprice)
        }
        else{

            val rest = this.originalprice % 100000000

            return "${NumberFormat.getInstance(Locale.KOREA).format(rest)}"
        }
    }

}