package br.com.syd.marvelcharacters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.IcallDetail

class LineAdapter(//private val items: MutableList<CharacterModel>
) : RecyclerView.Adapter<LineAdapter.ViewHolder>() {
    private val items: MutableList<CharacterModel> = mutableListOf()
    lateinit var call: IcallDetail
    private var layoutManager: StaggeredGridLayoutManager? = null

    enum class ViewType {
        SMALL,
        DETAILED
    }

    fun setList(newItems: List<CharacterModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setCallDetail(call: IcallDetail) {
        this.call = call
    }

    fun setLayoutManager(layoutManager: StaggeredGridLayoutManager) {
        this.layoutManager = layoutManager
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineAdapter.ViewHolder {
        return when (viewType) {
            ViewType.DETAILED.ordinal -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.character_grid_view, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.character_line_view, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: LineAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.SMALL.ordinal
        else ViewType.DETAILED.ordinal
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView)
        private val cardView: CardView = view.findViewById(R.id.card)
        val btnFav: ImageButton = view.findViewById(R.id.btn_fav)

        fun bind(item: CharacterModel) {

            textView.text = item.name
            cardView.setOnClickListener {
                call.callDetail(item)
            }
        }
    }
}