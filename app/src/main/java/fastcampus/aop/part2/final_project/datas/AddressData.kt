package fastcampus.aop.part2.final_project.datas

import com.google.gson.annotations.SerializedName

class AddressData(

    var oldAddress: String,
    var roadAddress: String,
    var zipCode: String,

) {

    constructor() : this("", "", "")

}