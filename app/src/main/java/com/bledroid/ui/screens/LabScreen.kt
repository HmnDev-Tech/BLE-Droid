package com.bledroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bledroid.ui.BleDroidViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabScreen(
    viewModel: BleDroidViewModel,
    onBack: () -> Unit,
) {
    val includeExperimental by viewModel.labIncludeExperimental.collectAsState()
    val showRawPayload by viewModel.labShowRawPayload.collectAsState()
    val hideUnknown by viewModel.labHideUnknown.collectAsState()
    val intervalMs by viewModel.intervalMs.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Lab Features", fontWeight = FontWeight.Bold)
                        Text(
                            "Experimental — may change anytime",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            // Warning banner
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Science, null, tint = MaterialTheme.colorScheme.onTertiaryContainer)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "Lab toggles are UI-only and never change the BLE engine loop. Safe to try.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                }
            }

            LabToggleRow(
                icon = Icons.Default.Science,
                title = "Include experimental in Mix All",
                subtitle = "Eddystone + iBeacon participate in Mix All shuffle",
                checked = includeExperimental,
                onChecked = { viewModel.setLabIncludeExperimental(it) },
            )

            LabToggleRow(
                icon = Icons.Default.Visibility,
                title = "Show raw payload in Radar",
                subtitle = "Display raw advertisement hex under each entry",
                checked = showRawPayload,
                onChecked = { viewModel.setLabShowRawPayload(it) },
            )

            LabToggleRow(
                icon = Icons.Default.Sensors,
                title = "Hide unknown devices",
                subtitle = "Radar shows only classified spam, hides Unknown BLE",
                checked = hideUnknown,
                onChecked = { viewModel.setLabHideUnknown(it) },
            )

            // Burst shortcut (uses existing interval setting, no engine change)
            Surface(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
            ) {
                Column(Modifier.fillMaxWidth().padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Bolt, null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Burst mode shortcut", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                            Text(
                                "Sets advertising interval to 20ms (current: ${intervalMs}ms)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilledTonalButton(onClick = { viewModel.applyBurstInterval() }) {
                            Text("Enable 20ms burst")
                        }
                        OutlinedButton(onClick = { viewModel.setInterval(100L) }) {
                            Text("Reset 100ms")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LabToggleRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        onClick = { onChecked(!checked) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Switch(checked = checked, onCheckedChange = onChecked)
        }
    }
}
