package com.bangkit.hansai.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bangkit.hansai.R
import com.bangkit.hansai.data.local.preferences.UserPreferences
import com.bangkit.hansai.data.remote.response.User
import com.bangkit.hansai.data.repository.Result
import com.bangkit.hansai.databinding.FragmentHomeBinding
import com.bangkit.hansai.ui.factory.UserViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(requireActivity())
        val homeViewModel: HomeViewModel by viewModels {
            factory
        }

        homeViewModel.login.observe(viewLifecycleOwner) { status ->
            if (status) {
                Log.d("HomeFragment", "Login: $status")
                homeViewModel.getProfile().observe(viewLifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> Log.d("HomeFragment", "Loading...")
                            is Result.Error -> Log.d("HomeFragment", "Error: ${result.error}")
                            is Result.Success -> {
                                val user = result.data
                                binding.greetings.text =
                                    getString(
                                        R.string.greetings_placeholder,
                                        user.displayName
                                    )
                                binding.currentWeightValue.text = "${user.currentWeight} lbs"
                                binding.targetWeightValue.text = "${user.targetWeight} lbs"
                                binding.valueToday.text = "2000 kcal"
                                binding.valueIntake.text = "2000 kcal"

                                binding.valueCarbs.text = "150 g"
                                binding.valueProtein.text = "25 g"
                                binding.valueFat.text = "10 g"

                                Log.d("HomeFragment", "Success: ${result.data}")
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}