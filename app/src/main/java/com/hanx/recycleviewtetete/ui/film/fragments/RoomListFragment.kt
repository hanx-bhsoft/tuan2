package com.hanx.recycleviewtetete.ui.film.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.hanx.recycleviewtetete.R
import com.hanx.recycleviewtetete.databinding.FragmentRoomListBinding
import com.hanx.recycleviewtetete.network.Task
import com.hanx.recycleviewtetete.ui.film.views.FilmRecycleViewController
import com.hanx.recycleviewtetete.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomListFragment : Fragment() {

    lateinit var binding : FragmentRoomListBinding
    lateinit var controller : FilmRecycleViewController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentRoomListBinding.inflate(inflater, container, false)
        controller = FilmRecycleViewController{
            val bundle = bundleOf("taskName" to it.title, "taskDescription" to it.completed.toString())
            findNavController().navigate(R.id.action_roomListFragment_to_detailFilmFragment, bundle)
        }
        viewModel.reload()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setup() {
        viewModel.tasksFlow.asLiveData().observe(viewLifecycleOwner){
            controller.tasks.clear()
            controller.tasks.addAll(it)
            controller.notifyDataSetChanged()
        }
        binding.apply {
            addTaskRoomButton.setOnClickListener {
                val taskName = newTaskName.text.toString()
                val taskDescription = newTaskDescription.text.toString()
                viewModel.addTask(Task(title = taskName, userId = 1, completed = false))
            }
            listTaskView.adapter = controller
            listTaskView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        }
    }

}