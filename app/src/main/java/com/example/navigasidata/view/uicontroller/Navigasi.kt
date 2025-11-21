package com.example.navigasidata.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigasidata.model.DataJK.JenisK
import com.example.navigasidata.view.FormIsian
import com.example.navigasidata.view.TampilData
import com.example.navigasidata.viewmodel.SiswaViewModel


enum class Navigasi {
    Formulirku,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaApp(
    modifier: Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang ->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,

            modifier = Modifier.padding( isiRuang)){
            composable(route = Navigasi.Formulirku.name){
                val konteks = LocalContext.current
                FormIsian(
                    pilihanJK = JenisK,
                    onSubmitButtonClicked = {
                        viewModel.setSiswa(it)
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name){
                TampilData(
                    statusUiSiswa = uiState.value,
                    onBackBtnClicked = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }

    }
}

fun cancelAndBackToFormulir(
    navController: NavController
) {
    navController.popBackStack(Navigasi.Formulirku.name, inclusive = false)
}
