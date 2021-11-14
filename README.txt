This app allows to:
- create, edit, delete, list URLs
- monitor urls on background with given intervals in seconds
- list last 10 monitored results for each particular URL

There are already 2 users in DB(id:1,2)
Endpoints to use it:
- to create endpoint:
POST JSON here
http://localhost:8080/api/v1/endpoints/
in format
{
    "name": "UrlName",
    "url": "https://www.imdb.com/",
    "monitoredIntervalInSeconds":30,
    "userId":1
}
- to edit endpont by id:
PUT JSON
http://localhost:8080/api/v1/endpoints/{id}

- to delete endpoint by id:
http://localhost:8080/api/v1/endpoints/{id}

- to get list with last 10 results of particular endpoint by EndpointId:
GET
http://localhost:8080/api/v1/results/{EndpointId}

- to get endpoints of particular user by UserId:
GET
http://localhost:8080/api/v1/endpoints/{UserId}



