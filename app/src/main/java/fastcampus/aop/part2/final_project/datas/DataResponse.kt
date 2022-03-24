package fastcampus.aop.part2.final_project.datas

import java.io.Serializable

class DataResponse(
        val user: UserData,
        val token: String,

        val product: List<ProductList>,

){
}