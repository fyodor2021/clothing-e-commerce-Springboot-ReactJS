print('START');


db = db.getSiblingDB('order-microservice');

db.createUser({
    user:'root',
    pwd: 'root',
    roles: [{role: 'readWrite', db: 'order-microservice'}],
});

db.createCollection('order-microservice');


print('END');





