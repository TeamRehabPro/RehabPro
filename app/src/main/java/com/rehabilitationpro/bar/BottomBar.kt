//package com.rehabilitationpro.bar
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.navigation.NavHostController
//import com.rehabilitationpro.Screen
//import com.rehabilitationpro.ui.theme.ColorPalette
//
//@Composable
//fun BottomBar(navController: NavHostController) {
//    val colors = ColorPalette()
//    BottomAppBar(
//        containerColor = colors.mySkyBlue
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            IconButton(
//                onClick = { navController.navigate(Screen.MainMenu.route) }
//            ) {
//                Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.White)
//            }
//            IconButton(
//                onClick = { /* TODO: Navigate to settings screen */ }
//            ) {
//                Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = Color.White)
//            }
//            IconButton(
//                onClick = { /* TODO: Navigate to favorite screen */ }
//            ) {
//                Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = Color.White)
//            }
//            IconButton(
//                onClick = { /* TODO: Navigate to info screen */ }
//            ) {
//                Icon(Icons.Filled.Info, contentDescription = "Info", tint = Color.White)
//            }
//            IconButton(
//                onClick = { /* TODO: Navigate to person screen */ }
//            ) {
//                Icon(Icons.Filled.Person, contentDescription = "Person", tint = Color.White)
//            }
//        }
//    }
//}
