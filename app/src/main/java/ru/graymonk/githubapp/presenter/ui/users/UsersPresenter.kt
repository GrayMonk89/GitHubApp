package ru.graymonk.githubapp.presenter.ui.users

import ru.graymonk.githubapp.domain.repository.UsersRepository

class UsersPresenter(
    private val usersRepository: UsersRepository
) : UsersContract.Presenter {

    private var view: UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        userListUpdate()
    }

    private fun userListUpdate() {
        view?.showProgress(true)

        usersRepository.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
            }
        )
    }
}