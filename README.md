Open the project in GitPod: [![Gitpod Ready-to-Code](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2253/gr2253/-/tree/master/)]

# Personal Finance Application

 * For project description and use see [personal_finance/README.md](personal_finance/README.md)
 * Documentation for each release and other documentation is found in the [docs folder](docs/README.md)
 * For prepackaged releases see [releases](releases/README.md)


 ## Building and running

 The project is built using Maven. 

 ***Developer installation***
 1. Clone project locally or open it in gitpod
 2. Open folder as Maven project in your IDE and cd in to personal_fionance
 3. Install modules by running 'mvn install -DskipTests'
 4. Run 'mvn compile'
 5. Start rest-server with 'mvn spring-boot:run -f rest/pom.xml'
 6. In a different terminal run tests to see that everything works with 'mvn verify'
 7. In that same terminal run 'mvn -Premoteapp -pl fxui javafx:run'
 8. Now the app should appear on your screen. Database (JSON) can be viewed in the web browser by going to http://localhost:8080/personal_finance/
