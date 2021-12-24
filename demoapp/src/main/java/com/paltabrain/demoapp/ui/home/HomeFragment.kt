package com.paltabrain.demoapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.paltabrain.PaltaAnalytics
import com.paltabrain.attribution.PaltaAttribution
import com.paltabrain.attribution.PaltaAttributionDelegate
import com.paltabrain.demoapp.databinding.FragmentHomeBinding
import com.paltabrain.purchases.PaltaPurchases

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private var revenueCatId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PaltaAttribution.instance.delegate = object : PaltaAttributionDelegate {
            override fun onReceiveUserID(userID: String?) {
                revenueCatId = userID
                requireActivity().runOnUiThread {
                    binding.userIdText.text = revenueCatId ?: "User id not defined"
                }
            }

            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            }

            override fun onConversionDataFail(error: String?) {
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
            }

            override fun onAttributionFailure(error: String?) {
            }
        }

        binding.status.setOnClickListener {
            revenueCatId?.let {
                PaltaPurchases.instance.getSubscriptionStatus(it) { response ->
                    binding.statusText.text = response
                }
            }
        }

        binding.unsubscribe.setOnClickListener {
            revenueCatId?.let {
                PaltaPurchases.instance.cancelSubscription(it) { response ->
                    Log.e("cancelSubscription", "response: $response")
                }
            }
        }

        PaltaAnalytics.instance.logEvent("HomeFragment started")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
