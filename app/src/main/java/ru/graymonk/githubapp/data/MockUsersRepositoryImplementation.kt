package ru.graymonk.githubapp.data

import android.os.Handler
import android.os.Looper
import ru.graymonk.githubapp.domain.entities.UserEntity
import ru.graymonk.githubapp.domain.repository.UsersRepository

private const val DATA_LOADING_MOCK_DELAY = 3500L

class MockUsersRepositoryImplementation : UsersRepository {

    private val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        }, DATA_LOADING_MOCK_DELAY)

    }

}