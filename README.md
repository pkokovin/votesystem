Выпускной проект Topjava

- Task

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

Technologies used in project:
- H2 in memory database
- Hibernate
- Spring Boot
- Spring MVC
- Spring Rest
- Spring Security
- Spring JPA

1. Unregistered user can register: 
<br/>URL: 127.0.0.1:8080/rest/profile/register
<br/>method POST
<br/>content: { "name":[String], "email":[String], "password":[String] }
<br/>Success response:
<br/>Code: 201 Created
<br/>content: { "id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": true, "registered": [Date], "roles": [[Role]...] }
<br/>curl -s -X POST -d '{"name":"newName","email":"newemail@ya.ru","password":"newPassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/profile/register
               
2. Registered users can get themselves:
<br/>URL: 127.0.0.1:8080/rest/profile
<br/>method GET
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "id": [Integer], "name":[String], "email":[String], "password":[String], 
"enabled": true, "registered": [Date], "roles": [[Role]...] }
<br/>curl -s http://localhost:8080/rest/profile --user user@yandex.ru:password

3. Registered users can update themselves:
<br/>URL: 127.0.0.1:8080/rest/profile
<br/>method PUT
<br/>content: { "id": [Integer], "name":[String], "email":[String], "password":[String] }
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": true, "registered": [Date], "roles": [[Role]...] }
<br/>curl -s -X PUT -d '{"name":"NewUser","email":"user@yandex.ru","password":"passwordNew"}' -H 'Content-Type: application/json' http://localhost:8080/rest/profile --user user@yandex.ru:password

4. Registered users can delete themselves:
<br/>URL: 127.0.0.1:8080/rest/profile
<br/>method DELETE
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "deleted": [Boolean] }
<br/>curl -s -X DELETE http://localhost:8080/rest/profile --user user@yandex.ru:password

5. Registered user can get list of restaurants:
<br/>URL: 127.0.0.1:8080/rest/profile/restaurants
<br/>method GET
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [ {"id": [Integer], "name":[String], "menu":null}...]
<br/>curl -s http://localhost:8080/rest/profile/restaurants --user user@yandex.ru:password

5. Registered user can get list of restaurants with today menus:
<br/>URL: 127.0.0.1:8080/rest/profile/restaurants/current
<br/>method GET
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [ {"id": [Integer], "name":[String], "menu": [[Dish]...]}...]
<br/>curl -s http://localhost:8080/rest/profile/restaurants/current --user user@yandex.ru:password

5. Registered user can get particular restaurant by id:
<br/>URL: 127.0.0.1:8080/rest/profile/restaurants/{restaurantId}
<br/>method GET
<br/>URL Params: restaurantId = [Integer]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: {"id": [Integer], "name":[String], "menu":null}
<br/>curl -s http://localhost:8080/rest/profile/restaurants/100006 --user user@yandex.ru:password

6. Registered user can get current menu for restaurant by restaurant id:
<br/>URL: 127.0.0.1:8080/rest/profile/dishes/current/{restaurantId}
<br/>method GET
<br/>URL Params: restaurantId = [Integer]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [ { "id": [Integer], "name":[String], "price": [Long], "dishDate": [LocalDate], "restaurant": { "id": [Integer], "name": [String], "menu": null } } ]
<br/>curl -s http://127.0.0.1:8080/rest/profile/dishes/current/100006 --user user@yandex.ru:password

6. Registered user can get current menu for restaurant by restaurant id and particular date:
<br/>URL: 127.0.0.1:8080/rest/profile/dishes/filter/{restaurantId}
<br/>method GET
<br/>URL Params: restaurantId = [Integer], date = [LocalDate]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [ { "id": [Integer], "name":[String], "price": [Long], "dishDate": [LocalDate], "restaurant": { "id": [Integer], "name": [String], "menu": null } } ]
<br/>curl -s http://127.0.0.1:8080/rest/profile/dishes/filter/100002?date=2020-02-25 --user user@yandex.ru:password

7. Registered user can get all own votes:
<br/>URL: URL: 127.0.0.1:8080/rest/profile/votes
<br/>method GET
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [  { "id": [Integer], "user": { "id": [Integer], "name": [String], "email": [String], "password": [String], "enabled": [Boolean], "registered": "roles": [["ROLE_USER"]...] }, "restaurant": { "id": [Integer], "name": [String], "menu": null }, "voteDateTime": [LocalDateTime] } ]
<br/>curl -s http://127.0.0.1:8080/rest/profile/votes --user user@yandex.ru:password

8. Registered user can vote:
<br/>URL: URL: 127.0.0.1:8080/rest/profile/votes/{restaurantId}
<br/>method POST
<br/>URL Params: restaurantId = [Integer]
<br/>Success response:
<br/>Code: 201 Created
<br/>content: [  { "id": [Integer], "user": null, "restaurant": null , "voteDateTime": [LocalDateTime] } ]
<br/>curl -s -X POST http://127.0.0.1:8080/rest/profile/votes/100005 --user user@yandex.ru:password

9. User with role ADMIN can get all users:
<br/>URL: URL: 127.0.0.1:8080/rest/admin/users
<br/>method GET
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [ {"id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": [Boolean], "registered": [Date], "roles": [[Role]...] }... ]
<br/>curl -s http://127.0.0.1:8080/rest/admin/users --user admin@gmail.com:admin

10. User with role ADMIN can get particular user by id:
<br/>URL: 127.0.0.1:8080/rest/admin/users/{userId}
<br/>method GET
<br/>URL Params: userId = [Integer]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: {"id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": [Boolean], "registered": [Date], "roles": [[Role]...] }
<br/>curl -s http://localhost:8080/rest/admin/users/100001 --user admin@gmail.com:admin

11. User with role ADMIN can get particular user by email:
<br/>URL: 127.0.0.1:8080/rest/admin/users/by?email={userEmail}
<br/>method GET
<br/>URL Params: email = [String]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: {"id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": [Boolean], "registered": [Date], "roles": [[Role]...] }
<br/>curl -s http://localhost:8080/rest/admin/users/by?email=user@yandex.ru --user admin@gmail.com:admin

12. User with role ADMIN can delete any user by id:
<br/>URL: 127.0.0.1:8080/rest/admin/users/{userId}
<br/>method DELETE
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "deleted": [Boolean] }
<br/>curl -s -X DELETE http://localhost:8080/rest/admin/users/100000 --user admin@gmail.com:admin

13. User with role ADMIN can create user:
<br/>URL: 127.0.0.1:8080/rest/admin/users
<br/>method POST
<br/>content: { "name":[String], "email":[String], "password":[String], "roles": [[Role]...] }
<br/>Success response:
<br/>Code: 201 Created
<br/>content: { "id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": true, "registered": [Date], "roles": [[Role]...] }
<br/>curl -s -X POST -d '{"name":"newName","email":"newemail@ya.ru","password":"newPassword", "roles": ["ROLE_USER", "ROLE_ADMIN"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@gmail.com:admin


13. User with role ADMIN can update any user:
<br/>URL: 127.0.0.1:8080/rest/admin/users/{userId}
<br/>method PUT
<br/>URL Params: userId = [Integer]
<br/>content: { "id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": [Boolean], "roles": [[Role]...] }
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "id": [Integer], "name":[String], "email":[String], "password":[String], "enabled": [Boolean], "registered": [Date], "roles": [[Role]...] }
<br/>curl -s -X PUT -d '{"name":"UpdatedName","email":"newemail@ya.ru","password":"newPassword", "roles": ["ROLE_USER", "ROLE_ADMIN"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/100057 --user admin@gmail.com:admin

14. User with role ADMIN can enable/disable user:
<br/>URL: 127.0.0.1:8080/rest/admin/users/{userId}
<br/>method PATCH
<br/>URL Params: userId = [Integer], enabled = [Boolean]
<br/>Success response:
<br/>Code: 204 No Content
<br/>curl -s -X PATCH http://localhost:8080/rest/admin/users/100057?enabled=false --user admin@gmail.com:admin

15. User with role ADMIN can create restaurant:
<br/>URL: 127.0.0.1:8080/rest/admin/restaurants
<br/>method POST
<br/>content: { "name":[String] }
<br/>Success response:
<br/>Code: 201 Created
<br/>content: { "id": [Integer], "name":[String] }
<br/>curl -s -X POST -d '{"name":"newName"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin

16. User with role ADMIN can update restaurant:
<br/>URL: 127.0.0.1:8080/rest/admin/restaurants/{restaurantId}
<br/>method PUT
<br/>URL Params: restaurantId = [Integer]
<br/>content: { "id":[Integer], "name":[String] }
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "id":[Integer], "name":[String] }
<br/>curl -s -X PUT -d '{"id":100003, "name":"updatedName"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100003 --user admin@gmail.com:admin

17. User with role ADMIN can delete restaurant:
<br/>URL: 127.0.0.1:8080/rest/admin/restaurants/{restaurantId}
<br/>method DELETE
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "deleted": [Boolean] }
<br/>curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100002 --user admin@gmail.com:admin

18. User with role ADMIN can create dish:
<br/>URL: 127.0.0.1:8080/rest/admin/dishes/{restaurantId}
<br/>method POST
<br/>URL Params: restaurantId = [Integer]
<br/>content: { "name":[String], "price":[Long] }
<br/>Success response:
<br/>Code: 201 Created
<br/>content: { "id":[Integer], "name":[String], "price":[Long], "dishDate":[LocalDate], "restaurant": { "id":[Integer], "name":[String], "menu":null } }
<br/>curl -s -X POST -d '{"name":"newName", "price":50000}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/dishes/100006 --user admin@gmail.com:admin

19. User with role ADMIN can update dish:
<br/>URL: 127.0.0.1:8080/rest/admin/dishes/{dishId}
<br/>method PUT
<br/>URL Params: dishId = [Integer]
<br/>content: { "id":[Integer], "name":[String], "price":[Long], "dishDate":[LocalDate] }
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "id":[Integer], "name":[String], "price":[Long], "dishDate":[LocalDate], "restaurant":null }
<br/>curl -s -X PUT -d '{"id":100008, "name":"newName", "price":50000, "dishDate":"2020-04-08"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/dishes/100008 --user admin@gmail.com:admin

20. User with role ADMIN can get dish by id:
<br/>URL: 127.0.0.1:8080/rest/admin/dishes/{dishId}
<br/>method GET
<br/>URL Params: dishId = [Integer]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: {"id": [Integer], "name":[String], "price":[Long], "dishDate":[LocalDate], "restaurant":null }
<br/>curl -s http://localhost:8080/rest/admin/dishes/100008 --user admin@gmail.com:admin

21. User with role ADMIN can delete dish:
<br/>URL: 127.0.0.1:8080/rest/admin/dishes/{id}
<br/>method DELETE
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "deleted": [Boolean] }
<br/>curl -s -X DELETE http://localhost:8080/rest/admin/dishes/100008 --user admin@gmail.com:admin

22. User with role ADMIN can get all votes:
<br/>URL: URL: 127.0.0.1:8080/rest/admin/votes
<br/>method GET
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [ {"id": [Integer], "user": { "id": [Integer], "name": [String], "email": [String], "password": [String], "enabled": [Boolean], "registered": [Date], "roles": [[Role]...] }, "restaurant": { "id": [Integer], "name": [String], "menu": null }, "voteDateTime": [LocalDateTime] }... ]
<br/>curl -s http://127.0.0.1:8080/rest/admin/votes --user admin@gmail.com:admin

23. User with role ADMIN can get all votes of particular user:
<br/>URL: URL: 127.0.0.1:8080/rest/admin/votes/user/{id}
<br/>method GET
<br/>URL Params: id = [Integer]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: [ {"id": [Integer], "user": { "id": [Integer], "name": [String], "email": [String], "password": [String], "enabled": [Boolean], "registered": [Date], "roles": [[Role]...] }, "restaurant": { "id": [Integer], "name": [String], "menu": null }, "voteDateTime": [LocalDateTime] }... ]
<br/>curl -s http://127.0.0.1:8080/rest/admin/votes/user/100001 --user admin@gmail.com:admin

24. User with role ADMIN can get vote by id:
<br/>URL: URL: 127.0.0.1:8080/rest/admin/votes/{id}
<br/>method GET
<br/>URL Params: id = [Integer]
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: {"id": [Integer], "user": { "id": [Integer], "name": [String], "email": [String], "password": [String], "enabled": [Boolean], "registered": [Date], "roles": [[Role]...] }, "restaurant": { "id": [Integer], "name": [String], "menu": null }, "voteDateTime": [LocalDateTime] }
<br/>curl -s http://127.0.0.1:8080/rest/admin/votes/100054 --user admin@gmail.com:admin

25. User with role ADMIN can delete vote:
<br/>URL: 127.0.0.1:8080/rest/admin/votes/{voteId}
<br/>method DELETE
<br/>Success response:
<br/>Code: 200 Ok
<br/>content: { "deleted": [Boolean] }
<br/>curl -s -X DELETE http://localhost:8080/rest/admin/votes/100054 --user admin@gmail.com:admin

