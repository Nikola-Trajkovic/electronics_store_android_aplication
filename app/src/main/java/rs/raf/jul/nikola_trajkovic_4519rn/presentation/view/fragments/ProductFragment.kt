package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import okhttp3.internal.wait
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentProductBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.KorpaContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.ProductsContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.adapters.ImageAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.KorpaState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.ProductDetailState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.ProductsResponseState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.KorpaViewModel
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.ProductsViewModel
import timber.log.Timber

class ProductFragment(id: Long) : Fragment(R.layout.fragment_product) {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val idZaSlanje: Long = id

    private lateinit var listaKorpa: List<Korpa>

    private lateinit var adapter: ImageAdapter

    private val productsViewModel :ProductsContract.ViewModel by viewModel<ProductsViewModel>()

    private val korpaViewModel :KorpaContract.ViewModel by viewModel<KorpaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
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

        binding.addBtn.setOnClickListener{

            var flag = true
            var a = 0

            Timber.e(listaKorpa.toString())

            listaKorpa.forEach {
                if(it.title.toString().equals(binding.title.text) && it.description.toString().equals(binding.descriptionTv.text) &&
                        it.price.toString().equals(binding.priceTv.text)){

                    flag = false


                }
            }

            if(flag){

                //Timber.e("USAO U DODAJ")
                korpaViewModel.insert(binding.title.text.toString(), binding.descriptionTv.text.toString(), Integer.parseInt(binding.priceTv.text.toString()))


            }else{


                //Timber.e("USAO U UPDATE")
                korpaViewModel.update(binding.title.text.toString(), binding.descriptionTv.text.toString(), Integer.parseInt(binding.priceTv.text.toString()))


            }


        }

    }

    private fun initRecycler(){

        binding.recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ImageAdapter()
        binding.recyclerView2.adapter = adapter


    }

    private fun initObservers(){

        productsViewModel.productsDetailState.observe(viewLifecycleOwner){

            renderState(it)

        }
        productsViewModel.productsResponseState.observe(viewLifecycleOwner){

            renderState2(it)

        }
        korpaViewModel.korpaState.observe(viewLifecycleOwner){

            renderState3(it)

        }



        productsViewModel.findProduct(idZaSlanje)
        korpaViewModel.getKorpa()


    }



    private fun renderState3(state: KorpaState){
        when (state) {
            is KorpaState.Success -> {

                listaKorpa = state.korpa

            }
            is KorpaState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun renderState(state: ProductDetailState){
        when (state) {
            is ProductDetailState.Success -> {
                adapter.submitList(state.product)
            }
            is ProductDetailState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun renderState2(state: ProductsResponseState){
        when (state) {
            is ProductsResponseState.Success -> {
                binding.title.text = state.product.title
                binding.brandTv.text = state.product.brand
                binding.categoryTv.text = state.product.category
                binding.priceTv.text = state.product.price.toString()
                binding.descriptionTv.text = state.product.description
                binding.discountTv.text = state.product.discountPercentage.toString()
                binding.ratingTv.text = state.product.rating.toString()

                if(state.product.rating < 4.2){
                    binding.ratingTv.setBackgroundColor(Color.RED);
                }else if(state.product.rating < 4.4){
                    binding.ratingTv.setBackgroundColor(Color.rgb(255,165,0));
                }else if(state.product.rating < 4.6){
                    binding.ratingTv.setBackgroundColor(Color.YELLOW);
                }else if(state.product.rating < 4.8){
                    binding.ratingTv.setBackgroundColor(Color.rgb(144,238,144));
                }else{
                    binding.ratingTv.setBackgroundColor(Color.GREEN);
                }


            }
            is ProductsResponseState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

}