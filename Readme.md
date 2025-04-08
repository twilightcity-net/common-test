# Common Test Library

A comprehensive testing support library for Java and Groovy applications, providing utilities for test data generation and object comparison. This library is a core component of the Twilight City ecosystem, embodying the testing best practices and patterns promoted by the Twilight City community.

## Overview

The Common Test Library is designed to simplify and standardize testing practices across Java and Groovy applications within the Twilight City ecosystem. It provides two main components:

1. **RandomGenerator**: A powerful utility for generating random test data of various types
2. **BeanCompare**: A flexible utility for comparing Java/Groovy objects and collections

## Features

### RandomGenerator
- Generates random test data for common types:
  - UUIDs, IDs, and numbers
  - Dates and timestamps (LocalDate, LocalDateTime, ZonedDateTime)
  - Text and Lorem Ipsum content
  - Business data (names, addresses, phone numbers, emails)
  - Collections and random selections
- Supports optional/nullable values
- Configurable with seed values for reproducible tests
- Thread-safe implementation
- Ideal for creating test fixtures with realistic but random data

### BeanCompare
- Deep comparison of Java/Groovy objects
- Flexible field inclusion/exclusion
- Support for comparing collections
- Integration with Spock's power assertions
- Detailed failure reporting

## Requirements

- Java 11 or higher
- Gradle 6.x or higher
- Maven Central repository access

## Installation

Add the following dependency to your project:

```gradle
dependencies {
    testImplementation 'net.twilightcity:common-test:1.0'
}
```

## Usage Examples

### Random Data Generation for Test Fixtures

The RandomGenerator is particularly useful for creating test fixtures with realistic but random data. Here's an example of creating a test fixture for a user profile:

```groovy
class UserProfile {
    String id
    String firstName
    String lastName
    String email
    String phoneNumber
    LocalDate dateOfBirth
    String address
    String city
    String state
    String zipCode
}

class UserProfileFixture {
    private static RandomGenerator generator = new RandomGenerator()
    
    static UserProfile createValidProfile() {
        return new UserProfile(
            id: generator.uuidString(),
            firstName: generator.firstName(),
            lastName: generator.lastName(),
            email: generator.email(),
            phoneNumber: generator.phoneNumber(),
            dateOfBirth: generator.dateInPastDays(365 * 18), // Must be at least 18 years old
            address: generator.streetAddress(),
            city: generator.city(),
            state: generator.state(),
            zipCode: generator.zipCode()
        )
    }
    
    static UserProfile createProfileWithOptionalFields() {
        return new UserProfile(
            id: generator.uuidString(),
            firstName: generator.firstName(),
            lastName: generator.lastName(),
            email: generator.email(),
            phoneNumber: generator.optionalPhoneNumber(), // May be null
            dateOfBirth: generator.dateInPastDays(365 * 18),
            address: generator.streetAddress(),
            city: generator.city(),
            state: generator.state(),
            zipCode: generator.zipCode()
        )
    }
}

// Usage in tests
def "should validate user profile"() {
    given:
    def validProfile = UserProfileFixture.createValidProfile()
    
    when:
    def result = profileValidator.validate(validProfile)
    
    then:
    result.isValid()
}

def "should handle missing phone number"() {
    given:
    def profile = UserProfileFixture.createProfileWithOptionalFields()
    
    when:
    def result = profileValidator.validate(profile)
    
    then:
    result.isValid()
}
```

### Object Comparison

```groovy
def beanCompare = new BeanCompare()

// Compare objects
beanCompare.assertEquals(expectedObject, actualObject)

// Compare with field exclusions
beanCompare.excludeFields("id", "createdDate")
    .assertEquals(expectedObject, actualObject)

// Compare collections
beanCompare.assertEquals(expectedList, actualList)
```

## Building from Source

1. Clone the repository:
```bash
git clone https://github.com/twilightcity/common-test.git
cd common-test
```

2. Build the project:
```bash
./gradlew build
```

3. Install to local Maven repository:
```bash
./gradlew publishToMavenLocal
```

## Contributing

We welcome contributions to the Common Test Library! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details on how to get started, coding standards, and the pull request process.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
