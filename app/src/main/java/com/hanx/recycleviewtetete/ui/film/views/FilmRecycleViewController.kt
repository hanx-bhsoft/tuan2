package com.hanx.recycleviewtetete.ui.film.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanx.recycleviewtetete.R
import com.hanx.recycleviewtetete.type.Tasks

class FilmRecycleViewController(val tasks:List<Tasks>, var callBack: (Tasks) -> Unit) : RecyclerView.Adapter<FilmRecycleViewController.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val filmName = view.findViewById<TextView>(R.id.filmName)
        val filmItem = view.findViewById<View>(R.id.filmItem)
        fun bind(name: String, action: ()->Unit ) {
            filmName.text = name
            filmItem.setOnClickListener {
                action()
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view =
            LayoutInflater.from(p0.context).inflate(if (p1%2== 0)R.layout.sample_fiml_item_view else R.layout.item_type2 , p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =
        this.tasks.count()

    override fun getItemViewType(position: Int): Int {
        return position%2
    }
    override fun onBindViewHolder(p0: ViewHolder, index: Int) {
            p0.bind(this.tasks[index].name) {
            callBack(this.tasks[index])
        }
    }
}