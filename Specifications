## Use/User Story

### FrontEnd

Ideally, a user should be able to log into Facebook using the OAuth API and concurrently log into RIT's Google Apps Calendar API (or choose to manually put in their university and classes+section). The class listing should only consist of the Class name and the section the person is in to match the easiest, then let the user just input what classes and which section they’re in. Then, the user should be able to see a breakdown (either by class or by users) of classes they share with other users; i.e., if the class breakdown is chosen have links to FB pages of people in that class or if the user breakdown is chosen, see what classes you have with other people ordered by the people themselves.

###BackEnd

The backend will consist of several parts.
	

1. Page Serving
This will contain several views which encompass the entirety of:
*Facebook/Google login
*Class entering {import from G Calendar, iCal, or form fill}
*A listed view of what classes you have followed by FB friends with that class, or FB friends you have with classes they share with you.

2. Server Stuff
* Login based on Facebook OAuth
* Information available using a REST API
* LINQ-esque queries across the Database to find matching University, Class, and Section.
* Module which imports a class list (either form-filled or from Google Calendar) and decides unique classes based on this, saves user and classes to DB.



3. Database
*Contains a Student entity made of: An ID field, a University field, and a collection of classes this user is in, and third-party access information (Facebook, Google).
*Contains a Class entity, which has an ID field, a University field, a Class Name, and a Class Section #.
*Contains a University entity, which has a collection of classes and a collection of Students.
