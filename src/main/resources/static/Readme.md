User Model
UUID id -- auto generated
String userName -- unique --def email alias
String Password
String email
String name
LocalDateTime createdAt -- auto generated -- now
Boolean Active -- System updated
List<Event> events -- user events (OneToMany)

Event Model
UUID id --auto Generated
String title
String description
String location
LocalDateTime start -- event start time
LocalDateTime end -- event end time
EventCategory categery -- enum(PERSONAL, SCHOOL, WORK, PROJECTS)
EventStatus status --enum(DRAFT, PUBLISHED, CANCELLED, COMPLETED)
Customize Toolbar --auto generated --now
Boolean active -- default true (//TODO make active ARCHIVE enum type in category)
User user -- event owner (ManyToOne)

RegisterUserDto --requires
String Name
String Email
String Password

AuthenticateUserDto --requires
String email;
String password;

UserResponseDto  -- returns
UUID id
String username
String email
String Name

CreateEventDto --requires
String title
String description
String location
LocalDateTime start
LocalDateTime end
String category
String status

EventResponseDto
UUID id
String title
String description
String location
LocalDateTime start
LocalDateTime end
EventCategory category
EventStatus status

Controller
RedirectController("/")
default get mapping("/") redirected to index.html by RedirectController

Usercontroller("/u")
authenticateUser() --POST <-AuthenticateUserDto
registerUser("/register") --POST <-RegisterUserDto
deleteUser("/delete") --DELETE <-@param userId

EventController("/events")
getEvents()
createEvent() --POST <- @param userId, CreateEventDto
updateEvent() -- PATCH <- @param userId, @param eventId, CreateEventDto (somewhat poorly      implemented because PATCH is implemented as PUT)
deleteEvent()  --DELETE <- @param userId, @param eventId
archiveEvent()  -- POST("/archieve") <--@param userId, @param EventId

