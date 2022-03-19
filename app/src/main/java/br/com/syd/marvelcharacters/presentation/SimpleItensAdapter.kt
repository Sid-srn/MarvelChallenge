package br.com.syd.marvelcharacters.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.databinding.AdapterSimpleItensBinding

class SimpleItensAdapter : RecyclerView.Adapter<SimpleItensAdapter.LineViewHolder>() {
    private var gridIcon = R.drawable.ic_comics
    private val items: MutableList<String> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newItems: List<String>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setIcon(icon: Int) {
        gridIcon = icon
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleItensAdapter.LineViewHolder {
        return LineViewHolder(
            AdapterSimpleItensBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimpleItensAdapter.LineViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class LineViewHolder(
        private val itemBinding: AdapterSimpleItensBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: String) {
            itemBinding.lineIcon.setImageResource(gridIcon)
            itemBinding.lineText.text = item
        }
    }
}