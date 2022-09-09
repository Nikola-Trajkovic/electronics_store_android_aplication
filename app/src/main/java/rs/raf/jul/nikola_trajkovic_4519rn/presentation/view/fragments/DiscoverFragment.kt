package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Products
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentDiscoverBinding
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentProfileBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.ProductsContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.recycles.adapters.ProductsAdapter
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.CategoryState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.ProductsState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.ProductsViewModel
import timber.log.Timber
import java.util.function.Consumer

class DiscoverFragment : Fragment(R.layout.fragment_discover), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    private val productsViewModel :ProductsContract.ViewModel by viewModel<ProductsViewModel>()
    private lateinit var adapter: ProductsAdapter

    //Posto nisam uspeo da pristupid podacima preko retrofita zato sto retrofit vraca listu a ne objekat spinner sam popunio na ovaj nacin
    private var list_of_items = arrayOf("Izaberite kategoriju","smartphones","laptops","fragrances","skincare","groceries","home-decoration","furniture","tops","womens-dresses","womens-shoes","mens-shirts","mens-shoes","mens-watches","womens-watches","womens-bags","womens-jewellery","sunglasses","automotive","motorcycle","lighting")



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
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

        binding.searchEt.doAfterTextChanged {
            binding.spinnerSample.setSelection(0)
            productsViewModel.filterSearch(binding.searchEt.text.toString())
        }

        binding.spinnerSample!!.onItemSelectedListener = this

        val array_adapter = ArrayAdapter(activity?.applicationContext!!, android.R.layout.simple_spinner_item, list_of_items)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.spinnerSample!!.setAdapter(array_adapter)


    }

    private fun initRecycler(){
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ProductsAdapter(Consumer {
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.mainFragmentFcv, ProductFragment(it.id))
            transaction.commit()
        })
        binding.recyclerViewProduct.adapter = adapter

    }

    private fun initObservers(){

        productsViewModel.productsState.observe(viewLifecycleOwner){

            renderState(it)

        }
        productsViewModel.getAll()


    }



    private fun renderState(state: ProductsState){
        when (state) {
            is ProductsState.Success -> {
                adapter.submitList(state.products)
            }
            is ProductsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ProductsState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is ProductsState.Loading -> {
                Thread.sleep(1000)
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        binding.searchEt.text.clear()
        binding.spinnerSample.setSelection(p2)

        if(!binding.spinnerSample.selectedItem.toString().equals("Izaberite kategoriju")){
            productsViewModel.filterCategory(binding.spinnerSample.selectedItem.toString())
        }



    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}