# HAProxy REST API

Now, you can call haproxy with the REST API.


## Overview  
This rest api server runs on a docker base. The haproxy 1.7.9 is installed inside the docker, and the Java spring-based API server runs haproxy as an external process. The network of the Docker container must be executed by the host method, and the forwarding ports that are set are opened to the port of the host machine. The data of the API uses a unit called service and it is set by inputting Id and Port, and one Id can have several sub-ports.


## REST API

### API 

|#|METHOD|URI| Description |
|---|---|---|---|
|1|GET    | /config                            | View the entire configuration
|2|POST   | /config/services/{id}              | Add service with custom port |
|3|DELETE | /config/services/{id}/ports/{bind port} | Delete service with the port |


### Service object

| # | Name | Type | Description |
|---|---|---|---|
|1|mode|String|TCP or HTTP |
|2|bindPort|Integer|Port number|
|3|host|String|Hostname or IP Address|
|4|timeout|Integer|Timeout for server,client and connection|
|5|(Opt) subdomain|String|Subdomain name when mode is HTTP|

## Usage

Bind service port on 9999 

```
docker run 
-d 
-v /<host>/conf:/var/lib/haproxy-restapi/conf 
--restart always 
-p 9999:9999   # REST API 포트(필수)
-p 8080:8080   # 서비스용 포트(선택)
haproxy-restapi:2.0
```
## 포트에 관하여
네트워크 모드는 bridge로 사용한다. 이때 bind port 갯수만큼 -p 옵션으로 미리 host의 포트를 열어두어야 한다. 
haproxy-restapi 포트는 9999를 사용하지만, 서비스는 8080으로 할수 있다.
이때 -p 8080:8080 옵션을 주어 호스트의 8080으로 들어오는 요청들이 haproxy가 받아서 밸런싱을 할수 있도록 한다.
 

## 사용법 예제

subdomain은 항상 uniq 해야 한다. PK와 같은 존재. 

myblog 서비스를 만들면서 8080 에서 리스닝한다.

song.danawa.io 로 들어오면 192.168.0.8의 80포트로 보낸다.

bindPort는 여러 서비스간에 겹쳐서 선언할수 있다.

```
POST /config/services/myblog
{
    "mode": "http",
    "bindPort": 8080,
    "host": "192.168.0.8",
    "port": 80,
    "timeout": 5000,
    "subdomain": "song"
}
```

전체 설정을 확인한다.

```
GET /config
{
    "myblog_8080_asdf4": {
        "mode": "http",
        "bindPort": 8080,
        "host": "192.168.0.6",
        "port": 8080,
        "timeout": 5000,
        "subdomain": "asdf4"
    },
    "myblog_8080_asdf1": {
        "mode": "http",
        "bindPort": 8080,
        "host": "192.168.0.7",
        "port": 8080,
        "timeout": 5000,
        "subdomain": "asdf1"
    }
.. 생략...
}
```

특정 룰을 삭제한다.
```
DELETE /config/services/myblog/ports/8080?acl=asdf5
{
    "mode": "http",
    "bindPort": 10000,
    "host": "google.com",
    "port": 81,
    "timeout": 5000,
    "subdomain": "asdf5"
}
```
삭제성공시에는 삭제 객체가 출력된다.