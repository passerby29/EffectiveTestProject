package dev.passerby.effectivetestproject.presentation.adapters

import android.annotation.SuppressLint
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.data.room.SearchWordsEntity

class SearchRVAdapter :
    RecyclerView.Adapter<SearchRVAdapter.SearchViewHolder>() {

    private val searchList = ArrayList<SearchWordsEntity>()

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

    override fun getItemCount() = searchList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemText.text = searchList[position].words
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<SearchWordsEntity>) {
        searchList.clear()
        searchList.addAll(newList)
        notifyDataSetChanged()
    }
}