package dev.passerby.effectivetestproject.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Suppress("unused")
@Entity(tableName = "Users")
data class UserEntity(
    val first_name: String,
    val last_name: String,
    val email: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

