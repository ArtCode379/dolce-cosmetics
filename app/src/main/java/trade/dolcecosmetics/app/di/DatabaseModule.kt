package trade.dolcecosmetics.app.di

import androidx.room.Room
import trade.dolcecosmetics.app.data.database.LCDSMDatabase
import org.koin.dsl.module

private const val DB_NAME = "lcdsm_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = LCDSMDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<LCDSMDatabase>().cartItemDao() }

    single { get<LCDSMDatabase>().orderDao() }
}