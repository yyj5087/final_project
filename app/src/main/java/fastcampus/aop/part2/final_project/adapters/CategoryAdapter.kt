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

class CategoryAdapter(
    val mContext: Context,
    resId: Int,
    val mList: ArrayList<CategoryData>
) : ArrayAdapter<CategoryData>(mContext, resId, mList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.view_category, null)

        }
        val row = tempRow!!
        val data = mList[position]

        val categoryImg = row.findViewById<ImageView>(R.id.categoryImg)
        val categoryName = row.findViewById<TextView>(R.id.categoryName)
        val categoryPrice = row.findViewById<TextView>(R.id.categoryPrice)
        val ratingBar = row.findViewById<ScaleRatingBar>(R.id.ratingBar)
        val txtRating = row.findViewById<TextView>(R.id.txtRating)
        categoryName.text = data.name
        categoryPrice.text = data.price.toString()

        Glide.with(mContext).load(data.categoryImageURL).into(categoryImg)
        ratingBar.rating = data.rating.toFloat()
        txtRating.text = data.rating.toString()




        return row
    }
}