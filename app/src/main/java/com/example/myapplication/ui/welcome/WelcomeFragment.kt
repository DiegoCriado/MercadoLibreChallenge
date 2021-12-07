package com.example.myapplication.ui.welcome

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.GONE
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWelcomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    lateinit var binding: FragmentWelcomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)

        lifecycleScope.launch {
            delay(1000)
            binding.progressBar.visibility = View.GONE
            binding.titleTxt.visibility = View.VISIBLE

            binding.buttonBtn.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_searchProductFragment)
            }
        }
    }


}