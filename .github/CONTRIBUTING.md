# Contributing

Read this before opening an issue or PR. Templates under `.github/ISSUE_TEMPLATE/`
and `.github/PULL_REQUEST_TEMPLATE.md` are enforced, not suggestions.

## Where things go

| Type | Destination |
|---|---|
| Reproducible bug | Issue, Bug report template |
| Concrete feature with a workflow | Issue, Feature request template |
| "How do I...", setup, usage | [Discussions → Q&A](https://github.com/HmnDev-Tech/BLE-Droid/discussions/categories/q-a) |
| Rough idea, not yet a request | [Discussions → Ideas](https://github.com/HmnDev-Tech/BLE-Droid/discussions/categories/ideas) |
| Praise, setups, screenshots | [Discussions → Show and tell](https://github.com/HmnDev-Tech/BLE-Droid/discussions/categories/show-and-tell) |
| Docs about building/using | Wiki |

## Issues

- Search first. Duplicates get closed with a link to the original.
- One bug per issue. Bundled reports get split by the maintainer.
- Required fields are required: reproduction steps, device, Android version, app version.
  Issues missing them get closed after one request for information.
- Security reports: see [SECURITY.md](../SECURITY.md). Do not open a public issue for
  vulnerabilities in the signing, CI secrets, or permission handling.
- Be precise. "Doesn't work" gets one clarifying question, then closure if unanswered.

## Pull requests

- Fork, branch from `main`, one focused change per PR.
- PR must build: `./gradlew assembleDebug` passes locally.
- Fill the PR template honestly; unchecked boxes without a reason block the review.
- No reformatting of untouched files, no unrelated refactors, no generated/build output.
- Style: match the surrounding code. Kotlin official style (ktlint defaults),
  Compose: previews not required, but no hardcoded strings in new UI — add to `strings.xml`.
- Public API changes (routes, settings keys, intent actions, file formats) must mention
  migration impact in the PR body.
- Maintainer reviews before merge. Force-push after review starts is discouraged —
  add commits instead.
- CI (`.github/workflows/build-apk.yml`) must be green: it builds both APKs and
  verifies their signatures match.

## Commits

- Imperative subject, scoped: `fix(radar): classify Apple type 0x0F as action modal`
- Types: `feat`, `fix`, `docs`, `refactor`, `test`, `ci`, `chore`
- Body explains why, when the why is not obvious from the subject.
- No secrets, no keystores, no personal paths, no `*.jks`, no `local.properties`.

## Releases and signing

- Release and debug APKs are signed with the **same** keystore so both can be
  installed and updated over each other on a device where the app IDs match expectations.
- The keystore lives only in CI secrets (`KEYSTORE_B64`) — never in the repo.
- Local signed builds: set `KEYSTORE_PATH`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`,
  `KEY_PASSWORD` before running Gradle.

## Legal

Authorized testing and research only. PRs that add capabilities intended solely to
bypass device security or permissions will be rejected.
