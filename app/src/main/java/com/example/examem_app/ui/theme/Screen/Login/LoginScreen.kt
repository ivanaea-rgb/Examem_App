package com.example.examem_app.ui.theme.Screen.Login

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examem_app.ui.theme.TextGray

@Composable
fun LoginScreen(
    navController: NavController
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf<String?>(null) }
    val passwordError = remember { mutableStateOf<String?>(null) }


    fun validateForm(): Boolean {
        var isValid = true
        if (email.value.isBlank()) {
            emailError.value = "El email es requerido"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailError.value = "El formato de email no es válido"
            isValid = false
        } else {
            emailError.value = null
        }

        if (password.value.isBlank()) {
            passwordError.value = "La contraseña es requerida"
            isValid = false
        } else {
            passwordError.value = null
        }
        return isValid
    }


    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Welcome back!",
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Campo de Email ---
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    emailError.value = null
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError.value != null,
                trailingIcon = {
                    if (emailError.value != null) {
                        Icon(Icons.Filled.Error, "Error", tint = MaterialTheme.colorScheme.error) // "colorScheme"
                    }
                },
                singleLine = true
            )
            if (emailError.value != null) {
                Text(
                    text = emailError.value!!,
                    color = MaterialTheme.colorScheme.error, // "colorScheme"
                    style = MaterialTheme.typography.labelSmall, // "labelSmall" de Type.kt
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                    passwordError.value = null
                },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = passwordError.value != null,
                trailingIcon = {
                    if (passwordError.value != null) {
                        Icon(Icons.Filled.Error, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true
            )
            if (passwordError.value != null) {
                Text(
                    text = passwordError.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { /* TODO */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Forgot Password?")
            }

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    if (validateForm()) {
                        println("Login exitoso (simulado)")
                    } else {
                        println("Falló la validación de login")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Login", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account?")
                TextButton(onClick = { navController.navigate("register") }) {
                    Text("Signup")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Or", color = TextGray)
            Spacer(modifier = Modifier.height(24.dp))

            SocialLoginButton(text = "Login with Facebook", color = Color(0xFF1877F2))
            Spacer(modifier = Modifier.height(16.dp))
            SocialLoginButton(text = "Login with Google", color = Color(0xFFDB4437))
        }
    }
}


@Composable
fun SocialLoginButton(text: String, color: Color) {
    OutlinedButton(
        onClick = { /* TODO */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = MaterialTheme.shapes.large, // Usa la forma de Shapes.kt
        border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
    ) {

        Text(text, fontWeight = FontWeight.SemiBold, color = color)
    }
}
