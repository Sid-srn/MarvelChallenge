package br.com.syd.marvelcharacters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.*

import com.google.android.material.tabs.TabLayout
import android.content.Intent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.syd.marvelcharacters.domain.model.CharacterModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllCharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllCharactersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView = view.findViewById<RecyclerView>(R.id.character_recycler_view)
        val btnChange = view.findViewById<Button>(R.id.appCompatButton)
        val btnOpen = view.findViewById<Button>(R.id.openBtn)
        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)

        val arrayList = ArrayList<String>()//Creating an empty arraylist
        arrayList.add("Ajay")//Adding object in arraylist
        arrayList.add("Vijay")
        arrayList.add("Prakash")
        arrayList.add("Rohan")
        arrayList.add("Vijay")
        arrayList.add("Ajay")//Adding object in arraylist
        arrayList.add("Vijay")
        arrayList.add("Prakash")
        arrayList.add("Rohan")
        arrayList.add("Vijay")
        arrayList.add("Ajay")//Adding object in arraylist
        arrayList.add("Vijay")
        arrayList.add("Prakash")
        arrayList.add("Rohan")
        arrayList.add("Vijay")


        //val layoutManager = LinearLayoutManager(this.activity,  LinearLayoutManager.HORIZONTAL, false)
        //val layoutManager = GridLayoutManager(this.activity,  2)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val mAdapter = LineAdapter(arrayList, layoutManager)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addItemDecoration(
            DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL)
        )

        btnChange.setOnClickListener {
                    if (layoutManager.spanCount == 1) {
                        layoutManager.spanCount = 2
                        btnChange.text = "list"
                    } else {
                        layoutManager.spanCount = 1
                        btnChange.text = "grid"
                    }
            mAdapter.notifyItemRangeChanged(0, mAdapter.itemCount ?: 0)
        }

        btnOpen.setOnClickListener{
            val characterModel = CharacterModel(1,"", "", "",ArrayList<String>(),ArrayList<String>(), false)
            val intent = Intent(this.activity, CharacterDetailActivity::class.java)
            intent.putExtra("name_of_extra", characterModel)
            startActivity(intent)
        }

        swipeContainer.setOnRefreshListener {

            val arrayList = ArrayList<String>()//Creating an empty arraylist
            arrayList.add("Updated - Ajay")//Adding object in arraylist
            arrayList.add("Updated - Vijay")
            arrayList.add("Updated - Prakash")
            arrayList.add("Updated - Rohan")
            arrayList.add("Updated - Vijay")
            arrayList.add("Updated - Ajay")//Adding object in arraylist
            arrayList.add("Updated - Vijay")
            arrayList.add("Updated - Prakash")
            arrayList.add("Updated - Rohan")
            arrayList.add("Updated - Vijay")
            arrayList.add("Updated - Ajay")//Adding object in arraylist
            arrayList.add("Updated - Vijay")
            arrayList.add("Updated - Prakash")
            arrayList.add("Updated - Rohan")
            arrayList.add("Updated - Vijay")


            //val layoutManager = LinearLayoutManager(this.activity,  LinearLayoutManager.HORIZONTAL, false)
            //val layoutManager = GridLayoutManager(this.activity,  2)
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            val mAdapter = LineAdapter(arrayList, layoutManager)
            mRecyclerView.layoutManager = layoutManager
            mRecyclerView.adapter = mAdapter
            swipeContainer.isRefreshing = false
        }
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

    }

    private fun setupRecycler() {


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllCharactersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllCharactersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}