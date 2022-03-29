package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.ViewDetailItemInfoActivity
import fastcampus.aop.part2.final_project.datas.ProductData

class ProductRecyclerAdapter(
    val mContext: Context,
    val mList: List<ProductData>
) : RecyclerView.Adapter<ProductRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName = view.findViewById<TextView>(R.id.categoryName)
        val categoryPrice = view.findViewById<TextView>(R.id.categoryPrice)
        val productImg = view.findViewById<ImageView>(R.id.productImg)

        fun bing(data: ProductData) {

            categoryName.text = data.name
            categoryPrice.text = data.original_price.toString()
            categoryPrice.text = "${data.getFormattedPrice()}원"


            Glide.with(mContext).load(data.product_main_images[0].image_url).into(productImg)

            itemView.setOnClickListener {
                val myIntent = Intent(mContext, ViewDetailItemInfoActivity::class.java)
                myIntent.putExtra("product", data)
                mContext.startActivity(myIntent)


            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(mContext).inflate(R.layout.view_category, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bing(data)
    }

    override fun getItemCount() = mList.size

}