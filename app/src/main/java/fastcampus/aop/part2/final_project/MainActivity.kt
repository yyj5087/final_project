package fastcampus.aop.part2.final_project

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import fastcampus.aop.part2.final_project.adapters.MainViewPager2Adapter
import fastcampus.aop.part2.final_project.databinding.ActivityMainBinding
import fastcampus.aop.part2.final_project.fragment.BarkerFragment
import fastcampus.aop.part2.final_project.fragment.CategoryListFragment
import fastcampus.aop.part2.final_project.fragment.LargeCategoryFragment
import fastcampus.aop.part2.final_project.fragment.MyInfoFragment

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    var startFragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupEvent()
        setupValues()
    }

    override fun setupEvent() {

    }

    override fun setupValues() {
        binding.mainViewPager2.offscreenPageLimit = 4
        binding.mainViewPager2.adapter = PagerAdapter(supportFragmentManager, lifecycle)
        binding.mainViewPager2.registerOnPageChangeCallback(PageChangeCallback())
        binding.Bottoms.setOnItemSelectedListener { MenuSelected(it) }

        startFragmentIndex = intent.getIntExtra("startFragmentIndex", 0)
        binding.Bottoms.selectedItemId = when(startFragmentIndex){
            0 -> R.id.menuHome
            1 -> R.id.menuCatelgoli
            2 -> R.id.menuBarket
            else -> R.id.menuMyProfile
        }

    }
    private inner class PagerAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc){
        override fun getItemCount() = 4
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> CategoryListFragment()
                1 -> LargeCategoryFragment()
                2 -> BarkerFragment()
                else -> MyInfoFragment()
            }
        }

    }
    private fun MenuSelected(item: MenuItem) : Boolean{
        val checked = item.setChecked(true)
        when(checked.itemId){
            R.id.menuHome -> {
                binding.mainViewPager2.currentItem = 0
                return true
            }
            R.id.menuCatelgoli -> {
                binding.mainViewPager2.currentItem = 1
                return true
            }
            R.id.menuBarket -> {
                binding.mainViewPager2.currentItem = 2
                return true
            }
            R.id.menuMyProfile -> {
                binding.mainViewPager2.currentItem = 3
                return true
            }
        }
        return false
    }
    private inner class PageChangeCallback: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.Bottoms.selectedItemId = when(position){
                0 -> R.id.menuHome
                1 -> R.id.menuCatelgoli
                2 -> R.id.menuBarket
                else -> R.id.menuMyProfile

            }
        }
    }
}