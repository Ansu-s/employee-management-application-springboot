# Employee Management Application 
![Screenshot (137)](https://github.com/learning-project-01/bookstore-app/assets/130679461/f4710b42-aa1e-4905-8a97-3a81dd7c92cb)
> Use this Postman [Employee Management Application](https://www.postman.com/rtxon005/workspace/api-testing/collection/21955767-36c1813d-fc7b-4309-bba0-b41bae043558?action=share&creator=21955767&active-environment=21955767-3c098dcc-09cc-46f9-be44-979df1d776a0) collection to test the APIs

> Modify the applications.properties file to change the run configurations
> The Swagger UI is available at `http://localhost:8080/my-swagger-ui` 
## API Documentation
<details>
  <summary>API Documentation</summary>

{"openapi":"3.0.1","info":{"title":"OpenAPI definition","version":"v0"},"servers":[{"url":"http://localhost:8080","description":"Generated server url"}],"paths":{"/employees/{id}":{"get":{"tags":["employee-controller"],"operationId":"getEmployeeById","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"integer","format":"int64"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"$ref":"#/components/schemas/Employee"}}}}}},"put":{"tags":["employee-controller"],"operationId":"updateEmployee","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"integer","format":"int64"}}],"requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/Employee"}}},"required":true},"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"$ref":"#/components/schemas/Employee"}}}}}},"delete":{"tags":["employee-controller"],"operationId":"deleteEmployee","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"integer","format":"int64"}}],"responses":{"204":{"description":"No Content"}}}},"/employees":{"get":{"tags":["employee-controller"],"operationId":"getAllEmployees","responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"array","items":{"$ref":"#/components/schemas/Employee"}}}}}}},"post":{"tags":["employee-controller"],"operationId":"createEmployee","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/Employee"}}},"required":true},"responses":{"201":{"description":"Created","content":{"*/*":{"schema":{"$ref":"#/components/schemas/Employee"}}}}}}},"/employees/search/{name}":{"get":{"tags":["employee-controller"],"operationId":"findEmployeeByName","parameters":[{"name":"name","in":"path","required":true,"schema":{"type":"string"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"array","items":{"$ref":"#/components/schemas/Employee"}}}}}}}},"/employees/age/{age}":{"get":{"tags":["employee-controller"],"operationId":"getEmployeeByAge","parameters":[{"name":"age","in":"path","required":true,"schema":{"type":"integer","format":"int64"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"$ref":"#/components/schemas/Employee"}}}}}}}},"components":{"schemas":{"Employee":{"type":"object","properties":{"id":{"type":"integer","format":"int64"},"name":{"type":"string"},"age":{"type":"integer","format":"int32"},"salary":{"type":"number","format":"double"},"address":{"type":"string"}}}}}}
</details>
<details>
<summary>Screenshots</summary>

![Screenshot (137)](https://github.com/learning-project-01/bookstore-app/assets/130679461/f4710b42-aa1e-4905-8a97-3a81dd7c92cb)
![image](https://github.com/learning-project-01/bookstore-app/assets/130679461/ae3668c3-fa42-42c8-b533-3d304bbcae12)
![image](https://github.com/learning-project-01/bookstore-app/assets/130679461/7ee79526-d773-4415-8ec2-26957f945e94)
![image](https://github.com/learning-project-01/bookstore-app/assets/130679461/708770e7-4a35-441b-9b2f-c8b811054e45)
![image](https://github.com/learning-project-01/bookstore-app/assets/130679461/28c905d8-afca-45c8-b3be-0778b73f0c99)


</details>

## CURL Commands
- Create a new employee 
```
curl --location 'http://localhost:8080/employee' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "name": "user",
    "age": 500,
    "salary": 90000,
    "address": "Delhi"
  }'
```
- Fetch all the employees list form the database
```
curl -X 'GET' \
  'http://localhost:8080/employees' \
  -H 'accept: */*'
```
- Get an employee with specific id
```
curl -X 'GET' \
  'http://localhost:8080/employees/1' \
  -H 'accept: */*'
```
- Get an employee by its name
```
curl --location 'http://localhost:8080/employees/search/user'
```
- Get an employee by its age
```
curl --location 'http://localhost:8080/employees/age/30'
```
- Update an employee data by using its id
```
curl --location --request PUT 'http://localhost:8080/employees/1' \
--header 'Content-Type: application/json' \
--data '{
  "id": 1,
  "name": "Naru",
  "age": 24,
  "salary": 16000,
  "address": "Bhubaneswar"
}'
```
- Delete an employee by its id
```
curl --location --request DELETE 'http://localhost:8080/employees   /2'
```