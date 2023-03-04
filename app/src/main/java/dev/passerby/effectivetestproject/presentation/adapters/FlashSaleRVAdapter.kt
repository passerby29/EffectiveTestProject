package dev.passerby.effectivetestproject.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.domain.models.FlashSale

class FlashSaleRVAdapter(private val flashSale: List<FlashSale>?) :
    RecyclerView.Adapter<FlashSaleRVAdapter.FlashSaleViewHolder>() {

    class FlashSaleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val background: ImageView = itemView.findViewById(R.id.flash_sale_background)
        val category: TextView = itemView.findViewById(R.id.flash_sale_category)
        val name: TextView = itemView.findViewById(R.id.flash_sale_name)
        val price: TextView = itemView.findViewById(R.id.flash_sale_price)
        val sale: TextView = itemView.findViewById(R.id.flash_sale_sale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_flash_sale_item, parent, false
        )
        return FlashSaleViewHolder(itemView)
    }

    override fun getItemCount() = flashSale!!.size

    override fun onBindViewHolder(holder: FlashSaleViewHolder, position: Int) {
        val flashSaleItem = flashSale?.get(position)
        val price = StringBuilder().append(flashSaleItem?.price).append("$ ").toString()
        val sale = StringBuilder().append(flashSaleItem?.discount).append("% off").toString()
        flashSale?.get(position).apply {
            Picasso.get().load(flashSaleItem?.image_url).into(holder.background)
            holder.category.text = flashSaleItem?.category
            holder.name.text = flashSaleItem?.name
            holder.price.text = price
            holder.sale.text = sale
        }
    }
}