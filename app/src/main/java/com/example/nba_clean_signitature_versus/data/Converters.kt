package com.example.nba_clean_signitature_versus.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream


// Quelle -> https://raw.githubusercontent.com/BenSchokoRiegel/NBASignatureVersus/204b78a0b243443ae1444a1c2fd7d5c267ce57eb/app/src/main/java/com/example/nba_clean_signitature_versus/FORLATERMAYBE/ImageBitmapString.java

class Converters {

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}