package rs.raf.jul.nikola_trajkovic_4519rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "korpa")
data class KorpaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val title: String?,
    val description: String?,
    val price: Int?,
    val amount: Int,


    )
