package com.example.tp2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }

    @Composable
    fun LoginScreen() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Connexion", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Adresse e-mail") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage.isNotEmpty() && email.isBlank() // Erreur si le champ est vide
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Mot de passe") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = errorMessage.isNotEmpty() && password.isBlank() // Erreur si le champ est vide
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Validation des champs
                    if (email.isBlank() || password.isBlank()) {
                        errorMessage = "Veuillez remplir tous les champs."
                    } else {
                        errorMessage = ""
                        // Sauvegarde des informations dans SharedPreferences
                        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()) {
                            putString("email", email)
                            putBoolean("isLoggedIn", true) // Marquer comme connecté
                            apply()
                        }

                        // Naviguer vers l'écran d'accueil
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish() // Pour fermer l'activité de connexion
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Se connecter")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }) {
                Text(text = "Mot de passe oublié ?")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }) {
                Text(text = "Créer un compte")
            }

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
