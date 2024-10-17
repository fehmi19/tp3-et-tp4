package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
// Import correct des composants Material3
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForgotPasswordScreen()
        }
    }

    @Composable
    fun ForgotPasswordScreen() {
        var email by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Réinitialiser le mot de passe", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Adresse e-mail") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (email.isBlank()) {
                        errorMessage = "Veuillez entrer une adresse e-mail."
                    } else {
                        errorMessage = ""
                        // Logique de réinitialisation du mot de passe ici
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Réinitialiser le mot de passe")
            }

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                finish()  // Retour à l'écran de connexion
            }) {
                Text(text = "Retour à la connexion")
            }
        }
    }
}
