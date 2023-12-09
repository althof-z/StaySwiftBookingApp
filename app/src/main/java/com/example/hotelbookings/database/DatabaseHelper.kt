import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hotelbookings.data.User

// Database For User Login And Register
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
         const val DATABASE_VERSION = 1
         const val DATABASE_NAME = "UserDatabase.db"

        // Tabel Pengguna
         const val TABLE_USER = "user"
         const val COLUMN_ID = "id"
         const val COLUMN_USERNAME = "username"
         const val COLUMN_EMAIL = "email"
         const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Membuat tabel pengguna
        val CREATE_USER_TABLE = "CREATE TABLE $TABLE_USER (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_PASSWORD TEXT)"
        db?.execSQL(CREATE_USER_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop tabel jika sudah ada
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
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
            user = User(username, email)
        }

        cursor.close()
        db.close()

        return user
    }
}
//=======================================================================================================================//




