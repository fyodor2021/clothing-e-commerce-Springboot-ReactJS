print('START');


db = db.getSiblingDB('cart-microservice');

db.createUser({
    user:'root',
    pwd: 'root',
    roles: [{role: 'readWrite', db: 'cart-microservice'}],
});

db.createCollection('cart-microservice');


print('END');


