package com.ianpedraza.collectiblecards.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ianpedraza.collectiblecards.R
import com.ianpedraza.collectiblecards.databinding.FragmentDetailBinding
import com.ianpedraza.collectiblecards.domain.models.CardModel
import com.ianpedraza.collectiblecards.ui.common.BaseLifecycleObserverFragment
import com.ianpedraza.collectiblecards.utils.ViewExtensions.Companion.loadImageByUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseLifecycleObserverFragment(TAG) {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var card: CardModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
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

    companion object {
        private const val TAG = "DetailFragment"
    }
}
