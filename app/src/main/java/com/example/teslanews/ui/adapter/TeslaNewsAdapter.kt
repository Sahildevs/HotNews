package com.example.teslanews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teslanews.R
import com.example.teslanews.data.model.TeslaNewsResponseItem
import com.example.teslanews.databinding.CellTeslaNewsBinding

class TeslaNewsAdapter(private var callback: Callback, private var newsList: ArrayList<TeslaNewsResponseItem>):
RecyclerView.Adapter<TeslaNewsAdapter.ViewHolder>(){

    fun updateList(list: ArrayList<TeslaNewsResponseItem>) {
        newsList = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: CellTeslaNewsBinding,
        listener: Callback
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TeslaNewsResponseItem) {
            binding.data = data
            binding.executePendingBindings()

            Glide.with(binding.imageView).load(data.urlToImage).placeholder(R.drawable.placeholder).into(binding.imageView)
        }

        init {
            binding.childLayout.setOnClickListener {
                binding.data?.let { data ->
                    listener.onClick(data, adapterPosition)
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CellTeslaNewsBinding.inflate(LayoutInflater.from(parent.context)), callback)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])


        /** Setting the width of the view explicitly, to ensure that all cards in RecyclerView inflate properly to their full width*/
        // Get the layout params of the view, or create new ones if they're null
        val layoutParams = holder.itemView.layoutParams ?: ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // Set the width of the view to match_parent
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT

        // Set the layout params of the view
        holder.itemView.layoutParams = layoutParams
    }


    interface Callback {
        fun onClick(data: TeslaNewsResponseItem , position: Int)
    }
}