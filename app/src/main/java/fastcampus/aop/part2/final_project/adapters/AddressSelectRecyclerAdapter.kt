package fastcampus.aop.part2.final_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.datas.AddressData

class AddressSelectRecyclerAdapter(val mContext:Context, val mList: List<AddressData>) : RecyclerView.Adapter<AddressSelectRecyclerAdapter.PlaceViewHolder>() {

//    장소 선택 리싸이클러뷰의 클릭이벤트 =>  액티비티 or 프래그먼트 등으로 할일을 넘겨주자. (interface 활용)

    interface OnItemClickListener {
        fun onItemClick( data: AddressData)
    }

//    인터페이스를 변수로 설정.

//    null : 아이템 클릭시 할일이 없다.
//    null 아니다 : 할일이 지정된 상태다.
    var onItemClickListener : OnItemClickListener? = null


    inner class  PlaceViewHolder(val row: View) : RecyclerView.ViewHolder(row) {

        val txtRoadAddress = row.findViewById<TextView>(R.id.txtRoadAddress)
        val txtOldAddress = row.findViewById<TextView>(R.id.txtOldAddress)
        val txtZipCode = row.findViewById<TextView>(R.id.txtZipCode)

//        함수 - 실제 데이터 연동 bind

        fun bind( data: AddressData ) {

            txtRoadAddress.text = data.roadAddress
            txtOldAddress.text = data.oldAddress
            txtZipCode.text = "[${data.zipCode}]"

//            한 줄이 눌리면 할일
            row.setOnClickListener {

//                아이템클릭리스너에 적혀있는 코드 실행.
                onItemClickListener?.onItemClick( data )

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.address_list_item, parent, false)
        return  PlaceViewHolder(row)

    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {

        holder.bind( mList[position] )

    }

    override fun getItemCount() = mList.size

}