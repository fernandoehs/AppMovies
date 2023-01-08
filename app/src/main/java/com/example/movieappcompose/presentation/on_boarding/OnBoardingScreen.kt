package com.example.movieappcompose.presentation.on_boarding

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.movieappcompose.R
import com.example.movieappcompose.presentation.on_boarding.components.CustomInputField
import com.example.movieappcompose.presentation.on_boarding.components.GradientButton
import com.example.movieappcompose.presentation.on_boarding.components.OnBoardingButtons
import com.example.movieappcompose.presentation.on_boarding.components.TopRatedMoviesItem
import com.example.movieappcompose.util.Screen
import com.example.movieappcompose.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    navController: NavController
) {
    var stateOfUserName = false
    var stateOfPassWord = false
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Message -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.ShowMovieTitle -> {
                    Toast.makeText(
                        context,
                        event.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    var spacingForLoading by remember {
        mutableStateOf(0.dp)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                TopSection(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 40.dp, end = 15.dp)
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//                Box(modifier = Modifier.fillMaxWidth()) {
//                    LazyRow(modifier = Modifier.padding(end = spacingForLoading)) {
//                        viewModel.state.listOfTopRatedMoviesItem.let { results ->
//                            items(results.size) { i ->
//                                Spacer(modifier = Modifier.width(35.dp))
//                                val topRatedMoviesResult = results[i]
//                                if (i >= results.size - 1 && !viewModel.state.endReached && !viewModel.state.isLoadingFromPaging) {
//                                    viewModel.loadNextItems()
//                                }
//                                TopRatedMoviesItem(
//                                    topRatedMoviesResult = topRatedMoviesResult,
//                                    modifier = Modifier
//                                        .height(280.dp)
//                                        .width(200.dp),
//                                    onItemClick = {
//                                        viewModel.onEvent(
//                                            OnBoardingEvent.OnMovieClick(
//                                                topRatedMoviesResult.name
//                                            )
//                                        )
//                                    }
//                                )
//                            }
//                        }
//                    }
//                    if (viewModel.state.isLoadingFromPaging) {
//                        if (viewModel.state.page == 1) {
//                            CircularProgressIndicator(
//                                modifier = Modifier
//                                    .align(Alignment.Center)
//                            )
//                        } else {
//                            spacingForLoading = 150.dp
//                            CircularProgressIndicator(
//                                modifier = Modifier
//                                    .align(Alignment.CenterEnd)
//                            )
//                        }
//                    } else {
//                        spacingForLoading = 0.dp
//                    }
//                }
//            }
//        }


        Surface(modifier = Modifier.fillMaxSize()) {
            val inputValueID = remember {
                mutableStateOf("")
            }
            if (inputValueID.value.equals("")){
                stateOfUserName = true
            }
            val inputValuePass = remember {
                mutableStateOf("")
            }
            if (inputValuePass.value.equals("")){
                stateOfPassWord = true
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFB42B93),
                                    Color(0xFF000000)
                                ),
                                radius = 415f
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(250.dp)
                            .offset(y = (-20.dp)),
                       // painter = painterResource(id = R.drawable.coloredtorus),
                        painter = painterResource(id =R.drawable.ic_placeholder) ,
                        contentDescription = "Background Image"
                    )
                }

                Card(
                    modifier = Modifier
                        .offset(y = -20.dp)
                        .width(290.dp),
                    shape = RoundedCornerShape(25.dp),
                    elevation = 15.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .padding(top = 30.dp, bottom = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Text(text = "Torus", style = MaterialTheme.typography.h1, modifier = Modifier.padding(bottom = 16.dp))
                        CustomInputField(inputValue = inputValueID, type = "uid")
                        CustomInputField(inputValue = inputValuePass, type = "password")
                    }
                }

            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

//                MoveForwardSection(
//                    modifier = Modifier
//                )
 //              Spacer(modifier = Modifier.height(20.dp))
//                DotsIndicator(
//                    selectedColor = Color(0xFFC2C6C5),
//                    unSelectedColor = Color(0xFF5A595C)
//                )
 //               Spacer(modifier = Modifier.height(100.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OnBoardingButtons(
                        modifier = Modifier,
                        text = stringResource(id = R.string.sign_up),
                        onClick = {

                        }
                    )
                    OnBoardingButtons(
                        modifier = Modifier,
                        text = stringResource(id = R.string.login_in),
                        borderColor = Color(0xFF9F2628),
                        backgroundColor = Color(0xFF9F2628),
                        onClick = {
                            if (stateOfUserName && stateOfPassWord){
                                navController.navigate(Screen.ViewAllPopularMoviesScreen.route)
                              //  navController.navigate(Screen.HomeScreen.route)
                            } else if (!stateOfUserName){
                                Toast.makeText(context,"Username is empty",Toast.LENGTH_SHORT).show()
                            } else if (!stateOfPassWord){
                                Toast.makeText(context,"Password is empty",Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
                }

            }
        }
    }
}

@Composable
fun SimpleAlertDialog() {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = {})
            { Text(text = "Campo de Username vacio") }
        },
    )
}


@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
//        Image(
//            painter = rememberAsyncImagePainter(
//                model = ImageRequest.Builder(context)
//                    .data("https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.svg")
//                    .decoderFactory(SvgDecoder.Factory())
//                    .build()
//            ),
//            contentDescription = stringResource(id = R.string.netflix_logo),
//            modifier = Modifier
//                .size(110.dp)
//        )

//        Row(
//            modifier = Modifier,
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = stringResource(id = R.string.privacy),
//                fontSize = 16.sp,
//                color = Color(0XFFC2C6C5)
//            )
//            Spacer(modifier = Modifier.width(1.dp))
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    imageVector = Icons.Default.MoreVert,
//                    contentDescription = stringResource(id = R.string.more_vert),
//                    tint = Color(0XFFC2C6C5)
//                )
//            }
//        }
    }
}

