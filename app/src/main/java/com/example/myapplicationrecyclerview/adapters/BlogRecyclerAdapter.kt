package com.example.myapplicationrecyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplicationrecyclerview.models.State
import com.example.myapplicationrecyclerview.R
import com.example.myapplicationrecyclerview.databinding.LayoutBlogListItemBinding
import kotlin.collections.ArrayList

class BlogRecyclerAdapter (
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var items: List<State> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(view: View , item: State)
    }
//
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutBlogListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(items[position])
             //   holder.bind(items[position])

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(state: List<State>) {
        items = state
    }





    open class BlogViewHolder
    constructor(
        private val binding: LayoutBlogListItemBinding, listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.run {
                this.listener = listener
            }
        }

        val blogImage = binding.blogImage
        val blogTitle = binding.blogTitle
        val blogAuthor = binding.blogAuthor
        val blogBody = binding.blogBody


        // taking each individual BlogPost object and bind it to the views in a layout
        fun bind(state : State) {
            binding.state = state

            blogTitle.text = state.name
            blogAuthor.text = state.nativeName
            blogBody.text = state.flag

            //default properties to Glide  - what to display if there is an error or the image cannot be displayed
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)

            Glide.with(blogAuthor.context)
                .setDefaultRequestOptions(requestOptions)
                .load(state.flag)
                .into(blogImage)




            //
//            itemView.setOnClickListener {
//                listener.onItemClick(blogPost)
//            }

        }
    }
}