package com.example.trainingapp.services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.trainingapp.model.User


class DatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 9
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_NAME = "Users"
        private val COLUMN_ID = "id"
        private val COLUMN_NAME = "user_name"
        private val COLUMN_EMAIL = "user_email"
        private val COLUMN_PASSWORD = "user_password"
        private val COLUMN_PHONE_NUMBER = "user_phone_number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_EMAIL TEXT UNIQUE, $COLUMN_PASSWORD TEXT,$COLUMN_PHONE_NUMBER TEXT);")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, prevVer: Int, newVer: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAllUser(): List<User> {
        val users = ArrayList<User>()
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        try {
            cursor.moveToFirst()
            do {
                val user = User()
                user.id =
                    Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)))
                user.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                user.email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
                user.password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
                user.phoneNumber =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER))
                users.add(user)
            } while (cursor.moveToNext())
        } catch (e: SQLException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        } finally {
            cursor.close()
        }
        return users
    }

    fun addUser(user: User): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, user.name)
        contentValues.put(COLUMN_EMAIL, user.email)
        contentValues.put(COLUMN_PASSWORD, user.password)
        contentValues.put(COLUMN_PHONE_NUMBER, user.phoneNumber)
        var result: Long = -1
        try {
            result = db.insert(TABLE_NAME, null, contentValues);
        } catch (e: SQLException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        }
        return (Integer.parseInt("$result") != -1)
    }

    fun checkLogin(email: String, password: String): Boolean {
        val db = writableDatabase
        val query =
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL LIKE '$email' AND $COLUMN_PASSWORD LIKE '$password'"
        val cursor = db.rawQuery(query, null)
        var result: Boolean = false

        try {
            result = cursor.moveToFirst()
        } catch (e: SQLException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        } finally {
            cursor.close()
        }
        return result
    }

    fun getDetailUser(id: Int): User {
        val user = User()
        val db = writableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $id"
        val cursor = db.rawQuery(query, null)

        try {
            cursor.moveToFirst()
            user.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)))
            user.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            user.email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            user.password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            user.phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER))
        } catch (e: SQLException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        } finally {
            cursor.close()
        }
        return user
    }

    fun deleteUser(id: Int){
        val db = writableDatabase

        try {
            db.delete(TABLE_NAME, COLUMN_ID + "=?", arrayOf(id.toString())).toLong()
            Toast.makeText(context, "Data has been deleted!", Toast.LENGTH_SHORT).show()
        } catch (e: SQLException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun updateUser(user: User) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, user.name)
        contentValues.put(COLUMN_EMAIL, user.email)
        contentValues.put(COLUMN_PASSWORD, user.password)
        contentValues.put(COLUMN_PHONE_NUMBER, user.phoneNumber)

        try {
            db.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", arrayOf(user.id.toString()))
                    .toLong()
            Toast.makeText(context, "Update data success!", Toast.LENGTH_SHORT).show()
        }catch (e: SQLException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        }

    }

}