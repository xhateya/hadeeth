package com.xhateya.idn.hadeeth

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.xhateya.idn.hadeeth.model.ModelHadith


class HadithAdapter(
    private val items: List<ModelHadith>,
    private val onSelectData: OnSelectedData
) : RecyclerView.Adapter<HadithAdapter.ViewHolder>() {
    interface OnSelectedData {
        fun onSelected(modelHadith: ModelHadith?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val hadith = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(hadith)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.cvHadith.setOnClickListener { onSelectData.onSelected(data) }
        holder.txtNumber.text = data.urutan
        holder.txtName.text = data.nama
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvHadith: CardView = itemView.findViewById(R.id.cvHadith)
        var txtName: TextView = itemView.findViewById(R.id.txtName)
        var txtNumber:TextView= itemView.findViewById(R.id.txtNumber)
    }
}