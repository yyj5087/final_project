package fastcampus.aop.part2.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.adapters.MainViewPager2Adapter
import fastcampus.aop.part2.final_project.databinding.ActivityMainBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupEvent()
        setupValues()
    }

    override fun setupEvent() {

    }

    override fun setupValues() {

        binding.mainViewPager2.adapter = MainViewPager2Adapter(this)


    }
}