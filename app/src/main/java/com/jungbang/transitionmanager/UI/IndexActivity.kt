package com.jungbang.transitionmanager.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.UI.Admin.AdminMainActivity
import com.jungbang.transitionmanager.UI.User.UserMainActivity

class IndexActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        findViewById<Button>(R.id.btn_1).setOnClickListener { onClick(it)}
        findViewById<Button>(R.id.btn_2).setOnClickListener { onClick(it)}
        findViewById<Button>(R.id.btn_3).setOnClickListener { onClick(it)}

    }

    fun onClick(v: View){
        when(v.id){
            R.id.btn_1 -> {
                startActivity(Intent(this,LoginActivity::class.java))
            }
            R.id.btn_2 -> {
                startActivity(Intent(this,UserMainActivity::class.java))
            }
            R.id.btn_3 -> {
                startActivity(Intent(this, AdminMainActivity::class.java))
            }

        }
    }
}