package com.hanx.recycleviewtetete.ui.film.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hanx.recycleviewtetete.R
import com.hanx.recycleviewtetete.databinding.FragmentListFirmBinding
import com.hanx.recycleviewtetete.type.Tasks
import com.hanx.recycleviewtetete.ui.film.views.FilmRecycleViewController


/**

 */
class ListFirmFragment : Fragment() {
    val dataTask = mutableListOf<Tasks>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentListFirmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListFirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listFilmView.adapter = FilmRecycleViewController(dataTask) { it ->
            val bundle = bundleOf("taskName" to it.name, "taskDescription" to it.description)
            findNavController().navigate(
                R.id.action_listFirmFragment_to_detailFilmFragment,
                bundle
            )
        }
        binding.listFilmView.layoutManager = GridLayoutManager(context, 2)
        binding.addTaskButton.setOnClickListener {
            dataTask.add(Tasks(binding.newTaskName.text.toString(), binding.newTaskDescription.text.toString()))
            binding.listFilmView.adapter?.notifyDataSetChanged()
        }
    }
}