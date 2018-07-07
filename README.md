# TW Service Template

## Overview

All services should start by cloning this template as a starting point.

### Pawn Code

If services have a Pawn interface, you should keep that code in this repository
for easy testing. This means the repository should also be a valid
[Pawn Package](https://github.com/Southclaws/sampctl/wiki/Packages) with a
`pawn.json` and a `test.pwn` for unit tests.

Pawn unit tests do not need to be exhaustive if the application itself provides
unit tests in the testing framework specific to the language it is written in.
Pawn tests can just be an ephemeral demonstration that the interface itself
works as intended.

### Versioning

Semantic versioning is not used on these projects because of the evolving nature
of their codebases. APIs should be set in stone and if changes are necessary, a
discussions should be opened in the
[Gamemode](https://github.com/TradeWars/gamemode) repository.

Version numbers are in the format `18w26.7.12` - or:

- Two digit year
- Week Number
- Day of Week (starting from 1)
- Hour of Day

This can be generated with the `date` command:

```bash
date -u +%yw%W.%u.%H
```

### `docs/Contributing.md`

Please fill out the end of this file with contributing guidelines specific to
the language that the service is written in.

### `docs/Style-Guide.md`

This should either outline or link to a style guide specific to the language the
service is written in.

### README.md

You should also keep a well written readme file following the
[Markdown Lint Rules](https://github.com/DavidAnson/markdownlint/blob/master/doc/Rules.md).
For example, start the document with a H1 heading: `# Service Name`

Below is the rest of the H2 sections that a readme should contain.

---

## Usage

How to interface with the service, either via Pawn or, if the service does not
directly communicate with the gamemode, whatever interface is provided.

## Deployment

Deployment should be done via Docker however there should also be instructions
on how to deploy a development instance for testing and integration with other
services.

## Tests

Outline how the program is tested, how to run the tests and what they cover.

If there are Pawn tests, also note that `sampctl package run` can be used.
