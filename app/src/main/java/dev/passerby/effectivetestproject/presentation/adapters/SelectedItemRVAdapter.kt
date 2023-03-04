package dev.passerby.effectivetestproject.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.passerby.effectivetestproject.R

class SelectedItemRVAdapter(private val urls: List<String>) :
    RecyclerView.Adapter<SelectedItemRVAdapter.SelectedItemViewHolder>() {

    class SelectedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.carousel_background1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_carousel_item,
                parent, false
            )
        return SelectedItemViewHolder(itemView)
    }

    override fun getItemCount() = urls.size

    override fun onBindViewHolder(holder: SelectedItemViewHolder, position: Int) {
        Picasso.get().load(urls[position]).into(holder.image)
    }
}