package com.lvb.projects.app_marvel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lvb.projects.app_marvel.R
import com.lvb.projects.app_marvel.data.model.character.CharacterModel
import com.lvb.projects.app_marvel.databinding.ItemCharacterBinding
import com.lvb.projects.app_marvel.util.limitDescription
import com.lvb.projects.app_marvel.util.loadImage

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Verify the difference between two lists
    private val differCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.description == newItem.description &&
                    oldItem.thumbnailModel.path == newItem.thumbnailModel.path && oldItem.thumbnailModel.extension == newItem.thumbnailModel.extension
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var characters: List<CharacterModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun getItemCount(): Int = characters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.apply {
            tvNameCharacter.text = character.name
            if (character.description == "") {
                tvDescriptionCharacter.text =
                    holder.itemView.context.getString(R.string.text_description_empty)
            }
            else {
                tvDescriptionCharacter.text = character.description.limitDescription(100)
            }

            // Get image from web and load into image component in recycle view holder
            loadImage(imgCharacter, character.thumbnailModel.path, character.thumbnailModel.extension)
        }

        holder.itemView.setOnClickListener {
            onItemCLickListener?.let {
                it(character)
            }
        }
    }

    private var onItemCLickListener: ((CharacterModel) -> Unit)? = null

      fun setOnCLickListener(listener: (CharacterModel) -> Unit) {
          onItemCLickListener = listener
      }

    fun getCharacterPosition(position: Int): CharacterModel {
        return characters[position]
    }

}