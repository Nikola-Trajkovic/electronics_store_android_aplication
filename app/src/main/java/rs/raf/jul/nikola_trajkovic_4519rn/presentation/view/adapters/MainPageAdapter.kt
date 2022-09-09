package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments.DiscoverFragment
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments.ProfileFragment

class MainPageAdapter(
    fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val itemCount = 3

    companion object{
        const val FRAGMENT1 = 0
        const val FRAGMENT2 = 1

    }

    override fun getCount(): Int {
        return itemCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            FRAGMENT1 -> DiscoverFragment()
            else -> ProfileFragment()

        }
    }

}