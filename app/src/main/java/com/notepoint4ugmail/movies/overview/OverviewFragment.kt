package com.notepoint4ugmail.movies.overview


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.notepoint4ugmail.movies.R
import com.notepoint4ugmail.movies.databinding.FragmentOverviewBinding
import com.notepoint4ugmail.movies.network.MovieTypes

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

        val adapter = OverviewAdapter(OverviewAdapter.MovieClickListener{
            overviewViewModel.navigateToDisplay(it)
        })

        overviewViewModel.onNavigationToDetail.observe(this, Observer {
            it?.let {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                overviewViewModel.navigateToDisplayComplete()
            }
        })

        binding.movieRecycler.adapter = adapter

//        overviewViewModel.moviesList.observe(this, Observer {
//            adapter.submitList(it)
//        })

        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.over_flow_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        overviewViewModel.updateSelectedMovieType(
            when(item?.itemId){
                R.id.popular_movies->MovieTypes.SHOW_POPULAR
                R.id.top_rated_movies->MovieTypes.SHOW_TOP
                R.id.upcoming_movies->MovieTypes.SHOW_UPCOMING
                R.id.now_playing_movies->MovieTypes.SHOW_NOW_PLAYING
                else->MovieTypes.SHOW_NOW_PLAYING
            }
        )
        return true
    }
}
