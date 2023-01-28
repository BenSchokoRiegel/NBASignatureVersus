package com.example.nba_clean_signitature_versus

import android.content.Context
import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.impl.ImageCaptureConfig

import androidx.compose.runtime.Composable
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/*
@Composable
fun takeAndSavePhoto(context: Context) {
    val imageCapture = ImageCapture(ImageCaptureConfig.Builder().build())

    //Take the photo
    val photo = imageCapture.takePicture()

    //Create a unique file name
    val fileName = "your_file_name_${System.currentTimeMillis()}.jpg"
    val file = File(context.getDrawable(R.drawable.your_file_name).toString(), fileName)

    //save the photo
    photo.save(file, ImageFormat.JPEG)
} */

@Composable
fun createFileName(){
/*
val fileName = "your_file_name_${System.currentTimeMillis()}.jpg"
val file = File(context.getDrawable(R.drawable.fileName).toString(), fileName)
val inputStream = context.resources.openRawResourceFd(R.drawable.your_file_name)
val outputStream = FileOutputStream(file)

inputStream.createInputStream().copyTo(outputStream)
inputStream.close()
outputStream.flush()
outputStream.close()

 */
}

