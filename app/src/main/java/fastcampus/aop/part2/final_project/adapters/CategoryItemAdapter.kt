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
import fastcampus.aop.part2.final_project.datas.CategoryData
import fastcampus.aop.part2.final_project.datas.CategoryItemData

class CategoryItemAdapter(
    val mContext: Context,
    resId: Int,
    val mList: ArrayList<CategoryItemData>
) : ArrayAdapter<CategoryItemData>(mContext, resId, mList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.category_list_item, null)

        }
        val row = tempRow!!
        val data = mList[position]

        val categoryLogo = row.findViewById<ImageView>(R.id.categoryLogo)
        val goodsItemName = row.findViewById<TextView>(R.id.goodsItemName)

        Glide.with(mContext).load(data.categoryItemImageURL).into(categoryLogo)
        goodsItemName.text = data.name






        return row
    }
}