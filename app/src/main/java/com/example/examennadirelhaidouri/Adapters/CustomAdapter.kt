package com.example.examennadirelhaidouri.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examennadirelhaidouri.R
import com.example.examennadirelhaidouri.Room.Mobles

class CustomAdapter(
private val mList: List<Mobles>,
private val itemClickListener: (Mobles) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]

        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground)
        holder.textViewNom.text = item.nom
        holder.textViewPreu.text = item.preu.toString() + "€"



        holder.itemView.setOnClickListener {
            itemClickListener(item)

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewNom: TextView = itemView.findViewById(R.id.Nom)
        val textViewPreu: TextView = itemView.findViewById(R.id.Preu)
    }
}
