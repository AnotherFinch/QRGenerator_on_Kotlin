package com.example.qrgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.zxing.WriterException

class MainActivity : AppCompatActivity() {
    var im : ImageView? = null
    var bGenerate : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        im = findViewById(R.id.imageView)
        bGenerate = findViewById(R.id.button)
        bGenerate?.setOnClickListener{
            generateQRCode("Добрый день, это мой первый опыт с Kotlin")
        }
    }

    private fun generateQRCode(text : String){
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 1000)
        try {
            val bMap = qrGenerator.encodeAsBitmap()
            im?.setImageBitmap(bMap)
        }catch (e : WriterException){

        }
    }
}