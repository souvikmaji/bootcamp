# Micro-services Boot Camp

This boot camp involves an exercise in designing an application specifically built for deploying
and maintaining in modern cloud environments. As part of this exercise, we will try to solve a very
simple problem by designing the solution as a collection of loosely coupled services and
orchestrating them within a self-contained and isolated network.

## Purpose

This boot camp serves twin purposes, that is to initiate newcomers, to micro-services by
demonstrating the usefulness of micro-services as well as demonstrating the typical challenges in
deploying the services in a cloud-native environment. It also demonstrates the usefulness of
containerisation in the context of micro-services and cloud-native deployments.

## The Problem

The problem is to generate and mine Lucky Hashes, which are essentially hex encoded SHA-
hashes starting with "0". The hashes are computed from secure random tokens (hex encoded
random bytes) of a given size and stored in a data store. The problem also includes **real-time**
display of the mining process.

## Applying Microservices to solve the problem

The primary concern of this project is to develop an application composed of four micro-services
deployed in a self-contained and isolated network, which in unison are responsible for mining the
Lucky Hashes and displaying the progress of mining. The required micro-services are listed
below:

1. Secure Token Generator stg - This is a RESTFUL web service responsible for generating
    secure random tokens.
2. Hasher - This too is a RESTFUL web service which takes a token as input, computes an SHA-
    256 hash of the token and responds with the hex encoded the representation of the hash.
3. Worker - This is a service which reacts to timer events to perform the task of storing and
    mining of the Lucky Hashes by orchestrating the other micro-services.
4. WebUI - This is a service responsible for displaying the rate of mining through a web page by
    updating a line chart on the web page at real-time.

The hashes should be stored in Redis.


```
Property Value
```
```
VERB GET
```
```
URL http://stg/tokens/:size
```
```
Content-Type application/json
```
The diagram below depicts the high-level architecture of the solution:

### Services:

#### Stg - Secure Token Generator

This service should be implemented as a restful web service adhering to the following
specification:

**Request:**

| Property | Value |
| ---------- | ------ |
| VERB | GET |
| URL | http://stg/tokens/:size  |
| Content-Type | application/json |

> NB: size is a restful URL parameter for specifying the size in bytes of the random token before hex encoding.

**Response:**

| Property | Value |
| ---------- | ------ |
| Content-Type | application/json |
| Body | { "token": "b35b06bd3f24237...074aa09c5263d"} |

Make sure the implementation includes the following:

1. Unit tests
2. Build script Makefile
3. Dockerfile
4. .gitignore
5. README.md containing all relevant information like build and setup instructions and
    instructions for starting the service, etc.

#### Hasher

This service should also be implemented as a restful web service adhering to the following
specification:

**Request:**

| Property | Value |
| ---------- | ------ |
| VERB | POST |
| URL | http://hasher/ |
| Content-Type | application/json |
| Body | { "token": "b35b06bd3f24237...074aa09c5263d"} |

**Response:**

| Property | Value |
| ---------- | ------ |
| Content-Type | application/json |
| Body | {"hash": "73483cb06571211730db...abc336c14349170af6a"}  |

> N.B: The token received as an input argument (in the request body) should be hex decoded before
and computing the hash and the computed hash should be hex encoded before forming
the response body.

Make sure the implementation includes the following:

1. Unit tests
2. Build script Makefile
3. Dockerfile
4. .gitignore
5. README.md containing all relevant information like build and setup instructions and
    instructions for starting the service, etc.

#### Worker

This should be implemented as a standalone worker service which reacts to timer events and
performs the following tasks:

1. Call stg service to generate a secure token.
2. Call hasher to generate a hash of the token.
3. Check if the hash is Lucky
4. Store the hashes in redis
5. Publish notification data in a redis topic/channel.

Make sure the implementation includes the following:

1. Unit tests
2. Build script Makefile
3. Dockerfile
4. .gitignore
5. README.md containing all relevant information like build and setup instructions and
    instructions for starting the service, etc.

#### Web UI

The web-based user interface should be implemented as a web application and should be
developed using Node.js. The application should be able to serve an html page containing a line
chart that is updated in real time. Use of Socket.io is recommended to introduce real-time
features and Chartist for displaying line charts with javascript.

The application should be designed to subscribe to a Redis topic/channel and should update the
line chart whenever the worker publishes data on the topic/channel.

## Composing the services

Finally, compose the entire application comprising of the above services using Docker Compose.
Make sure to expose the web UI to be available at host machine port 3000 (http://localhost:3000).
