package com.ianpedraza.superherocards.ui.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ianpedraza.superherocards.databinding.ItemCardGridBinding
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.utils.ViewExtensions.Companion.getDarkColor
import com.ianpedraza.superherocards.utils.ViewExtensions.Companion.loadImageByUrl

class CardsGridAdapter(
    private val onAction: (Action) -> Unit
) : ListAdapter<CardModel, CardsGridAdapter.ViewHolder>(CardDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(getItem(position), onAction)

    class ViewHolder private constructor(
        private val binding: ItemCardGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemCardGridBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: CardModel, onAction: (Action) -> Unit) {
            with(binding) {
                textViewItemGridName.text = item.name
                textViewItemGridRarity.text = item.rarity.number.toString()

                imageViewItemGrid.loadImageByUrl(item.image) { drawable ->
                    drawable?.toBitmap()?.getDarkColor { color ->
                        binding.cardViewItemGrid.setCardBackgroundColor(color)
                    }
                }

                cardViewItemGrid.setOnClickListener {
                    onAction(Action.OnClick(item))
                }
            }
        }
    }
}
