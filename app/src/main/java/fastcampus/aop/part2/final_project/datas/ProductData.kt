package fastcampus.aop.part2.final_project.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class ProductData(
    val id: Int,
    val name: String,
    val original_price: Int,
    val sale_price: Int,
    val created_at: String,
    val product_detail_images: List<ImageData>,
    val product_main_images: List<ImageData>,
    val product_options: List<ProductOptionData>,
    val index: Int,
    val icon_url: String,
    val largecategory: LargeCategoryData,







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