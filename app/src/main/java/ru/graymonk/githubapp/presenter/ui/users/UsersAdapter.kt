package ru.graymonk.githubapp.presenter.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.graymonk.githubapp.domain.entities.UserEntity

class UsersAdapter  : RecyclerView.Adapter<UsersViewHolder>() {

    private var data = mutableListOf<UserEntity>()

    init {
        // Заявляем что у элементов есть статичный id,
        // и включится механизм автоматического сравнения объектов списка по Id
        // (но это не точно) TODO Уточни!
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): UserEntity = data[position]


    @SuppressLint("NotifyDataSetChanged")
    fun setData(usersList: List<UserEntity>) {
        data.clear()
        data.addAll(usersList)
        notifyDataSetChanged()
    }
}