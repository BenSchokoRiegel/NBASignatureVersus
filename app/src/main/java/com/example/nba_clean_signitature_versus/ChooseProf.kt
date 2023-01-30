package com.example.nba_clean_signitature_versus

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nba_clean_signitature_versus.viewmodel.PlayerViewModel

@Composable
fun ChooseProfil(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    playerViewModel: PlayerViewModel,
    isFirst: Boolean
) {


    //val playerViewModel: PlayerViewModel = viewModel(factory = PlayerViewModelFactory(context.applicationContext as Application) )

    MaterialTheme() {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { Topbar(topAndBottomColor, "Choose Player", navController = navController) },
            bottomBar = {
            }
            //bottomBar = { BottomAppBar(backgroundColor = Color.Red, modifier = Modifier.fillMaxHeight()) { Text("Click FloatingAction to add Random elements ") } },
            // floatingActionButton = {
            //FloatingActionButton(onClick = { navController.navigate(Screen.CreateNote.route) }

        ) {
            RasterlayoutForPicture(
                navController = navController,
                sharedViewModel = sharedViewModel,
                isFirst
            )

        }
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RasterlayoutForPicture(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    isFirst: Boolean
) {


    val allProfils = all_pr


    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
        Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        )

    {
        items(allProfils.size) { index ->
            val bild = allProfils[index]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (isFirst) {
                            sharedViewModel.player1.picture = bild
                        } else {

                            sharedViewModel.player2.picture = bild
                        }
                        navController.navigate(Screen.GameSettingScreen.route)

                    },
                shape = RoundedCornerShape(16.dp),
                elevation = 4.dp
            ) {
                Box(modifier = Modifier.height(75.dp).background(color = topAndBottomColor)){
                    Image(painter = painterResource(id = bild), contentDescription = null)
            }
        }
    }
}
}