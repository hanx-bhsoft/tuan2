package com.hanx.recycleviewtetete.ui.film.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hanx.recycleviewtetete.R
import com.hanx.recycleviewtetete.databinding.FragmentDetailFilmBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFilmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFilmFragment : Fragment() {
    lateinit var title : String
    lateinit var description : String
    lateinit var binding: FragmentDetailFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
         title = it.getString("taskName")?:""
         description = it.getString("taskDescription")?:""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskNameDetail.text = title
        binding.taskDescriptionDetail.text = description
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}