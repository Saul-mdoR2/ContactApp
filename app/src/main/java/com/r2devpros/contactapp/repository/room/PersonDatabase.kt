package com.r2devpros.contactapp.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.r2devpros.contactapp.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        const val DATABASE_NAME = "people_database"
    }
}