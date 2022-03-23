package fastcampus.aop.part2.final_project.datas

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class CategoryData(

    val name: String,
    val price: Int,
    val rating: Double,
    val categoryImageURL: String,


) : Serializable {

    fun getFormattedPrice(): String{
        if(this.price < 100000000){
            return NumberFormat.getNumberInstance(Locale.KOREA).format(this.price)
        }
        else{

            val rest = this.price % 100000000

            return "${NumberFormat.getInstance(Locale.KOREA).format(rest)}"
        }
    }
}