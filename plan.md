# CSS Icon Class Replacement — Executor Agent Task

## Objective
The agent should replace icon CSS classes in the codebase using the provided icon inventory and mapping. The agent should perform a one-time replacement only within the approved root folders. The result of the replacement must be documented in a report which details all found files where a term was found, the term found and whether or not they were replaced with a term from the mapping.

## Inputs
- `all_old_icons.csv`
  - Contains all known old icon CSS classes, one per line.
- `mapping_si.csv`, `mapping_fa.csv`
  - Contain the mapping from old icon classes to new icon classes.
  - Each key should be an old icon CSS class and each value should be the corresponding new icon CSS class.
- `ROOT_FOLDERS`
  - The agent should only search and modify files inside these folders:
    - connectivity/
    - database/
    - demos-app/
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

### 0. Create a feature branch (optional)
The agent should check whether the root folder is a git project by checking for the existence of a `.git` folder. If the root folder is a git project, the agent should try to create a dedicated branch for this work before making any changes.
If the agent fails for any reason, it should report the failure and skip this step.

### 1. Prepare the old icon classes and the mapppings
The agent should read the `all_old_icons.csv` file to create a list of old icon CSS classes. Each entry starts either with 'fa ' or 'si '. For each entry, strip the leading three character prefix (for example: 'si si-add-circle' becomes 'si-add-circle'). Add the original and the stripped version to the list of old icon classes. This will allow the agent to find matches in the codebase regardless of whether they are prefixed with 'fa ' or 'si '. For example, the entry 'si si-add-circle' should be saved as two entries 'si si-add-circle' and 'si-add-circle' in the new list.
Save this combined list to a new file `all_old_icons_stripped.csv`. This file should contain all old icon classes with and without the prefixes.

Do the same for the mapping files `mapping_si.csv` and `mapping_fa.csv`. Each entry consists of an old icon class and a new icon class. Each new icon class starts with the prefix 'ti '. For each entry old_icon,new_icon strip the prefixes (for example: 'fa fa-anchor,ti ti-anchor' becomes 'fa-anchor,ti-anchor'). Add the original mappinig (with the prefix) and the stripped version to the mapping dictionary where the key is the stripped old icon class and the value is the stripped new icon class. Combine the mappings from both files into a single mapping dictionary and save it to a new file `mapping_stripped.csv`. For example, the entry 'fa fa-anchor,ti ti-anchor' should be saved as two entries 'fa fa-anchor,ti ti-anchor' and 'fa-anchor,ti-anchor' in the new mapping file. This will allow the agent to find mappings for old icon classes in the codebase regardless of whether they are prefixed with 'fa ' or 'si '.
This file should contain all old icon classes with and without the prefixes from both mapping files.

### 2. Search for old icon classes
The agent should find all exact matches of the old icon classes listed in `all_old_icons_stripped.csv` within files under `ROOT_FOLDERS`.

The agent should include relevant file types such as:
- source files
- templates
- styles
- markup
- generated files
- configuration files

The agent should not search or modify anything under `EXCLUDED_FOLDERS`.

### 3. Collect and group matches
The agent should group all matches by file and record every occurrence of each old icon class.

### 4. Resolve replacements
For each found old icon class, the agent should:
- check whether a replacement exists in `mapping_stripped.csv`
- if a mapping exists, record the old → new replacement pair
- if no mapping exists, record that no replacement is available

### 5. Report findings and replacements
Before replacing anything, the agent should create a CSV report covering all found old icon classes, including:
- file path
- line number of found old icon within file
- old icon class
- if mapping was found the new icon, empty otherwise
The report should not contain old icons that were not found in the project in order to keep the report readable.

### 6. Execute replacements
The agent should replace each found old icon class with its mapped new icon class where a mapping exists.

The agent should preserve:
- surrounding formatting
- whitespace
- other class names in the same string or attribute

The agent should not alter unrelated or similar strings.

### 7. Verify the result
The agent should re-scan the workspace to confirm:
- all mapped old icon classes have been removed
- the new icon classes appear in the expected locations
- no files under `EXCLUDED_FOLDERS` were changed

## Acceptance Criteria
- The agent should replace all old icon classes with a mapping.
- The agent should report any old icon classes without a mapping and leave them unchanged.
- The agent should not modify any files under `EXCLUDED_FOLDERS`.
- The agent’s final report should clearly list all matches and actions taken.
