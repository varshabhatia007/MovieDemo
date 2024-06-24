# Movies App


This Movies Demo App is a sample Android application that allows users to browse and discover movies.

It follows the principles of Clean Code Architecture, utilise the MVVM (Model-View-ViewModel) architectural pattern, and is built using Jetpack Compose.

## Features

- Browse and search movies
- View movie details, including title, overview, release date, and ratings etc.


## Libraries & Technologies Used

- Android Jetpack Compose: Modern UI toolkit for building native Android UI.
- Kotlin Coroutines: For asynchronous and concurrent programming.
- ViewModel: Part of the Android Architecture Components, used to manage UI-related data in a lifecycle-aware manner.
- Retrofit: HTTP client library for making network requests.
- Gson: JSON serialization/deserialization library for parsing API responses.
- Dagger Hilt: Dependency injection framework for Android.
- Coil: Image loading library for displaying movie posters and images.
- Kotlin DSL: Gradle build scripts written in Kotlin.

## Architecture

The app follows the principles of Clean Architecture, which promotes separation of concerns and modular development. The architecture consists of the following layers:

- **Presentation**: Contains the Jetpack Compose UI components, ViewModels, and UI-related logic.
- **Domain**: Contains the business logic and defines the use cases of the application.
- **Framework**: Implements the concrete implementations of the data sources, such as network.
- **Data**: Handles data operations, including fetching data from the network.
- **Dependency Injection**: Uses Dagger Hilt for dependency injection, enabling modular and testable code.

## Setup and Configuration

To run the Movies App on your local machine, follow these steps:

1. Clone the repository: `git clone https://github.com/varshabhatia007/MovieDemo`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## NOTE:
- Add your API key into secrets.properties file, add your perosnal API_KEY = "API_VALUE"

## Development Workflow

The development workflow for the Movies App follows the standard MVVM pattern and Clean Architecture principles. Here's an overview of the workflow:

1. UI components, screens, and layouts are implemented using Jetpack Compose.
2. ViewModel classes are created to hold and manage UI-related data and business logic.
3. Use cases are implemented in the domain layer to handle the app's specific business requirements.
4. Data is fetched from the network using Retrofit and converted to domain models using Moshi.
5. Dependency injection is used to provide necessary dependencies throughout the app.
6. Unit tests are written to ensure the correctness and reliability of the app.

## Testing

The Movies App includes both unit tests to verify the correctness of its components and functionality. Testing is an essential part of the development process to ensure stability and reliability.

- To run the unit tests, use the following command:
  ./gradlew testDebugUnitTest

## Future Enhancement

1. Add movies to the favourites
2. Add new navigation tab where user can see the favourite movies which is selected
3. The favourites movie storage should be in the room database
4. User can delete the the movie from the favourites which also deleted from the database
5. Implement the pagination for the movie data with multiple pages
