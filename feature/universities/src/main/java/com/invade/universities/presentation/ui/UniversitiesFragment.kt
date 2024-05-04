package com.invade.universities.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.invade.core.util.autoCleared
import com.invade.core.util.handleResponseError
import com.invade.core.util.lifecycleScope
import com.invade.universities.databinding.FragmentUniversitiesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UniversitiesFragment : Fragment() {

    private var binding: FragmentUniversitiesBinding by autoCleared()

    private lateinit var universitiesAdapter: UniversitiesAdapter

    private lateinit var navController: NavController

    private val viewModel: UniversitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        setupUniversitiesAdapter()

        observeStates()

    }


    override fun onResume() {
        super.onResume()

        viewModel.getUniversities("United%20Arab%20Emirates")
    }

    private fun observeStates() {
        lifecycleScope(this) {
            viewModel.viewStateLoading.collectLatest { isLoading ->
                val visibility = if (isLoading)
                    View.VISIBLE
                else
                    View.GONE
                binding.progress.visibility = visibility
            }
        }
        lifecycleScope(this) {
            viewModel.viewStateError.collectLatest {
                val error = handleResponseError(requireContext(), it)
                displayErrorMessage(error)
            }
        }

        lifecycleScope(this) {
            viewModel.viewStateUniversities.collect { universities ->
                universitiesAdapter.submitList(universities)
            }
        }

    }

    private fun displayErrorMessage(errorMessage: String?) {
        errorMessage?.let {
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupUniversitiesAdapter() {
        universitiesAdapter = UniversitiesAdapter(
            onItemClick = {
                navController.navigate(
                    com.invade.edutrac.R.id.universityDetailsFragment,
                    bundleOf(
                        "universityName" to it.name,
                        "universityState" to it.stateProvince,
                        "country" to it.country,
                        "countryCode" to it.alphaTwoCode,
                        "webPage" to (it.webPages.firstOrNull() ?: ""),
                        "domain" to (it.domains.firstOrNull() ?: "")
                    )
                )
            }
        )
        binding.rvUniversities.apply {
            adapter = universitiesAdapter
        }
    }
}