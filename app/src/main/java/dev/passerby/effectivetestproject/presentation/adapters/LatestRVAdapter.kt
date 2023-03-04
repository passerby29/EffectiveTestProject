package dev.passerby.effectivetestproject.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.domain.models.Latest

class LatestRVAdapter(private val latestList: List<Latest>?) :
    RecyclerView.Adapter<LatestRVAdapter.LatestViewHolder>() {

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

    override fun getItemCount() = latestList!!.size

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val latestItem = latestList?.get(position)
        val price =
            StringBuilder().append("$ ").append(latestItem?.price).toString()
        latestList?.get(position).apply {
            Picasso.get().load(latestItem?.image_url).into(holder.background)
            holder.category.text = latestItem?.category
            holder.name.text = latestItem?.name
            holder.price.text = price
        }
    }
}