# Economy

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8042cdcb73c04bd09355154a6af1b33e)](https://www.codacy.com/app/TradeWars/Economy?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=TradeWars/Economy&amp;utm_campaign=Badge_Grade)

| master | [![Build Status for master branch](https://travis-ci.org/TradeWars/Economy.svg?branch=master)](https://travis-ci.org/TradeWars/Economy) |
| - | - |
| **staging** | [![Build Status for staging branch](https://travis-ci.org/TradeWars/Economy.svg?branch=staging)](https://travis-ci.org/TradeWars/Economy) |
| **develop** | [![Build Status for develop branch](https://travis-ci.org/TradeWars/Economy.svg?branch=develop)](https://travis-ci.org/TradeWars/Economy) |

## Usage

TODO

How to interface with the service, either via Pawn or, if the service does not
directly communicate with the gamemode, whatever interface is provided.

## Deployment

TODO

## Contributing

[Please read the contribution guidelines](CONTRIBUTING.md)

## Tests

All Kotlin-specific tests reside inside of `src/test/kotlin`.
You can run the tests with the following command:

```bash
./gradlew test
```

If you need an example, take a look at `src/test/kotlin/org/tradewars/economy/ExampleTest.kt`.

### Writing new tests

When writing new tests, those should possibly reside in the same package as the tested classes.

For example:

```
└── src
    ├── main
    │   └── kotlin
    │       └── com
    │           └── example
    │               └── structure
    │                   └── for
    │                       └── unit
    │                           └── tests
    │                               └── NuclearWeapon.kt
    └── test
        └── kotlin
            └── com
                └── example
                    └── structure
                        └── for
                            └── unit
                                └── tests
                                    └── NuclearWeaponTest.kt
```

## Build

In order to build the Economy module as a standalone file, run:

```bash
./gradlew shadowJar
```

This will create the file `/build/libs/Economy.jar`. The `shadowJar` task runs the `test`
task automatically.