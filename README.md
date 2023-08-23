# job-search-be
java spring boot backend for job search app
Java 19, Spring Boot 3.1.1, MySQL, JWT

To run this app using docker, you may need to change the spring.datasource.url to use the ip address of the machine you are running the app on instead of using localhost.

Steps to run using Docker:

Download the zip folder and extract.
Make sure you have Docker Desktop, Java and Maven installed and up to date (for Windows users).
Navigate to the jobsearch-be project root folder and run 'mvn clean'.
Once successful, from the same root folder run 'mvn install'.
Once successful, run 'docker build -t job-search-be .'. Doing so will build the job-search-be docker image.
Then run 'docker run -p 8080:8080 job-search-be:latest'. Doing so should start a docker container using the image.
The backend should now be running.

You might also have to modify the permissions for user1 which you will have to have setup on your mysql server. If you would rather use a different user you are welcome to change the username used in the application.properties file and grant them the necessary permissions in Mysql Workbench.

The sql command to grant user1 the necessary permissions may look like this: CREATE USER 'user1'@'host.docker.internal' IDENTIFIED BY 'root'; GRANT SELECT, INSERT, UPDATE, DELETE ON jobsearch.* TO 'user1'@'host.docker.internal'; FLUSH PRIVILEGES;
--------------------------------------------------------------------------

Dump20230714.sql contains database structure and data

Instructions for Postman

http://localhost:8080/application/getoneapplication/{jobId} - GET - Get single job application by job id

http://localhost:8080/application/getbyjobid/{jobId} - GET - Get job applications by job id

http://localhost:8080/application/getbyapplicantid/{applicantId} - GET - Get job applications by applicant id

http://localhost:8080/application/create -POST - Create job application { "jobId":1, "applicantId":1}

http://localhost:8080/employer/list - GET - Get list of all employers

http://localhost:8080/employer/getbyid/{id} - GET - Get employer by employer id

http://localhost:8080/employer/getbyname/{name} - GET - Get employer by name

http://localhost:8080/employer/create - POST - Create new employer {"name":"My New Business"}

http://localhost:8080/employer/update/{id} - POST - Update employer with given id {"id":1,"name":"My Updated Business Name"}

http://localhost:8080/employer/delete/{id} - POST - Delete employer with given id

http://localhost:8080/job/list - GET - Get list of all jobs

http://localhost:8080/job/get/{id} - GET - Get job by job id

http://localhost:8080/job/create - POST - Create new job {"title":"UI tester","employerId":1,"description":"Responsible for testing the UI of various applications"}

http://localhost:8080/job/update/{id} - POST - Update job by job id {"id":1,"title":"My Updated Job Title","employerId":1,"description":"My updated list of duties","postDate":'2023-07-14'}

http://localhost:8080/job/delete/{id} - POST - Delete job by job id

http://localhost:8080/auth/signup - POST - Register new user {"email":"testacc1@fake.com","password":"test123","isEmployer":true,"employerName":"Best Deals R Us"}

http://localhost:8080/auth/login - POST - Login user {"email":"jax21@fake.com","password":"test123"}

http://localhost:8080/auth/getrole/{userid} - GET - Get role of user with given id

http://localhost:8080/auth/getuserid/{email} - GET - Get user id by email

http://localhost:8080/auth/getuser/{email} - GET - Get user by email
