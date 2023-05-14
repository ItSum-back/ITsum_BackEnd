# ITsum_BackEnd
**Kakao** , **Google**

Request 

```jsx
POST /auth/kakao HTTP 1.1

Accept :  */*

Connection : keep-alive

Content-Type : application/json

{"accessToken": "accessToken을 Body에 담아서 주세요. " }
```


Response

```jsx
HTTP/1.1 : 200OK
Content-Type : application/json

{
    "appToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzcxMzc0MTA0Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzI4NTc0NH0.o0Um132beR0w8sPSU7Hx5VMhv5mqxlR5zGwWnXrS1Bg",
    "isNewMember": true
}
```

**Refresh**

Request 

```jsx
GET /auth/refresh HTTP 1.1

Authorization:  Bearer appToken

Accept : */*

Connection : keep-alive

Content-Type : application/json

Authorization: Bearer appToken

```

Response

```jsx
HTTP/1.1 : 200OK
Content-Type : application/json

{
    "appToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzcxMzc0MTA0Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzI4NjI2N30.RBpZ9sFgC1uak1WpAA0hwHGshxMj8xXnnWvB4aB4hsM",
    "isNewMember": null
}

```

- 만료에 대한 응답 

---

```jsx
상태코드 : 403 forbidden
```

