package trade.dolcecosmetics.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import trade.dolcecosmetics.app.data.dao.CartItemDao
import trade.dolcecosmetics.app.data.dao.OrderDao
import trade.dolcecosmetics.app.data.database.converter.Converters
import trade.dolcecosmetics.app.data.entity.CartItemEntity
import trade.dolcecosmetics.app.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LCDSMDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}