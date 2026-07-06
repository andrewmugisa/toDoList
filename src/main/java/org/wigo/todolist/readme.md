
># TODOLIST API

## API endpoints

### Simple auth

| Method | Endpoint         | Auth    | Description                                  |
|---|------------------|---------|----------------------------------------------|
| POST | `/auth/register` | Public  | Register with username,password ,email ,name |
| POST | `/auth/login`    | Public  | Login with username , password               |

````
register

url: 
    http://localhost:8080/auth/register

body:
    {
      "username": "john",
      "password": "123456",
      "email": "test@test.com",
      "name": "myname"
    }
    
 >>>Response<<
    {
    "email": "test@test.com",
    "id": "18eb5ee5-f833-4f49-b4de-c5232dfd625f",
    "name": "myname",
    "password": "123456",
    "username": "john"
}
    
    >>>Response duplicate<<
    {
        "message": "Username already exists"
    }
    
        >>>Response Error<<
    {
        "message": "error message"
    }
````
````
login

url: 
    http://localhost:8080/auth/login

body:
    {
      "username": "john",
      "password": "123456"
    }
    
 >>>Response<<
    {
        "message": "success"
    }
    
    >>>Response Wrong password<<
    {
        "message": "Invalid username or password"
    }
    
    >>>Response Error<<
    {
        "timestamp": "2026-07-06T07:25:01.938Z",
        "status": 400,
        "error": "Bad Request",
        "path": "/auth/login"
    }
````


### Events

| Method | Endpoint          | Auth | Description                           |
|--------|-------------------|------|---------------------------------------|
| POST   | `/events/create`  | id   |  |
| GET    | `/events/all`     | id   |      |
| PATCH  | `/events/update`  | id   | |
| POST   | `/events/archive` | id   |              |
| DELETE | `/events/delete`  | id   | delete   |

create
http://localhost:8080/events/create?userId=8f9c6e3c-3cfc-49f7-a087-a7f1e5b09c46
{
"title":" my Fist Event",
"description":"Sing and Dance",
"location":"Orleans",
"start": "2026-07-04T07:14:24",
"end": "2026-07-04T09:00:00",
"category":"work",
"status":"Published"

}

{
"category": "WORK",
"description": "Sing and Dance",
"end": "2026-07-04T09:00:00",
"id": "9490eac1-ed27-40ed-ab04-33a28a858e61", //eventId
"location": "Orleans",
"start": "2026-07-04T07:14:24",
"status": "PUBLISHED",
"title": " my Fist Event"
}

get
http://localhost:8080/events/all?userId=c0ed8e6c-8f9c-4eff-8c34-ed5649c62976

[
{
"title": " my Fist Event",
"description": "Sing and Dance",
"location": "Orleans",
"start": "2026-07-04T07:14:24",
"end": "2026-07-04T09:00:00",
"category": "WORK",
"status": "PUBLISHED",
"user": {
"username": "john",
"password": "123456",
"email": "test@test.com",
"name": "myname",
"active": true,
"createdAt": "2026-07-06T02:47:13.445603",
"events": [
{
"title": " my Fist Event",
"description": "Sing and Dance",
"location": "Orleans",
"start": "2026-07-04T07:14:24",
"end": "2026-07-04T09:00:00",
"category": "WORK",
"status": "PUBLISHED",
"user"...


update
http://localhost:8080/events/update?userId=c0ed8e6c-8f9c-4eff-8c34-ed5649c62976&eventId=9490eac1-ed27-40ed-ab04-33a28a858e61

{
"title":" my Fist Event",
"description":"Sing and Dance",
"location":"London",
"start": "2026-07-04T07:14:24",
"end": "2026-07-04T09:00:00",
"category":"Personal",
"status":"draft"

}


{
"category": "PERSONAL",
"description": "Sing and Dance",
"end": "2026-07-04T09:00:00",
"id": "9490eac1-ed27-40ed-ab04-33a28a858e61",
"location": "London",
"start": "2026-07-04T07:14:24",
"status": "DRAFT",
"title": " my Fist Event"
}


archive
http://localhost:8080/events/archieve?userId=c0ed8e6c-8f9c-4eff-8c34-ed5649c62976&eventId=9490eac1-ed27-40ed-ab04-33a28a858e61

{
"message": "Event Archived Successfully"
}

delete
http://localhost:8080/events/delete?userId=c0ed8e6c-8f9c-4eff-8c34-ed5649c62976&eventId=9490eac1-ed27-40ed-ab04-33a28a858e61
{
"message": "Event deleted Successfully"
}