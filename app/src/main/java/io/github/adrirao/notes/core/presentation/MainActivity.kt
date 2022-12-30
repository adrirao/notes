package io.github.adrirao.notes.core.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.adrirao.notes.R
import io.github.adrirao.notes.core.presentation.components.NotesBackground
import io.github.adrirao.notes.core.presentation.util.Route
import io.github.adrirao.notes.feature_auth.presentation.login.LoginScreen
import io.github.adrirao.notes.feature_auth.presentation.register.RegisterScreen
import io.github.adrirao.notes.ui.theme.NotesTheme

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val startDestination = Route.LOGIN
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        MainScreen(startDestination, navController)
                    }
                }
            }
        }
    }

    @Composable
    fun MainScreen(startDestination: String, navController: NavHostController) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(Route.LOGIN) {
                NotesBackground(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    titleId = R.string.welcome,
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.banner_notes),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }) {
                    LoginScreen {
                        navController.navigate(Route.REGISTER)
                    }
                }
            }
            composable(Route.REGISTER) {
                NotesBackground(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    titleId = R.string.create_account,
                ) {
                    RegisterScreen(
                        onNavigateToHome = {
                            navController.navigate(Route.HOME)
                        }
                    ){
                        navController.navigate(Route.LOGIN)
                    }
                }
            }
            composable(Route.HOME){
                Text("Home")
            }
        }
    }
}