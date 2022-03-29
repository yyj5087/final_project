package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.ShipmentInfoData

class ShipmentInfoAdapter(
    val mContext: Context,
    val mList:List<ShipmentInfoData>) : RecyclerView.Adapter<ShipmentInfoAdapter.ViewHolder>() {

    interface OnItemClick {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClick {
        fun onItemLongClick(position: Int)
    }

    var oic : OnItemClick? = null
    var oilc : OnItemLongClick? = null

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.oic = onItemClick
    }

    fun setOnItemLongClick(onItemLongClick: OnItemLongClick) {
        this.oilc = onItemLongClick
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val txtReceiverName = view.findViewById<TextView>(R.id.txtReceiverName)
        val txtIsBasicShipment = view.findViewById<TextView>(R.id.txtIsBasicShipment)
        val txtAddress = view.findViewById<TextView>(R.id.txtAddress)
        val txtPhoneNum = view.findViewById<TextView>(R.id.txtPhoneNum)

        fun bind(data: ShipmentInfoData, position: Int) {

            txtReceiverName.text = data.name
            if (data.is_basic_address) {
                txtIsBasicShipment.visibility = View.VISIBLE
            }
            else {
                txtIsBasicShipment.visibility = View.GONE
            }

            txtAddress.text = "${data.address1} ${data.address2}"

            txtPhoneNum.text = data.phone

            if (oic != null) {
                view.setOnClickListener {
                    oic!!.onItemClick(position)
                }
            }


            if (oilc != null) {
                view.setOnLongClickListener {
                    oilc!!.onItemLongClick(position)
                    return@setOnLongClickListener true
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.shipment_info_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position], position)
    }

    override fun getItemCount() = mList.size

}