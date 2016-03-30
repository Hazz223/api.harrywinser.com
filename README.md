# api.harrywinser.com

Simple project to host the api for the multiple blogging portals i now seem to have (tech.harrywinser.com, harrywinser.com).
This project should allow me to separate the frontends from the backend. I'll attempt to make it generic as possible, so
anyone can fork it and attach their own database etc. I'll attach the schema too (found under the SQL folder).

## API

'article/{name, clean_name, or id}'
Get an article either by it's id, or by it's name or clean_name. This will return you a single arctile.

Example: /article/example_page.

'article/type/{type}?page={number}&limit={number}&sort={sorting}'

Get all articles by type. They will be returned in an array. The current types are: blog, review, tech, all.
This data is contained within the Spring pagination. You can check out more of the docs here:

'http://docs.spring.io/spring-data/rest/docs/1.1.x/reference/html/paging-chapter.html'

Example: /article/type/blog?page2&limit=3

'article?searchTerm={some name}'

Searches, and will return any matches where the name or clean_title contains / matches. The same pageination rules apply as above.

Example: /article?searchTerm=example_blog&page=1&limit=2


## Installation

This all runs on Springboot. Make sure you've got Java 8 installed, and then navigatae to the cloned project.
Use this command: ```gradlew bootRun``` and you should see everything working.

Remember that linux / macs block port 80.

You'll also need to update the connection strings. Take a look at the application.properties.template and update them to
point at your database.






