package fastcampus.aop.part2.final_project

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import fastcampus.aop.part2.final_project.adapters.MainViewPager2Adapter
import fastcampus.aop.part2.final_project.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupEvent()
        setupValues()
    }

    override fun setupEvent() {

        binding.Bottoms.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menuHome -> binding.mainViewPager2.currentItem = 0
                R.id.menuCatelgoli -> binding.mainViewPager2.currentItem = 1
                R.id.menuBarket -> binding.mainViewPager2.currentItem = 2
                R.id.menuMyProfile -> binding.mainViewPager2.currentItem = 3
            }
            return@setOnItemSelectedListener true
        }
        binding.mainViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.Bottoms.selectedItemId = when(position){
                    0 -> R.id.menuHome
                    1 -> R.id.menuCatelgoli
                    2 -> R.id.menuBarket
                    else -> R.id.menuMyProfile
                }
            }

        })

    }

    override fun setupValues() {

        binding.mainViewPager2.adapter = MainViewPager2Adapter(this)


    }
}