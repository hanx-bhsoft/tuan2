package com.hanx.recycleviewtetete.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanx.recycleviewtetete.network.KtorClient
import com.hanx.recycleviewtetete.network.RetrofitClient
import com.hanx.recycleviewtetete.network.Task
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel():ViewModel() {
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