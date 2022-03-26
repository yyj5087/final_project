package fastcampus.aop.part2.final_project.datas.prodictionfos

import fastcampus.aop.part2.final_project.datas.prodictionfos.option.option
import fastcampus.aop.part2.final_project.datas.prodictionfos.productInfo.productinfo
import java.io.Serializable

class productinfosData(
    val id: Int,
    val product_id: Int,
    val user_id: Int,
    val quantity: Int,
    val product_info: List<productinfo>,
    val option_info: List<option>,

) : Serializable {
}