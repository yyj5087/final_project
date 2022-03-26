package fastcampus.aop.part2.final_project.datas.prodictionfos.option

import fastcampus.aop.part2.final_project.datas.prodictionfos.option.productinfo.productinfor

class option(
    val id: Int,
    val product_id: Int,
    val user_id: Int,
    val quantity: Int,
    val product_info: List<productinfor>,
    val option_info: option,
) {
}