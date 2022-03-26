package fastcampus.aop.part2.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part2.final_project.adapters.ViewDetailRecyclerAdapter
import fastcampus.aop.part2.final_project.databinding.ActivityCartAddItemBinding
import fastcampus.aop.part2.final_project.datas.ProductData

class CartAddItemActivity : BaseActivity() {
    lateinit var binding: ActivityCartAddItemBinding
    lateinit var mAdapter: ViewDetailRecyclerAdapter
    lateinit var mProductData: ProductData

    var mCartList = ArrayList<ProductData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart_add_item)

        mProductData = intent.getSerializableExtra("cartitem") as ProductData

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

    }

    override fun setupValues() {

        mAdapter = ViewDetailRecyclerAdapter(mContext, mCartList)
        binding.cartListRecyclerView.adapter = mAdapter
        binding.cartListRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }
}