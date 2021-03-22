# Kitchen-Service-Scheduler 🍽️
We would like to thank you for taking time and participate in our coding challenge, we named it **Kitchen Service Plan**.

## The Story
We have a kitchen in our office and we need a maintainer to check if it is clean regularly, meaning an employee per day.
For that we would like to have a **Kitchen Service Plan**. If an employee starts at Verimi, one will be added to this 
plan. If one leaves the company, the one should be removed from the plan. 
The order of cleaning employees can be random.
Every Monday the plan for the current week will be printed and displayed in the kitchen.

## What we have
The source data implements the backend for the service as described above. Please check the included postman collection.
It includes the functionalities to
- Add and remove employees
- List all employees
- Get a plan for the current week

The backend is implemented as spring boot application with a file based persistence (H2). It can be started with
```shell
mvn spring-boot:run
```
The application will then be listening to <http://localhost:8080>


## Your tasks

### Introduction
This test consists of 3 different tasks, while you have 3 different options for the second task. Please choose one 
option and let us know, why you picked that.
We would be happy if you can provide any changes of the code as **git patch**, so we can easily see, what you changed.

So we suggest to start with
```shell
git init
git add .
git commit -m "initial commit" 
git checkout -b "your-branch"
```
And after you finished your work create the patch with
```shell
git format-patch master --stdout > kitchenservice-<yourname>.patch
```

All non-code changes are then good as plain text in an email.

### 1: Choose a week
Instead of the current week, it has to be possible to get the kitchen plan for any week. Input should be any date, as 
long as it is less than 6 weeks in the past or future.

### 2 - Option a: Weekly email
Every Monday at 06:00 AM, an email with a PDF attachment should be sent to a configurable email address. 
The PFD contains the plan for the week which just started. The sending functionality has to be
implemented as feature of the backend. For sending, please use [Mailjet] (https://www.mailjet.com/). You can create a 
free account for this and delete it afterwards.

### 2 - Option b: Create a web interface
Instead of using postman, a web interface should show up on <http://localhost:8080>. On this interface a date can be 
picked and as result a printable plan for the corresponding week is provided. Using a default template you find online
is fine, as long as it looks like "state-of-the-art" and is compliant for mobile devices and desktop screens.

### 2 - Option c: Consider vacation days
It should be possible to tell the system, on which day(s) a member of the staff is not available. For those days, the 
colleague cannot be considered for kitchen service

### 3: Closing task
Now that you know the software, please tell us the three things which you think should be refactored. Also tell us, why 
you picked this and how it should be changed. This can be in regards of the code structure, the functionality or non
function requirements, like security.

### 3: Answer

1. Code refactoring would be better as it simplifies the API source code understanding
2. Remove exception stacktrace and make sure response contains a message. Stacktrace expose internal 
classes and dependencies in some cases which people identify security breaches.
3. Consider adding API documentation using Swagger or at least write all the available endpoints
and how to use them in the README.md file.

## Keep in Mind

Try to design and implement **your changes** as you would do for real production code. If you feel like improving the 
original source, that is appreciated, but not expected. Show us how **you** create clean, maintainable code that does 
awesome stuff. Build something that we'd be happy to contribute to. 



