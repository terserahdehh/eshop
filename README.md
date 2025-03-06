<Details>
<Summary>Assignment 1</Summary>

**Reflection 1**

I organized my code into 4 different parts which are controller, service, model, and repository so that each part has its own job. 
The controller handles web requests, the service does the main work, the model is used to represent and define the structure of the data, and the repository manages data. 
I used clear method names like  `findAll` and `delete` which makes the code easy to understand.
Something that I could improve is, adding input validation. I didn’t add checks for valid product data when editing or creating items, so adding input validation would help prevent issues.

**Reflection 2**
1. After writing my unit tests, I feel like I understand my code better. 
The number of tests you need depends on your code; I had to create enough tests to cover all important cases and edge cases, 
rather than aiming for a specific number. 
Tools that measure code coverage shows which parts of the code are being tested, but even with 100% coverage, 
there might still be bugs because some issues only appear in rare or unexpected situations.

2. Having a new test suite with similar setup code and instance variables can lead to repeated code, which isn't clean. 
When you copy and paste the same setup in many test classes, it becomes harder to maintain and update later because you have to change it in several places. 
A better approach is to extract the common setup code into a base class or utility methods that every test class can use. 
This makes the code cleaner, easier to maintain, and reduces the chance of errors.

</Details>

<Details>
<Summary>Assignment 2</Summary>

**Reflection**

1. I fixed some code quality issues like removing unnecessary exception declarations, adding comments for empty methods, avoiding hardcoded version numbers, switching to constructor injection, and getting rid of deprecated annotations. 
Some of the strategies I used were making sure the tests still worked after removing the extra throws declarations, checking empty methods and explaining why they were left empty, and etc.

2. I think the current implementation meets the definition of Continuous Integration and Continuous Deployment. 
The workflows automatically run tests, security checks, and code analysis on every branch push and pull request, which helps catch issues early. 
Also, changes are automatically deployed to production when pushed to the main branch, ensuring that our app is always up to date and any bugs are quickly fixed.

</Details>

<Details>
<Summary>Assignment 3</Summary>

Reflection

1. - Single Responsibility Principle (SRP):
    I separated the request handling into controllers, business logic into services, and data access into repositories which makes the code more maintainable.

    - Open/Closed Principle (OCP):
    I designed the code so it’s easy to add new features without altering existing classes too much. 

    - Liskov Substitution Principle (LSP):
    I made sure my services depend on repository interfaces, which allows different implementations to be used without breaking existing functionality. 

    - Dependency Inversion Principle (DIP):
    I made my high-level modules (services) depend on abstractions (interfaces) instead of concrete implementations so that the application is flexible and easy to adapt whenever I need to change the underlying data storage mechanism.
   
      
2. - Single Responsibility Principle (SRP):  Each class has one clear responsibility, making the code easier to understand, maintain, and modify.
    Example: Controllers (e.g., ProductController), Services (e.g., ProductServiceImpl and CarServiceImpl), as well as Repositories (e.g., ProductRepository and CarRepository)

   - Open/Closed Principle (OCP): Can add new features by extending existing classes or interfaces instead of changing the original code.
   Example: Service interfaces (ProductService, CarService) and Repository interfaces (IProductRepository, ICarRepository) allows implementing new features or swap out implementations without altering the dependent classes.
   
   - Liskov Substitution Principle (LSP): Adding new features without altering the existing code.
   Example: Any repository implementing IProductRepository can be swapped without affecting ProductServiceImpl, ensuring seamless integration as long as the new repository follows the interface contract.
   
   - Dependency Inversion Principle (DIP): Controllers are designed to depend on service interfaces rather than on specific implementations, ensuring that any changes in the service layer do not directly impact the controllers.
   Example: In ProductServiceImpl, the use of the IProductRepository abstraction allows you to swap out the repository with mocks or other implementations as needed. Likewise, controllers rely on service interfaces rather than concrete classes, which enhances both modularity and testability.


3. Not applying SOLID principles, including the Single Responsibility Principle, makes the code more difficult to modify and test. For example, if ProductServiceImpl uses a concrete ProductRepository instead of the IProductRepository interface, any change in the repository might break the service and complicate testing. 
Likewise, if controllers depend on concrete services rather than interfaces, even minor updates in the service layer may force changes in the controllers, resulting in a less flexible and more error-prone system.
</Details>

<Details>
<Summary>Assignment 4</Summary>

Reflection

1. I found the TDD flow quite useful because it made me think about test cases before writing code, which helped catch issues early. 
However, I noticed I could improve by adding more negative and edge case tests to cover scenarios that I might have missed initially.
2. I believe my tests mostly followed the F.I.R.S.T. principle because they run quickly, don't rely on each other, give the same results every time, and tell me when something's wrong. 
However, I noticed some tests could be more isolated. Next time, I'll work on ensuring that each test strictly checks one functionality without relying on shared state.

</Details>

