package rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.KorpaEntity

@Database(
    entities = [KorpaEntity::class],
    version = 4,
    exportSchema = false)
@TypeConverters
abstract class StoreDataBase : RoomDatabase() {
    abstract fun getKorpaDao() : KorpaDao
}