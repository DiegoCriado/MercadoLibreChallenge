package com.example.myapplication.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchProductBinding
import com.example.myapplication.model.DataSource
import com.example.myapplication.presentation.MainViewModel
import com.example.myapplication.presentation.MainViewModelFactory
import com.example.myapplication.repository.RepositoryImplement
import com.example.myapplication.repository.RetrofitClient

class SearchProductFragment : Fragment(R.layout.fragment_search_product) {

    lateinit var binding: FragmentSearchProductBinding

    private lateinit var sharedViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchProductBinding.bind(view)

        sharedViewModel = ViewModelProvider(
            requireActivity(), MainViewModelFactory(
                RepositoryImplement(
                    DataSource(RetrofitClient.webService)
                )
            )
        ).get(MainViewModel::class.java)

        binding.btnSearch.setOnClickListener {

            if(binding.searcherTxt.text.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Debe completar el campo de busqueda", Toast.LENGTH_SHORT).show()
            }else{
                var query = binding.searcherTxt.text.toString()
                sharedViewModel.query = query
                findNavController().navigate(R.id.action_searchProductFragment_to_productFragment)
                clear()
            }
        }
    }

    private fun clear(){
        binding.searcherTxt.setText("")
    }


}