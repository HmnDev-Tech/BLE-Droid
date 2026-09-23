# Contributing

## Issues

- Use the provided templates. Issues missing reproduction steps, device/Android version, or app version get closed.
- One bug per issue. No duplicates — search first.
- Feature requests must describe a concrete workflow, not a slogan.

## Pull requests

- Fork, branch from `master`, keep PRs focused on one change.
- PR must build: `./gradlew assembleDebug` passes.
- Fill in the PR template: what/why/how tested.
- No reformatting of untouched files, no unrelated refactors, no generated/build output.
- Style: match the surrounding code. Kotlin official style.
- Maintainer reviews before merge. Force-push after review starts is discouraged — add commits instead.

## Commits

- imperative subject, scoped: `fix(radar): classify Apple type 0x0F as action modal`
- no secrets, no keystores, no local paths
