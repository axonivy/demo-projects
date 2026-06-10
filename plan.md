# CSS Icon Class Replacement — Executor Agent Task

## Objective
The agent should replace icon CSS classes in the codebase using the provided icon inventory and mapping. The agent should perform a one-time replacement only within the approved root folders. The result of the replacement must be documented in a report which details all found files where a term was found, the term found and whether or not they were replaced with a term from the mapping.

## Inputs
- `all_old_icons.csv`
  - Contains all known old icon CSS classes, one per line.
- `mapping_si.csv`, `mapping_fa.csv`
  - Contain the mapping from old icon classes to new icon classes.
  - The agent should combine them into a full list of all available mappings.
  - Each key should be an old icon CSS class and each value should be the corresponding new icon CSS class.
- `ROOT_FOLDERS`
  - The agent should only search and modify files inside these folders:
    - connectivity/
    - database/
    - demos-apps/
    - error-handling/
    - html-dialog/
    - quick-start-tutorial/
    - workflow/
- `EXCLUDED_FOLDERS`
  - The agent should not search or modify files inside these folders in any case:
    - target/
    - src_generated/

## Tooling Constraints
The agent should attempt to use helpful command-line tools and local utilities such as `python`, `python3`, `rg`, `grep`, and `sed` when available. The agent should verify that each tool exists before using it. If a tool is unavailable, the agent should not assume it can be installed and should instead proceed using the remaining available tools or a tool-free approach.

## Execution Steps

### 1. Search for old icon classes
The agent should find all exact matches of the old icon classes listed in `all_old_icons.csv` within files under `ROOT_FOLDERS`.

The agent should include relevant file types such as:
- source files
- templates
- styles
- markup
- generated files
- configuration files

The agent should not search or modify anything under `EXCLUDED_FOLDERS`.

### 2. Collect and group matches
The agent should group all matches by file and record every occurrence of each old icon class.

### 3. Resolve replacements
For each found old icon class, the agent should:
- check whether a replacement exists in `mapping_si.csv` or `mapping_fa.csv`
- if a mapping exists, record the old → new replacement pair
- if no mapping exists, record that no replacement is available

### 4. Report findings and replacements
Before replacing anything, the agent should create a CSV report covering all found old icon classes, including:
- file path
- line number of found old icon within file
- old icon class
- if mapping was found the new icon, empty otherwise
The report should not contain old icons that were not found in the project in order to keep the report readable.

### 5. Execute replacements
The agent should replace each found old icon class with its mapped new icon class where a mapping exists.

The agent should preserve:
- surrounding formatting
- whitespace
- other class names in the same string or attribute

The agent should not alter unrelated or similar strings.

### 6. Verify the result
The agent should re-scan the workspace to confirm:
- all mapped old icon classes have been removed
- the new icon classes appear in the expected locations
- no files under `EXCLUDED_FOLDERS` were changed

## Acceptance Criteria
- The agent should replace all old icon classes with a mapping.
- The agent should report any old icon classes without a mapping and leave them unchanged.
- The agent should not modify any files under `EXCLUDED_FOLDERS`.
- The agent’s final report should clearly list all matches and actions taken.
