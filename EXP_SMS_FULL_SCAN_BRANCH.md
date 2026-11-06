# SMS Full scan branch experiment

Branch: `exp-sms-full-scan-branch`

Purpose: test whether setting Admin SMS **Full scan branch** to this branch creates a separate Semgrep project.

Expected: scan runs on this ref for the **same** project `DemoTestingApril/javaspringvulny` — does **not** create a new project by itself.
