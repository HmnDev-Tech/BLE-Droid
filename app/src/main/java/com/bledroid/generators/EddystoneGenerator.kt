package com.bledroid.generators

import android.os.ParcelUuid
import com.bledroid.helpers.HexUtils
import com.bledroid.models.*
import java.util.UUID

/**
 * Google Eddystone advertisements (Service UUID 0xFEAA).
 * URL frame (0x10) and UID frame (0x00). Useful for proximity/URL testing.
 */
class EddystoneGenerator : SpamGenerator {
    override val name = "Eddystone"

    private val eddystoneUuid = ParcelUuid(UUID.fromString("0000feaa-0000-1000-8000-00805f9b34fb"))

    // URL frame: frameType(10) + txPower + urlScheme + encodedUrl + ...
    // Schemes: 00=http://www. 01=https://www. 02=http:// 03=https://
    // Suffixes: 00=.com/ 01=.org/ 02=.edu/ 03=.net/ 04=.info/ 05=.biz/ 06=.gov/ 07=.com 08=.org 09=.edu 0A=.net
    private fun urlPayload(urlBodyHex: String, txHex: String = "EE"): ByteArray {
        return HexUtils.decodeHex("10$txHex$urlBodyHex")
    }

    val urlSets: List<AdvertisementSet> = listOf(
        // https://www.google.com -> 03 + google(676F6F676C65) + .com(07)
        Triple("Eddystone URL — Google", "03676F6F676C6507", "EE"),
        Triple("Eddystone URL — YouTube", "03696F757475626505", "EE"),
        Triple("Eddystone URL — GitHub", "0367697468756207", "EE"),
        Triple("Eddystone URL — BLE-Droid", "03424C452D44726F696407", "F0"),
    ).map { (title, body, tx) ->
        AdvertisementSet(
            title = title,
            target = AdvertisementTarget.ANDROID,
            type = SpamType.EDDYSTONE_URL,
            serviceData = ServiceData(eddystoneUuid, urlPayload(body, tx)),
            includeTxPower = false,
        )
    }

    val uidSets: List<AdvertisementSet> = listOf(
        // UID frame: 00 + tx + 10-byte namespace + 6-byte instance
        "00EE0102030405060708090A0B0C0D0E0F1011121314" to "Eddystone UID — Demo Tag 1",
        "00EEAABBCCDDEEFF00112233445566778899001122" to "Eddystone UID — Demo Tag 2",
        "00F000112233445566778899AABBCCDDEEFF000001" to "Eddystone UID — Demo Tag 3",
    ).map { (hex, title) ->
        AdvertisementSet(
            title = title,
            target = AdvertisementTarget.TRACKER,
            type = SpamType.EDDYSTONE_UID,
            serviceData = ServiceData(eddystoneUuid, HexUtils.decodeHex(hex)),
            includeTxPower = false,
        )
    }

    override fun generate(): List<AdvertisementSet> = urlSets + uidSets
}
