package com.ar019.faturam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.ar019.faturam.ui.theme.FaturamTheme

import java.io.File

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State

import kotlinx.coroutines.delay

import androidx.compose.ui.platform.LocalContext

import android.os.Environment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import androidx.compose.runtime.Composable
import androidx.compose.material3.Button

import android.content.Context
import androidx.compose.ui.platform.LocalContext

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : ComponentActivity() {
    
    private var okumayetkisi = false
    private var yazmayetkisi = false
    private var yetkisiisteme: ActivityResultLauncher<Array<String>>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaturamTheme {
                var girdi by remember { mutableStateOf("Buraya yazı girin") }
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(48.dp)
                            .background(Color.Black),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = girdi,
                            onValueChange = { girdi = it },
                            label = { Text("Buraya yazı girin") }
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        dugme(onClick = { try {
                            val fos: FileOutputStream =
                            context.openFileOutput("Text.txt", Context.MODE_PRIVATE)
                            fos.write(girdi.toByteArray())
                            fos.flush()
                            fos.close() 
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        
                    } // Column burada kapanıyor
                } // Scaffold lambda kapanıyor
            } // FaturamTheme kapanıyor
        } // setContent kapanıyor
    } // onCreate kapanıyor
    
    @Composable
fun dugme(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
          Text("Kırmızı")  
    }
}

    private fun yetkiistiyormu() {
        val hasReadPermission = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        val hasWritePermission = ContextCompat.checkSelfPermission(
        this,
        ) == PackageManager.PERMISSION_GRANTED
        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                            
        okumayetkisi = hasReadPermission
        yazmayetkisi = hasWritePermission || minSdk29
        
        val permissionToRequest = mutableListOf<String>{}
        if(!okumayetkisi) {
            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!yazmayetkisi)
           permissionToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        if(permissionsToRequest.isNotEmpty()) {
            yetkiisteme.launch(permissionsToRequest.toTypedArray())
        }   
       }
}