# Currency Converter

A simple Java-based Currency Converter application that allows you to convert amounts between different currencies using live exchange rates from the Exchangerate API.

## Features
- Converts currencies based on the latest exchange rates
- Allows users to input the source and target currencies
- Provides real-time conversion for any given amount
- Error handling for invalid inputs or connection failures

## Requirements
- Java Development Kit (JDK) 21 or higher
- Maven for managing dependencies

## Dependencies
- **OkHttp** (for HTTP requests)
  - `com.squareup.okhttp3:okhttp:4.10.0`
- **JSON** (for parsing API responses)
  - `org.json:json:20220924`

## Usage
1. Clone the repository.
2. Set up your Maven project.
3. Add your API key from the Exchangerate API.
4. Run the program, then input:
   - Source currency (e.g., `USD`).
   - Target currency (e.g., `EUR`).
   - Amount to convert (e.g., `100`).

5. View the conversion result.

## API Setup
- Get your API key from Exchangerate API.
- Replace the placeholder in the code with your API key.
