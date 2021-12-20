package com.jungbang.transitionmanager.ui

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jungbang.transitionmanager.R
import com.jungbang.transitionmanager.ui.admin.AdminHistoryActivity
import com.jungbang.transitionmanager.ui.admin.AdminHistoryDialog
import com.jungbang.transitionmanager.ui.checkcar.CheckCarActivity
import com.jungbang.transitionmanager.ui.admin.AdminMainActivity
import com.jungbang.transitionmanager.ui.admin.UserManageActivity
import com.jungbang.transitionmanager.ui.common.SingleDialog
import com.jungbang.transitionmanager.ui.notice.NoticeActivity
import com.jungbang.transitionmanager.ui.user.UserMainActivity
import java.security.MessageDigest

class IndexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        findViewById<Button>(R.id.btn_1).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_2).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_3).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_4).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_5).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_6).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_7).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_8).setOnClickListener { onClick(it) }
        findViewById<Button>(R.id.btn_9).setOnClickListener { onClick(it) }

    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.btn_2 -> {
                startActivity(Intent(this, UserMainActivity::class.java))
            }
            R.id.btn_3 -> {
                startActivity(Intent(this, AdminMainActivity::class.java))
            }
            R.id.btn_4 -> {
                SingleDialog("팝업", "여기에는 내용이 들어갑니다.", DialogInterface.OnClickListener { dialog, i ->
                    dialog.dismiss()
                }).show(supportFragmentManager, "a")
            }
            R.id.btn_5 -> {
                startActivity(Intent(this, NoticeActivity::class.java))
            }

            R.id.btn_6 -> {
                startActivity(Intent(this, CheckCarActivity::class.java))
            }

            R.id.btn_7 -> {
                startActivity(Intent(this, AdminHistoryActivity::class.java))
            }

            R.id.btn_8 -> {
                AdminHistoryDialog().show(supportFragmentManager,null)
            }

            R.id.btn_9 -> {
                startActivity(Intent(this, UserManageActivity::class.java))
            }

        }
    }
}