This program allows to:
- create, edit, delete, list URLs
- monitor urls on background with given intervals in seconds
- list last 10 monitored results for each particular URL

There are already 2 users in DB(id:1,2)
Endpoints to use it:
- to create endpoint:
POST json here
http://localhost:8080/api/v1/endpoints/
in format
{
    "name": "UrlName",
    "url": "www.imdb.com",
    "monitoredIntervalInSeconds":30,
    "userId":1
}
- to edit endpont:
PUT json
http://localhost:8080/api/v1/endpoints/

- to delete endpoint by id:
http://localhost:8080/api/v1/endpoints/{id}

- to get list with last 10 results of particular endpoint by id:
GET
http://localhost:8080/api/v1/results/{id}

- to get endpoints of particular user by id:
GET
http://localhost:8080/api/v1/endpoints/{id}



