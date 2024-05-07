package com.serhiibaliasnyi.luckywheel.rule_screen

import android.media.AudioManager
import android.media.SoundPool
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.serhiibaliasnyi.luckywheel.R
import com.serhiibaliasnyi.luckywheel.ui.theme.Red
import kotlin.math.roundToInt

@Composable
fun RuleScreen(sound: SoundPool?, composition:LottieComposition?) {

    var isPlayingLottie by remember {
        mutableStateOf(false)
    }
    val animSpec= LottieClipSpec.Progress(
        0f,
        0.8f
    )
    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var number by remember{
          mutableStateOf(0)
    }
    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
             number=((360f-(it%360))/(360f/8)).roundToInt()
             isPlayingLottie=true
           
        }
    )


    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){


        Text(modifier= Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentWidth()
            .wrapContentHeight(),
            text=number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color= Color.White
            )
        Box(modifier= Modifier
            .weight(1f)
            .fillMaxSize()){
            Image(
                //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                painter= painterResource(id = R.drawable.rulette5),
                contentDescription = "lucky wheel",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .rotate(angle)
            )
        }

        Button(onClick = {
            isPlayingLottie=false
            rotationValue=(720..1080).random().toFloat()+angle
            sound?.play(1, 1F, 1F, 0, 0, 1F)

        },
            colors=ButtonDefaults.buttonColors(Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ){
            Text(text="Start",
                color= White)

        }
    }
    LottieAnimation(composition = composition,
        isPlaying = isPlayingLottie,
        speed = 1.5f,
        iterations = 3,
        clipSpec = animSpec
    )
}