package com.bledroid.generators

import com.bledroid.helpers.HexUtils
import com.bledroid.models.*

/**
 * Apple iBeacon advertisements (Manufacturer 0x004C, prefix 02 15).
 * Format: 02 15 + 16-byte UUID + 2-byte major + 2-byte minor + 1-byte TX.
 * Triggers proximity/scanner detection on iOS and generic BLE scanners.
 */
class IBeaconGenerator : SpamGenerator {
    override val name = "iBeacon"

    private fun beaconPayload(uuidHex: String, majorHex: String, minorHex: String, txHex: String = "C5"): ByteArray {
        return HexUtils.decodeHex("0215$uuidHex$majorHex$minorHex$txHex")
    }

    val beacons = listOf(
        Triple("E2C56DB5DFFB48D2B060D0F5A71096E0", "0001", "0001") to "iBeacon - AirTag-like",
        Triple("74278BDAB64445208F0C720EAF059A00", "0102", "0A0B") to "iBeacon - Tile-like",
        Triple("B9407F30F5F8466EAFF925556B57FE6D", "00AA", "00BB") to "iBeacon - Estimote-like",
        Triple("00112233445566778899AABBCCDDEEFF", "1234", "5678") to "iBeacon - Custom Demo",
    )

    override fun generate(): List<AdvertisementSet> = beacons.mapNotNull { (ids, title) ->
        val (uuidHex, major, minor) = ids
        // Guard: a malformed hex entry must never crash app startup (ViewModel init)
        val payload = runCatching { beaconPayload(uuidHex, major, minor) }.getOrNull()
            ?: return@mapNotNull null
        AdvertisementSet(
            title = title,
            target = AdvertisementTarget.TRACKER,
            type = SpamType.IBEACON,
            manufacturerData = ManufacturerData(
                ManufacturerIds.APPLE,
                payload,
            ),
        )
    }
}
