package com.hanx.recycleviewtetete.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanx.recycleviewtetete.network.KtorClient
import com.hanx.recycleviewtetete.network.RetrofitClient
import com.hanx.recycleviewtetete.network.Task
import com.hanx.recycleviewtetete.room.Taskdao
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val dao : Taskdao):ViewModel() {
    private val client  = RetrofitClient.instance
    private val ktorClient = KtorClient.client
    val tasks = MutableStateFlow<List<Task>>(emptyList())
    fun getTasks(onSuccess:()-> Unit = {}){
        viewModelScope.launch {
          try{  val response = client.getUser()
              if(response.isSuccessful){
                  println("$response")
                  response.body()?.let {
                      it.forEach {t->
                          println(t.title)
                      }
                     tasks.emit(it.toList())
                  }
                  onSuccess()
              }else{
                  println("error")
              }}
          catch (e:Exception){
              println(e.message)
          }
        }
    }
    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                dao.insert(task)
            }
            catch (e:Exception){
                println(e.message)
            }
        }
        reload()
    }
    private val taskFlow = MutableStateFlow<List<Task>>(emptyList())
    val tasksFlow :StateFlow<List<Task>> = taskFlow
     fun reload() {
        viewModelScope.launch {
            try {
                val tasks = dao.getAll()
                tasks.forEach { println(it.title) }
                taskFlow.emit(tasks)
            }
            catch (e:Exception){
                println(e.message)
            }
        }
    }

    fun getTasksWithKtor(onSuccess:()-> Unit = {}){
        viewModelScope.launch {
           try{


              val r = ktorClient.get {
                  url {
                      protocol = URLProtocol.HTTPS
                      host = "dummy-json.mock.beeceptor.com"
                      path("todos")
                  }
              }
              r.body<List<Task>>().let {
                  tasks.emit(it)
                  onSuccess()
              }
          }
          catch (e:Exception){
              println("error"+e)
          }
        }
    }
}