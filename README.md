# Shopify_Project
Simple CRUD application

https://user-images.githubusercontent.com/30647012/150270176-e4d7f866-a6f2-425c-bdb6-79f381027f2a.mp4


This is a spring project(maven) with amazon dynamoDB as storage, HTML as front-end and java spring boot as backend.

Steps to run this project:
  1. [Spring.io](https://start.spring.io/) to get basic spring boilerplate code with configuration as below;
    1. Project: Maven Project
    2. Language: Java
    3. Spring Boot: 2.6.2
    4. Package name (Under Project Metadata): com.app
    5. Packaging (Under Project Metadata): Jar
    6. Java version: 8
    7. Hit generate
   2. Download and install IntelliJ IDEA (Community or Enterprise) from [IntelliJ Download](https://www.jetbrains.com/idea/download/#section=mac) and follow the installation guide
   3. Load the downloaded package from step 1 on IntelliJ
   4. Download the main package from this GitHub (Downloads as a zip file) and extract it.
   5. Copy the contents of the main folder(of downloaded Github project) into the main folder of the project in IntelliJ.
   6. Copy the content of pom.xml (Form GitHub) to pom.xml of your project(one in IntelliJ)
   7. Add your aws scretkey and userId to application properties file
   8. Build the project (maven build) to download all dependencies
   9. Run the project (CrudApplication.java)
  
  Now open the Chrome browser and load http://localhost:8082/viewAll which is the home page of this application.
  Method to use the project is in the demo video
