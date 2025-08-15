# ftc-robot (FTC Starter Kit 2025–2026)

This repo targets FTC. It provides:

- Drop-in FTC TeamCode for a 4-wheel, 4-motor drivetrain (Starter Kit 3200-4008-2526)
- Circle-driving kinematics utility
- Unit tests for math/util components (runs on Java 17, no Android SDK needed)

See `ftc/README-FTC.md` for instructions to copy the TeamCode into the official FTC SDK and deploy to the robot controller.

## Build & Test (desktop)
```powershell
./gradlew test jacocoTestReport jacocoTestCoverageVerification
```

## Repo safeguards
- Large/binary ignores: see `.gitignore` and `.gitattributes`.
- Pre-commit guard: a hook blocks committing local distributions and files > 25MB.

Enable the hook locally (one time):
```powershell
git config core.hooksPath .githooks
```

Recommended GitHub settings:
- Protect `master` with “Require linear history” and “Require pull request reviews”.
