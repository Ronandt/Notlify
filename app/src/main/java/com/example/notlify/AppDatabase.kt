package com.example.notlify

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction

object AppDatabase{
    private lateinit var coreDatabase: CoreDatabase
    fun getDb(context: Context): CoreDatabase {
        if(!this::coreDatabase.isInitialized) {
            coreDatabase = Room.databaseBuilder(name = "Database", klass = CoreDatabase::class.java, context = context).build()
        }
        return coreDatabase
    }
}

@Database(entities = [Note::class, NoteItem::class], version = 1)
abstract class CoreDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun noteItemDao() : NoteItemDao
}

@Entity
data class Note(
    val title: String,
    val tags: String,
    val lastCreated: String,
    var lastOpened: String? = null,
    val image: String? ,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)


@Entity
data class NoteItem(
    val parentNoteId: Int,
    val parentNoteItem: Int? = null,

    val itemType: String,
    val description: String,
    val indentationCount: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)


@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun selectAll(): List<Note>

    @Query("SELECT * FROM note WHERE :id = id")
    suspend fun selectNote(id: Int): List<Note>

    @Insert
    suspend fun insertNote(note: Note): Long


}

@Dao
interface NoteItemDao {
    @Query("SELECT * FROM noteitem WHERE :parentNoteId = parentNoteId ")
    suspend fun selectNoteItems(parentNoteId: Int): List<NoteItem>
    @Insert
    suspend fun insertNoteItem(noteItem: NoteItem)


}


//metadata