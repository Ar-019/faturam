package com.example.newcanaryproject

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newcanaryproject.ui.theme.NewCanaryProjectTheme
import com.example.newcanaryproject.ui.theme.greenColor
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : ComponentActivity() {

    var message = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            NewCanaryProjectTheme {
                // on below line we are specifying 
                // background color for our application
                Surface(
                    // on below line we are specifying modifier and color for our app
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

                    // on the below line we are specifying the theme as the scaffold.
                    Scaffold(

                        // in scaffold we are specifying the top bar.
                        topBar = {

                            // inside top bar we are specifying background color.
                            TopAppBar(backgroundColor = greenColor,

                                // along with that we are specifying 
                                // title for our top bar.
                                title = {

                                    // in the top bar we are specifying 
                                    // title as a text
                                    Text(
                                        // on below line we are specifying
                                        // text to display in top app bar.
                                        text = "GFG",

                                        // on below line we are specifying
                                        // modifier to fill max width.
                                        modifier = Modifier.fillMaxWidth(),

                                        // on below line we are specifying
                                        // text alignment.
                                        textAlign = TextAlign.Center,

                                        // on below line we are specifying
                                        // color for our text.
                                        color = Color.White
                                    )
                                })
                        }) {
                        // on below line we are calling method to display UI
                        saveDataToExternalStorage(context = LocalContext.current, message)
                    }
                }
            }
        }
    }
}

@Composable
fun saveDataToExternalStorage(context: Context, msg: String) {
    // on below line creating a variable for message.
    val message = remember {
        mutableStateOf("")
    }
    val txtMsg = remember {
        mutableStateOf("")
    }
    val activity = context as Activity

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        // on below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = "Internal Storage in Android",
            // on below line we are specifying text color,
            // font size and font weight
            color = greenColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(10.dp))
        // on below line we are creating a text field 
        // for our message number.
        TextField(
            // on below line we are specifying value
            // for our message text field.
            value = message.value,
            // on below line we are adding on value 
            // change for text field.
            onValueChange = { message.value = it },
            // on below line we are adding place holder as 
            // text as "Enter your email"
            placeholder = { Text(text = "Enter your message") },
            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            // on below line we ar adding single line to it.
            singleLine = true,
        )

        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(10.dp))


        // on below line adding a button.
        Button(
            onClick = {
                // on below line we are saving data to internal storage
                try {
                    val fos: FileOutputStream =
                        context.openFileOutput("demoFile.txt", Context.MODE_PRIVATE)
                    fos.write(message.value.toByteArray())
                    fos.flush()
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                message.value = ""
                Toast.makeText(context, "Data saved successfully..", Toast.LENGTH_SHORT).show()

            },
            // on below line adding a modifier for our button.
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            // on below line adding a text for our button.
            Text(text = "Write Data to Internal Storage", textAlign = TextAlign.Center)
        }
        // on below line adding a spacer
        Spacer(modifier = Modifier.width(10.dp))

        // on below line adding a text view to display our data.
        Text(
            // on below line we are specifying text
            text = "Data will appear below : \n" + txtMsg.value,
            // on below line we are specifying text color,
            // font size and font weight
            color = greenColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        // adding spacer on below line.
        Spacer(modifier = Modifier.height(20.dp))
        // on below line creating a button
        Button(
            onClick = {
                // on below line we are reading the
                // data which we stored in our file.
                try {
                    val fin: FileInputStream = context.openFileInput("demoFile.txt")
                    var a: Int
                    val temp = StringBuilder()
                    while (fin.read().also { a = it } != -1) {
                        temp.append(a.toChar())
                    }

                    txtMsg.value = temp.toString()
                    fin.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            },
            // on below line adding a 
            // modifier for our button.
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            // on below line adding a text for our button.
            Text(text = "Read Data from Internal Storage", textAlign = TextAlign.Center)
        }
    }
}


        Button(
            onClick = {
                // on below line we are saving data to internal storage
                try {
                    val fos: FileOutputStream =
                        context.openFileOutput("demoFile.txt", Context.MODE_PRIVATE)
                    fos.write(message.value.toByteArray())
                    fos.flush()
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                message.value = ""
                Toast.makeText(context, "Data saved successfully..", Toast.LENGTH_SHORT).show()

            },