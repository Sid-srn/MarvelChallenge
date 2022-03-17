package br.com.syd.marvelcharacters.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.databinding.FragmentAllCharactersBinding
import br.com.syd.marvelcharacters.databinding.FragmentFavoriteCharactersBinding
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.IFavoriteHandle
import br.com.syd.marvelcharacters.util.IcallDetail
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteCharactersFragment : Fragment(), IcallDetail, IFavoriteHandle {
    private lateinit var binding: FragmentFavoriteCharactersBinding

    private val characterAdapter: LineAdapter by lazy {
        LineAdapter()
    }
    private lateinit var lManager: StaggeredGridLayoutManager

    private val characterViewModel: CharacterViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteCharactersBinding.bind(view)
        lManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        setupView()
    }

    private fun setupView() {
        setupRecyclewView()
        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        characterViewModel.favoriteCharactersListOb.observe(
            viewLifecycleOwner,
            Observer(::handleCharacters)
        )
        /*characterViewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CharactersViewEvents.NotifyReloadCharactersSuccess -> handleReloadedCharacters(it.characters)
            }
        })*/
    }

    private fun handleCharacters(charactersList: List<CharacterModel>) {
        characterAdapter.setList(charactersList)
        binding.allCharactersView.swipeContainer.isRefreshing = false
    }

    private fun setupRecyclewView() {
        characterAdapter.setCallDetail(this)
        characterAdapter.setFavoriteHandle(this)
        characterAdapter.setLayoutManager(lManager)
        binding.allCharactersView.characterRecyclerView.apply {
            layoutManager =
                lManager//StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = characterAdapter
        }

        binding.allCharactersView.swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    private fun setListeners() {
        binding.allCharactersView.swipeContainer.setOnRefreshListener {
            characterViewModel.reloadCharacters()
        }

        binding.allCharactersView.changeListBtn.setOnClickListener {
            if (lManager.spanCount == 1) {
                lManager.spanCount = 2
                binding.allCharactersView.changeListBtn.text = "list"
            } else {
                lManager.spanCount = 1
                binding.allCharactersView.changeListBtn.text = "grid"
            }
            characterAdapter.notifyItemRangeChanged(0, characterAdapter.itemCount ?: 0)
        }
    }

    override fun callDetail(characterModel: CharacterModel) {
        val intent = Intent(this.activity, CharacterDetailActivity::class.java)
        intent.putExtra("name_of_extra", characterModel)
        startActivity(intent)
    }

    override fun saveFavorite(characterModel: CharacterModel) {
        characterViewModel.saveFavorite(
            characterModel
        )
    }

    override fun deleteFavorite(characterModel: CharacterModel) {
        characterViewModel.removeFavorite(
            characterModel
        )
    }

}