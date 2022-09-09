package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentKorpaBinding
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentProductBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.KorpaContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.adapters.ImageAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.adapters.KorpaAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.KorpaState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.ProductDetailState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.KorpaViewModel
import timber.log.Timber

class KorpaFragment : Fragment(R.layout.fragment_korpa) {

    private var _binding: FragmentKorpaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: KorpaAdapter

    private val korpaViewModel : KorpaContract.ViewModel by viewModel<KorpaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKorpaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        initRecycler()
        initObservers()
        initListeners()

    }

    private fun initListeners(){
        binding.button2.setOnClickListener {

            korpaViewModel.deleteAll()

            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.mainFragmentFcv, MainFragment())
            transaction.commit()

        }
    }

    private fun initRecycler(){

        binding.korpaRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = KorpaAdapter(clicked = {

            korpaViewModel.delete(it.id!!)

        })
        binding.korpaRv.adapter = adapter


    }

    private fun initObservers(){

        korpaViewModel.korpaState.observe(viewLifecycleOwner){

            renderState(it)

        }

        korpaViewModel.getKorpa()

    }



    private fun renderState(state: KorpaState){
        when (state) {
            is KorpaState.Success -> {
                adapter.submitList(state.korpa)

                var price = 0

                state.korpa.forEach {
                    price += (it.price!! * it.amount)
                }

                binding.amountTv.text = price.toString()

            }
            is KorpaState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }

        }
    }






}