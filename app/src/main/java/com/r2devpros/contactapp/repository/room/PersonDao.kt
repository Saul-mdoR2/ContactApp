package com.r2devpros.contactapp.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.r2devpros.contactapp.model.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    suspend fun getAll(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg person: Person)
}