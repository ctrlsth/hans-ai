package com.bangkit.hansai.ui.track

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.hansai.data.local.entity.LogEntity
import com.bangkit.hansai.databinding.FragmentTrackBinding
import com.bangkit.hansai.ui.factory.LogViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TrackFragment : Fragment() {

    private var _binding: FragmentTrackBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory: LogViewModelFactory = LogViewModelFactory.getInstance(requireActivity())
        val trackViewModel: TrackViewModel by viewModels {
            factory
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), CreateLogActivity::class.java)
            startActivity(intent)
        }

        val content = binding.content

        val date = Date()
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

        content.tvMonthNavigation.text = dateFormat.format(date)
        content.navBeforeButton.setOnClickListener {
            // Get Prev Month List
        }
        content.navNextButton.setOnClickListener {
            // Get Next Month List
        }

        val logAdapter = LogAdapter()
        content.rvLogs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = logAdapter
        }

        logAdapter.apply {
//            submitList(trackViewModel.dummy)
            setOnItemClickCallback(object : LogAdapter.OnItemClickCallback {
                override fun onItemClicked(log: LogEntity) {
                    val intent = Intent(requireContext(), CreateLogActivity::class.java)
                    intent.putExtra(CreateLogActivity.EXTRA_LOG, log)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}