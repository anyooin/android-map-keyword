package campus.tech.kakao.map

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log


data class MapItem(
    val name: String,
    val address: String,
    val category: String
)

object MapItemDB : BaseColumns {
    const val TABLE_NAME = "mapItem"
    const val TABLE_COLUMN_ID = "id"
    const val TABLE_COLUMN_NAME = "name"
    const val TABLE_COLUMN_ADDRESS = "address"
    const val TABLE_COLUMN_CATEGORY = "category"
}

class MapItemDbHelper(context: Context) : SQLiteOpenHelper(context, "mapItem.db", null, 1) {
    //val wDb = writableDatabase
    //val rDb = readableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE ${MapItemDB.TABLE_NAME} (" +
                    "${MapItemDB.TABLE_COLUMN_ID} Integer primary key autoincrement," +
                    "${MapItemDB.TABLE_COLUMN_NAME} varchar(15) not null," +
                    "${MapItemDB.TABLE_COLUMN_ADDRESS} varchar(30) not null," +
                    "${MapItemDB.TABLE_COLUMN_CATEGORY} varchar(10)" +
                    ");"
        )
        insertMapItem(db, "카페", "서울 성동구 성수동", "카페")
        insertMapItem(db, "약국", "서울 강남구 대치동", "약국")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${MapItemDB.TABLE_NAME}")
        onCreate(db)
    }

    private fun insertMapItem(db: SQLiteDatabase?, name: String, address: String, category: String) {
        if(db != null) {
            for (i in 1..20) {
                val values = ContentValues()
                values.put(MapItemDB.TABLE_COLUMN_NAME, name + i)
                values.put(MapItemDB.TABLE_COLUMN_ADDRESS, address + i)
                values.put(MapItemDB.TABLE_COLUMN_CATEGORY, category)

                db.insert(MapItemDB.TABLE_NAME, null, values)
            }
        }
    }


    fun makeAllMapItemList(): MutableList<MapItem> {
        val rDb = readableDatabase
        val cursor = rDb.rawQuery("Select * from ${MapItemDB.TABLE_NAME}", null)
        val mapItemList = mutableListOf<MapItem>()
        while (cursor.moveToNext()) {
            mapItemList.add(
                MapItem(
                    cursor.getString(cursor.getColumnIndexOrThrow(MapItemDB.TABLE_COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MapItemDB.TABLE_COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MapItemDB.TABLE_COLUMN_CATEGORY))
                )
            )
        }
        cursor.close()

        return mapItemList
    }

    fun printAllMapItemList() {
        val rDb = readableDatabase
        val cursor = rDb.rawQuery("Select * from ${MapItemDB.TABLE_NAME}", null)
        while (cursor.moveToNext()) {
            Log.d(
                "uin",
                "" + cursor.getInt(cursor.getColumnIndexOrThrow(MapItemDB.TABLE_COLUMN_ID)) +
                        "" + cursor.getString(cursor.getColumnIndexOrThrow(MapItemDB.TABLE_COLUMN_NAME))
            )
        }
        cursor.close()
    }


}