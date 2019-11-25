MealRestControllerTest

getAll (GET method)
curl http://localhost:8080/rest/meals   

get (GET method)
curl http://localhost:8080/rest/meals/100002 

createWithLocation (POST method)
curl -X POST -H "Content-Type: application/json" -d '{"dateTime": "2019-11-25T10:00:00","description": "Dinner","calories": 666}' http://localhost:8080/rest/meals

delete (DELETE method)
curl -X DELETE http://localhost:8080/rest/meals/100003

update (PUT method)
url -X PUT -H "Content-Type: application/json" -d '{"dateTime":"2015-05-30T20:00:00", "description": "Lunch", "calories": 250}' http://localhost:8080/rest/meals/100004

getBetween (GET method)
curl http://localhost:8080/rest/meals/between?startDate=30-05-2015&startTime=00:00&endDate=30-05-2015&endTime=21:00
