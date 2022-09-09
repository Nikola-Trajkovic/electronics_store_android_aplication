package rs.raf.jul.nikola_trajkovic_4519rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.local.KorpaDao
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.Korpa
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.KorpaEntity

class KorpaRepositoryImpl(
    private val localDataSource: KorpaDao
) : KorpaRepository {

    override fun insert(
        title: String?,
        description: String?,
        price: Int?,
    ): Completable {

        val korpa = KorpaEntity(null,title,description,price,1)
        return localDataSource.insert(korpa)

    }


    override fun delete(id: Long): Completable {
        return localDataSource.delete(id)
    }

    override fun deleteAll(): Completable {
        return localDataSource.deleteAll()
    }

    override fun getAll(): Observable<List<Korpa>> {
        return localDataSource.getAll().
                map {
                    it.map {
                        Korpa(
                            it.id,
                            it.title,
                            it.description,
                            it.price,
                            it.amount
                        )
                    }
                }
    }



    override fun update(title: String?, description: String?, price: Int?): Completable {
        return localDataSource.update(title!!, description!!,price!!)
    }

}