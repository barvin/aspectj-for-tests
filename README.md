# AspectJ application in tests
This is a simple example of how to use AspectJ to help you with logging the actions of your tests. The huge benefit here is that you can apply the aspects no matter how old is your solution is, as long as it has some structure (reasonable division by packages/classes).
## Run the app under test
In order to run the Reminders application, make sure you have at least JDK 8 installed, then run the following command:
```
cd reminders-app
gradlew bootRun --args '--server.port=8000'
```
## Run the tests
Simply run the following command (make sure you have Google Chrome installed):
```
cd automated-tests
gradlew test
```
You will see a lot of logs on different levels in the console output, while there are no explicit log methods calls in the tests at all.