package com.withjetpack.screennavigationwithjetpackcompose.navigation

sealed class Screen(val route:String){
    object mainScreen:Screen("main_screen")
    object detailScreen:Screen("detail_screen")
}
