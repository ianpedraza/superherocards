package com.ianpedraza.superherocards.ui.cards.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.SuperheroCardsApplication
import com.ianpedraza.superherocards.databinding.FragmentListBinding
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.ui.cards.CardsViewModel

class ListFragment : Fragment(), MenuProvider {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    private val viewModel: CardsViewModel by viewModels {
        val application = (requireContext().applicationContext as SuperheroCardsApplication)
        CardsViewModel.CardsViewModelFactory(
            application.getAllCardsUseCase,
            application.getAllByRarityUseCase
        )
    }

    private lateinit var adapter: CardsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        adapter = CardsListAdapter(onAction)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        subscribeObservers()
    }

    private fun setupUI() {
        setupMenu()
        setupRecyclerView()
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerView() {
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // val snapHelper = PagerSnapHelper()
        // snapHelper.attachToRecyclerView(binding.recyclerViewCardsList)

        val itemDecorator = DividerItemDecoration(requireContext(), manager.orientation)

        binding.recyclerViewCardsList.apply {
            this.adapter = this@ListFragment.adapter
            layoutManager = manager
            addItemDecoration(itemDecorator)
        }
    }

    private fun subscribeObservers() {
        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            adapter.submitList(cards)
        }
    }

    private val onAction: (Action) -> Unit = { action ->
        when (action) {
            is Action.OnClick -> {
                val navigationAction =
                    ListFragmentDirections.actionHorizontalListFragmentToDetailFragment(
                        action.card
                    )

                findNavController().navigate(navigationAction)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_rarity_filter, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean = when (menuItem.itemId) {
        R.id.menuItemAll -> {
            viewModel.fetchData()
            true
        }
        R.id.menuItemRarity1 -> {
            viewModel.filterByRarity(Rarity.Rarity1)
            true
        }
        R.id.menuItemRarity2 -> {
            viewModel.filterByRarity(Rarity.Rarity2)
            true
        }
        R.id.menuItemRarity3 -> {
            viewModel.filterByRarity(Rarity.Rarity3)
            true
        }
        R.id.menuItemRarity4 -> {
            viewModel.filterByRarity(Rarity.Rarity4)
            true
        }
        R.id.menuItemRarity5 -> {
            viewModel.filterByRarity(Rarity.Rarity5)
            true
        }
        else -> false
    }
}
