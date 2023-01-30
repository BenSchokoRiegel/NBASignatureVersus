package com.example.nba_clean_signitature_versus.load_read_data

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import coil.compose.rememberImagePainter

//Quelle https://github.com/risalfajar/coding-ark-compose-gallery-picker/blob/master/app/src/main/java/ark/coding/imagegallerypicker/MainActivity.kt
// Quelle https://github.com/Kiran-Bahalaskar/Pick-Image-From-Gallery-With-Jetpack-Compose/blob/master/app/src/main/java/com/kiranbahalaskar/pickimagefromgallery/MainActivity.kt







/*@Composable
fun MainApp() {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        selectedImage = uri
    }

    var x = MainContent(selectedImage) {
        launcher.launch("image/jpeg")
    }
    print( " ")
}

@Composable
private fun MainContent(
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
): Unit {
    Scaffold() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedImage != null)
                Image(
                    painter = rememberImagePainter(selectedImage),
                    contentDescription = "Selected image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            return@clickable Bitmap
                        })
            else
                OutlinedButton(onClick = onImageClick) {
                    Text(text = "Choose Image")
                }
        }
    }
}*/

@Composable
fun PickImageFromGallery() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Pick Image")
        }
    }
    print("")

}
