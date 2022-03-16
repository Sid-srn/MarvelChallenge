package br.com.syd.marvelcharacters

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.syd.marvelcharacters.domain.model.CharacterModel

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<CharacterModel>("name_of_extra")
        val text = findViewById<TextView>(R.id.name)
        text.text = character?.name?:""

    }
}