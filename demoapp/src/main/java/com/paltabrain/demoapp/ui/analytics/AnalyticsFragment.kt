package com.paltabrain.demoapp.ui.analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.paltabrain.PaltaAnalytics
import com.paltabrain.demoapp.databinding.FragmentGalleryBinding

class AnalyticsFragment : Fragment() {

    private lateinit var analyticsViewModel: AnalyticsViewModel
    private var _binding: FragmentGalleryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        analyticsViewModel =
            ViewModelProvider(this).get(AnalyticsViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener {
            PaltaAnalytics.instance.logEvent("button 1 clicked")
        }

        binding.button2.setOnClickListener {
            PaltaAnalytics.instance.logEvent("button 2 clicked")
        }

        binding.button3.setOnClickListener {
            PaltaAnalytics.instance.logEvent("button 3 clicked")
        }

        PaltaAnalytics.instance.logEvent("GalleryFragment started")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}