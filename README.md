# java-ecomerce

Ecommerce Backend desarrollado con Spring framework. Es una aplicación de e-commerce para vender productos.

### Se uso para el desarrollo:
- Programacion Orientada a Aspetos (AOP)
- MongoDB
- Spring Boot
- Spring Boot mail
- Spring Data con MongoDB
- JWT
- Redis

### APIs implementadas:
|          	| GET                                                         	| POST                  	| PUT                      	| DELETE                   	|
|----------	|-------------------------------------------------------------	|-----------------------	|--------------------------	|--------------------------	|
| Category 	| getAllCategories<br>getCategoryByCode                       	| createCategory        	| updateCategory           	| deleteCategory           	|
| Product  	| getProductByCode<br>getAllProducts<br>getProductsByCategory 	| createProduct         	| updateProduct            	| deleteProduct            	|
| User     	|                                                             	| register<br>login     	|                          	|                          	|
| Cart     	| getCartByEmail<br>getAllCarts<br>getAllItems                	| createCart<br>addItem 	| updateCart<br>updateItem 	| deleteCart<br>deleteItem 	|
| Order    	| getAllOrders<br>getOrderByOrderNumber                       	| createOrder           	|                          	|                          	|
