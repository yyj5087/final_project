package fastcampus.aop.part2.final_project.utils

import java.text.NumberFormat

class WonFormatUtil {
    
    companion object {
        fun getWonFormat(money: Int) : String {
            return "${NumberFormat.getNumberInstance().format(money)}원"
        }
    }
}