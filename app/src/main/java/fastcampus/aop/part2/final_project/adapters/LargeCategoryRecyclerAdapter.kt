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
import fastcampus.aop.part2.final_project.datas.LargeCategoryData

class LargeCategoryRecyclerAdapter(
    val mContext: Context,
    val mList: List<LargeCategoryData>
) : RecyclerView.Adapter<LargeCategoryRecyclerAdapter.ViewHolder>() {

    interface OnItemClick {
        fun onItemClick(position: Int)
    }
    interface OnItemLongClick{
        fun onItemLongClick(position: Int)
    }
    var oic : OnItemClick? = null
    var oilc : OnItemLongClick? = null

    fun setOnItemClick(onItemClick: OnItemClick){
        this.oic = onItemClick
    }
    fun setOnItemLongClick(onItemLongClick: OnItemLongClick){
        this.oilc = onItemLongClick
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val LargecategoryLogo = view.findViewById<ImageView>(R.id.LargecategoryLogo)
        val LargeGoodsItemName = view.findViewById<TextView>(R.id.LargeGoodsItemName)


        fun bing(data: LargeCategoryData, position: Int) {

            Glide.with(mContext).load(data.icon_url).into(LargecategoryLogo)
            LargeGoodsItemName.text = data.name

            if (oic != null){
                view.setOnClickListener {
                    oic!!.onItemClick(position)
                }
            }
            if (oilc != null){
                view.setOnLongClickListener {
                    oilc!!.onItemLongClick(position)
                    return@setOnLongClickListener true
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(mContext).inflate(R.layout.category_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bing(mList[position], position)
    }

    override fun getItemCount() = mList.size

}