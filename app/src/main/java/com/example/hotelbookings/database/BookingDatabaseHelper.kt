import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hotelbookings.data.Booking

class BookingDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, BOOKING_DATABASE_NAME, null, BOOKING_DATABASE_VERSION) {

    companion object {
        private const val BOOKING_DATABASE_VERSION = 2
        private const val BOOKING_DATABASE_NAME = "BookingDatabase.db"

        private const val BOOKING_TABLE_NAME = "booking"
        private const val BOOKING_COLUMN_ID = "bookingId"
        private const val BOOKING_COLUMN_DATE_CHECK_IN = "dateCheckIn"
        private const val BOOKING_COLUMN_DATE_CHECK_OUT = "dateCheckOut"
        private const val BOOKING_COLUMN_ADULT_NUM = "adultNum"
        private const val BOOKING_COLUMN_CHILDREN_NUM = "childrenNum"
        private const val BOOKING_COLUMN_COMMENTS_TEXT = "commentsText"
        private const val BOOKING_COLUMN_RESERVER_NAME = "reserverName"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = (
                "CREATE TABLE $BOOKING_TABLE_NAME (" +
                        "$BOOKING_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$BOOKING_COLUMN_DATE_CHECK_IN TEXT," +
                        "$BOOKING_COLUMN_DATE_CHECK_OUT TEXT," +
                        "$BOOKING_COLUMN_ADULT_NUM INTEGER," +
                        "$BOOKING_COLUMN_CHILDREN_NUM INTEGER," +
                        "$BOOKING_COLUMN_COMMENTS_TEXT TEXT," +
                        "$BOOKING_COLUMN_RESERVER_NAME TEXT)"
                )
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $BOOKING_TABLE_NAME")
        onCreate(db)
    }

    //Fungsi Tambahan
    fun deleteBooking(bookingId: Long): Boolean {
        val db = this.writableDatabase
        val whereClause = "$BOOKING_COLUMN_ID = ?"
        val whereArgs = arrayOf(bookingId.toString())

        val deletedRows = db.delete(BOOKING_TABLE_NAME, whereClause, whereArgs)
        db.close()

        return deletedRows > 0
    }

    fun insertBooking(
        dateCheckIn: String,
        dateCheckOut: String,
        adultNum: Int,
        childrenNum: Int,
        commentsText: String,
        reserverName: String  // Add the new parameter here
    ): Long {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(BOOKING_COLUMN_DATE_CHECK_IN, dateCheckIn)
        values.put(BOOKING_COLUMN_DATE_CHECK_OUT, dateCheckOut)
        values.put(BOOKING_COLUMN_ADULT_NUM, adultNum)
        values.put(BOOKING_COLUMN_CHILDREN_NUM, childrenNum)
        values.put(BOOKING_COLUMN_COMMENTS_TEXT, commentsText)
        values.put(BOOKING_COLUMN_RESERVER_NAME, reserverName)  // Add the new column value here

        val newRowId = db.insert(BOOKING_TABLE_NAME, null, values)
        db.close()

        return newRowId
    }

    fun updateBooking(
        bookingId: Long,
        dateCheckIn: String,
        dateCheckOut: String,
        adultNum: Int,
        childrenNum: Int,
        commentsText: String,
        reserverName: String  // Add the new parameter here
    ): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(BOOKING_COLUMN_DATE_CHECK_IN, dateCheckIn)
        values.put(BOOKING_COLUMN_DATE_CHECK_OUT, dateCheckOut)
        values.put(BOOKING_COLUMN_ADULT_NUM, adultNum)
        values.put(BOOKING_COLUMN_CHILDREN_NUM, childrenNum)
        values.put(BOOKING_COLUMN_COMMENTS_TEXT, commentsText)
        values.put(BOOKING_COLUMN_RESERVER_NAME, reserverName)  // Add the new column value here

        val whereClause = "$BOOKING_COLUMN_ID = ?"
        val whereArgs = arrayOf(bookingId.toString())

        val updatedRows = db.update(BOOKING_TABLE_NAME, values, whereClause, whereArgs)
        db.close()

        return updatedRows
    }

    fun getAllBookings(): List<Booking> {
        val bookingList = mutableListOf<Booking>()
        val db = readableDatabase
        val cursor = db.query(
            BOOKING_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BOOKING_COLUMN_ID))
                val dateCheckIn = getString(getColumnIndexOrThrow(BOOKING_COLUMN_DATE_CHECK_IN))
                val dateCheckOut = getString(getColumnIndexOrThrow(BOOKING_COLUMN_DATE_CHECK_OUT))
                val adultNum = getInt(getColumnIndexOrThrow(BOOKING_COLUMN_ADULT_NUM))
                val childrenNum = getInt(getColumnIndexOrThrow(BOOKING_COLUMN_CHILDREN_NUM))
                val commentsText = getString(getColumnIndexOrThrow(BOOKING_COLUMN_COMMENTS_TEXT))
                val reserverName = getString(getColumnIndexOrThrow(BOOKING_COLUMN_RESERVER_NAME))  // Retrieve the new column value

                bookingList.add(Booking(id, dateCheckIn, dateCheckOut, adultNum, childrenNum, commentsText, reserverName))
            }
        }

        cursor.close()
        return bookingList
    }


}
