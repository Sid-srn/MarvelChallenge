package br.com.syd.marvelcharacters.presentation

import android.content.Context
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
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import br.com.syd.marvelcharacters.util.IFavoriteHandle
import br.com.syd.marvelcharacters.util.IcallDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.reflect.Type


class AllCharactersFragment : Fragment(), IcallDetail, IFavoriteHandle {
    //private val binding: FragmentAllCharactersBinding by lazy {
    //    FragmentAllCharactersBinding.inflate(layoutInflater)
    //}
    private lateinit var binding: FragmentAllCharactersBinding

    private val characterAdapter: LineAdapter by lazy {
        LineAdapter()
    }

    private lateinit var lManager: StaggeredGridLayoutManager

    private val characterViewModel: CharacterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllCharactersBinding.bind(view)
        lManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        setupView()
    }

    private fun setupView() {
        setupRecyclewView()
        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        characterViewModel.charactersListOb.observe(
            viewLifecycleOwner,
            Observer(::handleCharacters)
        )
        characterViewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CharactersViewEvents.NotifyReloadCharactersSuccess -> handleReloadedCharacters(it.characters)
            }
        })
    }

    private fun handleCharacters(charactersList: List<CharacterModel>) {
        characterAdapter.setList(charactersList)
    }

    private fun handleReloadedCharacters(charactersList: List<CharacterModel>) {
        characterAdapter.setList(charactersList)
        binding.swipeContainer.isRefreshing = false
    }

    private fun setupRecyclewView() {
        characterAdapter.setCallDetail(this)
        characterAdapter.setFavoriteHandle(this)
        characterAdapter.setLayoutManager(lManager)
        binding.characterRecyclerView.apply {
            layoutManager =
                lManager//StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = characterAdapter
        }
    }

    private fun setListeners() {
        binding.swipeContainer.setOnRefreshListener {
            characterViewModel.reloadCharacters()

        }
        binding.swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        binding.changeListBtn.setOnClickListener {

            if (lManager.spanCount == 1) {
                lManager.spanCount = 2
                binding.changeListBtn.text = "list"
            } else {
                lManager.spanCount = 1
                binding.changeListBtn.text = "grid"
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
            FavoriteCharacterModel(
                characterModel.id,
                characterModel.name,
                characterModel.picture
            )
        )
    }

    override fun deleteFavorite(characterModel: CharacterModel) {
        TODO("Not yet implemented")
    }
}