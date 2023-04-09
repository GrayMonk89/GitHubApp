package ru.graymonk.githubapp.presenter.ui.users

import ru.graymonk.githubapp.domain.entities.UserEntity
import ru.graymonk.githubapp.domain.repository.UsersRepository

class UsersPresenter(
    private val usersRepository: UsersRepository
) : UsersContract.Presenter {

    private var view: UsersContract.View? = null

    private var usersList: List<UserEntity>? = null
    private var inProgress: Boolean = false

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        usersList?.let { view.showUsers(it) }

    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        userListUpdate()
    }

    private fun userListUpdate() {
        view?.showProgress(true)
        inProgress = true
        usersRepository.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                usersList = it
                inProgress = false
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                inProgress = false
            }
        )
    }
}