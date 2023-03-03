package dev.passerby.effectivetestproject.data.room

import dev.passerby.effectivetestproject.domain.models.User

class UserMapper {
    fun mapEntityToDbModel(user: User) = UserEntity(
        first_name = user.first_name,
        last_name = user.last_name,
        email = user.email,
    )

    fun mapListDbModel(list: List<UserEntity>) = list.map {
        mapDbModelToEntity(it)
    }

    private fun mapDbModelToEntity(userEntity: UserEntity) = User(
        first_name = userEntity.first_name,
        last_name = userEntity.last_name,
        email = userEntity.email,
    )
}