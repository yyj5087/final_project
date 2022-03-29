package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.Smallcategories

class SmallCategorySpinnerAdapter(
    val mContext: Context,
    val mList: List<Smallcategories>
) : ArrayAdapter<Smallcategories>(mContext, R.layout.small_category_spinner_list_item, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row== null) {
            row = LayoutInflater.from(mContext).inflate(R.layout.small_category_spinner_list_item, null)
        }
        row!!

        val data = mList[position]

        val txtCategoryName = row.findViewById<TextView>(R.id.txtCategoryName)
        txtCategoryName.text = data.name

        return row
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

}