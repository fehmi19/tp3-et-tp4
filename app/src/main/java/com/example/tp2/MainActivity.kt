package com.example.tp2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tp2.ui.theme.Tp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Utilisation de votre thème principal Tp2Theme
            Tp2Theme {
                val navController = rememberNavController()
                // Affichage de l'écran de connexion
                LoginScreen(navController)
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Connexion", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Champ pour l'email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Adresse e-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Champ pour le mot de passe
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation() // Masquage du mot de passe
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton pour la connexion
        Button(onClick = {
            if (email.isBlank() || password.isBlank()) {
                errorMessage = "Veuillez remplir tous les champs."
            } else {
                // Sauvegarde de l'email dans les SharedPreferences
                sharedPreferences.edit().putString("email", email).apply()

                // Navigation vers HomeActivity après la connexion
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Se connecter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton pour accéder à l'écran d'inscription
        TextButton(onClick = {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Créer un compte")
        }

        // Affichage du message d'erreur s'il y en a un
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
