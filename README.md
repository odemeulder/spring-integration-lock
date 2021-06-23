# Distributed locks in PSQL with Springboot

## Create database

```bash
psql
create database orders;
create user orders;
alter user orders with encrypted password 'password'
grant all privileges on database orders to orders;
grant all privileges on all tables in schema public to orders;
\c orders
create table reservation (id int primary key, name varchar);
insert into reservation (id, name) values (1, 'Olivier');
insert into reservation (id, name) values (2, 'Allison');
```

## Run application

Spin up two versions of the application (that is why the port is set to 0).
Grab the port number that the application is listening to.

First curl: `curl http://localhost:xxxxx/reservation/1/Olivier/30000`

This will Update reservation 1, and wait for 30 seconds. During that time a lock is in place.

Second curl: `curl http://localhost:xxyyy/reservation/1/Matthew/5000`

The second curl won't do anything, because the lock that is held.

## Spring integration

Spring integration and JdbcLockRegistry do all the work. New tables get created, including INT_LOCK to handle the locks.