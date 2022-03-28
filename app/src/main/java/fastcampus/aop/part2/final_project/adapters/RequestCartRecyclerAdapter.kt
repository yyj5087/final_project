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
import fastcampus.aop.part2.final_project.datas.CartItemData

class RequestCartRecyclerAdapter(
    val mContext: Context,
    val mList: List<CartItemData>
) : RecyclerView.Adapter<RequestCartRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val viewDetailImg = view.findViewById<ImageView>(R.id.viewDetailImg)
        val viewDetailPrice = view.findViewById<TextView>(R.id.viewDetailPrice)
        val viewDetailPrice2 = view.findViewById<TextView>(R.id.viewDetailPrice2)
        val viewDetailName = view.findViewById<TextView>(R.id.viewDetailName)

        fun bing(data: CartItemData) {

            Glide.with(mContext).load(data.product_info.product_detail_images).into(viewDetailImg)

            viewDetailPrice.text = data.product_info.original_price.toString()
            viewDetailPrice2.text = data.product_info.original_price.toString()
            viewDetailName.text = data.product_info.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(mContext).inflate(R.layout.my_add_item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bing(data)
    }

    override fun getItemCount() = mList.size

}