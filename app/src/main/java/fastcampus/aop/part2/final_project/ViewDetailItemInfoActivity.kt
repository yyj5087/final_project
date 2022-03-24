package fastcampus.aop.part2.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import fastcampus.aop.part2.final_project.databinding.ActivityViewDetailItemInfoBinding
import fastcampus.aop.part2.final_project.datas.CategoryData

class ViewDetailItemInfoActivity : BaseActivity() {

    lateinit var binding: ActivityViewDetailItemInfoBinding
    lateinit var mCategoryData: CategoryData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_detail_item_info)

        mCategoryData = intent.getSerializableExtra("item") as CategoryData

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

        binding.btnAddItem.setOnClickListener {



        }

    }

    override fun setupValues() {

        Glide.with(this).load(mCategoryData.categoryImageURL).into(binding.categoryImg)

        binding.categoryName.text = mCategoryData.name
        binding.ratingBar.rating = mCategoryData.rating.toFloat()
        binding.txtRating.text = "${mCategoryData.rating}"



    }
}