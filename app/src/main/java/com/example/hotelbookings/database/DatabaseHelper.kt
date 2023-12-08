import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hotelbookings.data.BookingInfo
import com.example.hotelbookings.data.User

//Database For User Login And Register and Booking
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase.db"

        // Tabel Pengguna
        private const val TABLE_USER = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"

        // Tabel Booking
        private const val TABLE_BOOKING = "booking"
        private const val COLUMN_BOOKING_ID = "booking_id"
        private const val COLUMN_DATE_CHECK_IN = "dateCheckIn"
        private const val COLUMN_DATE_CHECK_OUT = "dateCheckOut"
        private const val COLUMN_ADULT = "adult"
        private const val COLUMN_CHILDREN = "children"
        private const val COLUMN_COMMENTS = "comments"
        private const val COLUMN_USER_ID = "user_id"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Membuat tabel pengguna
        val CREATE_USER_TABLE = "CREATE TABLE $TABLE_USER (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_PASSWORD TEXT)"
        db?.execSQL(CREATE_USER_TABLE)

        // Membuat tabel booking
        val CREATE_BOOKING_TABLE = "CREATE TABLE $TABLE_BOOKING (" +
                "$COLUMN_BOOKING_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_DATE_CHECK_IN TEXT," +
                "$COLUMN_DATE_CHECK_OUT TEXT," +
                "$COLUMN_ADULT INTEGER," +
                "$COLUMN_CHILDREN INTEGER," +
                "$COLUMN_COMMENTS TEXT," +
                "$COLUMN_USER_ID INTEGER," +
                "FOREIGN KEY($COLUMN_USER_ID) REFERENCES $TABLE_USER($COLUMN_ID))"
        db?.execSQL(CREATE_BOOKING_TABLE)

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop tabel jika sudah ada
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKING")
        onCreate(db)
    }

    // Menambahkan pengguna baru ke dalam tabel
    fun addUser(username: String, email: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USERNAME, username)
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_PASSWORD, password)

        // Menyimpan data pengguna ke dalam tabel
        val success = db.insert(TABLE_USER, null, contentValues)

        db.close()
        return success
    }

    // Memeriksa apakah pengguna sudah terdaftar atau belum
    fun checkUser(email: String, password: String): Boolean {
        val columns = arrayOf(COLUMN_ID)
        val db = this.readableDatabase
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD =?"
        val selectionArgs = arrayOf(email, password)

        // Query untuk memeriksa pengguna dengan email dan password tertentu
        val cursor: Cursor =
            db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)
        val cursorCount = cursor.count

        cursor.close()
        db.close()

        return cursorCount > 0
    }

    // Add this function in your DatabaseHelper
    fun getUserByEmailAndPassword(email: String, password: String): User? {
        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_USERNAME)
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD =?"
        val selectionArgs = arrayOf(email, password)

        val cursor: Cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)

        var user: User? = null

        if (cursor.moveToFirst()) {
            val usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME)
            val username = cursor.getString(usernameIndex)
            user = User(username)
        }

        cursor.close()
        db.close()

        return user
    }

    //=======================================================================================================================//

    // Menambahkan data booking ke dalam tabel
    fun addBooking(
        dateCheckIn: String,
        dateCheckOut: String,
        adult: Int,
        children: Int,
        comments: String,
        userId: Long
    ): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_DATE_CHECK_IN, dateCheckIn)
        contentValues.put(COLUMN_DATE_CHECK_OUT, dateCheckOut)
        contentValues.put(COLUMN_ADULT, adult)
        contentValues.put(COLUMN_CHILDREN, children)
        contentValues.put(COLUMN_COMMENTS, comments)
        contentValues.put(COLUMN_USER_ID, userId)

        // Menyimpan data booking ke dalam tabel
        val success = db.insert(TABLE_BOOKING, null, contentValues)

        db.close()
        return success
    }

    // Get booking information for a user
    fun getBookingInfoByUserId(userId: Long): List<BookingInfo> {
        val db = this.readableDatabase
        val columns = arrayOf(
            COLUMN_BOOKING_ID,
            COLUMN_DATE_CHECK_IN,
            COLUMN_DATE_CHECK_OUT,
            COLUMN_ADULT,
            COLUMN_CHILDREN,
            COLUMN_COMMENTS
        )
        val selection = "$COLUMN_USER_ID = ?"
        val selectionArgs = arrayOf(userId.toString())

        val cursor: Cursor =
            db.query(TABLE_BOOKING, columns, selection, selectionArgs, null, null, null)

        val bookingList = mutableListOf<BookingInfo>()

        while (cursor.moveToNext()) {
            val bookingIdIndex = cursor.getColumnIndex(COLUMN_BOOKING_ID)
            val dateCheckInIndex = cursor.getColumnIndex(COLUMN_DATE_CHECK_IN)
            val dateCheckOutIndex = cursor.getColumnIndex(COLUMN_DATE_CHECK_OUT)
            val adultIndex = cursor.getColumnIndex(COLUMN_ADULT)
            val childrenIndex = cursor.getColumnIndex(COLUMN_CHILDREN)
            val commentsIndex = cursor.getColumnIndex(COLUMN_COMMENTS)

            val bookingId = cursor.getLong(bookingIdIndex)
            val dateCheckIn = cursor.getString(dateCheckInIndex)
            val dateCheckOut = cursor.getString(dateCheckOutIndex)
            val adult = cursor.getInt(adultIndex)
            val children = cursor.getInt(childrenIndex)
            val comments = cursor.getString(commentsIndex)

            val bookingInfo = BookingInfo(bookingId, dateCheckIn, dateCheckOut, adult, children, comments)
            bookingList.add(bookingInfo)
        }

        cursor.close()
        db.close()

        return bookingList
    }


}



