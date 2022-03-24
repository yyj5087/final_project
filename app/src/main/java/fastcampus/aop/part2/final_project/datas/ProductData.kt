package fastcampus.aop.part2.final_project.datas

import java.io.Serializable

class ProductData(
    val id: Int,
    val name: String,
    val icon_url: String,
    val large_category_id: Int,
    val original_price: Int,
    val sale_price:  Int,



) : Serializable{
}