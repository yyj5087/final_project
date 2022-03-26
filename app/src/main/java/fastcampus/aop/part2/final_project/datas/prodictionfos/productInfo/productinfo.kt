package fastcampus.aop.part2.final_project.datas.prodictionfos.productInfo

import fastcampus.aop.part2.final_project.datas.prodictionfos.productInfo.lci.*

class productinfo(
    val id: Int,
    val large_category_info: List<large_category_info>,
    val small_category_info: List<small_category_info>,
    val name: String,
    val original_price: Int,
    val sale_price: Int,
    val product_detail_images: List<product_detail_images>,
    val product_infos: product_infos,
    val created_at: String,
    val product_options: List<product_options>,
    val product_main_images: List<product_main_images>,
) {
}