package com.example.jetpackcomposenumberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Interface()
        }
    }

}

var loop : Int = nextInt(1,1000)
var count = 0
var percentage: Float = 1F
var min = 1
var max = 1000

@Composable
fun Interface(){

    val textState = remember { mutableStateOf(TextFieldValue()) }
    var result = remember { mutableStateOf("Enter Your Number!")}
    var score = remember { mutableStateOf("")}

    fun checkAns() {
        var number = if (textState.value.text.isEmpty()){0} else {textState.value.text.toInt()}
        if(number > loop){
            max = number
            score.value = "\t\t\t\t\t\t\t\t\t\tTry again\n" +
                    "number is between($min,$max)."
            result.value = "The number is too high!"
            count += 1
        }
        else if(number < loop){
            min = number
            score.value = "\t\t\t\t\t\t\t\t\t\tTry again\n" +
                    "number is between($min,$max)."
            result.value = "The number is too low!"

            count += 1
        }
        else{
            count += 1
            percentage = percentage.div(count)*100
            score.value = "Total Try : $count times.\n" +
                    "Accuracy : %,.2f".format(percentage)+"%."
            result.value = "Enter number for new game!"
            loop = nextInt( 1, 1000)
            count = 0
            percentage = 1F
            min = 1
            max = 1000

        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = score.value)

        TextField(value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .padding(20.dp)
                .size(120.dp, 60.dp),

            )

        Button(onClick = {checkAns()}) {
            Text(
                text = result.value,
            )
        }
    }
}

@Preview
@Composable
fun Previewer(){
    Interface()
}