package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.BannersData
import fastcampus.aop.part2.final_project.datas.ImageData

class BannerSlideAdapter(
    val mContext: Context,
    val mList:List<BannersData>) : RecyclerView.Adapter<BannerSlideAdapter.ViewHolder>() {


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val imgImage = view.findViewById<ImageView>(R.id.imgImage)

        fun bind(data: BannersData, position: Int) {

            Glide.with(mContext).load(data.img_url).into(imgImage)

            view.setOnClickListener {
                val myUri = Uri.parse(data.main_url)
                val myIntent = Intent(Intent.ACTION_VIEW, myUri)
                mContext.startActivity(myIntent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.banner_slider_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position], position)
    }

    override fun getItemCount() = mList.size

}