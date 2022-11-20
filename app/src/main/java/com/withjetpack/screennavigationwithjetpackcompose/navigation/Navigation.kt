package com.withjetpack.screennavigationwithjetpackcompose.navigation

import android.app.assist.AssistContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {

    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {

        composable(route = "home") {
            HomeScreen(navController = navController)
        }
        composable(route = "secondlist/{content}", arguments = listOf(navArgument("content") {
            type= NavType.StringType
        })) {
            SecondScreen(navController = navController, content = it.arguments?.getString("content"))
        }

    }
}

@Composable
fun HomeScreen(navController: NavController) {

    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            modifier = Modifier.border(2.dp, MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = { navController.navigate("secondlist/$text") }) {
            Text(text = "Second Scren")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController, content: String?) {
    require(content != null)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = content, style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text(text = "Go Back")
        }

    }

}