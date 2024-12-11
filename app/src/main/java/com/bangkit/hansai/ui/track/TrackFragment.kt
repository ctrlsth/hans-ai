package com.bangkit.hansai.ui.track

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.hansai.databinding.FragmentTrackBinding

class TrackFragment : Fragment() {

    private var _binding: FragmentTrackBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trackViewModel =
            ViewModelProvider(this)[TrackViewModel::class.java]

        _binding = FragmentTrackBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), CreateLogActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}