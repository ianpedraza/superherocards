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
import com.ianpedraza.superherocards.utils.ViewExtensions.Companion.loadImageByUrl

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        val application = (requireContext().applicationContext as SuperheroCardsApplication)

        DetailViewModel.DetailViewModelFactory(
            application.getAllObtainedUseCase,
            application.addObtainedUseCase,
            application.removeObtainedUseCase
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
        binding.fabDetailObtained.setOnClickListener { viewModel.toggleObtained() }
    }

    private fun subscribeObservers() {
        viewModel.isObtained.observe(viewLifecycleOwner) { isObtained ->
            val iconResource = if (isObtained) {
                R.drawable.ic_checked
            } else {
                R.drawable.ic_unchecked
            }

            binding.fabDetailObtained.setImageResource(iconResource)
            binding.fabDetailObtained.tag = iconResource
        }
    }

    private fun setupCard(card: CardModel) {
        with(binding) {
            textViewDetailName.text = card.name
            textViewDetailDescription.text = card.description
            chipDetailRarity.text = getString(R.string.format_rarity, card.rarity.number)
            chipDetailCategory.text = card.category.toString()
            imageViewDetailCover.loadImageByUrl(card.image)
        }
    }
}
