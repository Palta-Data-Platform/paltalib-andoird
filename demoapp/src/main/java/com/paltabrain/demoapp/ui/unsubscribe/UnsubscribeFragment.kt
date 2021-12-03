package com.paltabrain.demoapp.ui.unsubscribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.paltabrain.PaltaAnalytics
import com.paltabrain.demoapp.databinding.FragmentSlideshowBinding

class UnsubscribeFragment : Fragment() {

    private lateinit var unsubscribeViewModel: UnsubscribeViewModel
    private var _binding: FragmentSlideshowBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        unsubscribeViewModel =
            ViewModelProvider(this).get(UnsubscribeViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        unsubscribeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PaltaAnalytics.instance.logEvent("SlideshowFragment started")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}