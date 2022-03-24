package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.BarketData

class BarketRecyclerAdapter(
    val mContext: Context,
    val mList: List<BarketData>
) : RecyclerView.Adapter<BarketRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {




        fun bing(data: BarketData) {



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