package br.com.syd.marvelcharacters.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.databinding.ActivityCharacterDetailBinding
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.Constants.DETAIL_IMAGE
import br.com.syd.marvelcharacters.util.Constants.LIST_IMAGE
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailBinding
    private var detailedCharacter: CharacterModel? = null
    private val comicsAdapter: SimpleItensAdapter by lazy {
        SimpleItensAdapter()
    }
    private val seriesAdapter: SimpleItensAdapter by lazy {
        SimpleItensAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        detailedCharacter = intent.getParcelableExtra<CharacterModel>("name_of_extra")

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.title = detailedCharacter?.name ?: ""

        Picasso.with(this)
            .load(getLargeImage())
            .into(binding.detailCharacterImage)

        detailedCharacter?.let {
            if (!it.description.isNullOrBlank())
                binding.descriptionText.text = it.description
        }

        if ((detailedCharacter?.comics ?: arrayListOf()).isNotEmpty()) {
            binding.comicsRecyclerView.visibility = View.VISIBLE
            binding.emptyComicsCard.visibility = View.GONE
            comicsAdapter.setList(detailedCharacter?.comics ?: arrayListOf())
            binding.comicsRecyclerView.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = comicsAdapter
            }
        } else {
            binding.comicsRecyclerView.visibility = View.GONE
            binding.emptyComicsCard.visibility = View.VISIBLE
        }

        if ((detailedCharacter?.series ?: arrayListOf()).isNotEmpty()) {
            binding.seriesRecyclerView.visibility = View.VISIBLE
            binding.emptySeriesCard.visibility = View.GONE
            seriesAdapter.setList(detailedCharacter?.series ?: arrayListOf())
            binding.seriesRecyclerView.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = seriesAdapter
            }
        }else {
            binding.seriesRecyclerView.visibility = View.GONE
            binding.emptySeriesCard.visibility = View.VISIBLE
        }

    }

    private fun getLargeImage() =
        detailedCharacter?.picture?.replace(LIST_IMAGE, DETAIL_IMAGE) ?: ""

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu);
        val icon = menu.findItem(R.id.favorite_chacter)
        updateMenuIcon(icon)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite_chacter -> {
                detailedCharacter?.isFavority =
                    !(detailedCharacter?.isFavority ?: false)
                updateMenuIcon(item)
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    private fun updateMenuIcon(icon: MenuItem) {
        if (detailedCharacter?.isFavority == true)
            icon.setIcon(R.drawable.ic_star_filled)
        else
            icon.setIcon(R.drawable.ic_star)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        Intent().apply {
            putExtra("resultTest", detailedCharacter)
            setResult(RESULT_OK, this)
        }
        super.onBackPressed()
    }
}