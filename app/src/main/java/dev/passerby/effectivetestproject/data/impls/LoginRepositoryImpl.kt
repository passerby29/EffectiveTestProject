package dev.passerby.effectivetestproject.data.impls

import dev.passerby.effectivetestproject.data.room.UserDao
import dev.passerby.effectivetestproject.data.room.UserMapper
import dev.passerby.effectivetestproject.domain.models.User
import dev.passerby.effectivetestproject.domain.repos.LoginRepository

@Suppress("unused")
class LoginRepositoryImpl(
    private val userDao: UserDao,
    private val mapper: UserMapper
) : LoginRepository {
    override suspend fun addUser(user: User) {
        userDao.addUser(mapper.mapEntityToDbModel(user))
    }

    override suspend fun getUser(first_name: String): User {
        val entity = userDao.getUser(first_name)
        return mapper.mapDbModelToEntity(entity)
    }
}