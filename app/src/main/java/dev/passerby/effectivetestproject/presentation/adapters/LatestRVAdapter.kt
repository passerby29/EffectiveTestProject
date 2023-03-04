package dev.passerby.effectivetestproject.presentation.adapters

import android.annotation.SuppressLint
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.domain.models.Latest

class LatestRVAdapter :
    RecyclerView.Adapter<LatestRVAdapter.LatestViewHolder>() {

    private val latestList = ArrayList<Latest>()

    class LatestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val background: ImageView = itemView.findViewById(R.id.latest_background)
        val category: TextView = itemView.findViewById(R.id.latest_category)
        val name: TextView = itemView.findViewById(R.id.latest_name)
        val price: TextView = itemView.findViewById(R.id.latest_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_latest_item,
                parent, false
            )
        return LatestViewHolder(itemView)
    }

    override fun getItemCount() = latestList.size

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val latestItem = latestList[position]
        latestList[position].apply {
            Picasso.get().load(latestItem.image_url).into(holder.background)
            holder.category.text = latestItem.category
            holder.name.text = latestItem.name
            holder.price.text = latestItem.price.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Latest>) {
        latestList.clear()
        latestList.addAll(newList)
        notifyDataSetChanged()
    }
}