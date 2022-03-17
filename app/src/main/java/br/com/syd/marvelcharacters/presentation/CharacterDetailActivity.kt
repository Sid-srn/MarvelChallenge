package br.com.syd.marvelcharacters.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.databinding.ActivityCharacterDetailBinding
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<CharacterModel>("name_of_extra")
        val text = findViewById<TextView>(R.id.name)
        binding.name.text = character?.name?:""
        Picasso.with(this)
            .load(character?.picture?.replace("portrait_small","standard_amazing")?:"")
            .into(binding.detailCharacterImage)
    }
}