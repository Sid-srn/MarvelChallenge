package br.com.syd.marvelcharacters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.IcallDetail

class LineAdapter(private val dataSet: ArrayList<String>, private val call: IcallDetail, private val layoutManager: StaggeredGridLayoutManager? = null): RecyclerView.Adapter<LineAdapter.ViewHolder>() {

    enum class ViewType {
        SMALL,
        DETAILED
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val cardView: CardView = view.findViewById(R.id.card)
        val btnFav: ImageButton = view.findViewById(R.id.btn_fav)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            ViewType.DETAILED.ordinal -> ViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.character_grid_view, viewGroup, false))
            else -> ViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.character_line_view, viewGroup, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position]
        holder.cardView.setOnClickListener {
            val characterModel = CharacterModel(1,dataSet[position], "", "",ArrayList<String>(),ArrayList<String>(), false)
            call.callDetail(characterModel)
        }
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.SMALL.ordinal
        else ViewType.DETAILED.ordinal
    }

    fun clear() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(tweetList: List<String>) {
        dataSet.addAll(tweetList)
        notifyDataSetChanged()
    }
}