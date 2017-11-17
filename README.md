# NapptilusTest
Napptilus entry level test

## Used libraries
### RxJava2:
Used to simplify thread handling and avoid the callback hell which usually exist on a multi-module application. In 
addition RxJava provides a lot of useful operators for reactive programming. 

### Dagger2:
It is a dependency injection framework automatically creates factories for all of your classes.
I used it to generate a dependency injection tree divided by scopes for each section of the application. One of the 
advantages of use Dagger it's I can retain all non-view instances (like presenters, use cases...) during the rotation.

### Retrofit2:
Retrofit has given the API's to make server call and to receive response. 
Internally they also use GSON to do the parsing. And it's fully compatible with RxJava 2.

### Picasso:
Picasso download the image in another thread and it manages for you. And also includes a cache feature. 
 
### Circular ImageView:
View which extends from ImageView used to show images cropped in circle.
