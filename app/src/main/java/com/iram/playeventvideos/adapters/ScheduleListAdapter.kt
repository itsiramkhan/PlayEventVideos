package com.iram.playeventvideos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.iram.playeventvideos.R
import com.iram.playeventvideos.databinding.ItemRviewBinding
import com.iram.playeventvideos.model.Schedule
import com.iram.playeventvideos.utils.DateFormat

class ScheduleListAdapter(private val listener: ScheduleItemListener) :
    RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder>() {

    private var items = ArrayList<Schedule>()

    fun setItems(items: List<Schedule>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface ScheduleItemListener {
        fun onClickedItemData(title: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder {
        val itemDataBinding =
            ItemRviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleListViewHolder(itemDataBinding, listener)
    }

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ScheduleListViewHolder(
        private val itemBinding: ItemRviewBinding, private val listener: ScheduleItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private lateinit var itemList: Schedule
        fun bind(item: Schedule) {
            itemList = item
            itemBinding.tvTitle.text = item.title
            val formattedDate = DateFormat.dateToDayTime(DateFormat.stringToDate(item.date))
            itemBinding.tvDate.text = formattedDate
            Glide.with(itemBinding.root).load(item.imageUrl).transform(CircleCrop())
                .placeholder(R.drawable.dazn)
                .placeholder(R.drawable.dazn)
                .error(R.drawable.dazn)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemBinding.imageView)

            itemBinding.root.setOnClickListener {
                listener.onClickedItemData(itemList.title)
            }
        }
    }
}