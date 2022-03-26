package fastcampus.aop.part2.final_project.datas

import fastcampus.aop.part2.final_project.datas.prodictionfos.productinfosData
import fastcampus.aop.part2.final_project.datas.product.LargeCategoryData
import fastcampus.aop.part2.final_project.datas.product.ProductData
import java.io.Serializable

class DataResponse(
        val user: UserData,
        val token: String,

        val todays_hot_lists: List<ProductData>,
        val large_categories: List<LargeCategoryData>,
        val carts: List<productinfosData>,
) : Serializable{
}