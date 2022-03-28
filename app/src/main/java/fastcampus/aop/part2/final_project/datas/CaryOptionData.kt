package fastcampus.aop.part2.final_project.datas

import java.io.Serializable

class CaryOptionData(
    val id: Int,
    val cart_id: Int,
    val option_id: Int,
    val value_id: Int,
    val option: ProductOptionData,
    val value: ProductOptionValueData,



) : Serializable {
}