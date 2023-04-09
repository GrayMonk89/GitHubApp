package ru.graymonk.githubapp.domain.repository

import ru.graymonk.githubapp.domain.entities.UserEntity

interface UsersRepository {
    //CRUD
    //Create
    //Read
    //Update
    //Delete

    // синхронный вариант fun getUsers(): List<UserEntity>
    fun getUsers(
        //Лямбда подразумевает такую функцию на вход которой приходит параметр типа List<UserEntity>,
        // а возвращает значение типа Unit(т.е. ни хуя в данном случае).
        // Также возможен вариант и с несколькими входными полями
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}