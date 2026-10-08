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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaturamTheme {
                var girdi by remember { mutableStateOf("Buraya yazı girin") }
                
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
                        
                        Card(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = girdi)
                            }
                        }
                        
                        LaunchedEffect(girdi) {
                        delay(5000)
                        val root = Environment.getExternalStorageDirectory()
                        val file = File(root, "ornek.txt")
                        file.writeText(girdi)
                        }
                        
                    } // Column burada kapanıyor
                } // Scaffold lambda kapanıyor
            } // FaturamTheme kapanıyor
        } // setContent kapanıyor
    } // onCreate kapanıyor
}
