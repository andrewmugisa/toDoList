# myTodoList API

Base URL: `{{baseUrl}}` (e.g. `http://localhost:8080`)

Auth endpoints are public. Event endpoints require a `userId` query parameter (obtained from register or login).

## Table of contents

- [Auth](#auth)
    - [Register](#post-authregister)
    - [Login](#post-authlogin)
    - [Delete User](#delete-authdelete)
- [Events](#events)
    - [Create Event](#post-eventscreate)
    - [Get All Events](#get-eventsall)
    - [Update Event](#patch-eventsupdate)
    - [Archive Event](#post-eventsarchieve)
    - [Delete Event](#delete-eventsdelete)

---

## Auth

### POST `/auth/register`

Register a new user.

Request body (JSON):

```json
{
  "username": "john",
  "password": "123456",
  "email": "test@test.com",
  "name": "myname"
}
```

Returns the created user, including the `id` used as `userId` for event endpoints.

---

### POST `/auth/login`

Authenticate an existing user.

Request body (JSON):

```json
{
  "username": "john",
  "password": "123456"
}
```

Returns the user, including `id`.

---

### DELETE `/auth/delete`

Delete a user by id.

| Query param | Value |
|-------------|-------|
| `userId`    | `{{userId}}` |

Example: `{{baseUrl}}/auth/delete?userId={{userId}}`

---

## Events

### POST `/events/create`

Create an event for a user.

| Query param | Value |
|-------------|-------|
| `userId`    | `{{userId}}` |

Request body (JSON):

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

Returns the created event, including its `id` (used as `eventId`).

---

### GET `/events/all`

Get all events for a user.

| Query param | Value |
|-------------|-------|
| `userId`    | `{{userId}}` |

Example: `{{baseUrl}}/events/all?userId={{userId}}`

Returns an array of events.

---

### PATCH `/events/update`

Update an existing event.

| Query param | Value |
|-------------|-------|
| `userId`    | `{{userId}}` |
| `eventId`   | `{{eventId}}` |

Request body (JSON):

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

---

### POST `/events/archieve`

Archive an event.

| Query param | Value |
|-------------|-------|
| `userId`    | `{{userId}}` |
| `eventId`   | `{{eventId}}` |

Example: `{{baseUrl}}/events/archieve?userId={{userId}}&eventId={{eventId}}`

---

### DELETE `/events/delete`

Delete an event.

| Query param | Value |
|-------------|-------|
| `userId`    | `{{userId}}` |
| `eventId`   | `{{eventId}}` |

Example: `{{baseUrl}}/events/delete?userId={{userId}}&eventId={{eventId}}`

---

## Notes

- `category` accepts values like `work` / `Personal`; the API normalizes them to uppercase (`WORK`, `PERSONAL`).
- `status` accepts values like `Published` / `draft`; normalized to uppercase (`PUBLISHED`, `DRAFT`).
- `start` and `end` use ISO-8601 local date-time format (`yyyy-MM-ddTHH:mm:ss`).
- The endpoint path `/events/archieve` is spelled as implemented (not `archive`).