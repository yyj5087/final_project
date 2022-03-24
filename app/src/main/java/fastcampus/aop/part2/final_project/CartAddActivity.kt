package fastcampus.aop.part2.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.databinding.ActivityCartAddBinding

class CartAddActivity : BaseActivity() {
    lateinit var binding: ActivityCartAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cart_add)
    }

    override fun setupEvent() {

    }

    override fun setupValues() {

    }
}