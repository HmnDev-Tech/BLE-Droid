# BLE-Droid

Android BLE advertisement spam and detection tool. Kotlin, Jetpack Compose, Material 3 Expressive.

**Version:** 1.5.0 · **Min SDK:** 26 (Android 8.0) · **Target SDK:** 36 · **License:** MIT

## What it does

Broadcasts crafted Bluetooth Low Energy advertisements that trigger pairing/assistant popups on nearby devices, and scans/classifies incoming BLE spam.

### Spam protocols

| Protocol | Target | Payload |
|---|---|---|
| Google Fast Pair | Android | Service data `0xFE2C`, 550+ real model IDs |
| Apple Continuity — device popup | iOS/macOS | Manufacturer data `0x004C`, type `0x07` |
| Apple Continuity — action modal | iOS/macOS | Manufacturer data `0x004C`, type `0x0F` |
| Samsung Easy Setup | Samsung phones/watches | Manufacturer data `0x0075` |
| Windows Swift Pair | Windows 10/11 | Manufacturer data `0x0006` |
| Lovespouse | IoT toys | Manufacturer data `0x00FF`, play/stop commands |
| Mix All | all of the above | interleaved, shuffleable |

### Tools

- **Custom BLE** — build arbitrary Service Data or Manufacturer Data advertisements from hex input, with presets.
- **Spam Radar** — live BLE scan that classifies advertisements by manufacturer ID and payload (Fast Pair, Apple, Samsung, Swift Pair, Lovespouse), shows RSSI, MAC, raw payload hex. Capped at 100 tracked devices.

## Requirements

- Android 8.0+ device with BLE advertising support (`android.hardware.bluetooth_le`)
- Bluetooth ON
- Runtime permissions: Bluetooth (API 31+), Location (API ≤ 30, required by Android for BLE scan), Notifications (API 33+)

Not every phone supports BLE advertising. Some vendors lock the advertiser; the app reports failure instead of pretending to run.

## Build

```bash
./gradlew assembleDebug     # debug APK
./gradlew assembleRelease   # release APK (unsigned unless signing env is set)
```

CI: every push to `master` runs `.github/workflows/build-apk.yml` and uploads APKs as workflow artifacts.

For signed release builds set env vars: `KEYSTORE_PATH`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`.

## Usage

1. Open a category (Fast Pair, Apple, Samsung, Swift Pair, Lovespouse, Mix All).
2. Select target devices (search, select all / deselect all).
3. START. Foreground notification shows live packet count; stop from notification, control bar, or dashboard.
4. Spam Radar: START SCAN, watch classified devices appear in real time.

Settings: advertising interval (10–10000 ms), TX power, foreground service on/off, keep screen on during spam, theme mode (system/light/dark), OLED black, theme color.

## Structure

```
app/src/main/java/com/bledroid/
  engine/BleAdvertiserEngine.kt   # advertise loop + radar scan + classifier
  generators/                     # per-protocol payload builders
  service/SpamForegroundService.kt
  ui/screens/                     # Compose screens
  ui/theme/                       # Material 3 Expressive theme
```

## Legal

Authorized testing and research only. Broadcasting unsolicited advertisements may be restricted by local law. You are responsible for how you use this software.

## Issues and PRs

Bug reports and focused PRs only. Read `.github/ISSUE_TEMPLATE/*` and `.github/PULL_REQUEST_TEMPLATE.md` before opening anything.
