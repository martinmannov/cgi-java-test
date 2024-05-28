# CGI-Loan-TEST

## Installation

+ run `mvn clean install`
+ run `mvn dockerfile:build`
+ run `docker run -p 8080:8080  mmannovhm/cgi-loan:latest`

## Data structures

### Loan

+ id: 12311515 (bigint)
+ principalAmount: 10000 (big decimal, required)
+ interestRate: 3.5 (big decimal, required) - in #.##%
+ term: 3 (int, required) - in months

## API EndPoints

<details>
 <summary><code>POST</code> <code><b>/api/loan</b></code> <code>(save a new loan)</code></summary>

##### Parameters

> | name | type     | data type          | example                                                         |
> |------|----------|--------------------|-----------------------------------------------------------------|
> | None | required | loan object (JSON) | { "principalAmount": 10000.11, "interestRate": 1.50, "term": 1} |

##### Responses

> | http code | content-type               | response                                                                            |
> |-----------|----------------------------|-------------------------------------------------------------------------------------|
> | `201`     | `text/plain;charset=UTF-8` | `{"id": 11,"principalAmount": 10000.00,"interestRate": 3.50,"term": 3,"npv": 6.78}` |
> | `400`     | `application/json`         | `{"code":"400","message":"Bad Request"}`                                            |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" --data @post.json http://localhost:8080/api/loan
> ```

</details>

<details>
  <summary><code>GET</code> <code><b>/api/loan</b></code> <code>(get all loans)</code></summary>

##### Parameters

##### Responses

> | http code | content-type       | response                                                                      |
> |-----------|--------------------|-------------------------------------------------------------------------------|
> | `200`     | `application/json` | [ { "id": 1, "principalAmount": 10000.00, "interestRate": 3.50, "term": 60 }] |
> | `400`     | `application/json` | `{"code":"400","message":"Bad Request"}`                                      |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/loan
> ```

</details>

<details>
  <summary><code>GET</code> <code><b>/api/loan/{id}</b></code> <code>(get a specific loan by id)</code></summary>

##### Parameters

> | name | type     | data type | description                         |
> |------|----------|-----------|-------------------------------------|
> | `id` | required | string    | The specific loan unique identifier |

##### Responses

> | http code | content-type       | response                                                                   |
> |-----------|--------------------|----------------------------------------------------------------------------|
> | `200`     | `application/json` | { "id": 1, "principalAmount": 10000.00, "interestRate": 3.50, "term": 60 } |
> | `400`     | `application/json` | `{"code":"400","message":"Bad Request"}`                                   |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/loan/1
> ```

</details>

<details>
  <summary><code>PUT</code> <code><b>/api/loan/{id}</b></code> <code>(updates given loan)</code></summary>

##### Parameters

> | name | type     | data type | description                         |
> |------|----------|-----------|-------------------------------------|
> | `id` | required | string    | The specific loan unique identifier |

##### Responses

> | http code | content-type       | response                                                                                                 | description                                                     |
> |-----------|--------------------|----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------|
> | `200`     | `application/json` | { "id": 1, "principalAmount": 10000.00, "interestRate": 3.50, "term": 60,"npv": 3.021E,"oldNpv": 2E-10 } | oldNpv was value before update and npv is current/updated value |
> | `400`     | `application/json` | `{"code":"400","message":"Bad Request"}`                                                                 |                                                                 |

##### Example cURL

> ```javascript
>  curl -X PUT -H "Content-Type: application/json" --data @put.json http://localhost:8080/api/loan/1
> ```

</details>