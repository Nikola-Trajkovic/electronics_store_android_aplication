package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentMainBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.adapters.MainPageAdapter

class MainFragment : Fragment(R.layout.fragment_main){
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initViewPager()
        initNavigation()
    }

    private fun initViewPager(){
        viewPager = binding.bottomNavViewPager
        viewPager.adapter = MainPageAdapter(childFragmentManager)
    }

    private fun initNavigation(){
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_1) {
                viewPager.setCurrentItem(MainPageAdapter.FRAGMENT1, false)
            }else if(item.itemId == R.id.navigation_2){
                viewPager.setCurrentItem(MainPageAdapter.FRAGMENT2, false)
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}