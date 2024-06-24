print('START');


db = db.getSiblingDB('shift-microservice');

db.createUser({
    user:'root',
    pwd: 'root',
    roles: [{role: 'readWrite', db: 'shift-microservice'}],
});

db.createCollection('shift-microservice');


print('END');


