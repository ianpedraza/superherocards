package com.ianpedraza.superherocards.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class CardModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val image: String,
    val rarity: Rarity = Rarity.Rarity1,
    val description: String,
    val category: Category = Category.Default
) : Parcelable

@Parcelize
sealed class Category : Parcelable {
    object Default : Category()

    override fun toString(): String = when (this) {
        Default -> "Default"
    }
}

@Parcelize
sealed class Rarity : Parcelable {
    object Rarity1 : Rarity() {
        override val number: Int get() = 1
    }

    object Rarity2 : Rarity() {
        override val number: Int get() = 2
    }

    object Rarity3 : Rarity() {
        override val number: Int get() = 3
    }

    object Rarity4 : Rarity() {
        override val number: Int get() = 4
    }

    object Rarity5 : Rarity() {
        override val number: Int get() = 5
    }

    abstract val number: Int
}
