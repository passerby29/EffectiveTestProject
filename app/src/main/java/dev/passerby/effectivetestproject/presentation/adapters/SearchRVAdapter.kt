package dev.passerby.effectivetestproject.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.passerby.effectivetestproject.R

class SearchRVAdapter(
    private val searchWords: List<String>
) :
    RecyclerView.Adapter<SearchRVAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_words_item,
                parent, false
            )
        return SearchViewHolder(itemView)
    }

    override fun getItemCount() = searchWords.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemText.text = searchWords[position]
    }
}