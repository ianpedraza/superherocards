package com.ianpedraza.superherocards.ui.cards.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ianpedraza.superherocards.R
import com.ianpedraza.superherocards.databinding.ItemCardListBinding
import com.ianpedraza.superherocards.domain.models.CardModel
import com.ianpedraza.superherocards.utils.ViewExtensions.Companion.loadImageByUrl

class CardsListAdapter(
    private val onAction: (Action) -> Unit
) : ListAdapter<CardModel, CardsListAdapter.ViewHolder>(CardDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(getItem(position), onAction)

    class ViewHolder private constructor(
        private val binding: ItemCardListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemCardListBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: CardModel, onAction: (Action) -> Unit) {
            with(binding) {
                textViewItemListName.text = item.name
                textViewItemListRarity.text =
                    root.context.getString(R.string.format_rarity, item.rarity.number)

                imageViewItemList.loadImageByUrl(item.image)

                root.setOnClickListener {
                    onAction(Action.OnClick(item))
                }
            }
        }
    }
}

object CardDiffCallback : DiffUtil.ItemCallback<CardModel>() {
    override fun areItemsTheSame(
        oldItem: CardModel,
        newItem: CardModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CardModel,
        newItem: CardModel
    ): Boolean = oldItem == newItem
}

sealed interface Action {
    data class OnClick(
        val card: CardModel
    ) : Action
}
