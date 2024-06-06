package com.serhiibaliasnyi.luckywheel.rule_screen

import android.graphics.BitmapFactory
import android.media.SoundPool
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.matchParentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
//import com.airbnb.lottie.LottieComposition
//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieClipSpec
import com.serhiibaliasnyi.luckywheel.MainActivity
import com.serhiibaliasnyi.luckywheel.R
//import com.serhiibaliasnyi.luckywheel.ui.theme.GreenBackground
//import com.serhiibaliasnyi.luckywheel.ui.theme.GreenBg
//import com.serhiibaliasnyi.luckywheel.ui.theme.GreenMain
import com.serhiibaliasnyi.luckywheel.ui.theme.MainActionColor
import com.serhiibaliasnyi.luckywheel.ui.theme.irishGroverFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Collections
import kotlin.time.Duration.Companion.seconds

/*class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
} */
//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun RuleScreen(sound: SoundPool?, player: ExoPlayer, playList: List<MainActivity.Music>) {
    val quantityOfSectors:Int=8

    var quantityOfButtons = remember {
        mutableStateOf(3)
    }
    val quantityOfWinCount:Int =5

    val musicDurationMs=10000;
   // val alphaDisabled=0.0f
   // val alphaRuletteDisabled=1f

    val strokeWidth=3.dp;
    val volumeCoin=1f
    val infiniteTransition = rememberInfiniteTransition()
    val coroutineScope = rememberCoroutineScope()
/*
    val heartbeatAnimation by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )


    val flashAnimation by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        //durationMillis = 2000,
       // animationSpec = tween(
       //     durationMillis = 2000,
       //     easing = LinearOutSlowInEasing
       // )
        animationSpec = infiniteRepeatable(
          //  durationMillis = 2000,
            tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )


    )
*/
    val imageVisible = remember { mutableStateListOf(false, false, false, false) }
    val borderColour =remember {mutableStateListOf(0, 0, 0)}

    //var currentProgress by remember { mutableStateOf(0f) }
    //var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope
    val taskVeriable = remember {mutableStateOf("Hello World")}

    var toggleState = remember {
        mutableStateOf(0)
    }
    var imageLittleCoin:MutableState<Int> = remember {
        mutableStateOf(0)
    }
/*    var imageVisible1 by remember {
        mutableStateOf(false)
    }
    var imageVisible2 by remember {
        mutableStateOf(false)
    }

    var imageVisible3 by remember {
        mutableStateOf(false)
    }  */
  /*  var alphaCoin1 by remember {
        mutableStateOf(1f)
    }
    var alphaCoin2 by remember {
        mutableStateOf(1f)
    }
    var alphaCoin3 by remember {
        mutableStateOf(1f)
    }
   */
    var visibleCount by remember{
        mutableStateOf(1f)
    }
    var visibleWinImage by remember{
        mutableStateOf(0f)
    }
  /*  var alphaStartButton:Float by remember {
         mutableStateOf(1f)
     } */

  /*  var alphaRulette:Float by remember {
        mutableStateOf(alphaRuletteDisabled)
    }*/
  /*  var alphaButtons:Float by remember {
        mutableStateOf(alphaDisabled)
    } */

    var playListShuffle:MutableList<MainActivity.Music> = remember{
        mutableStateListOf<MainActivity.Music>()
    }
    var  listUtilSongs:MutableList<MainActivity.Music> = remember{
        mutableStateListOf<MainActivity.Music>()

    }

    var songId:Int by remember{
        mutableStateOf(-1)
    }

    var winCount = remember{
        mutableStateOf(0)
    }

    var totalWinCount = remember{
        mutableStateOf(0)
    }
    /*var borderColour1:Int by remember {
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
    }  */
    var isButtonStartEnabled:Boolean by remember{
        mutableStateOf(true)
    }
    var isButtonsEnabled:Boolean by remember{
        mutableStateOf(false)
    }

    var winVisible:Boolean by remember{
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
                 //   buttonTextStart="Start"
                 //88   alphaStartButton=1f
                 //55   alphaButtons=alphaDisabled
                 //55   alphaRulette=alphaRuletteDisabled
                    if(winCount.value>0) winCount.value--
                    //alphaCoin1 = 1f
                    imageLittleCoin.value=R.drawable.fire_coin2
                    for(x in 0 .. quantityOfButtons.value-1){
                        imageVisible.set(x, true)
                    }
                    sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)
                    if(toggleState.value==2) {
                        coroutineScope.launch {
                            for (n in 1..2) {
                                delay(1000)
                            }
                            listUtilSongs.clear()
                        }
                    }
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

   /* var isPlayingLottie by remember {
        mutableStateOf(false)
    }
    val animSpec= LottieClipSpec.Progress(
        0f,
        0.8f
    ) */
    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var flashValue by remember {
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
                loadProgress {loading,  progress ->
                    currentProgress = progress as Float
                }

           */
            if (player.isPlaying) {
                player.pause()
            } else {

                isButtonsEnabled=true
             //555   alphaButtons=1f
                player.seekTo(number-1, C.TIME_UNSET);
                player.setPlayWhenReady(true);
                player.play()
            }

        }
    )

    val flashState: Float by animateFloatAsState(
        targetValue = flashValue,
        animationSpec = tween(
            durationMillis = 4000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
                winCount.value=0
                flashValue = 0f
                Log.d("rul", "finish")
                winVisible = false
                visibleWinImage=0f
            for (x in 0..quantityOfButtons.value - 1) {
                imageVisible.set(x, false)
            }
            listUtilSongs.clear()
           //    alphaCoin1 = 0f

        }
    )

    val color by infiniteTransition.animateColor(
        initialValue = Red,
        targetValue = Color(0xff800000), // Dark Red
        animationSpec = infiniteRepeatable(
            // Linearly interpolate between initialValue and targetValue every 1000ms.
            animation = tween(1000, easing = LinearEasing),
            // Once [TargetValue] is reached, starts the next iteration in reverse (i.e. from
            // TargetValue to InitialValue). Then again from InitialValue to TargetValue. This
            // [RepeatMode] ensures that the animation value is *always continuous*.
            repeatMode = RepeatMode.Reverse
        )
    )

 /*   var isHeartBeating by remember { mutableStateOf(true) }
    val heartScale:Float by animateFloatAsState(
        animationSpec = tween(
            durationMillis = 4000,
            easing = LinearOutSlowInEasing
        ),

      //  targetValue = if (isHeartBeating) 1.2f else 1f,
        targetValue =  1.2f,
                finishedListener = {
                    visibleWinImage=0f
                    isHeartBeating=false
                    Log.d("rul","heartScaleStop")
        }
    )
*/
    if(winCount.value==quantityOfWinCount){
        winVisible=true
        visibleWinImage = 1f
        flashValue = 1f
        sound?.play(2, 1F, 1F, 0, 0, 1F)
    }
    Image(painter = painterResource(id = R.drawable.bg6),
        contentDescription = "bg",
        modifier= Modifier
            .fillMaxSize()
            .alpha(0.6f),
        contentScale = ContentScale.FillBounds)
    Row(
        modifier= Modifier
            .fillMaxSize()
            .padding(2.dp)
         //   .background(color = GreenMain)
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
                        .fillMaxWidth(0.1f)
                    //  .background(Color.Red) ,
                    //horizontalAlignment = Alignment.CenterHorizontally
                    // horizontalArrangement = Arrangement.Center
                ) {
                    /* Box(
                       // contentAlignment = Alignment.Center,
                        modifier = Modifier
                        //    .width(100.dp),
                            .fillMaxHeight()

                        ) {

                    */
                    Column(
                        modifier = Modifier
                            //  .background(color = Yellow)
                            .fillMaxHeight(),
                        //.fillMaxWidth()
                        //   .width(100.dp),
                        // .weight(1f),
                        // horizontalArrangement = Arrangement.spacedBy(-30.dp)
                        verticalArrangement = Arrangement.Top
                    ) {

                        for (x in 1..winCount.value) {
                            Image(

                                painter = painterResource(id = R.drawable.coin3),
                                contentDescription = "coin",
                                modifier = Modifier
                                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                    // .width(200.dp)
                                    .height(70.dp)
                                    //  .weight(0.2f)
                                    .alpha(1f)
                                )
                        }
                    }
                    //}
                }

                Box(
                    // interactionSource = remember { NoRippleInteractionSource() },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .clickable {
                            if (!isButtonStartEnabled) return@clickable
                            if( winVisible==true) return@clickable
                            if (winCount.value == quantityOfWinCount) {
                                visibleCount = 0f
                                winCount.value = 0
                            }

                            visibleCount = 1f
                            visibleWinImage = 0f
                            flashValue=0f
                            winVisible=false
                            listUtilSongs.clear()
                           // for (x in 0..quantityOfButtons.value - 1) {
                            for (x in 0..3) {
                                imageVisible.set(x, false)
                            }
                          //  for (x in 0..quantityOfButtons.value - 1) {
                          //      borderColour.set(x, 0)
                          //  }
                           // alphaCoin1 = 0f
                          //  buttonTextStart = ""
                          //  alphaStartButton = alphaDisabled
                            isButtonsEnabled = false
                         //555   alphaButtons = alphaDisabled
                        //555    alphaRulette = 1f
                            isButtonStartEnabled = false
                            songId = -1

                            initSongs(playListShuffle, quantityOfSectors, playList, player)
                            //Log.d("rul","playListShuffleButton="+playListShuffle)
                          //  isPlayingLottie = false
                            rotationValue = ((0..360).random().toFloat() + 720) + angle
                            //   Log.d("rul", "angle="+(angle%360).toString() +" rotationValue "+rotationValue.toString())
                            sound?.play(1, 1F, 1F, 0, 0, 1F)

                        }
                ) {

                    Image(
                        //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                        painter = painterResource(id = R.drawable.external_rul8),
                        contentDescription = "lucky wheel",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            //  .rotate(angle)
                          //555  .alpha(alphaRulette)
                    )
                    Image(
                        //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                        painter = painterResource(id = R.drawable.internal_rul9),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .rotate(angle)
                        //555    .alpha(alphaRulette)
                    )
                    if (isButtonStartEnabled && visibleWinImage == 0f) {
                        Image(
                            painter = painterResource(id = R.drawable.btn_spin4),
                            contentDescription = "arrow",
                            colorFilter = ColorFilter.tint(color),
                            //tint=color,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                            //  .alpha(alphaStartButton)
                            //    .scale(heartbeatAnimation)
                            //    .background(Color.Cyan.copy(flashAnimation))
                            //   .rotate(angle)
                        )
                    }
                    Row(){


                 /* AnimatedVisibility(
                        visible = winVisible,
                        enter = fadeIn(animationSpec = tween(2000)),
                        exit = fadeOut(animationSpec = tween(1))
                    ) {*/
                        if (visibleWinImage == 1f) {
                        //    flashValue=1f
                        Image(
                            //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                            painter = painterResource(id = R.drawable.crown),
                            contentDescription = "crown",

                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                                .alpha(visibleWinImage)
                             //   .alpha(flashState)
                            //   .scale(heartbeatAnimation)
                                .scale(1.4f),
                            // .background(Color.Cyan.copy(flashAnimation))
                            //   .rotate(angle)
                        )
                      // if (this.transition.currentState == this.transition.targetState){
                         //  Log.d("rul","Ok!!!!!!!!!!")
                         //   winVisible=false
                         //  visibleWinImage=0f
                     //  }
                    }
                }
            }
                Column(
                    modifier = Modifier
                        .padding(0.dp,0.dp,0.dp,20.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(0.1f)
                        .alpha(0.8f),
                    verticalArrangement = Arrangement.Center
                    //horizontalAlignment = Alignment.CenterHorizontally
                    // horizontalArrangement = Arrangement.Center
                ) {
                 ToggleMode(toggleState, quantityOfButtons)
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

                SliderMusic(sliderPosition)



        } //Column
//========================================================
        Column(
            modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
            // .fillMaxHeight()
            // .fillMaxWidth(0.6f)
        ) {
            for (x in 0..quantityOfButtons.value-1) {

                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .weight(0.33f)
                        .fillMaxSize()
                        .clickable {
                            //onClick: ()->Unit
                            if (!isButtonsEnabled) return@clickable
                            isButtonStartEnabled= true
                            isButtonsEnabled = false

                            choiceSong(x, songId,volumeCoin, sound,
                            imageLittleCoin,winCount, listUtilSongs,
                            imageVisible,
                            sliderPosition,player )

                        /*    sliderPosition.longValue=0;
                              player.pause()
                          //77  currentValue = 0;
                          //88   alphaStartButton= 1f
                          //555  alphaButtons = alphaDisabled
                            imageVisible.set(x, true)
                          // alphaCoin1 = 1f
                            if (songId == listUtilSongs.get(x).id) {
                                winCount++;
                                imageLittleCoin = R.drawable.coin3
                                sound?.play(3, volumeCoin, volumeCoin, 0, 0, 1F)
                            } else {
                                if (winCount > 0) winCount--
                                imageLittleCoin = R.drawable.fire_coin2
                                sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)
                            } */
                        },

                ) {
                    var buttonText = ""

                    if(toggleState.value ==0) {
                        var cloud: Int = R.drawable.button0
                        if (x == 1) cloud = R.drawable.button2
                        else if (x == 2) cloud = R.drawable.button4

                        Image(
                            //painter= painterResource(id = R.drawable.lucky_wheel_bg),
                            painter = painterResource(cloud),
                            contentDescription = "count",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                            //  .rotate(angle)
                            //  .alpha(alphaRulette)
                        )
                        if (!listUtilSongs.isEmpty()) {
                                buttonText = listUtilSongs.get(x).name
                        }
                    }else if(toggleState.value ==1) {
                        var buttonImage: String = ""
                        if (!listUtilSongs.isEmpty()) {
                            buttonImage = listUtilSongs.get(x).cover
                            AssetImage(buttonImage.toString())
                        }
                    }

                    if(toggleState.value ==0 && toggleState.value ==1) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AnimatedVisibility(
                                visible = imageVisible.get(x),
                                enter = fadeIn(animationSpec = tween(1000)),
                                exit = fadeOut(animationSpec = tween(1))
                            ) {
                                Image(
                                    alignment = Alignment.Center,
                                    painter = painterResource(id = imageLittleCoin.value),
                                    contentDescription = "coin",
                                    modifier = Modifier
                                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                                        .width(50.dp)
                                        .height(50.dp)
                                    //   .alpha(alphaCoin1)

                                )
                            }

                            Text(
                                text = buttonText,
                                textAlign = TextAlign.Center,
                                //fontFamily = FontFamily.Serif,
                                fontFamily = irishGroverFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth()
                                    .padding(15.dp, 5.dp)
                                    //   .background(color = Yellow)
                                    .wrapContentHeight(align = Alignment.CenterVertically),
                            )
                        }
                    }
                }

            }
            //--------------------------------------------

        }

    }

    Row(
    modifier= Modifier
        .fillMaxSize()
        .padding(1.dp)

       // .background(Yellow)
    ) {
       // for (x in 0..quantityOfButtons - 1) {

        if ( toggleState.value ==2 && !listUtilSongs.isEmpty() ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
              //  .background(Red)
            ) {
                Box(modifier= Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .clickable {
                        if (!isButtonsEnabled) return@clickable
                        isButtonStartEnabled= true
                        isButtonsEnabled = false

                            choiceSong(0, songId,volumeCoin, sound,
                            imageLittleCoin,winCount, listUtilSongs,
                            imageVisible,
                            sliderPosition,player )

                           coroutineScope.launch {
                            for(n in 1..2){
                                delay(1000)
                            }
                            listUtilSongs.clear()
                        }

                    }
                ) {
                       AssetImage(listUtilSongs.get(0).cover)
                      // Row(verticalAlignment = Alignment.CenterVertically) {
                           /*   AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(animationSpec = tween(1000)),
                            exit = fadeOut(animationSpec = tween(1))
                        ) {  */
                           if (imageVisible.get(0)) {
                               Image(
                                   alignment = Alignment.Center,
                                   painter = painterResource(id = imageLittleCoin.value),
                                   contentDescription = "coin",
                                   modifier = Modifier
                                       .padding(15.dp, 0.dp, 0.dp, 0.dp)
                                       .width(50.dp)
                                       .height(50.dp)
                                   //   .alpha(alphaCoin1)

                               )
                           }
                       //}
                 }
                Box(modifier= Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clickable {
                        if (!isButtonsEnabled) return@clickable
                        isButtonStartEnabled= true
                        isButtonsEnabled = false

                        choiceSong(2, songId,volumeCoin, sound,
                            imageLittleCoin,winCount, listUtilSongs,
                            imageVisible,
                            sliderPosition,player )
                        coroutineScope.launch {
                            for(n in 1..2){
                                delay(1000)
                            }
                            listUtilSongs.clear()
                        }
                    }
                ) {

                    AssetImage(listUtilSongs.get(2).cover)
                    if (imageVisible.get(2)) {
                        Image(
                            alignment = Alignment.Center,
                            painter = painterResource(id = imageLittleCoin.value),
                            contentDescription = "coin",
                            modifier = Modifier
                                .padding(15.dp, 0.dp, 0.dp, 0.dp)
                                .width(50.dp)
                                .height(50.dp)
                            //   .alpha(alphaCoin1)

                        )
                    }
                }
            }
           Column(modifier= Modifier
                  .fillMaxHeight()
                  .fillMaxWidth()
               // .background(White)
           )
           {
               Box(modifier= Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(0.5f)
                   .clickable {
                       if (!isButtonsEnabled) return@clickable
                       isButtonStartEnabled= true
                       isButtonsEnabled = false

                       choiceSong(1, songId,volumeCoin, sound,
                           imageLittleCoin,winCount, listUtilSongs,
                           imageVisible,
                           sliderPosition,player )
                       coroutineScope.launch {
                           for(n in 1..2){
                               delay(1000)
                           }
                           listUtilSongs.clear()
                       }
                   }
               ) {
                   AssetImage(listUtilSongs.get(1).cover)
                   if (imageVisible.get(1)) {
                       Image(
                           alignment = Alignment.Center,
                           painter = painterResource(id = imageLittleCoin.value),
                           contentDescription = "coin",
                           modifier = Modifier
                               .padding(15.dp, 0.dp, 0.dp, 0.dp)
                               .width(50.dp)
                               .height(50.dp)
                           //   .alpha(alphaCoin1)

                       )
                   }
               }
               Box(modifier= Modifier
                   .fillMaxWidth()
                   .fillMaxHeight()
                   .clickable {
                       if (!isButtonsEnabled) return@clickable
                       isButtonStartEnabled= true
                       isButtonsEnabled = false

                       choiceSong(3, songId,volumeCoin, sound,
                           imageLittleCoin,winCount, listUtilSongs,
                           imageVisible,
                           sliderPosition,player )
                       coroutineScope.launch {
                           for(n in 1..2){
                               delay(1000)
                           }
                           listUtilSongs.clear()
                       }
                   }
               ) {

                   AssetImage(listUtilSongs.get(3).cover)
                   if (imageVisible.get(3)) {
                       Image(
                           alignment = Alignment.Center,
                           painter = painterResource(id = imageLittleCoin.value),
                           contentDescription = "coin",
                           modifier = Modifier
                               .padding(15.dp, 0.dp, 0.dp, 0.dp)
                               .width(50.dp)
                               .height(50.dp)
                           //   .alpha(alphaCoin1)

                       )
                   }
               }

                }
            }
        }


  /*  LottieAnimation(composition = composition,
        isPlaying = isPlayingLottie,
        speed = 1.5f,
        iterations = 2,
        clipSpec = animSpec
    ) */


}

fun choiceSong(x: Int,songId:Int,volumeCoin:Float, sound: SoundPool?,
               imageLittleCoin:MutableState<Int>,winCount:MutableState<Int>, listUtilSongs:MutableList<MainActivity.Music> ,
               imageVisible: SnapshotStateList<Boolean>,
               sliderPosition: MutableLongState, player:ExoPlayer ){
   //onClick() {
       sliderPosition.longValue = 0;
       player.pause()
       //currentValue.longValue = 0;
  //99  isButtonStartEnabled.value = true
  //99  isButtonsEnabled.value = false
       //88alphaStartButton.value = 1f
       //555alphaRulette.value=alphaRuletteDisabled

      // alphaButtons.value = alphaDisabled
       imageVisible.set(x, true)
       //alphaCoin1.value = 1f
       if (songId == listUtilSongs.get(x).id) {
           winCount.value++;
           imageLittleCoin.value = R.drawable.coin3
           sound?.play(3, volumeCoin, volumeCoin, 0, 0, 1F)

       } else {
           if (winCount.value > 0) winCount.value--
           imageLittleCoin.value = R.drawable.fire_coin2
           sound?.play(4, volumeCoin, volumeCoin, 0, 0, 1F)
       }
  // }
}
/*suspend fun loadProgress( load:Boolean, updateProgress: (Float) -> Unit) {
    if (load) {
        for (i in 1..(10000 / 100).toInt()) {
            updateProgress(i.toFloat() / 100)
            delay(100)
        }
    }
} */

@Composable
//fun AssetImage(trackName: MutableState<String>) {
fun AssetImage(trackName: String) {
    /* var imageVisible by remember { mutableStateOf(false) }
     val imageAlpha: Float by animateFloatAsState(
         targetValue = if (imageVisible) 1f else 0f,
         animationSpec = tween(
             durationMillis = 2000,
             easing = LinearEasing,
         )
     )
    */

    val imageAlpha = 1f
    val context = LocalContext.current
    val assetManger = context.assets
    val inputStream = assetManger.open(trackName.toString() + ".jpg")
    val bitMap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitMap.asImageBitmap(),
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
        alpha = imageAlpha,
        modifier = Modifier.fillMaxSize().padding(1.dp).border(2.dp,White)
    )
}
fun  getUtilSongs(song : MainActivity.Music,list:List<MainActivity.Music> ): MutableList<MainActivity.Music> {

    var squeezeListUtil:MutableList<MainActivity.Music> =list.toMutableStateList()
    squeezeListUtil.remove(song)
    var returnListUtil:ArrayList<MainActivity.Music> = ArrayList(squeezeListUtil);
    //Log.d("rul","In1="+squeezeList.toList())
    Collections.shuffle(returnListUtil); // тут делаем рандом
    returnListUtil.add(0,song)
    if (returnListUtil.size > 4) { // отрезаем не нужную часть

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