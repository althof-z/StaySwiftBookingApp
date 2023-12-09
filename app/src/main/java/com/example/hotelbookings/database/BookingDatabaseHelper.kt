import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookingDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, BOOKING_DATABASE_NAME, null, BOOKING_DATABASE_VERSION) {

    companion object {
        private const val BOOKING_DATABASE_VERSION = 1
        private const val BOOKING_DATABASE_NAME = "BookingDatabase.db"

        private const val BOOKING_TABLE_NAME = "booking"
        private const val BOOKING_COLUMN_ID = "bookingId"
        private const val BOOKING_COLUMN_DATE_CHECK_IN = "dateCheckIn"
        private const val BOOKING_COLUMN_DATE_CHECK_OUT = "dateCheckOut"
        private const val BOOKING_COLUMN_ADULT_NUM = "adultNum"
        private const val BOOKING_COLUMN_CHILDREN_NUM = "childrenNum"
        private const val BOOKING_COLUMN_COMMENTS_TEXT = "commentsText"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE $BOOKING_TABLE_NAME (" +
                "$BOOKING_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$BOOKING_COLUMN_DATE_CHECK_IN TEXT," +
                "$BOOKING_COLUMN_DATE_CHECK_OUT TEXT," +
                "$BOOKING_COLUMN_ADULT_NUM INTEGER," +
                "$BOOKING_COLUMN_CHILDREN_NUM INTEGER," +
                "$BOOKING_COLUMN_COMMENTS_TEXT TEXT)"
                )
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $BOOKING_TABLE_NAME")
        onCreate(db)
    }

    fun addBooking(dateCheckIn: String, dateCheckOut: String, adultNum: Int, childrenNum: Int, commentsText: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(BOOKING_COLUMN_DATE_CHECK_IN, dateCheckIn)
        contentValues.put(BOOKING_COLUMN_DATE_CHECK_OUT, dateCheckOut)
        contentValues.put(BOOKING_COLUMN_ADULT_NUM, adultNum)
        contentValues.put(BOOKING_COLUMN_CHILDREN_NUM, childrenNum)
        contentValues.put(BOOKING_COLUMN_COMMENTS_TEXT, commentsText)

        val result = db.insert(BOOKING_TABLE_NAME, null, contentValues)
        db.close()

        return result
    }
}
