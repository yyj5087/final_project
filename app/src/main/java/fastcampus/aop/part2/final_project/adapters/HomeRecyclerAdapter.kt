package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.BannersData
import fastcampus.aop.part2.final_project.datas.LargeCategoryData
import fastcampus.aop.part2.final_project.datas.ProductData
import fastcampus.aop.part2.final_project.utils.WonFormatUtil
import java.util.*
import java.util.logging.Handler

class HomeRecyclerAdapter(
    val mContext: Context,
    val mBannerList: List<BannersData>,
    val mList: List<ProductData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClick {
        fun onItemClick(position: Int)
    }
    interface OnItemLongClick{
        fun onItemLongClick(position: Int)
    }
    var oic : OnItemClick? = null
    var oilc : OnItemLongClick? = null

    var isBannerViewPagerInit = false
    lateinit var mBannerSlideAdapter : BannerSlideAdapter



    inner class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val bannerViewPager = view.findViewById<ViewPager2>(R.id.bannerViewPager)
        val dotsIndicator = view.findViewById<DotsIndicator>(R.id.dotsIndicator)


        fun bing() {
            mBannerSlideAdapter = BannerSlideAdapter(
                mContext,
                mBannerList
            )
            bannerViewPager.adapter = mBannerSlideAdapter
            dotsIndicator.setViewPager2(bannerViewPager)

            if(!isBannerViewPagerInit){

                var currentPage = 0

                val nextPage = {

                    currentPage++

                    if(currentPage == mBannerList.size){
                        currentPage = 0
                    }
                    bannerViewPager.currentItem = currentPage
                }
                val myHandler = android.os.Handler(Looper.getMainLooper())

                val timer = Timer()
                timer.schedule(object  : TimerTask(){
                    override fun run() {
                        myHandler.post(nextPage)
                    }

                }, 2000, 2000)
                isBannerViewPagerInit = true
            }


        }

    }
    inner class TodayHotViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val imgProductThumbnail = view.findViewById<ImageView>(R.id.imgProductThumbnail)
        val imgLargeCategory = view.findViewById<ImageView>(R.id.imgLargeCategory)
        val txtSmallCategoryName = view.findViewById<TextView>(R.id.txtSmallCategoryName)
        val txtProductName = view.findViewById<TextView>(R.id.txtProductName)
        val txtSalePrice = view.findViewById<TextView>(R.id.txtSalePrice)

        fun bind(data: ProductData, position: Int){

            Glide.with(mContext).load(data.product_main_images[0].image_url).into(imgProductThumbnail)
            Glide.with(mContext).load(data.large_category_info.icon_url).into(imgLargeCategory)

            txtSmallCategoryName.text = data.small_category_info.name
            txtSalePrice.text = WonFormatUtil.getWonFormat(data.sale_price)
            txtProductName.text = data.name

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
    val HEADER_VIEW_TYPE = 1000
    val TODAY_HOT_ITEM_TYPE = 1001

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> HEADER_VIEW_TYPE
            else -> TODAY_HOT_ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            HEADER_VIEW_TYPE -> {

                val view =
                    LayoutInflater.from(mContext).inflate(R.layout.main_banner_layout, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(mContext).inflate(R.layout.today_hot_product_list_item, parent, false)
                TodayHotViewHolder(view)
            }
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bing()

            }
            is TodayHotViewHolder -> {

                holder.bind(mList[position - 1], position-1)

            }
        }
    }

    override fun getItemCount() = mList.size

}