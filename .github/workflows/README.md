# Semgrep PR Labeler

Automatically labels a pull request when the Semgrep bot posts a comment on it. Useful for filtering PRs that have outstanding Semgrep findings.

## How it works

The workflow listens for `issue_comment` events (GitHub fires these for PR comments too). When a comment is created, it checks the author's login against a list of Semgrep bot accounts. If it matches, it adds a label to the PR using `GITHUB_TOKEN`.

## Deploy

1. **Create the label** in the repository (Settings → Labels, or via UI on any issue/PR). Default name is `semgrep-findings`.
2. **Copy** `semgrep-label.yml` into `.github/workflows/` in the target repo.
3. **Commit and push** to the default branch. The workflow activates immediately.

That's it — no secrets to configure. `GITHUB_TOKEN` is provided automatically.

## Configuration

Edit the `env:` block at the top of `semgrep-label.yml`:

| Variable | Default | Purpose |
|---|---|---|
| `LABEL_NAME` | `semgrep-findings` | Label applied to the PR. |
| `SEMGREP_BOT_PATTERN` | `semgrep*` | Case-insensitive glob matched against the comment author's login. |

The Semgrep GitHub App posts under accounts like `semgrep-app[bot]` or per-deployment names such as `semgrep-code1-acme[bot]`. The default `semgrep*` glob covers both. To restrict to one account, set `SEMGREP_BOT_PATTERN` to the exact login.

## Permissions

The workflow declares the minimum required:

```yaml
permissions:
  pull-requests: write
  issues: write
```

If the repo has restrictive default `GITHUB_TOKEN` permissions (Settings → Actions → General → Workflow permissions), make sure "Read and write permissions" is enabled, or that workflow-level permissions are honored.

## Verify

1. Open a test PR.
2. Have Semgrep scan it (or manually post a comment from the Semgrep bot).
3. Confirm the `semgrep-findings` label appears on the PR.

Workflow run logs are in the **Actions** tab under "Label PRs with Semgrep findings".
