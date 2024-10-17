package com.example.tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tp2.ui.theme.Tp2Theme  // Assurez-vous que le thème est correctement défini

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp2Theme {
                val navController = rememberNavController()
                RegisterScreen(navController)
            }
        }
    }
}

@Composable
fun RegisterScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current // Récupérer le contexte avant l'utilisation

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Créer un compte", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Champs pour l'adresse e-mail
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Adresse e-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Champs pour le mot de passe
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Champs pour la confirmation du mot de passe
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmer mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Bouton S'inscrire
        Button(
            onClick = {
                when {
                    email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                        errorMessage = "Veuillez remplir tous les champs."
                    }
                    password != confirmPassword -> {
                        errorMessage = "Les mots de passe ne correspondent pas."
                    }
                    else -> {
                        // Rediriger vers l'écran de connexion après l'inscription
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "S'inscrire")
        }

        // Affichage du message d'erreur, s'il existe
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
