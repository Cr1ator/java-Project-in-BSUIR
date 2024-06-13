# Object-Oriented Design and Programming

## Laboratory Practice

This project is a culmination of 8 laboratory exercises designed to illustrate and practice modern object-oriented design and programming techniques. Each lab focuses on different aspects of software development, employing patterns such as Abstract Factory, Factory Method, and Decorator.

### Key Features:
- **Abstract Factory and Factory Method Patterns**: Implemented to create various types of motorcycle ammunition.
- **Decorator Pattern**: Used to dynamically add discounts to the ammunition without modifying the original classes.
- **Multithreading**: Applied for reading and writing data to JSON files, ensuring efficient data management.
- **Custom Serialization and Deserialization**: Implemented for JSON operations using standard Java libraries, supporting UTF-8 encoding for correct handling of Russian characters.
- **Data Management**: Includes sorting, filtering, and grouping functionalities for the ammunition data.

### Project Structure:
- **Factories**: Contains factory classes for creating different types of ammunition.
- **Decorators**: Contains decorator classes for adding additional features to ammunition.
- **Models**: Contains the core classes representing different types of ammunition and the motorcyclist.
- **Tasks**: Contains classes for handling file read/write operations in a multithreaded manner.
- **Utils**: Contains utility classes for file operations.

### How to Use:
1. **Add Ammunition**: Choose the type and input the price and weight.
2. **View Total Cost**: Display the total cost of all added ammunition.
3. **Sort Ammunition**: Sort by weight or price.
4. **Filter Ammunition**: Find items within a specific price range.
5. **Group Ammunition**: Group items by type.
6. **Save/Load Data**: Save current data to a JSON file or load from an existing file.
7. **Apply Discounts**: Add a discount to all items using the decorator pattern.

### Completion:
All laboratories were completed within a total time of 90 minutes, showcasing efficient and practical use of object-oriented design principles.

### Requirements:
- Java 8 or higher

### Running the Project:
1. Clone the repository.
2. Open the project in your preferred IDE.
3. Run the `Main` class located in the `MotorcycleApp` package.

### Contributions:
Contributions are welcome! Feel free to submit pull requests or open issues to improve the project.

### License:
This project is licensed under the MIT License.
