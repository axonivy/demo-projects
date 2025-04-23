[![CI-Build](https://github.com/axonivy-market/demo-projects/actions/workflows/ci.yml/badge.svg)](https://github.com/axonivy-market/demo-projects/actions/workflows/ci.yml)

# Demo-Projekte

Dieses Repository enthält mehrere Demos zur Verwendung der Axon Ivy Plattform.

- **Connectivity-demos**: Wie werden REST- oder SOAP-Services erstellt und konsumiert?
- **Error-handling-demos**: Wie können Fehler behandelt werden?
- **Html-dialog-demos**: Wie werden Dialoge erstellt und [PrimeFaces](https://www.primefaces.org)-Widgets verwendet?
- **Rule-engine-demos**: Wie kann man Projekte mit der Rule Engine erstellen?
- **Workflow-demos**: Wie können komplexere Workflow-Prozesse erstellt werden?

- **Quick-start-tutorial**: Schneller Einstieg in unsere Plattform
- **Demos-app**: Vereint alle Demos in einer deploybaren App

## Test

Anforderungen _(Web-Integrationstests)_:

- Firefox  
- Maven _(Terminal)_

Um die Tests auszuführen, importiere ein Projekt in deinen Axon Ivy Designer oder öffne ein Terminal, navigiere zum Projekt und führe es aus:

```console
mvn clean verify

