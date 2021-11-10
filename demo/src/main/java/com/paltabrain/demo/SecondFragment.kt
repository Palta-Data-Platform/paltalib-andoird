package com.paltabrain.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paltabrain.PaltaAnalytics
import com.paltabrain.demo.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
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

        PaltaAnalytics.instance.logEvent("SecondFragment started")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}