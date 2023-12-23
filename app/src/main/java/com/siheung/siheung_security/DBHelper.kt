package com.siheung.siheung_security

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast


class DBHelper(context: Context?,name:String?,factory:SQLiteDatabase.CursorFactory?,version: Int)
    : SQLiteOpenHelper(context,name,factory,version) {

    //매개변수는 DB instance
    override fun onCreate(db: SQLiteDatabase?) {
        //쿼리문. 존재하지 않는다면 테이블을 생성하라는 말
        //시퀀스
        //컬럼명

        var sql = "CREATE TABLE IF NOT EXISTS User (user_id INTEGER PRIMARY KEY, user_name CHAR(10), user_email CHAR(50), password CHAR(30));"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var sql = " DROP TABLE IF EXISTS User ;"
        db?.execSQL(sql)
        onCreate(db)
    }

    fun insert(db: SQLiteDatabase,id: Int, name: String, email: String, password: String){
        var sql = " INSERT INTO User VALUES('${id}', '${name}', '${email}', '${password}');"


        db.execSQL(sql)

    }

    @SuppressLint("Range")
    fun login(db: SQLiteDatabase, email: String, password: String) : Boolean{
        var sql =
            " SELECT user_email, password FROM User WHERE user_email='$email' AND password='$password';"
        var result = db.rawQuery(sql, null)

        var loginSuccess = false
//        while (result.moveToNext()){
//            str += "유저 아이디 :" +result.getString(result.getColumnIndex("user_id")) + "\n" +
//                    "유저 이름 :" + result.getString(result.getColumnIndex("user_name")) + "\n" +
//                    "유저 이메일 :" + result.getString(result.getColumnIndex("user_email")) + "\n" +
//                    "유저 비밀번호 :" + result.getString(result.getColumnIndex("user_password"))
//        }
        loginSuccess = result.moveToFirst()

        return loginSuccess
    }

    fun delete(db:SQLiteDatabase){
        val sql = " DELETE FROM User;"
        db.execSQL(sql)
    }


}