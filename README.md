# WPILib Java Robot Project

This is a minimal WPILib Java project scaffold for an FRC-style robot using GradleRIO.

## Requirements
- JDK 17+
- Gradle (if you don't have the Gradle Wrapper files in this repo)
- WPILib dependencies are resolved from the WPILib Maven repo

## Configure
- Edit `.wpilib/wpilib_preferences.json` and set your real `teamNumber`.

## Build & Test
```powershell
# From the project root
gradle build
```

## Run Desktop Simulation (optional)
```powershell
gradle simulateJava
```

If you prefer using the WPILib VS Code extension or the WPILib installer, you can also create and manage projects from there.

## Repo safeguards
- Large/binary ignores: see `.gitignore` and `.gitattributes`.
- Pre-commit guard: a hook blocks committing local distributions, sim GUI files, and files > 25MB.

Enable the hook locally (one time):
```powershell
# From the repo root
git config core.hooksPath .githooks
```

Recommended GitHub settings:
- Protect `master` with “Require linear history” and “Require pull request reviews”.
- Optionally enable Git LFS for legitimate large assets.
