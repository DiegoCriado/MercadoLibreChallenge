package com.example.myapplication.ui.product

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.core.Resource
import com.example.myapplication.databinding.FragmentProductBinding
import com.example.myapplication.model.DataSource
import com.example.myapplication.model.Product
import com.example.myapplication.presentation.MainViewModel
import com.example.myapplication.presentation.MainViewModelFactory
import com.example.myapplication.repository.RepositoryImplement
import com.example.myapplication.repository.RetrofitClient
import com.example.myapplication.ui.product.adapters.ProductAdapter
import java.util.*

class ProductFragment : Fragment(R.layout.fragment_product), ProductAdapter.OnProductClickListener {

    lateinit var binding: FragmentProductBinding

    private lateinit var sharedViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        sharedViewModel = ViewModelProvider(
            requireActivity(), MainViewModelFactory(
                RepositoryImplement(
                    DataSource(RetrofitClient.webService)
                )
            )
        ).get(MainViewModel::class.java)

        binding.recyclerView.adapter = ProductAdapter(listOf(), this)

        sharedViewModel.searchProductByName()
            .observe(
                viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                    when (result) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                           if(result.data.results.isNotEmpty()){
                               binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                               binding.recyclerView.adapter = ProductAdapter(result.data.results, this@ProductFragment)
                           }else{
                               Toast.makeText(requireContext(), "No se han encontrado productos relacionados, vuelva a buscar", Toast.LENGTH_SHORT).show()
                           }
                        }
                        is Resource.Failure -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText( requireContext(), "No existen productos relacionados", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
    }

    override fun onProductClick(porduct: Product) {
        val action = ProductFragmentDirections.actionProductFragmentToDetailProductFragment(
            porduct.name,
            porduct.price,
            porduct.image
        )
        findNavController().navigate(action)
    }

}