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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tp2.ui.theme.Tp2Theme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp2Theme {
                val navController = rememberNavController()
                HomeScreen(navController)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val email = sharedPreferences.getString("email", "Inconnu") ?: "Inconnu"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bienvenue dans l'application !", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Votre adresse e-mail : $email", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Bouton pour se déconnecter
        Button(onClick = {
            sharedPreferences.edit().clear().apply()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Déconnexion")
        }
    }
}
