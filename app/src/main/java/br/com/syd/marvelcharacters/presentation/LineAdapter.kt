package br.com.syd.marvelcharacters.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.IFavoriteHandle
import br.com.syd.marvelcharacters.util.IcallDetail
import com.squareup.picasso.Picasso

class LineAdapter(//private val items: MutableList<CharacterModel>
) : RecyclerView.Adapter<LineAdapter.ViewHolder>() {
    private val items: MutableList<CharacterModel> = mutableListOf()
    private lateinit var call: IcallDetail
    private lateinit var favoriteHandle: IFavoriteHandle
    private var layoutManager: StaggeredGridLayoutManager? = null

    enum class ViewType {
        SMALL,
        DETAILED
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newItems: List<CharacterModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(newItems: List<CharacterModel>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setCallDetail(call: IcallDetail) {
        this.call = call
    }

    fun setFavoriteHandle(favoriteHandle: IFavoriteHandle) {
        this.favoriteHandle = favoriteHandle
    }

    fun setLayoutManager(layoutManager: StaggeredGridLayoutManager) {
        this.layoutManager = layoutManager
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.SMALL.ordinal
        else ViewType.DETAILED.ordinal
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView)
        private val cardView: CardView = view.findViewById(R.id.card)
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        val btnFav: ImageButton = view.findViewById(R.id.btn_fav)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: CharacterModel) {
            Picasso.with(itemView.context).load(item.picture).into(imageView)
            textView.text = item.name
            if (item.isFavority)
                btnFav.setImageResource(R.drawable.ic_star_filled)
            else
                btnFav.setImageResource(R.drawable.ic_star)
            cardView.setOnClickListener {
                call.callDetail(item)
            }
            btnFav.setOnClickListener {
                if(item.isFavority)
                    favoriteHandle.deleteFavorite(item)
                else
                    favoriteHandle.saveFavorite(item)

                item.isFavority = !item.isFavority
                notifyDataSetChanged()
            }
        }
    }
}