package fastcampus.aop.part2.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.databinding.ActivityOrderHistoryBinding

class OrderHistoryActivity : BaseActivity() {
    lateinit var binding: ActivityOrderHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_history)

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

    }

    override fun setupValues() {

    }
}