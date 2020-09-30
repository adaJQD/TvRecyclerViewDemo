package cn.com.jack.tvrecyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.com.jack.tvrecyclerviewdemo.databinding.ItemBinding

class MainAdapter() : ListAdapter<String, RecyclerView.ViewHolder>(MainDiffCallback()) {

    /** 记录获得焦点和失去焦点的item */
    private val map = mutableMapOf<Boolean, String>()
    private var lastSelectedView: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainViewHolder(
                ItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder as MainViewHolder) {
            itemView.tag = item
            bind(item)
        }
    }

    inner class MainViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnFocusChangeListener { view, hasFocus ->
                map[hasFocus] = view.tag as String
                if (map[true] == map[false]) {
                    // 获得焦点和失去焦点的是同一个item，会有以下两种情况：
                    //  RecyclerView失去焦点
                    //  RecyclerView重新获得焦点
                    // 让此item保持选中状态，
                    view.isSelected = true
                    lastSelectedView = view
                } else {
                    lastSelectedView?.isSelected = false
                }
            }
        }


        fun bind(item: String) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }
}

private class MainDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
