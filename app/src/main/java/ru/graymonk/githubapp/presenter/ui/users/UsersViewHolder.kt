package ru.graymonk.githubapp.presenter.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.graymonk.githubapp.R
import ru.graymonk.githubapp.databinding.ItemUserBinding
import ru.graymonk.githubapp.domain.entities.UserEntity

class UsersViewHolder (parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {

    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        binding.itemUserImageViewAvatar.load(userEntity.avatarUrl){
            crossfade(true)
            crossfade(2000)
            transformations(RoundedCornersTransformation(40F))
            scale(Scale.FILL)
        }
        binding.itemUserTextViewLogin.text = userEntity.login
        binding.itemUserTextViewId.text = userEntity.id.toString()
    }
}