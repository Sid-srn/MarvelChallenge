package br.com.syd.marvelcharacters

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.syd.marvelcharacters.presentation.CharacterViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var charatersPagerAdapter: CharatersPagerAdapter
    val characterViewModel  by viewModel<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.pager)

        charatersPagerAdapter = CharatersPagerAdapter(supportFragmentManager)
        charatersPagerAdapter.add(AllCharactersFragment(),"All")
        charatersPagerAdapter.add(FavoriteCharactersFragment(),"Favorite")
        viewPager.adapter = charatersPagerAdapter

        //characterViewModel.getCaracters()
    }

    private fun observeVM(){
        characterViewModel.viewState.observe(this, Observer {
            when(it){

            }
        })
    }

}

class CharatersPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val listFragments = ArrayList<Fragment>()
    private val listFragmentsTitle = ArrayList<String>()

    override fun getCount(): Int  = listFragments.size

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
