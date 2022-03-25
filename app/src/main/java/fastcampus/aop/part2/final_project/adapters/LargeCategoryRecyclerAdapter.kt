package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.ProductData

class LargeCategoryRecyclerAdapter(
    val mContext: Context,
    val mList: List<ProductData>
) : RecyclerView.Adapter<LargeCategoryRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val LargecategoryLogo = view.findViewById<ImageView>(R.id.LargecategoryLogo)
        val LargeGoodsItemName = view.findViewById<TextView>(R.id.LargeGoodsItemName)


        fun bing(data: ProductData) {

            Glide.with(mContext).load(data.icon_url).into(LargecategoryLogo)
            LargeGoodsItemName.text = data.name


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(mContext).inflate(R.layout.category_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bing(data)
    }

    override fun getItemCount() = mList.size

}