package com.example.lab12_maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen() {
    val arequipaLocation = LatLng(-16.4040102, -71.559611)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(arequipaLocation, 12f)
    }

    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f),
            durationMs = 3000
        )
    }

    val locations = remember {
        listOf(
            LatLng(-16.433415, -71.5442652),  // JLByR
            LatLng(-16.4205151, -71.4945209), // Paucarpata
            LatLng(-16.3524187, -71.5675994)  // Zamacola
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = rememberMarkerState(position = arequipaLocation),
                icon = BitmapDescriptorFactory.fromResource(R.drawable.arequipaicon),
                title = "Arequipa, Perú"
            )

            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(key = location.toString(), position = location),
                    title = "Ubicación",
                    snippet = "Punto de interés"
                )
            }
        }
    }
}