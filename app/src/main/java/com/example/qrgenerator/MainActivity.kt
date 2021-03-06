package com.example.qrgenerator

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.WriterException
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var bGenerate: Button? = null
    var bScanner: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        im = findViewById(R.id.imageView)
        bGenerate = findViewById(R.id.button)
        bScanner = findViewById(R.id.bScan)

        bScanner?.setOnClickListener {
           checkCameraPermission()
        }
        bGenerate?.setOnClickListener {
            generateQRCode("Добрый день, это мой первый опыт с Kotlin")
        }
    }

    private fun generateQRCode(text: String) {
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 1000)
        try {
            val bMap = qrGenerator.encodeAsBitmap()
            im?.setImageBitmap(bMap)
        } catch (e: WriterException) {

        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 12)
        } else {
            startActivity(Intent(this, ScannerActivity::class.java))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 12){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this, ScannerActivity::class.java))
            }
        }
    }
}