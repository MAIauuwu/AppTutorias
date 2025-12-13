package com.example.apptutorias

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.example.apptutorias.ui.navigation.NavGraph
import com.example.apptutorias.ui.theme.AppTutoriasTheme

class MainActivity : ComponentActivity() {

    // 1. Registro del launcher de permisos
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // Aquí se puede manejar la lógica si se concedió o no
        val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
        val notificationsGranted = permissions[Manifest.permission.POST_NOTIFICATIONS] ?: false
        // ...
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        requestPermissions() // 2. Solicitar permisos al iniciar
        
        setContent {
            AppTutoriasTheme {
                NavGraph()
            }
        }
    }

    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf(
            Manifest.permission.CAMERA
        )
        
        // Permiso de Notificaciones (solo necesario en API 33+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        // Permiso de almacenamiento (puede ser complejo en versiones modernas)
        // Por simplicidad en la EFT, nos enfocamos en Cámara y Notificaciones
        
        if (permissionsToRequest.isNotEmpty()) {
            requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }
}