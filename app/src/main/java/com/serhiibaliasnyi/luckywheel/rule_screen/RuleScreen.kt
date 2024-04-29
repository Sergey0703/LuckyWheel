package com.serhiibaliasnyi.luckywheel.rule_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhiibaliasnyi.luckywheel.R
import com.serhiibaliasnyi.luckywheel.ui.theme.Red

@Composable
fun RuleScreen() {
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(modifier=Modifier.fillMaxWidth()
            .height(100.dp)
            .wrapContentWidth()
            .wrapContentHeight(),
            text="0",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color= Color.White
            )
        Box(modifier= Modifier
            .weight(1f)
            .fillMaxSize()){
            Image(
                painter= painterResource(id = R.drawable.lucky_wheel_bg),
                contentDescription = "lucky wheel",
                modifier = Modifier.fillMaxSize().padding(10.dp)
            )
        }

        Button(onClick = {},
            colors=ButtonDefaults.buttonColors(Red),
            modifier = Modifier.fillMaxWidth().padding(10.dp)
            ){
            Text(text="Start",
                color= White)

        }
    }
}