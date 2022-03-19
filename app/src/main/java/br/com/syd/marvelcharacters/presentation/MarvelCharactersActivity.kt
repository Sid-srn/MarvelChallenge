package br.com.syd.marvelcharacters.presentation

import android.os.Bundle
import android.view.WindowInsets.Side.all
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.databinding.ActivityMarvelCharactersBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarvelCharactersBinding

    private lateinit var charatersPagerAdapter: CharatersPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_marvel_characters)

        val viewPager = findViewById<ViewPager>(R.id.pager)

        charatersPagerAdapter = CharatersPagerAdapter(supportFragmentManager)
        charatersPagerAdapter.add(AllCharactersFragment(), resources.getString(R.string.all))
        charatersPagerAdapter.add(FavoriteCharactersFragment(), resources.getString(R.string.favorite))
        viewPager.adapter = charatersPagerAdapter
    }
}

class CharatersPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val listFragments = ArrayList<Fragment>()
    private val listFragmentsTitle = ArrayList<String>()

    override fun getCount(): Int = listFragments.size

    fun add(frag: Fragment, title: String) {
        listFragments.add(frag)
        listFragmentsTitle.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return listFragments[position];
    }

    override fun getPageTitle(position: Int): CharSequence {
        return listFragmentsTitle[position]
    }
}
