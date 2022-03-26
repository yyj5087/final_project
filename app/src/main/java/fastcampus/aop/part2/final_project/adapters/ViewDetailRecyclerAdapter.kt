package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.ViewDetailItemInfoActivity
import fastcampus.aop.part2.final_project.datas.ProductData

class ViewDetailRecyclerAdapter(
    val mContext: Context,
    val mList: List<ProductData>
) : RecyclerView.Adapter<ViewDetailRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val viewDetailImg = view.findViewById<ImageView>(R.id.viewDetailImg)
        val viewDetailPrice = view.findViewById<TextView>(R.id.viewDetailPrice)
        val viewDetailPrice2 = view.findViewById<TextView>(R.id.viewDetailPrice2)


        fun bing(data: ProductData) {


            Glide.with(mContext).load(data.product_main_images[0].image_url).into(viewDetailImg)

            itemView.setOnClickListener {
                val myIntent = Intent(mContext, ViewDetailItemInfoActivity::class.java)
                myIntent.putExtra("product", data)
                mContext.startActivity(myIntent)


            }

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