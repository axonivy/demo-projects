[![CI-Build](https://github.com/axonivy-market/demo-projects/actions/workflows/ci.yml/badge.svg)](https://github.com/axonivy-market/demo-projects/actions/workflows/ci.yml)

# Demo Projects

This repository contains multiple demos how to use the Axon Ivy platform.

- **connectivity-demos**: How to build and consume Rest or Soap services
- **error-handling-demos**: How to handle errors
- **html-dialog-demos**: How to build dialogs and use [PrimeFaces](https://www.primefaces.org) widgets
- **workflow-demos**: How to build complexer workflow processes

- **quick-start-tutorial**: Quick jump in our platform
- **demos-app**: Collects all Demos to one deployable app

## Test

Requirements _(web integration tests)_:

- Firefox
- Maven _(terminal)_

To run the tests import a project into your Axon Ivy Designer or open a terminal,
navigate to a project and run:

```console
mvn clean verify
```

If you want to know more about testing checkout our documentation chapter: [Testing](https://developer.axonivy.com/doc/dev/concepts/testing.html)
