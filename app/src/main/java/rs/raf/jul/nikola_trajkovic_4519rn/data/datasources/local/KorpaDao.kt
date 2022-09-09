package rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.KorpaEntity

@Dao
abstract class KorpaDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: KorpaEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<KorpaEntity>): Completable

    @Query("SELECT * FROM korpa")
    abstract fun getAll(): Observable<List<KorpaEntity>>


    @Query("DELETE FROM korpa")
    abstract fun deleteAll(): Completable

    @Query("DELETE FROM korpa WHERE id= :id")
    abstract fun delete(id:Long): Completable

    @Query("UPDATE korpa SET amount = amount + 1 WHERE title = :title and description = :description and price = :price")
    abstract fun update(title: String, description: String, price: Int): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<KorpaEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }



}