package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 1. 허락 받을 필요 있는 권한 행렬 작성
    val permission_list = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = ""

        // 2. 권한 여부를 하나씩 하기
        for(permission in permission_list){
            // 3. 권한 체크
            val check = checkCallingOrSelfPermission(permission)
            if (check == PackageManager.PERMISSION_GRANTED){
                textView.append("$permission: 허용\n")
            }
            else{
                textView.append("$permission: 거부\n")
            }
        }

        button.setOnClickListener {
            // 4. 거부되어 있는 권한들을 사용자에게 확인받음
            requestPermissions(permission_list, 0)
        }
    }
    // 5. 권한 물어본 이후 자동 실행되는 동작 설정
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        textView.text = ""
        // 6. grantResult : 물어본 권한들이 들어있는 리스트임
        for (idx in grantResults.indices){
            if (grantResults[idx] == PackageManager.PERMISSION_GRANTED){
                textView.append("${permissions[idx]}: 허용\n")
            }else
                textView.append("${permissions[idx]}: 거부\n")
        }
    }
}