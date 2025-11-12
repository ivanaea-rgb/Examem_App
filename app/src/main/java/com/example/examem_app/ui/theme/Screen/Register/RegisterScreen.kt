package com.example.examem_app.ui.theme.Screen.Register // REVISA QUE TU RUTA DE PACKAGE SEA ESTA

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
// Asegúrate de que esta ruta de import sea correcta
import com.example.examem_app.ui.theme.Screen.Login.SocialLoginButton
import com.example.examem_app.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController
) {
    // --- Estado de la UI (sin cambios) ---
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val nameError = remember { mutableStateOf<String?>(null) }
    val emailError = remember { mutableStateOf<String?>(null) }
    val passwordError = remember { mutableStateOf<String?>(null) }
    val confirmPasswordError = remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    // --- Lógica de Validación (sin cambios) ---
    fun validateForm(): Boolean {
        var isValid = true
        if (name.value.isBlank()) {
            nameError.value = "El nombre es requerido"; isValid = false
        } else nameError.value = null

        if (email.value.isBlank()) {
            emailError.value = "El email es requerido"; isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailError.value = "El formato de email no es válido"; isValid = false
        } else emailError.value = null

        if (password.value.isBlank()) {
            passwordError.value = "La contraseña es requerida"; isValid = false
        } else passwordError.value = null

        if (confirmPassword.value.isBlank()) {
            confirmPasswordError.value = "La confirmación es requerida"; isValid = false
        } else if (password.value != confirmPassword.value) {
            confirmPasswordError.value = "Las contraseñas no coinciden"; isValid = false
        } else confirmPasswordError.value = null

        return isValid
    }

    // --- UI (Corregida) ---
    Scaffold(
        containerColor = Color.White,
        topBar = {
            // --- CAMBIO 1: Esta es la barra que tú me dijiste ---
            TopAppBar(
                title = { Text("Regresar") }, // Correcto
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState), // Para que no se tape con el teclado
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- CAMBIO 2: Títulos agregados en la Columna (como en el PDF) ---
            Text(
                text = "Create your account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Signup",
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp)) // Espacio antes del formulario

            // --- Campo de Nombre ---
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it; nameError.value = null },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = nameError.value != null,
                trailingIcon = {
                    if (nameError.value != null) {
                        Icon(Icons.Filled.Error, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true
            )
            if (nameError.value != null) {
                Text(
                    text = nameError.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo de Email ---
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it; emailError.value = null },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError.value != null,
                trailingIcon = {
                    if (emailError.value != null) {
                        Icon(Icons.Filled.Error, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true
            )
            if (emailError.value != null) {
                Text(
                    text = emailError.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo de Contraseña ---
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it; passwordError.value = null; confirmPasswordError.value = null
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
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo de Confirmar Contraseña ---
            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = {
                    confirmPassword.value = it; confirmPasswordError.value = null
                },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = confirmPasswordError.value != null,
                trailingIcon = {
                    if (confirmPasswordError.value != null) {
                        Icon(Icons.Filled.Error, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true
            )
            if (confirmPasswordError.value != null) {
                Text(
                    text = confirmPasswordError.value!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón de Signup ---
            Button(
                onClick = {
                    if (validateForm()) {
                        println("Signup exitoso (simulado)")
                    } else {
                        println("Falló la validación de signup")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Signup", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Or", color = TextGray)
            Spacer(modifier = Modifier.height(24.dp))

            SocialLoginButton(text = "Login with Facebook", color = Color(0xFF1877F2))
            Spacer(modifier = Modifier.height(16.dp))
            SocialLoginButton(text = "Login with Google", color = Color(0xFFDB4437))
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
