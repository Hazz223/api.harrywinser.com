# api.harrywinser.com

Simple project to host the api for the multiple blogging portals i now seem to have (tech.harrywinser.com, harrywinser.com).
This project should allow me to separate the frontends from the backend. I'll attempt to make it generic as possible, so
anyone can fork it and attach their own database etc. I'll attach the schema too (found under the SQL folder).

## API

#####Getting Articles
`article/{name, clean_name, or id}`
Get an article either by it's id, or by it's name or clean_name. This will return you a single article.
This will throw a 404 if none are found

Example: /article/example_page.

`article/type/{type}?page={number}&limit={number}&sort={sorting}`

Get all articles by type. They will be returned in an array. The current types are: blog, review, tech, all.
This will throw a 404 if the type is not found. However, it will return an empty list if no content is found for
a valid type.
This data is contained within the Spring pagination. You can check out more of the docs here:

[Springboot Pagination Documentation] (http://docs.spring.io/spring-data/rest/docs/1.1.x/reference/html/paging-chapter.html)

Example: /article/type/blog?page2&limit=3

#####Searching By type

`article/type?search={first-type},{second-type}&page={number}&limit={number}&sort={sorting}`

You can also search using a list of types. This will return a list of articles by the different types, in date order.

Example: /article/type?search=blog,review

`article?search={some name}&page={number}&limit={number}&sort={sorting}`

Searches, and will return any matches where the name or clean_title contains / matches. The same pageination rules apply as above.
This will return an empty list if none are found.

Example: /article?searchTerm=example_blog&page=1&limit=2

Please note that search is currently not working 100%. For some arbitrary reason, it doesn't always search the content in the
main data block. Not 100% sure why. But will update once I've gotten to the bottom of this.

## Installation

This all runs on Springboot. Make sure you've got Java 8 installed, and then navigatae to the cloned project.
Use this command: `gradlew bootRun` and you should see everything working.

Remember that linux / macs block port 80.

You'll also need to update the connection strings. Take a look at the application.properties.template and update them to
point at your database.






