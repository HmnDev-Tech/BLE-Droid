# Security Policy

## Reporting

Report vulnerabilities privately via
[GitHub Security Advisories](https://github.com/HmnDev-Tech/BLE-Droid/security/advisories/new)
(Reports → "New draft advisory"). Do not open a public issue.

Include: affected version/commit, reproduction steps, impact, and any suggested fix.

Acknowledgement: valid reports get credited in the advisory unless you prefer not to be.

## Scope

In scope:

- Remote code execution, data disclosure, or privilege escalation in the app
- Leakage of signing material, CI secrets, or user data through this repository
- Weaknesses in how the app handles permissions or Bluetooth state

Out of scope:

- The BLE advertisement payloads themselves — the app's stated purpose is authorized
  testing of advertising behavior on devices you are authorized to test.
- Issues in third-party libraries without a demonstrated impact on this app
- Device/vendor BLE restrictions (vendor firmware limitations, not a vulnerability)

## Supported versions

Only the latest release and the `main` branch receive fixes.
