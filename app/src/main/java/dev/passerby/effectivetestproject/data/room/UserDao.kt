package dev.passerby.effectivetestproject.data.room

import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from Users where first_name = :first_name")
    suspend fun getUser(first_name: String): List<UserEntity>

    @Query("select * from Users where email = :email")
    suspend fun checkUserIsExists(email: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity)
}