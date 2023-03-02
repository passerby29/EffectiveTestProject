package dev.passerby.effectivetestproject.data.impls

import android.app.Application
import dev.passerby.effectivetestproject.data.room.EffectiveAppDB
import dev.passerby.effectivetestproject.data.room.UserMapper
import dev.passerby.effectivetestproject.domain.models.User
import dev.passerby.effectivetestproject.domain.repos.LoginRepository

@Suppress("unused")
class LoginRepositoryImpl(application: Application) : LoginRepository {

    private val userDao = EffectiveAppDB.getDB(application).getUserDao()
    private val mapper = UserMapper()
    override suspend fun addUser(user: User) {
        userDao.addUser(mapper.mapEntityToDbModel(user))
    }

    override suspend fun getUser(first_name: String): List<User> {
        val entity = userDao.getUser(first_name)
        return mapper.mapListDbModel(entity)
    }

    override suspend fun checkUserIsExists(email: String): List<User> {
        val entity = userDao.checkUserIsExists(email)
        return mapper.mapListDbModel(entity)
    }
}