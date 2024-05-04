package com.invade.univ_details.presentation.ui

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.invade.core.util.autoCleared
import com.invade.univ_details.databinding.FragmentUniversityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UniversityDetailsFragment : Fragment() {

    private var binding: FragmentUniversityDetailsBinding by autoCleared()

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUniversityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createUnderLine()

        arguments?.run {
            getString("universityName")?.let {
                binding.tvName.text = it
            }
            getString("universityState")?.let {
                binding.tvState.text = it
            }
            getString("country")?.let {
                binding.tvCountry.text = it
            }
            getString("countryCode")?.let {
                binding.tvCountryCode.text = it
            }
            getString("webPage")?.let { webPage ->
                getString("domain")?.let { domain->
                    binding.tvWebPage.text = "$webPage$domain"
                }
            }
        }

        navController = findNavController()


        binding.btnRefresh.setOnClickListener {
            navController.popBackStack()
        }

    }

    private fun createUnderLine(){
        val paint = Paint()

        paint.color = resources.getColor(android.R.color.black)

        paint.style = Paint.Style.STROKE

        paint.strokeWidth = 2f

        binding.tvWebPage.paintFlags = binding.tvWebPage.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        binding.tvWebPage.setTextColor(resources.getColor(android.R.color.black))

        binding.tvWebPage.setLayerType(View.LAYER_TYPE_SOFTWARE, paint)
    }
}