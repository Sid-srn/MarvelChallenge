package br.com.syd.marvelcharacters.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.syd.marvelcharacters.R
import br.com.syd.marvelcharacters.databinding.FragmentAllCharactersBinding
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.Constants.CHARACTER_INTENT_EXTRA
import br.com.syd.marvelcharacters.util.Constants.CHARACTER_INTENT_RESULT
import br.com.syd.marvelcharacters.util.IFavoriteHandle
import br.com.syd.marvelcharacters.util.IcallDetail
import org.koin.android.viewmodel.ext.android.sharedViewModel


class AllCharactersFragment : Fragment(), IcallDetail, IFavoriteHandle {
    private lateinit var binding: FragmentAllCharactersBinding

    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }
    private lateinit var lManager: StaggeredGridLayoutManager

    private val characterViewModel: CharacterViewModel by sharedViewModel()

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
        characterViewModel.characterExceptionOb.observe(
            viewLifecycleOwner,
            Observer(::handleError)
        )
    }


    private fun handleError(exception: Exception?) {
        showErrorView()
    }

    private fun handleCharacters(charactersList: List<CharacterModel>) {
        characterAdapter.setList(charactersList)
        showSuccessView()
    }

    private fun setupRecyclewView() {
        characterAdapter.setCallDetail(this)
        characterAdapter.setFavoriteHandle(this)
        characterAdapter.setLayoutManager(lManager)
        binding.allCharactersView.characterRecyclerView.apply {
            layoutManager =
                lManager
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
            characterViewModel.resetList()
        }

        binding.swipeConnectionError.setOnRefreshListener {
            characterViewModel.resetList()
        }

        binding.allCharactersView.changeListBtn.setOnClickListener {
            if (lManager.spanCount == 1) {
                lManager.spanCount = 2
                binding.allCharactersView.changeListBtn.setImageResource(R.drawable.ic_list_grid)
            } else {
                lManager.spanCount = 1
                binding.allCharactersView.changeListBtn.setImageResource(R.drawable.ic_grid_list)
            }
            characterAdapter.notifyItemRangeChanged(0, characterAdapter.itemCount ?: 0)
        }

        binding.allCharactersView.characterRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1) && lManager.childCount > 0) {
                    characterViewModel.loadMoreCharacters()
                }
            }
        })
    }

    override fun callDetail(characterModel: CharacterModel) {
        val intent = Intent(this.activity, CharacterDetailActivity::class.java)
        intent.putExtra(CHARACTER_INTENT_EXTRA, characterModel)
        register.launch(intent)
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { data ->
                if (data.hasExtra(CHARACTER_INTENT_RESULT)) {
                    val resultCharacter = data.getParcelableExtra<CharacterModel>(CHARACTER_INTENT_RESULT)
                    resultCharacter?.let { result ->
                        if (result.isFavority)
                            characterViewModel.saveFavorite(result)
                        else
                            characterViewModel.removeFavorite(result)
                    }
                }
            }
        }
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

    override fun onPause() {
        super.onPause()
        binding.shimmerViewContainer.stopShimmer()
    }

    private fun showLoadingView() {
        binding.shimmerViewContainer.visibility = View.VISIBLE
        binding.shimmerViewContainer.startShimmer()
    }

    private fun showErrorView() {
        binding.connectionErrorImage.visibility = View.VISIBLE
        binding.connectionErrorText.visibility = View.VISIBLE
        binding.allCharactersView.swipeContainer.isRefreshing = false
        binding.swipeConnectionError.isRefreshing = false
        binding.shimmerViewContainer.visibility = View.GONE
        binding.allCharactersView.charactersGridLayout.visibility = View.GONE
        binding.shimmerViewContainer.stopShimmer();
    }

    private fun showSuccessView() {
        binding.connectionErrorImage.visibility = View.GONE
        binding.connectionErrorText.visibility = View.GONE
        binding.allCharactersView.swipeContainer.isRefreshing = false
        binding.swipeConnectionError.isRefreshing = false
        binding.shimmerViewContainer.visibility = View.GONE
        binding.allCharactersView.charactersGridLayout.visibility = View.VISIBLE
        binding.shimmerViewContainer.stopShimmer();
    }
}