package com.serhiibaliasnyi.luckywheel.rule_screen

import android.media.SoundPool
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.serhiibaliasnyi.luckywheel.MainActivity
import com.serhiibaliasnyi.luckywheel.R
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenBackground
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenBg
import com.serhiibaliasnyi.luckywheel.ui.theme.GreenMain
import com.serhiibaliasnyi.luckywheel.ui.theme.MainActionColor
import com.serhiibaliasnyi.luckywheel.ui.theme.irishGroverFontFamily
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Collections
import kotlin.time.Duration.Companion.seconds


//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RuleScreen(sound: SoundPool?, composition:LottieComposition?, player: ExoPlayer, playList: List<MainActivity.Music>) {
    val quantityOfSectors:Int=8
    val quantityOfButtons:Int=3

    val musicDurationMs=10000;
    val alphaDisabled=0.0f
    val alphaRuletteDisabled=0.2f

    val strokeWidth=3.dp;
    val volumeCoin=1f


    val imageVisible = remember { mutableStateListOf(false, false, false) }
    val borderColour =remember {mutableStateListOf(0, 0, 0)}

    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    var imageLittleCoin:Int by remember {
        mutableStateOf(0)
    }
    var imageVisible1 by remember {
        mutableStateOf(false)
    }
    var imageVisible2 by remember {
        mutableStateOf(false)
    }

    var imageVisible3 by remember {
        mutableStateOf(false)
    }
    var alphaCoin1 by remember {
        mutableStateOf(1f)
    }
    var alphaCoin2 by remember {
        mutableStateOf(1f)
    }
    var alphaCoin3 by remember {
        mutableStateOf(1f)
    }
    var visibleCount by remember{
        mutableStateOf(1f)
    }
    var visibleWinImage by remember{
        mutableStateOf(0f)
    }
    var alphaStartButton:Float by remember {
         mutableStateOf(1f)
     }

    var alphaRulette:Float by remember {
        mutableStateOf(alphaRuletteDisabled)
    }
    var alphaButtons:Float by remember {
        mutableStateOf(alphaDisabled)
    }

    var playListShuffle:MutableList<MainActivity.Music> = remember{
        mutableStateListOf<MainActivity.Music>()
    }
    var  listUtilSongs:MutableList<MainActivity.Music> = remember{
        mutableStateListOf<MainActivity.Music>()

    }

    var songId:Int by remember{
        mutableStateOf(-1)
    }

    var winCount:Int by remember{
        mutableStateOf(0)
    }

  //  Log.d("rul", "Recomposition "+playListShuffle.toList())
  //  Log.d("rul", "RecompositionUtil "+listUtilSongs.toList())


    var buttonText1:String by remember {
        mutableStateOf("")
    }


    var buttonText2:String by remember {
        mutableStateOf("")
    }
    var buttonText3:String by remember {
        mutableStateOf("")
    }

    var borderColour1:Int by remember {
        mutableStateOf(0)
    }
    var borderColour2:Int by remember {
        mutableStateOf(0)
    }
    var borderColour3:Int by remember {
        mutableStateOf(0)
    }
    var buttonTextStart:String by remember {
        mutableStateOf("Start")
    }
    var isButtonStartEnabled:Boolean by remember{
        mutableStateOf(true)
    }
    var isButtonsEnabled:Boolean by remember{
        mutableStateOf(false)
    }


    var currentValue by remember { mutableStateOf(0L) }
    var sliderPosition = remember {
        mutableLongStateOf(0)
    }


    var isPlaying by remember { mutableStateOf(false) }

    val numberOfTrack = remember {
        mutableStateOf(-1)
    }

   // val playingSongIndex = remember {
   //     mutableIntStateOf(0)
   // }

    LaunchedEffect(numberOfTrack.value) {
       // Log.d("counter", "Launch1")
   //     playingSongIndex.intValue = numberOfTrack.value - 1
        //  player.seekTo(numberOfTrack.value-1, 0)
    }

    LaunchedEffect(Unit) {
      //   initSongs(playListShuffle,quantytyOfSectors,playList,player )
    /*
        playListShuffle.clear()
        getRandomElements(quantytyOfSectors,playList).forEach {
           playListShuffle.add(it)
       }

        Log.d("rul","Launch="+ playListShuffle.toList())

        playListShuffle.forEach {
            val path = "android.resource://" + "com.serhiibaliasnyi.luckywheel" + "/" + it.music
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
            list.add(it.name)

        }
        Log.d("rul","Launch2="+ playListShuffle.toList())
        Log.d("rul", "Launch2s="+list)
        player.prepare()
        */
    }

   // val currentPosition by remember {
   //     mutableLongStateOf(0)
   // }

   // val totalDuration by remember {
   //     mutableLongStateOf(0)
   // }



    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying_: Boolean) {
                isPlaying = isPlaying_
            }
        }
        player.addListener(listener)
        onDispose {
            player.removeListener(listener)
        }
    }
    if (isPlaying) {
        LaunchedEffect(Unit) {
            while (true) {
                currentValue = player.currentPosition
              //  Log.d("rul", currentValue.toString())
                if(currentValue>musicDurationMs){
                    player.pause()
                    Log.d("rul", "pause="+currentValue.toString())
                    currentValue =0;
                //    sliderPosition.longValue=0
                    isButtonStartEnabled=true
                    isButtonsEnabled=false
                    buttonTextStart="Start"
                    alphaStartButton=1f
                    alphaButtons=alphaDisabled
                    alphaRulette=alphaRuletteDisabled
                    if(winCount>0) winCount--
                    alphaCoin1 = 1f
                    imageLittleCoin=R.drawable.fire_coin2
                    for(x in 0 .. quantityOfButtons-1){
                        imageVisible.set(x, true)
                    }
                    sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)
                  //  currentPosition.longValue = 0
                //    if (player.isPlaying) {
                //    player.seekToNextMediaItem()
                 //   currentValue=0;
                 //   }
                }
                delay(1.seconds/10 )
                Log.d("rul", "play="+currentValue.toString())
                // delay(1000 )
               // currentPosition.longValue = currentValue
                sliderPosition.longValue = currentValue.toLong()
            }
        }
    }

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
             number=((360f-(it%360))/(360f/quantityOfSectors)).toInt()+1
             if(number>quantityOfSectors) number=quantityOfSectors
             Log.d("rul","Before song="+ playListShuffle.toList().toString())

             var song:MainActivity.Music=playListShuffle.get(number-1)
             songId=song.id
           //  listUtilSongs=getUtilSongs(song, playList)
            listUtilSongs.clear()
             getUtilSongs( song, playList).forEach {
                 listUtilSongs.add(it)
             }
                Log.d("rul","Song="+ song.name)
         /*   loading = true
            scope.launch {
                /*loadProgress {loading,  progress ->
                    currentProgress = progress as Float
                }
                 */
                Log.d("rul","loading="+ loading)
            //    loadProgress( loading,  {progress -> currentProgress = progress as Float})


                loading = false // Reset loading when the coroutine finishes
            }
        */
            if (player.isPlaying) {
                player.pause()
            } else {


               // var newSongs=(0..quantytyOfSectors-1).random()
              //  numbers.removeAt(1)
               // buttonText1=list.get(number-1)
//                buttonText1=listUtilSongs.get(0).name
//                buttonText2=listUtilSongs.get(1).name
//                buttonText3=listUtilSongs.get(2).name
                isButtonsEnabled=true
                alphaButtons=1f
                player.seekTo(number-1, C.TIME_UNSET);
                player.setPlayWhenReady(true);
                player.play()

            }
           
        }
    )
    if(winCount==5){
     //   visibleCount=0f
        //    winCount=0
        visibleWinImage=1f
        isPlayingLottie=true

        sound?.play(2, 1F, 1F, 0, 0, 1F)
        buttonTextStart="Start new Game"
    }

    Row(
        modifier= Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(color = GreenMain)
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally
           // horizontalArrangement = Arrangement.Center
        ) {
/*
            Box(
                modifier = Modifier
                    .background(color = White)
                    .fillMaxWidth(),

                  //  .weight(1f),

             contentAlignment = Alignment.Center,

                ) {

                Image(
            //        alignment = Alignment.Center,
                    painter = painterResource(id = R.drawable.crown),
                    contentDescription = "crown",
                    modifier = Modifier
                        .padding(0.dp, 2.dp, 0.dp, 0.dp)
                        .width(120.dp)
                        .height(120.dp)
                        .alpha(visibleWinImage)

                )

                Text(
                    modifier = Modifier
                        //.fillMaxWidth()
                        .height(0.dp)
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        // .wrapContentWidth()
                        // .wrapContentHeight()

                        // .alpha(visibleCount),
                        .alpha(0f),
                    textAlign = TextAlign.Center,
                    text = winCount.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    color = Color.White
                )

            } */
            Row() {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.1f),
                    //horizontalAlignment = Alignment.CenterHorizontally
                    // horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(70.dp),

                        ) {
                        Column(
                            modifier = Modifier
                               // .background(color = Yellow)
                                //.fillMaxWidth()
                                .width(70.dp),
                            //  .weight(1f),
                            // horizontalArrangement = Arrangement.spacedBy(-30.dp)

                        ) {

                            for (x in 1..winCount) {
                                Image(
                                    alignment = Alignment.Center,
                                    painter = painterResource(id = R.drawable.coin3),
                                    contentDescription = "coin",
                                    modifier = Modifier
                                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                        .width(70.dp)
                                        .height(70.dp)
                                        .alpha(1f)

                                )
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .clickable {
                            if (!isButtonStartEnabled) return@clickable
                            if (winCount == 5) {
                                visibleCount = 0f
                                winCount = 0
                            }

                            visibleCount = 1f
                            visibleWinImage = 0f

                            listUtilSongs.clear()
                            for (x in 0..quantityOfButtons - 1) {
                                imageVisible.set(x, false)
                            }
                            for (x in 0..quantityOfButtons - 1) {
                                borderColour.set(x, 0)
                            }
                            alphaCoin1 = 0f
                            buttonTextStart = ""
                            alphaStartButton = alphaDisabled
                            isButtonsEnabled = false
                            alphaButtons = alphaDisabled
                            alphaRulette=1f
                            isButtonStartEnabled = false
                            songId = -1

                            initSongs(playListShuffle, quantityOfSectors, playList, player)
                            //Log.d("rul","playListShuffleButton="+playListShuffle)
                            isPlayingLottie = false
                            rotationValue = ((0..360).random().toFloat() + 720) + angle
                            //   Log.d("rul", "angle="+(angle%360).toString() +" rotationValue "+rotationValue.toString())
                            sound?.play(1, 1F, 1F, 0, 0, 1F)

                        }
                ) {
                    Image(
                        //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                        painter = painterResource(id = R.drawable.rulette5_2),
                        contentDescription = "lucky wheel",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .rotate(angle)
                            .alpha(alphaRulette)
                    )
                    Image(
                        //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                        painter = painterResource(id = R.drawable.arrow2),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                         //   .rotate(angle)
                    )
                    Image(
                        //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                        painter = painterResource(id = R.drawable.title_click2),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .alpha(alphaStartButton)
                        //   .rotate(angle)
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.1f),
                    //horizontalAlignment = Alignment.CenterHorizontally
                    // horizontalArrangement = Arrangement.Center
                ) {
                }
            }
//========================================================
            /*
            LinearProgressIndicator(
              //  currentProgress,
                (sliderPosition.longValue/100000).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 0.dp),
                color = MainActionColor,
                trackColor = White

            )
         */
            Row() {
                Slider(
                    value = (sliderPosition.longValue / 1000).toFloat(),
                    modifier = Modifier.weight(0.1f),
                    //onValueChange = { sliderPosition = it },
                    onValueChange = { },
                    thumb = {
                        // val shape = RectangleShape
                        Spacer(
                            modifier = Modifier
                                .size(2.dp)

                            //        .hoverable(interactionSource = interactionSource)
                            //        .shadow(if (enabled) 6.dp else 0.dp, shape, clip = false)
                            //        .background(Red, shape)
                        )
                    },

                    colors = SliderDefaults.colors(
                        thumbColor = MainActionColor,
                        activeTrackColor = MainActionColor,
                        inactiveTrackColor = White,
                    ),
                    // steps = 100,
                    valueRange = 0f..9f

                )
            }
         // Text(text = (currentProgress/100000).toString()+"-"+(sliderPosition.longValue/1000).toFloat())
         //  Spacer(Modifier.requiredHeight(3.dp))

        } //Column
//========================================================
        Column(
            modifier = Modifier.background(GreenBackground)
            // .fillMaxHeight()
            // .fillMaxWidth(0.6f)
        ) {
            for (x in 0..quantityOfButtons - 1) {

                Card(

                    colors = CardDefaults.cardColors(
                        // containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        containerColor = GreenMain
                    ),
                    //onClick = {Log.d("rul","Click2")},
                    //enabled = isButtonsEnabled,
                    modifier = Modifier
                        // .alpha(alphaButtons)
                        .fillMaxWidth()
                        .padding(3.dp)
                        .weight(0.25f)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                if (!isButtonsEnabled) return@clickable
                                // Log.d("rul","Click2")
                                Log.d("rul", "Click")

                                loading = false
                                sliderPosition.longValue=0;
                              //  scope.// STOPSHIP:
                                player.pause()
                                currentValue = 0;
                                buttonTextStart = "Start"
                                isButtonStartEnabled = true
                                alphaStartButton = 1f
                                alphaRulette=0.2f
                                isButtonsEnabled = false
                                alphaButtons = alphaDisabled
                                imageVisible.set(x, true)
                                alphaCoin1 = 1f
                                //  currentProgress= (musicDurationMs/100).toFloat()
                                if (songId == listUtilSongs.get(x).id) {
                                    winCount++;
                                    borderColour.set(x, 1)
                                    imageLittleCoin = R.drawable.coin3
                                    sound?.play(3, volumeCoin, volumeCoin, 0, 0, 1F)

                                } else {
                                    if (winCount > 0) winCount--
                                    borderColour.set(x, 2)
                                    imageLittleCoin = R.drawable.fire_coin2
                                    sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)

                                }
                            }
                        )

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AnimatedVisibility(
                            visible = imageVisible.get(x),
                            enter = fadeIn(animationSpec = tween(1000)),
                            exit = fadeOut(animationSpec = tween(0))
                        ) {
                            Image(
                                alignment = Alignment.Center,
                                painter = painterResource(id = imageLittleCoin),
                                contentDescription = "coin",
                                modifier = Modifier
                                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                    .width(50.dp)
                                    .height(50.dp)
                                    .alpha(alphaCoin1)

                            )
                        }

                        var buttonText = ""
                        if (!listUtilSongs.isEmpty()) {
                            buttonText = listUtilSongs.get(x).name
                        }
                        Text(
                            text = buttonText,
                            textAlign = TextAlign.Center,
                            //fontFamily = FontFamily.Serif,
                            fontFamily = irishGroverFontFamily,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = White,
                            modifier = Modifier
                                .fillMaxWidth()
                                // .clickable {
//
//                            }
                                .wrapContentWidth()
                                .padding(5.dp, 3.dp)
                        )
                    }
                }
                /*
                OutlinedButton(
                    border = BorderStroke(
                        strokeWidth,
                        if (borderColour.get(x) == 0) {
                            White
                        } else if (borderColour.get(x) == 1) {
                            MainActionColor
                        } else {
                            Color.Red
                        }
                    ),

                    onClick = {
                        // Log.d("rul", "listUtil="+listUtilSongs.toList().toString())
                        loading = false
                        player.pause()
                        currentValue = 0;
                        buttonTextStart = "Start"
                        isButtonStartEnabled = true
                        alphaStartButton = 1f
                        isButtonsEnabled = false
                        alphaButtons = alphaDisabled
                        imageVisible.set(x, true)
                        alphaCoin1 = 1f
                        //  currentProgress= (musicDurationMs/100).toFloat()
                        if (songId == listUtilSongs.get(x).id) {
                            winCount++;
                            borderColour.set(x, 1)
                            imageLittleCoin = R.drawable.coin3
                            sound?.play(3, volumeCoin, volumeCoin, 0, 0, 1F)

                        } else {
                            if (winCount > 0) winCount--
                            borderColour.set(x, 2)
                            imageLittleCoin = R.drawable.fire_coin2
                            sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)

                        }
                    },
                    enabled = isButtonsEnabled,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(GreenMain),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 5.dp)
                        .alpha(alphaButtons)
                ) {

                    AnimatedVisibility(
                        visible = imageVisible.get(x),
                        enter = fadeIn(animationSpec = tween(1000)),
                        exit = fadeOut(animationSpec = tween(0))
                    ) {
                        Image(
                            alignment = Alignment.Center,
                            painter = painterResource(id = imageLittleCoin),
                            contentDescription = "coin",
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .alpha(alphaCoin1)

                        )
                    }

                    var buttonText = ""
                    if (!listUtilSongs.isEmpty()) {
                        buttonText = listUtilSongs.get(x).name
                    }
                    Text(//text=listUtilSongs.get(x).name,
                        text = buttonText,
                        textAlign = TextAlign.Center,
                        fontFamily = irishGroverFontFamily,
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp

                    )
                } */
            }
            //--------------------------------------------
            /*      OutlinedButton(border= BorderStroke(strokeWidth,
            if (borderColour1==0){ White}
            else if(borderColour1==1) {
                MainActionColor}
            else{Color.Red}
        ),

            onClick = {
           // Log.d("rul", "listUtil="+listUtilSongs.toList().toString())
            loading = false
            player.pause()
            currentValue =0;
            buttonTextStart="Start"
            isButtonStartEnabled=true
            alphaStartButton=1f
            isButtonsEnabled=false
            alphaButtons=alphaDisabled
            imageVisible1=true
            alphaCoin1=1f
          //  currentProgress= (musicDurationMs/100).toFloat()
            if(songId==listUtilSongs.get(0).id){
                winCount++;
                borderColour1=1
                imageLittleCoin=R.drawable.coin3
                sound?.play(3, volumeCoin, volumeCoin, 0, 0, 1F)

            }else{
                if(winCount>0) winCount--
                borderColour1=2
                imageLittleCoin=R.drawable.fire_coin2
                sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)

            }

           },
            enabled = isButtonsEnabled,
            shape = RoundedCornerShape(20.dp),
            colors=ButtonDefaults.buttonColors(GreenMain),

            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp)
                .alpha(alphaButtons)
                //.border(
                //    width = 1.dp,
                //    color = if (true) Color.Red else Color.Gray,
                  //  shape = RoundedCornerShape(16.dp)
               // )
        ){


            AnimatedVisibility(
                visible = imageVisible1,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(0))
            ) {
               // if( borderColour1==1) {
                    Image(
                        alignment = Alignment.Center,
                        //painter = painterResource(id = imageId),
                        //painter = painterResource(
                        //    LocalContext.current.resources.getIdentifier(icon, "drawable", LocalContext.current.packageName)),
                        painter = painterResource(id = imageLittleCoin),
                        //   painter = painterResource(id = imageId),
                        contentDescription = "coin",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .alpha(alphaCoin1)

                    )

            }

            Text(text=buttonText1,
                textAlign = TextAlign.Center,
                fontFamily = irishGroverFontFamily,
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp

            )
        }

        OutlinedButton(border= BorderStroke(strokeWidth,
            if (borderColour2==0){ White}
            else if(borderColour2==1) {
            MainActionColor}
            else{Color.Red}),
        onClick = {
            player.pause()
            currentValue =0;
            buttonTextStart="Start"
            alphaStartButton=1f
            isButtonStartEnabled=true
            isButtonsEnabled=false
            alphaButtons=alphaDisabled
            imageVisible2=true
            alphaCoin2=1f
            if(songId==listUtilSongs.get(1).id){
                winCount++;
                borderColour2=1
                imageLittleCoin=R.drawable.coin3
                sound?.play(3, volumeCoin, volumeCoin, 0, 0, 1F)

            }else{
                if(winCount>0) winCount--
                borderColour2=2
                imageLittleCoin=R.drawable.fire_coin2
                sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)
            }

        },
            enabled = isButtonsEnabled,
            shape = RoundedCornerShape(20.dp),
            colors=ButtonDefaults.buttonColors(GreenMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp)
                .alpha(alphaButtons)
        ){
            AnimatedVisibility(
                visible = imageVisible2,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(0))
            ) {
                Image(
                    alignment = Alignment.Center,
                    painter = painterResource(id = imageLittleCoin),
                    contentDescription = "coin",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .width(50.dp)
                        .height(50.dp)
                        .alpha(alphaCoin2)

                )
            }
            Text(text=buttonText2,
                textAlign = TextAlign.Center,
                fontFamily = irishGroverFontFamily,
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        OutlinedButton(border= BorderStroke(strokeWidth,
            if (borderColour3==0){ White}
            else if(borderColour3==1) {
                MainActionColor}
            else{Color.Red}),
            onClick = {
                player.pause()
                currentValue =0;
                buttonTextStart="Start"
                alphaStartButton=1f
                isButtonStartEnabled=true
                isButtonsEnabled=false
                alphaButtons=alphaDisabled
                imageVisible3=true
                alphaCoin3=1f
                if(songId==listUtilSongs.get(2).id){
                    winCount++;
                    borderColour3=1
                    imageLittleCoin=R.drawable.coin3
                    sound?.play(3, volumeCoin, volumeCoin, 0, 0, 1F)

                }else{
                    if(winCount>0) winCount--
                    borderColour3=2
                    imageLittleCoin=R.drawable.fire_coin2
                    sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)
                }

        },
            enabled = isButtonsEnabled,
            shape = RoundedCornerShape(20.dp),
            colors=ButtonDefaults.buttonColors(GreenMain),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp)
                .alpha(alphaButtons)
        ){
            AnimatedVisibility(
                visible = imageVisible3,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(0))
            ) {
                Image(
                    alignment = Alignment.Center,
                    painter = painterResource(id = imageLittleCoin),
                    contentDescription = "coin",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .width(50.dp)
                        .height(50.dp)
                        .alpha(alphaCoin3)

                )
            }
            Text(text=buttonText3,
                textAlign = TextAlign.Center,
                fontFamily = irishGroverFontFamily,
                color= White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    */
/*
            OutlinedButton(
                border = BorderStroke(strokeWidth, White),
                onClick = {
                    if (winCount == 5) {
                        visibleCount = 0f
                        winCount = 0
                    }

                    visibleCount = 1f
                    visibleWinImage = 0f

                    listUtilSongs.clear()
                    for (x in 0..quantityOfButtons - 1) {
                        imageVisible.set(x, false)
                    }
                    for (x in 0..quantityOfButtons - 1) {
                        borderColour.set(x, 0)
                    }
                    alphaCoin1 = 0f
                    buttonTextStart = ""
                    alphaStartButton = alphaDisabled
                    isButtonsEnabled = false
                    alphaButtons = alphaDisabled
                    isButtonStartEnabled = false
                    songId = -1

                    initSongs(playListShuffle, quantityOfSectors, playList, player)
                    //Log.d("rul","playListShuffleButton="+playListShuffle)
                    isPlayingLottie = false
                    rotationValue = ((0..360).random().toFloat() + 720) + angle
                    //   Log.d("rul", "angle="+(angle%360).toString() +" rotationValue "+rotationValue.toString())
                    sound?.play(1, 1F, 1F, 0, 0, 1F)
                    //numberOfTrack.value =numberOfTrack.value+1
                },
                enabled = isButtonStartEnabled,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(GreenMain),
                //modifier = if (true) Modifier else Modifier.alpha(0.5F)
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxWidth()
                    .padding(5.dp, 3.dp)
                    .alpha(alphaStartButton)
            ) {
                Text(
                    text = buttonTextStart,
                    fontFamily = irishGroverFontFamily,
                    color = MainActionColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

            }

        */
        }

    }

    LottieAnimation(composition = composition,
        isPlaying = isPlayingLottie,
        speed = 1.5f,
        iterations = 2,
        clipSpec = animSpec
    )


}

suspend fun loadProgress( load:Boolean, updateProgress: (Float) -> Unit) {
    if (load) {
        for (i in 1..(10000 / 100).toInt()) {
            updateProgress(i.toFloat() / 100)
            delay(100)
        }
    }
}


fun  getUtilSongs(song : MainActivity.Music,list:List<MainActivity.Music> ): MutableList<MainActivity.Music> {

    var squeezeListUtil:MutableList<MainActivity.Music> =list.toMutableStateList()
    squeezeListUtil.remove(song)
    var returnListUtil:ArrayList<MainActivity.Music> = ArrayList(squeezeListUtil);
    //Log.d("rul","In1="+squeezeList.toList())
    Collections.shuffle(returnListUtil); // тут делаем рандом
    returnListUtil.add(0,song)
    if (returnListUtil.size > 3) { // отрезаем не нужную часть

        // тут отрезаем не нужную часть
        //  list.subList(returnList.size - amount, returnList.size).clear()
        // list.subList(0, returnList.size)
        Collections.shuffle(returnListUtil.subList(0,3))
        squeezeListUtil= returnListUtil

        // squeezeList= returnList.subList(returnList.size - amount, returnList.size)
    }
    // Log.d("rul","In="+list)
    Log.d("rul","InThree="+squeezeListUtil.toList())
    return squeezeListUtil;
}
fun  getRandomElements(amount:Int,  list:List<MainActivity.Music> ): MutableList<MainActivity.Music> {
     var returnList:ArrayList<MainActivity.Music> = ArrayList(list);
     var squeezeList:MutableList<MainActivity.Music> =list.toMutableStateList()
    Log.d("rul","In1="+squeezeList.toList())
       Collections.shuffle(returnList); // тут делаем рандом
    if (returnList.size > amount) { // отрезаем не нужную часть
        Log.d("rul", "delSize")
        // тут отрезаем не нужную часть
        //  list.subList(returnList.size - amount, returnList.size).clear()
       // list.subList(0, returnList.size)
        squeezeList= returnList.subList(0,amount)
       // squeezeList= returnList.subList(returnList.size - amount, returnList.size)
    }
   // Log.d("rul","In="+list)
    Log.d("rul","In2="+squeezeList.toList())
        return squeezeList;
    }

fun initSongs(playListShuffle:MutableList<MainActivity.Music>,quantytyOfSectors:Int,playList:List<MainActivity.Music>,player:ExoPlayer ){
    // playListShuffle = getRandomElements(quantytyOfSectors,playList)
    playListShuffle.clear()
    getRandomElements(quantytyOfSectors,playList).forEach {
        playListShuffle.add(it)
    }
    // playListShuffle = playList as SnapshotStateList<MainActivity.Music>
    //playListShuffle = playList.toMutableStateList()
    //  playListShuffle.add(playList[0])

    Log.d("rul","Launch="+ playListShuffle.toList())
    //   Log.d("counter", "Launch0=" + player.currentMediaItemIndex.toString())
    //playList.forEach {
    player.clearMediaItems()
    playListShuffle.forEach {
        val path = "android.resource://" + "com.serhiibaliasnyi.luckywheel" + "/" + it.music
        val mediaItem = MediaItem.fromUri(Uri.parse(path))
        player.addMediaItem(mediaItem)
        //list.add(it.name)

    }
    Log.d("rul","Launch2="+ playListShuffle.toList())
    //Log.d("rul", "Launch2s="+list)
    player.prepare()
}
//fun  getRandomElementsString(amount:Int,  list:MutableList<String> ):MutableList<String> {
//    var returnList:ArrayList<String> = ArrayList(list);
//    var squeezeList:MutableList<String> =list
//
//    Collections.shuffle(returnList); // тут делаем рандом
//    if (returnList.size > amount) { // отрезаем не нужную часть
//        Log.d("rul", "delString")
//        // тут отрезаем не нужную часть
//        //  list.subList(returnList.size - amount, returnList.size).clear()
//        // list.subList(0, returnList.size)
//        squeezeList= returnList.subList(0,amount)
//    }
//    return squeezeList;
//}