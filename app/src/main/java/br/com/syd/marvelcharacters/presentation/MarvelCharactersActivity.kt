package br.com.syd.marvelcharacters.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var charatersPagerAdapter: CharatersPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.pager)

        charatersPagerAdapter = CharatersPagerAdapter(supportFragmentManager)
        charatersPagerAdapter.add(AllCharactersFragment(), "All")
        charatersPagerAdapter.add(FavoriteCharactersFragment(), "Favorite")
        //binding.pager.adapter= charatersPagerAdapter
        viewPager.adapter = charatersPagerAdapter
    }
}

class CharatersPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

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

private const val ARG_OBJECT = "object"
