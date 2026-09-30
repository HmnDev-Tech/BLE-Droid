## What changed

<!-- Files + behavior. One sentence per logical change. -->

-

## Why

<!-- Link the issue: fixes #N. No issue? Explain the concrete trigger. -->

## How was it tested

<!-- Exact commands run (e.g. ./gradlew assembleDebug) and manual steps on a device.
     "It builds" alone is not enough for behavior changes. -->

-

## Scope

<!-- Keep this honest. Unrelated refactors go in their own PR. -->

- [ ] Diff touches only files this change requires
- [ ] No reformatting of untouched files, no renamed symbols outside the change
- [ ] No new dependency, flag, or abstraction that nothing in this PR uses

## Quality

- [ ] `./gradlew assembleDebug` passes locally
- [ ] Behavior change covered in README/Wiki if user-facing
- [ ] New/changed logic has a test, or the PR explains why a test is not feasible
- [ ] No secrets, keystores, personal paths, `local.properties`, build output in the diff
- [ ] Commit messages follow `type(scope): subject` (see `.github/CONTRIBUTING.md`)

## Review notes

<!-- Risky parts, follow-ups you deliberately did not do, anything a reviewer must look at. -->

-
