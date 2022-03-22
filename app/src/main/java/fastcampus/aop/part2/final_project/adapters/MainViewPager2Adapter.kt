package fastcampus.aop.part2.final_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import fastcampus.aop.part2.final_project.fragment.BarketFragment
import fastcampus.aop.part2.final_project.fragment.CategoryItemListFragment
import fastcampus.aop.part2.final_project.fragment.CategoryListFragment
import fastcampus.aop.part2.final_project.fragment.MyInfoFragment

class MainViewPager2Adapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return  when(position){
            0 -> CategoryListFragment()
            1 -> CategoryItemListFragment()
            2 -> BarketFragment()
            else -> MyInfoFragment()



        }
    }
}