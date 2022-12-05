package com.ianpedraza.superherocards.ui.favorites

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
import androidx.recyclerview.widget.GridLayoutManager
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.SuperheroCardsApplication
import com.ianpedraza.superherocards.databinding.FragmentFavoritesBinding
import com.ianpedraza.superherocards.domain.models.Rarity
import com.ianpedraza.superherocards.ui.cards.list.Action
import com.ianpedraza.superherocards.ui.cards.grid.CardsGridAdapter

class FavoritesFragment : Fragment(), MenuProvider {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!

    private lateinit var adapter: CardsGridAdapter

    private val viewModel: FavoritesViewModel by viewModels {
        val application = (requireContext().applicationContext as SuperheroCardsApplication)
        FavoritesViewModel.FavoritesViewModelFactory(
            application.getAllObtainedByRarityUseCase
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
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
        val layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = CardsGridAdapter(onAction)

        binding.recyclerViewFavorites.apply {
            this.adapter = this@FavoritesFragment.adapter
            this.layoutManager = layoutManager
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
                    FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(action.card)

                findNavController().navigate(navigationAction)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_rarity_filter, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean = when (menuItem.itemId) {
        R.id.menuItemAll -> {
            viewModel.filterByRarity(null)
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
