package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.MyInfoData

class MyInfoAdapter(
    val mContext: Context,
    resId: Int,
    val mList: ArrayList<MyInfoData>
) : ArrayAdapter<MyInfoData>(mContext, resId, mList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.my_info_list, null)

        }
        val row = tempRow!!
        val data = mList[position]




        return row
    }
}