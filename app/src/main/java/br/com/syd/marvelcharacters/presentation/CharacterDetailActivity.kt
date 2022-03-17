package br.com.syd.marvelcharacters.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import com.bumptech.glide.Glide

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<CharacterModel>("name_of_extra")
        val text = findViewById<TextView>(R.id.name)
        val imgView = findViewById<ImageView>(R.id.detail_character_image)
        text.text = character?.name?:""
        //Picasso.with(this)
        //    .load(character?.picture?.replace("portrait_small","portrait_medium")?:"")
        //    .into(imgView);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(imgView);

    }
}