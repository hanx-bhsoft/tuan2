package com.hanx.recycleviewtetete.ui.film.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanx.recycleviewtetete.R
import com.hanx.recycleviewtetete.databinding.FragmentListFirmBinding
import com.hanx.recycleviewtetete.ui.film.views.FilmRecycleViewController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFirmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFirmFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentListFirmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListFirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listFilmView.adapter = FilmRecycleViewController() { it ->
            val bundle = bundleOf("taskName" to it, "taskDescription" to "abc")
            findNavController().navigate(
                R.id.action_listFirmFragment_to_detailFilmFragment,
                bundle
            );
        }
        binding.listFilmView.layoutManager = GridLayoutManager(context, 2)

    }
}