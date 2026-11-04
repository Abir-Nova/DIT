

1. what's an API ? 

* API stands for Application Programming Interface

* The client doesn't need to know how your SQL tables are organized internally. It receives the data through the API's agreed-upon format

* REST stands for Representational State Transfer. It's an architectural style for designing web APIs

* REST 
1. Resources have names. 
For example, ads, companies, and applications

2. HTTP methods describe the operation. For example, GET reads data and POST creates something

3. URLs identify the resource. 
For example, /ads/1 refers to the job ad with ID 1

4. Responses communicate the result. Use HTTP status codes and a consistent response format, usually JSON

5. Requests are independent. Each request should contain the information needed to process it rather than depending on hidden server-side conversation state

* A RESTful API is not simply a database with URLs. It is a structured contract between our back-end and its clients.


2. HTTP methods: the verbs of our API

* The HTTP method tells the server what the client wants to do

Method	    Purpose 	                                examples
GET	        Read information	                        GET /ads/1
POST  	    Create a resource or submit data        	POST /ads
PUT	        Replace a resource	                        PUT /ads/1
PATCH 	    Update part of a resource	                PATCH /ads/1
DELETE      Delete a resource	                        DELETE /ads/1

* GET : retrieve information 
GET /companies/1/ads 
return all ads published by company 1

* POST : create or submit information
POST /ads
create a new job ad using the data provided in the request body
POST /ads/1/applications
submit an application for job ad 1


3. URLs, resources, IDs ,route parameters

Let's break down our four required routes

* GET /companies/:id/ads      : Returns every job ad belonging to one company
* GET /ads/:id                : Returns the details of one job ad
* POST /ads                   : Creates a new ad
* POST /ads/:id/applications  : Records an application for the specified job ad

* What does :id mean?

It's a route parameter. 
The colon notation is a placeholder used in many backend frameworks 
the actual request contains a value instead

For example:
/ads/1
/ads/2
/ads/3

All can match the same route pattern:
/ads/:id

* The backend extracts the ID from the URL and uses it in the database query.


* Why not ?  /createApplication?adId=1

That URL puts an action in the path and places the ad ID in a query string. 
Our assignment instead wants a resource-oriented path: 
POST /ads/1/applications

This expresses the relationship clearly: the application belongs to job ad 1.


* Path parameters vs. query parameters

These are different concepts:
- Path parameter: identifies a resource, ex: /ads/1.
- Query parameter: usually filters, sorts or searches a collection, ex: /ads?category=1.
we will probably use query parameters later for job search and filtering



4. How our REST API connects to our existing SQL schema

* This is where our database work becomes useful
We don't need to create new tables for the API
Our four routes can use the tables we've already created

* Let's map each route to its database operation : 

API route	                    Tables involved	                    Database operation
GET /companies/:id/ads      	company, jobAD, category        	SELECT with JOIN
GET /ads/:id	                jobAD, company, category	        SELECT with JOIN
POST /ads	                    jobAD	                            INSERT
POST /ads/:id/applications	    jobAD, applicant, application	    Check IDs, then INSERT

The API is a layer on top of our database not a replacement for it


* Route 1: List a company's ads

- request : GET /companies/1/ads
- Our API needs to find every ad with id_company = 1
- A suitable SQL query is:
SELECT
    j.id_jobAD,
    j.title,
    j.position,
    j.description_jobAD,
    c.name_category
FROM jobAD AS j
JOIN category AS c
    ON j.id_category = c.id_category
WHERE j.id_company = ?;

- The ? is a parameter placeholder.
Our backend supplies the company ID safely when executing the query.

- Why use a JOIN? 
The job ad stores id_category, not the category's name. 
The JOIN allows us to return a useful category name to the client.

- One important decision: should a company with no ads return an empty list or a 404? 
For a collection endpoint, 
the usual RESTful behavior is 200 OK with an empty array ([]) if the company exists but has no ads. 
Return 404 Not Found if the company itself does not exist.

* Route 2 : Get one ad's details
- Request: GET /ads/1
- SQL:
SELECT
    j.id_jobAD,
    j.title,
    j.position,
    j.description_jobAD,
    c.name_company,
    c.address_company,
    cat.name_category
FROM jobAD AS j
JOIN company AS c
    ON j.id_company = c.id_company
JOIN category AS cat
    ON j.id_category = cat.id_category
WHERE j.id_jobAD = ?;

- This query combines 3 tables to return a complete job ad

- If the query returns a row, the API responds with the job details. 
If it returns no rows, the requested ad doesn't exist, so the API returns 404 Not Found.

* Route 3: Create an ad 

- Request: POST /ads

- The client sends the new ad's information in the request body.

- Example JSON body:
{
  "title": "Junior PHP Developer",
  "position": "Backend Developer",
  "id_company": 1,
  "id_category": 3,
  "description_jobAD": "Build and maintain backend applications."
}

- The API validates the fields and inserts the data:
- SQL : 
INSERT INTO jobAD
    (title, position, id_company, id_category, description_jobAD)
VALUES
    (?, ?, ?, ?, ?);

- After a successful insertion, the server can retrieve the generated ID and respond with 201 Created.

- For example:
{
  "id_jobAD": 4,
  "title": "Junior PHP Developer",
  "position": "Backend Developer",
  "id_company": 1,
  "id_category": 3,
  "description_jobAD": "Build and maintain backend applications."
}

- The exact returned fields depend on our API design, but returning the newly created resource is useful.

- Important: 
our existing schema has foreign keys. 
A company ID or category ID that doesn't exist cannot be inserted successfully. Our backend should validate those references and return a meaningful error rather than exposing a raw database exception.


* Route 4: Record an application

- Request: POST /ads/1/applications

- Example JSON body:
{
  "id_applicant": 3,
  "message": "I am interested in this position and have relevant experience."
}

- Our application table already contains the fields needed:
+ id_application: generated automatically.
+ id_jobAD: obtained from the URL
+ id_applicant: supplied by the client
+ message: supplied by the client
+ date_application: generated by our database by default

- SQL:
INSERT INTO application
    (id_jobAD, id_applicant, message)
VALUES
    (?, ?, ?);

- We notice that the request body does not need to contain id_jobAD, because the URL already identifies the job.

- Before inserting, the API should check that both the job ad and the applicant exist.

-Our schema also has this constraint:
UNIQUE (id_jobAD, id_applicant)

- This prevents the same applicant from applying to the same job twice. 
The API should handle that database error gracefully, ideally returning 409 Conflict for a duplicate application.



5. HTTP request anatomy 

* A request contains several parts. Understanding these is essential when we start testing routes.

* Consider:
POST /ads HTTP/1.1
Host: localhost:3000
Content-Type: application/json

* With a body:
{
  "title": "Junior PHP Developer",
  "position": "Backend Developer",
  "id_company": 1,
  "id_category": 3,
  "description_jobAD": "Build backend applications."
}

* Method and path
POST /ads
Which operation and resource are involved?

* Headers
Content-Type: application/json
Tells the server the format of the request body.

* Body

The JSON object containing the new job's fields.
Contains the information the server needs to create the resource.


* What is JSON?
JSON stands for JavaScript Object Notation. 
It's a text format commonly used to exchange structured data between a front-end and a backend.

For example:
{
  "title": "Frontend Developer",
  "id_company": 1
}

JSON looks similar to a JavaScript object, but there are important differences:
- JSON keys and string values use double quotes.
- JSON cannot contain functions, comments, or undefined.
- Numbers and booleans are not quoted.
- The body travels as text and must be parsed by the server.

* On the front-end, you might convert a JavaScript object into JSON with JSON.stringify(). The backend parses it into data it can validate and use.



6. HTTP responses and status codes

* The server must communicate what happened. A status code is part of the HTTP response and tells the client whether the request succeeded or failed.

These are the most important codes :  

* 200 : OK : A request succeeded. Commonly used for successful GET requests
Example: GET /ads/1 finds the ad

* 201 : Created : A new resource was successfully created
Example: POST /ads creates a job ad

* 400 : Bad Request : The request is invalid, such as missing a required field or containing invalid input, according to our API's validation rules

* 404 : Not Found : The requested company, ad, or other required resource doesn't exist.

* 409 : Conflict : The request conflicts with the current state of the database. For example, an applicant tries to apply to the same job twice.

* 500: internal Server Error : An unexpected server error occurred. Log the details on the server, but don't expose database credentials or stack traces to clients.


7. Validation: never trust the request

* Our API receives data from a client. That client could be our own website, Postman, a script, or someone deliberately sending invalid data.

* Even if our front-end checks required fields, the backend must validate them again.

* For example, this request is incomplete:
{
  "title": "Junior Developer"
}

Our jobAD table requires id_company and id_category as well as title. 
The API should reject the request with 400 Bad Request instead of attempting the insertion.


* Our validation checklist for HireLoop:

Field	                    Validation
title	                    Required, non-empty string
position	                Optional, 
description_jobAD	        Optional,
id_company	                Required, valid integer referring to an existing company
id_category	                Required, valid integer referring to an existing category
id_applicant	            Required for an application; must refer to an existing applicant
message	                    Validate according to our chosen application rules


* The distinction between required and optional matters. Your SQL schema defines which columns require values, while our API defines additional rules about the data clients may submit.

* For example, position is nullable in our database. we could allow it to be omitted from the request, while requiring it would be an additional API-level rule.