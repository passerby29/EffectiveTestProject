package dev.passerby.effectivetestproject.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from Users where first_name = :first_name")
    suspend fun getUser(first_name: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity)
}