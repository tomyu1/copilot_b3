# Getting Started

### Reference Documentation

### insert test CURL:
curl --location 'localhost:8080/insert' \
--header 'Content-Type: application/json' \
--data '[
{
"countryCode": "CN",
"countryDesc": "Chinese",
"holidayDate": "2033-10-01",
"holidayName": "33 Chinese national day"
},
{
"countryCode": "CN",
"countryDesc": "Chinese",
"holidayDate": "2034-10-01",
"holidayName": "34 Chinese national day"
},
{
"countryCode": "CN",
"countryDesc": "Chinese",
"holidayDate": "2034-10-01",
"holidayName": "34 Chinese national day"
}
]'

### queryNextYear CURL:
curl --location --request GET 'localhost:8080/queryNextYear?countryCode=CN'

### update holiday By CountryCode And HolidayDate CURL:
curl --location 'localhost:8080/updateByCountryCodeAndHolidayDate' --header 'Content-Type: application/json' --data '[
    {
        "countryCode": "CN",
        "countryDesc": "Chinese",
        "holidayDate": "2033-10-01",
        "holidayDesc": "New 33 Chinese national day"
    }
]'

### remove holiday by CountryCode And HolidayDate CURL:
curl --location 'localhost:8080/removeByCountryCodeAndHolidayDate' --header 'Content-Type: application/json' --data '{
"countryCode": "CN",
"countryDesc": "Chinese",
"holidayDate": "2033-10-01",
"holidayName": "New 33 Chinese national day"
}
'

### query next latest holiday by CountryCode CURL:
curl --location --request GET 'localhost:8080/queryNextHoliday?countryCode=CN'
'

### query specific day is holiday in which country CURL:
curl --location --request GET 'localhost:8080/queryIsHoliday?date=2023-10-01'