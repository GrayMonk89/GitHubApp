package ru.graymonk.githubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.graymonk.githubapp.data.MockUsersRepositoryImplementation
import ru.graymonk.githubapp.domain.repository.UsersRepository

class App : Application() {

    val userRepository: UsersRepository by lazy { MockUsersRepositoryImplementation() }

}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App