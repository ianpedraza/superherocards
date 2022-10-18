package com.ianpedraza.superherocards.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.SuperheroCardsApplication
import com.ianpedraza.superherocards.databinding.FragmentGridListBinding

class GridListFragment : Fragment() {

    private var _binding: FragmentGridListBinding? = null
    private val binding: FragmentGridListBinding get() = _binding!!

    private val viewModel: CardsViewModel by viewModels {
        val application = (requireContext().applicationContext as SuperheroCardsApplication)
        CardsViewModel.CardsViewModelFactory(application.getAllCardsUseCase)
    }

    private lateinit var adapter: CardsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridListBinding.inflate(inflater, container, false)
        adapter = CardsListAdapter(onAction)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        subscribeObservers()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCards.apply {
            this.adapter = this@GridListFragment.adapter
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
                    GridListFragmentDirections.actionGridListFragmentToDetailFragment(action.card)

                val extras = FragmentNavigatorExtras(
                    action.imageView to getString(R.string.transition_card_image),
                    action.textViewName to getString(R.string.transition_card_name),
                    action.textViewRarity to getString(R.string.transition_card_rarity)
                )

                findNavController().navigate(navigationAction, extras)
            }
        }
    }
}
