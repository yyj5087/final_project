package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.willy.ratingbar.ScaleRatingBar
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.BarketData
import fastcampus.aop.part2.final_project.datas.CategoryData
import fastcampus.aop.part2.final_project.datas.CategoryItemData

class BarketAdapter(
    val mContext: Context,
    resId: Int,
    val mList: ArrayList<BarketData>
) : ArrayAdapter<BarketData>(mContext, resId, mList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.my_add_item_list, null)

        }
        val row = tempRow!!
        val data = mList[position]




        return row
    }
}