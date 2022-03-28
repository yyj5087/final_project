package fastcampus.aop.part2.final_project.datas

import java.io.Serializable

class ProductOptionData(
    val id: Int,
    val name: String,
    val product_id: Int,
    val option_values: List<ProductOptionValueData>

) : Serializable {
}