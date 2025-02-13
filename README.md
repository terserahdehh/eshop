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