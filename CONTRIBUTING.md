# Contributing to Common Test Library

Thank you for your interest in contributing to the Common Test Library! This document provides guidelines and instructions for contributing to the project.

## Code of Conduct

By participating in this project, you agree to abide by our [Code of Conduct](CODE_OF_CONDUCT.md). Please report any violations to conduct@twilightcity.net.

## Getting Started

1. Fork the repository
2. Clone your fork: `git clone https://github.com/your-username/common-test.git`
3. Create a new branch: `git checkout -b feature/your-feature-name`
4. Make your changes
5. Push to your fork: `git push origin feature/your-feature-name`
6. Create a Pull Request

## Development Environment Setup

1. Ensure you have Java 11 or higher installed
2. Install Gradle 6.x or higher
3. Run `./gradlew build` to verify your setup
4. Install the project locally: `./gradlew publishToMavenLocal`

## Coding Standards

### General Guidelines

- Follow the existing code style and patterns
- Write clear, concise, and maintainable code
- Include appropriate documentation
- Ensure all tests pass
- Keep commits focused and self-contained

### Testing Requirements

- All new features must include appropriate tests
- Use the existing test utilities (RandomGenerator, BeanCompare) where applicable
- Follow the project's testing patterns and best practices
- Maintain or improve test coverage

### Documentation

- Update relevant documentation when adding new features
- Include clear examples of usage
- Document any breaking changes
- Keep the README.md up to date

## Pull Request Process

1. Ensure your code builds successfully
2. Run all tests: `./gradlew test`
3. Update documentation as needed
4. Submit your PR with a clear description of changes
5. Address any feedback or requested changes

## Release Process

1. Update version numbers in `gradle.properties`
2. Update CHANGELOG.md with changes
3. Create a release tag
4. Build and publish the release

## Questions?

If you have any questions about contributing, please:
- Open an issue
- Join our community chat
- Contact the maintainers

Thank you for contributing to the Twilight City ecosystem! 