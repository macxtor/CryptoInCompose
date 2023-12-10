
<img src="https://github.com/macxtor/CryptoInCompose/blob/main/screenshots/home-screen.png" alt="Architecture Diagram" width="30%" height="30%">

# CryptoInCompose Android App
CryptoInCompose is an Android application that allows users to explore and track cryptocurrency data using Jetpack Compose.

## Overview

CryptoInCompose leverages the power of Jetpack Compose to provide an intuitive and modern user interface for interacting with cryptocurrency information. Users can view a list of trending cryptocurrencies, their prices, and relevant market data.

## Features

- **Trending Crypto List**: Browse a list of trending cryptocurrencies.
- **Detailed Information**: View detailed information about each cryptocurrency, including current price and 24-hour price change percentage.
- **Market Data**: Access market data such as market cap and trading volume.
- **Crypto Details**(Coming Soon)
- **Favourite Crypto**(Coming Soon)
- **Search Crypto**(Coming Soon)
- **Authentication**(Coming Soon)

## Installation

To run CryptoInCompose locally, follow these steps:

```bash
git clone https://github.com/macxtor/CryptoInCompose.git
cd cryptoincompose
./gradlew build
```

## Clean Architecture

CryptoInCompose is designed based on the principles of Clean Architecture. This architectural approach separates the application into distinct layers, each with its own responsibilities:

- **Presentation Layer (ViewModel & View)**: Jetpack Compose components for the UI. In CryptoInCompose, this is where the user interface is defined using modern Compose elements. HomeViewModel is responsible for managing UI-related data and orchestrating interactions between the presentation layer and the domain layer. It contains the business logic and state management.
- **Domain Layer (UseCase and entities)**: UseCases, such as `GetTrendingCryptoUseCase`, encapsulate the business logic, ensuring separation from the presentation layer. Entities represent the core data models used throughout the application.
- **Data Layer (Repository and API)**: The CryptoRepositoryImpl communicates with the CryptoApi to fetch cryptocurrency data. This separation allows for easier testing and modification of data sources.

This architecture promotes maintainability, testability, and flexibility in adapting to future changes.

## Unit Testing

CryptoInCompose follows a robust unit testing approach using the JUnit framework and the MockK library for Kotlin. Unit tests are written to ensure the correctness and reliability of individual components:

- **ViewModel Tests**: The `HomeViewModelTest` class contains tests for the `HomeViewModel`, verifying its behavior under different scenarios.
- **UseCase Tests**: The `GetTrendingCryptoUseCaseTest` class contains tests for the `GetTrendingCryptoUseCase`, verifying its behavior under different scenarios.
- **Mapper Tests**: The `CoinsMapperTest` class contains test for the `Mapper`, to check if appropriate mapping is done from data to domain layer.
- **Repository Tests**: The `CryptoRepositoryImplTest` class tests the `CryptoRepositoryImpl` implementation, ensuring proper handling of API responses and errors.

To run unit tests, use the following command:

```bash
./gradlew test
```

### References:
- [Coin gecko API](https://www.coingecko.com/en/api/documentation)
