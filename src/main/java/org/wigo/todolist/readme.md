# TODOLIST API

Auth + Events flow. IDs auto-inject: register/login save `{{userId}}`, create saves `{{eventId}}`, and update/archive/delete reuse them.

Run top-to-bottom via Collection Runner or Newman.

## API Endpoints

### Auth

| Method | Endpoint         | Auth   | Description                      |
| ------ | ---------------- | ------ | -------------------------------- |
| POST   | `/auth/register` | Public | Register a new user              |
| POST   | `/auth/login`    | Public | Login with username and password |

### Events

| Method | Endpoint          | Auth                | Description               |
| ------ | ----------------- | ------------------- | ------------------------- |
| POST   | `/events/create`  | `userId`            | Create an event           |
| GET    | `/events/all`     | `userId`            | Get all events for a user |
| PATCH  | `/events/update`  | `userId`, `eventId` | Update an event           |
| POST   | `/events/archive` | `userId`, `eventId` | Archive an event          |
| DELETE | `/events/delete`  | `userId`, `eventId` | Delete an event           |

---

## Auth

### POST Register

**Endpoint**

```text
{{baseUrl}}/auth/register
```

**Body**

```json
{
  "username": "john",
  "password": "123456",
  "email": "test@test.com",
  "name": "myname"
}
```

**Success Response**

```json
{
  "email": "test@test.com",
  "id": "18eb5ee5-f833-4f49-b4de-c5232dfd625f",
  "name": "myname",
  "password": "123456",
  "username": "john"
}
```

**Duplicate Username Response**

```json
{
  "message": "Username already exists"
}
```

**Error Response**

```json
{
  "message": "error message"
}
```

### POST Login

**Endpoint**

```text
{{baseUrl}}/auth/login
```

**Body**

```json
{
  "username": "john",
  "password": "123456"
}
```

**Success Response**

```json
{
  "message": "success"
}
```

**Wrong Password Response**

```json
{
  "message": "Invalid username or password"
}
```

**Error Response**

```json
{
  "timestamp": "2026-07-06T07:25:01.938Z",
  "status": 400,
  "error": "Bad Request",
  "path": "/auth/login"
}
```

---

## Events

### POST Create Event

**Endpoint**

```text
{{baseUrl}}/events/create?userId={{userId}}
```

**Query Parameters**

```text
userId={{userId}}
```

**Body**

```json
{
  "title": " my Fist Event",
  "description": "Sing and Dance",
  "location": "Orleans",
  "start": "2026-07-04T07:14:24",
  "end": "2026-07-04T09:00:00",
  "category": "work",
  "status": "Published"
}
```

**Success Response**

```json
{
  "category": "WORK",
  "description": "Sing and Dance",
  "end": "2026-07-04T09:00:00",
  "id": "9490eac1-ed27-40ed-ab04-33a28a858e61",
  "location": "Orleans",
  "start": "2026-07-04T07:14:24",
  "status": "PUBLISHED",
  "title": " my Fist Event"
}
```

> The returned `id` is saved as `{{eventId}}` for subsequent event operations.

### GET Get All Events

**Endpoint**

```text
{{baseUrl}}/events/all?userId={{userId}}
```

**Query Parameters**

```text
userId={{userId}}
```

**Success Response**

```json
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
      "createdAt": "2026-07-06T02:47:13.445603"
    }
  }
]
```

### PATCH Update Event

**Endpoint**

```text
{{baseUrl}}/events/update?userId={{userId}}&eventId={{eventId}}
```

**Query Parameters**

```text
userId={{userId}}
eventId={{eventId}}
```

**Body**

```json
{
  "title": " my Fist Event",
  "description": "Sing and Dance",
  "location": "London",
  "start": "2026-07-04T07:14:24",
  "end": "2026-07-04T09:00:00",
  "category": "Personal",
  "status": "draft"
}
```

**Success Response**

```json
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
```

### POST Archive Event

**Endpoint**

```text
{{baseUrl}}/events/archieve?userId={{userId}}&eventId={{eventId}}
```

**Query Parameters**

```text
userId={{userId}}
eventId={{eventId}}
```

**Success Response**

```json
{
  "message": "Event Archived Successfully"
}
```

### DELETE Delete Event

**Endpoint**

```text
{{baseUrl}}/events/delete?userId={{userId}}&eventId={{eventId}}
```

**Query Parameters**

```text
userId={{userId}}
eventId={{eventId}}
```

**Success Response**

```json
{
  "message": "Event deleted Successfully"
}
```
