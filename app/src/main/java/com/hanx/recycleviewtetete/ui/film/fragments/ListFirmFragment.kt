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
import androidx.recyclerview.widget.GridLayoutManager
import com.hanx.recycleviewtetete.R
import com.hanx.recycleviewtetete.databinding.FragmentListFirmBinding
import com.hanx.recycleviewtetete.network.Task
import com.hanx.recycleviewtetete.type.Tasks
import com.hanx.recycleviewtetete.ui.film.views.FilmRecycleViewController
import com.hanx.recycleviewtetete.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@AndroidEntryPoint
class ListFirmFragment : Fragment() {
    val dataTask = mutableListOf<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //    val viewModel by viewModels<Proxy>()
    val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentListFirmBinding
    private lateinit var controller: FilmRecycleViewController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        viewModel.getTasks()
        controller = FilmRecycleViewController() { it ->
            val bundle =
                bundleOf("taskName" to it.title, "taskDescription" to it.completed.toString())
            findNavController().navigate(
                R.id.action_listFirmFragment_to_detailFilmFragment,
                bundle
            )
        }
        binding = FragmentListFirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tasks.asLiveData().observe(viewLifecycleOwner) {
            it?.let {
                controller.tasks.clear()
                controller.tasks.addAll(it)
                controller.notifyDataSetChanged()
            }
        }
        binding.toRoom.setOnClickListener {
            findNavController().navigate(R.id.action_listFirmFragment_to_roomListFragment)
        }
        binding.listFilmView.adapter = controller
        binding.listFilmView.layoutManager = GridLayoutManager(context, 2)
        binding.addTaskButton.setOnClickListener {

            viewModel.getTasksWithKtor {
                controller.notifyDataSetChanged()
            }
//            viewModel.getTasks{
//                controller.tasks.apply { clear()
//                    addAll( viewModel.tasks.value)
//                }
//                controller.notifyDataSetChanged()
//            }

        }
    }
}