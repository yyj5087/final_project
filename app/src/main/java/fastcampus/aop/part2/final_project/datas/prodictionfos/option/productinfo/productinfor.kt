package fastcampus.aop.part2.final_project.datas.prodictionfos.option.productinfo

import fastcampus.aop.part2.final_project.datas.prodictionfos.option.productinfo.category.large_category_info
import fastcampus.aop.part2.final_project.datas.prodictionfos.option.productinfo.category.product_detail_images
import fastcampus.aop.part2.final_project.datas.prodictionfos.option.productinfo.category.product_main_images
import fastcampus.aop.part2.final_project.datas.prodictionfos.option.productinfo.category.small_category_info
import fastcampus.aop.part2.final_project.datas.prodictionfos.productInfo.lci.product_infos
import fastcampus.aop.part2.final_project.datas.prodictionfos.productInfo.lci.product_options

class productinfor(
    val id: Int,
    val large_category_info: List<large_category_info>,
    val small_category_info: List<small_category_info>,
    val name: String,
    val original_price: Int,
    val sale_price: Int,
    val product_detail_images: List<product_detail_images>,
    val product_infos: List<product_infos>,
    val created_at: String,
    val product_options: List<product_options>,
    val product_main_images: List<product_main_images>,

) {
}