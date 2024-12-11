package com.bangkit.hansai.ui.track

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.hansai.R
import com.bangkit.hansai.data.local.entity.LogEntity
import com.bangkit.hansai.databinding.ItemLogBinding
import java.text.SimpleDateFormat
import java.util.Locale

class LogAdapter : ListAdapter<LogEntity, LogAdapter.LogViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class LogViewHolder(
        val binding: ItemLogBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(log: LogEntity) {
            binding.logDay.text = SimpleDateFormat("EEE", Locale.getDefault()).format(log.date)
            binding.logDate.text = SimpleDateFormat("dd", Locale.getDefault()).format(log.date)

            binding.logWeight.text = String.format(Locale.getDefault(), "%.1f", log.currentWeight)
            binding.logCalories.text = String.format(Locale.getDefault(), "%.1f", log.calories)
            binding.logBaseGoal.text = String.format(Locale.getDefault(), " / %.1f ", log.baseGoal)
            binding.logTitle.text = log.title

            binding.actionButton.setOnClickListener { view ->
                val popupMenu = PopupMenu(view.context, binding.actionButton)
                popupMenu.inflate(R.menu.rv_item_menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.edit -> {
                            // Handle edit action
                            true
                        }

                        R.id.delete -> {
                            // Handle delete action
                            true
                        }

                        else -> false
                    }
                }

                popupMenu.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val log = getItem(position)
        Log.d("LogAdapter", "Binding log: $log")
        holder.bind(log)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(log) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(log: LogEntity)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LogEntity>() {
            override fun areItemsTheSame(
                oldItem: LogEntity,
                newItem: LogEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LogEntity,
                newItem: LogEntity
            ): Boolean {
                return oldItem.lastUpdate == newItem.lastUpdate
            }
        }
    }
}