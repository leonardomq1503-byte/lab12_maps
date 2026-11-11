package com.example.lab12_maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polygon
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

    val yanahuaraPolygon = remember {
        listOf(
            LatLng(-16.388300, -71.541700),
            LatLng(-16.388300, -71.541100),
            LatLng(-16.388800, -71.541100),
            LatLng(-16.388800, -71.541700)
        )
    }

    val unsaPolygon = remember {
        listOf(
            LatLng(-16.406000, -71.527000),
            LatLng(-16.406000, -71.522000),
            LatLng(-16.411000, -71.522000),
            LatLng(-16.411000, -71.527000)
        )
    }

    val aeropuertoPolygon = remember {
        listOf(
            LatLng(-16.345000, -71.580000),
            LatLng(-16.338000, -71.568000),
            LatLng(-16.344000, -71.562000),
            LatLng(-16.351000, -71.574000)
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Marcador principal
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

            Polygon(
                points = yanahuaraPolygon,
                strokeColor = Color(0xFF2E7D32),
                fillColor = Color(0x664CAF50),
                strokeWidth = 4f
            )

            Polygon(
                points = unsaPolygon,
                strokeColor = Color(0xFF1565C0),
                fillColor = Color(0x662196F3),
                strokeWidth = 5f
            )

            Polygon(
                points = aeropuertoPolygon,
                strokeColor = Color(0xFFE65100),
                fillColor = Color(0x66FF9800),
                strokeWidth = 6f
            )
        }
    }
}