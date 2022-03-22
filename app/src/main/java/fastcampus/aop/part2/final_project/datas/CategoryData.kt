package fastcampus.aop.part2.final_project.datas

import java.io.Serializable

class CategoryData(

    val name: String,
    val price: Int,
    val rating: Double,
    val categoryImageURL: String,


) : Serializable {
}