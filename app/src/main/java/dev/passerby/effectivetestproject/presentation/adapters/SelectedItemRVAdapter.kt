package dev.passerby.effectivetestproject.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.passerby.effectivetestproject.R

class SelectedItemRVAdapter(
    private val urls: List<String>,
    private val itemClickListener: ItemClickListener,
) :
    RecyclerView.Adapter<SelectedItemRVAdapter.SelectedItemViewHolder>() {

    var selectedItem = urls[0]

    class SelectedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.carousel_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedItemViewHolder {
        Log.d("ViewHolder", "onCreateViewHolder: ")
        val layout = when (viewType) {
            0 -> R.layout.rv_carousel_item
            1 -> R.layout.rv_carousel_item2
            else -> throw RuntimeException("Unknown viewType $viewType")
        }
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                layout,
                parent, false
            )
        return SelectedItemViewHolder(itemView)
    }

    override fun getItemCount() = urls.size

    override fun getItemViewType(position: Int): Int {
        Log.d("ViewHolder", "getItemViewType: ")
        val item = urls[position]
        return if (item == selectedItem) {
            1
        } else {
            0
        }
    }


    override fun onBindViewHolder(holder: SelectedItemViewHolder, position: Int) {
        Picasso.get().load(urls[position]).into(holder.image)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(urls[position])
        }
    }

    interface ItemClickListener {
        fun onItemClick(url: String)
    }
}