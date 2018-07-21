# Contributing To This Project

When contributing to this repository, please first discuss the change you wish to make via issue,
email, or any other method with the owners of this repository before making a change. 

## Style

See the [Style Guide](Style-Guide.md) for details on the standards that
contributions should adhere to.

## General

1. All commits need to be GPG signed in order to accept them into the official `develop` branch
2. Ensure that you don't commit directly to `staging` or `master`.

## Pull Request Process

1. Explain in what way your changes will effect behaviour of the application and usage of the API

## Branches

If you have push access to the repository, this section is very important.

This project makes use of
[**Git Flow**](https://nvie.com/posts/a-successful-git-branching-model/) as a
model for managing branches.

You should read the above link for more information. In short:

- `master` branch only contains working code that will go live
- `staging` stage before `master`, to check if everything will work
- `hotfix` branches fix minor problems in `master`
- `develop` branch contains latest experimental code
- `feature-*` branches implement and test new features
