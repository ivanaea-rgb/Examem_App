package com.example.examem_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examem_app.ui.theme.Screen.Login.LoginScreen
import com.example.examem_app.ui.theme.Screen.Register.RegisterScreen
import com.example.examem_app.ui.theme.ExamemAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. Usamos el tema de M3
            ExamemAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // "colorScheme"
                ) {
                    // 2. Configuramos el controlador de navegación
                    val navController = rememberNavController()

                    // 3. Definimos las rutas
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}