package com.notepoint4ugmail.movies.overview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.notepoint4ugmail.movies.R
import com.notepoint4ugmail.movies.databinding.FragmentOverviewBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class OverviewFragment : Fragment() {

    private lateinit var binding:FragmentOverviewBinding
    private lateinit var overviewViewModel:OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentOverviewBinding.inflate(inflater)
        overviewViewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        binding.overviewModel = overviewViewModel
        binding.lifecycleOwner = this

        binding.movieRecycler.adapter = OverviewAdapter()



        return binding.root
    }


}
