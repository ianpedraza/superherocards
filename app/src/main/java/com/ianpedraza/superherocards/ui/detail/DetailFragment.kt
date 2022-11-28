package com.ianpedraza.superherocards.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.databinding.FragmentDetailBinding
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.utils.ViewExtensions.Companion.fromUrl

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var card: CardModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card = args.card
        setupUI()
    }

    private fun setupUI() {
        setupCard(card)
    }

    private fun setupCard(card: CardModel) {
        with(binding) {
            textViewDetailName.text = card.name
            textViewDetailDescription.text = card.description
            chipDetailRarity.text = getString(R.string.format_rarity, card.rarity.number)
            chipDetailCategory.text = card.category.toString()
            imageViewDetailCover.fromUrl(card.image)
        }
    }
}
