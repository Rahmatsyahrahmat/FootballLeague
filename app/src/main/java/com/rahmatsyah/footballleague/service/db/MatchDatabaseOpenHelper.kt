package com.rahmatsyah.footballleague.service.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.rahmatsyah.footballleague.model.Match
import org.jetbrains.anko.db.*

class MatchDatabaseOpenHelper(context: Context):ManagedSQLiteOpenHelper(context,"FavoriteMatch.db",null,1){


    companion object{
        private var instance:MatchDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context):MatchDatabaseOpenHelper{
            if (instance==null){
                instance = MatchDatabaseOpenHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Match.table_name,true,
            Match.id to TEXT + PRIMARY_KEY,
            Match.title to TEXT,
            Match.sport to TEXT,
            Match.date to TEXT,
            Match.homeScore to TEXT,
            Match.awayScore to TEXT,
            Match.idHome to TEXT,
            Match.idAway to TEXT,
            Match.homeShot to INTEGER,
            Match.awayShot to INTEGER,
            Match.homeYellowCard to TEXT,
            Match.awayYellowCard to TEXT,
            Match.homeRedCard to TEXT,
            Match.awayRedCard to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Match.table_name, ifExists = true)
    }

}
val Context.database:MatchDatabaseOpenHelper
    get() = MatchDatabaseOpenHelper.getInstance(applicationContext)