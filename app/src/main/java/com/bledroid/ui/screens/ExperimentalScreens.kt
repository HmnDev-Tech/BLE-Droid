package com.bledroid.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.bledroid.models.SpamType
import com.bledroid.ui.BleDroidViewModel
import com.bledroid.ui.components.DeviceListWithControls

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EddystoneScreen(
    viewModel: BleDroidViewModel,
    onBack: () -> Unit,
) {
    val sets by viewModel.eddystoneSets.collectAsState()
    val isRunning by viewModel.engine.isRunning.collectAsState()
    val packetsSentState = viewModel.engine.packetsSent.collectAsState()
    val isControlBarExpanded by viewModel.isControlBarExpanded.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eddystone") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
            )
        },
    ) { paddingValues ->
        DeviceListWithControls(
            sets = sets,
            isRunning = isRunning,
            packetsSentProvider = { packetsSentState.value },
            onToggle = { viewModel.toggleDeviceSelection(SpamType.EDDYSTONE_URL, it) },
            onStart = { viewModel.startSpam(SpamType.EDDYSTONE_URL) },
            onStop = { viewModel.stopSpam() },
            onSelectAll = { viewModel.selectAll(SpamType.EDDYSTONE_URL, true) },
            onDeselectAll = { viewModel.selectAll(SpamType.EDDYSTONE_URL, false) },
            searchQuery = searchQuery,
            onSearchChange = { searchQuery = it },
            isControlBarExpanded = isControlBarExpanded,
            onExpandChange = { viewModel.setControlBarExpanded(it) },
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IBeaconScreen(
    viewModel: BleDroidViewModel,
    onBack: () -> Unit,
) {
    val sets by viewModel.ibeaconSets.collectAsState()
    val isRunning by viewModel.engine.isRunning.collectAsState()
    val packetsSentState = viewModel.engine.packetsSent.collectAsState()
    val isControlBarExpanded by viewModel.isControlBarExpanded.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("iBeacon") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
            )
        },
    ) { paddingValues ->
        DeviceListWithControls(
            sets = sets,
            isRunning = isRunning,
            packetsSentProvider = { packetsSentState.value },
            onToggle = { viewModel.toggleDeviceSelection(SpamType.IBEACON, it) },
            onStart = { viewModel.startSpam(SpamType.IBEACON) },
            onStop = { viewModel.stopSpam() },
            onSelectAll = { viewModel.selectAll(SpamType.IBEACON, true) },
            onDeselectAll = { viewModel.selectAll(SpamType.IBEACON, false) },
            searchQuery = searchQuery,
            onSearchChange = { searchQuery = it },
            isControlBarExpanded = isControlBarExpanded,
            onExpandChange = { viewModel.setControlBarExpanded(it) },
            modifier = Modifier.padding(paddingValues),
        )
    }
}
