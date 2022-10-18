package com.ianpedraza.superherocards.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.SuperheroCardsApplication
import com.ianpedraza.superherocards.databinding.FragmentDetailBinding
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.ui.profile.DetailViewModel
import com.ianpedraza.superherocards.utils.ViewExtensions.Companion.loadImageByUrl

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        val application = (requireContext().applicationContext as SuperheroCardsApplication)

        DetailViewModel.DetailViewModelFactory(
            application.getFavoritesUseCase,
            application.addFavoritesUseCase,
            application.removeFavoriteUseCase
        )
    }

    private lateinit var card: CardModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        card = args.card
        viewModel.setCard(card)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        subscribeObservers()
    }

    private fun setupUI() {
        setupCard(card)
        binding.fabFavorite.setOnClickListener { viewModel.toggleFavorite() }
    }

    private fun subscribeObservers() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            val iconResource = if (isFavorite) {
                R.drawable.ic_start
            } else {
                R.drawable.ic_star_outline
            }

            binding.fabFavorite.setImageResource(iconResource)
        }
    }

    private fun setupCard(card: CardModel) {
        with(binding) {
            textViewName.text = card.name
            textViewDescription.text = card.description
            chipRarity.text = getString(R.string.format_rarity, card.rarity.number)
            chipCategory.text = card.category.toString()
            imageViewCover.loadImageByUrl(card.image)
        }
    }
}
