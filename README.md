<!-- ABOUT THE PROJECT -->

## Hacker News Project

The official [Hacker News API][hacker-news-url] was used for the project.

Project contains:

* Main page with the latest 100 hacker news.
* After click on table row return information about specific news.
* After click on comments with icon open show other comments in tree.

### Built With

* [![Java][Java]][java-url]
* [![Spring Boot][Spring_Boot]][spring-url]
* [![JavaScript][JS]][js-url]
* [![React][React.js]][React-url]
* [![Material UI][material]][material-url]
* [![Docker][Docker]][docker-url]

## Getting Started

For starting application run services together or use docker-compose.

### For run frontend and backend

Inside the root directory, for run backend do a:

```
mvn spring-boot:run
```

Inside the root directory, for run frontend do a:

```
cd frontend
npm start
```

### Docker Instruction

For generated targer folder

````
mvn clean install
````

Build and Run Docker Images

````
docker-compose up
````

Rebuild Docker Images

````
docker-compose build
````

Backend Local:

+ http://localhost:8080/
+ http://localhost:8080/swagger-ui.html

Frontend Local:

+ http://localhost:3000/

## Contact

[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- MARKDOWN LINKS & IMAGES -->

[project-screenshot]: readme_image/cat_info_main_page.PNG

[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white

[java-url]: https://docs.oracle.com/en/java/

[Spring_Boot]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white

[spring-url]: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

[JS]: https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black

[js-url]: https://developer.mozilla.org/en-US/docs/Web/JavaScript

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

[React-url]: https://reactjs.org/

[material]: https://img.shields.io/badge/Material--UI-0081CB?style=for-the-badge&logo=material-ui&logoColor=white

[material-url]: https://mui.com/

[Docker]: https://img.shields.io/badge/-Docker-fff?style=for-the-badge&logo=Docker

[docker-url]: https://docs.docker.com/

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://www.linkedin.com/in/kkarpekina

[hacker-news-url]: https://github.com/HackerNews/API