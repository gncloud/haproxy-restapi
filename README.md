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
|3|DELETE | /config/services/{id}/ports/{port} | Delete service with the port |


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
-v /<host>/conf:/var/lib/haproxy-restapi/conf --restart always 
--network host 
-p 9999:9999
haproxy-restapi
```

