package ru.graymonk.githubapp.presenter.ui.users

import ru.graymonk.githubapp.domain.entities.UserEntity

interface UsersContract {
    interface View {
        fun showProgress(inProgress: Boolean)
        fun showError(throwable: Throwable)
        fun showUsers(users: List<UserEntity>)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }
}