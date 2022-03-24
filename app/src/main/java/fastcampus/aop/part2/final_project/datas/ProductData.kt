package fastcampus.aop.part2.final_project.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class ProductData(
    val id: Int,
    val name: String,
    val icon_url: String,
    val large_category_id: Int,
    val original_price: Int,
    val sale_price:  Int,
    val index: Int,
    val product_id: Int,
    val image_url: String,
    val description: String,
    val description_content: String,
    val created_at: String,
    val option_id: Int,





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