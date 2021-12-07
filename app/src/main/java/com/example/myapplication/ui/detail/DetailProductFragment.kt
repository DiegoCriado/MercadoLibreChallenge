package com.example.myapplication.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailProductBinding
import com.example.myapplication.databinding.FragmentProductBinding


class DetailProductFragment : Fragment(R.layout.fragment_detail_product) {

    lateinit var binding : FragmentDetailProductBinding
    private val args by navArgs<DetailProductFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailProductBinding.bind(view)
        showPoductDetails()

        binding.btnBuyNow.setOnClickListener {
            Toast.makeText(requireContext(), "No es posible comprar en este momento", Toast.LENGTH_SHORT).show()
        }

        binding.btnQuestion.setOnClickListener {
            Toast.makeText(requireContext(), "No es posible preguntar en este momento", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddToCard.setOnClickListener {
            Toast.makeText(requireContext(), "No es posible comprar en este momento", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPoductDetails() {
        Glide.with(requireContext()).load(args.image).placeholder(android.R.drawable.ic_menu_gallery).into(binding.productImageDetails)
        binding.productNameDetailsTxt.text = args.name
        binding.productPriceDetailsTxt.text = "$${args.price}"
    }



}