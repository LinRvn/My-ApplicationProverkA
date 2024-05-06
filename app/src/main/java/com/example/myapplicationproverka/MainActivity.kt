package com.example.myapplicationproverka

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.Properties

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		GlobalScope.launch {
			val connection = DBConnection.connection
			val statement = connection.createStatement()
			val response = statement.executeQuery("select * from test123.`add`")
			for (i in 0..2)
				response.next()
			while(response.next()) {
				val str = response.getString("Name")
				Log.i("BEBRA", str.toString())
			}
		}
	}
}





object DBConnection {
	private const val DB_URL =
		"jdbc:mysql://10.0.2.2:3306/test123?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8"
	private const val DB_USER = "root"
	private const val DB_PASSWORD = "1234"
	
	@get:Throws(SQLException::class)
	val connection: Connection
		get() = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
}