package rs.raf.jul.nikola_trajkovic_4519rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.KorpaEntity

interface KorpaRepository {

    fun insert(title: String?, description: String?, price: Int?): Completable
    fun delete(id:Long): Completable
    fun deleteAll(): Completable
    fun getAll(): Observable<List<Korpa>>
    fun update(title: String?, description: String?, price: Int?): Completable


}