@Document maps to tables

@Id are usually string in mongodb

we can use "name" attribute to use specific name for column

Use @Transient to store columns in memory but not to be persisted to db

Use @Indexed to index the fields so that we can perform lot of queries

We can also mention the direction of index, ascending or descending
It can be unique as well

Use @TextIndexed for full text search

@CompoundIndex allows us to create index from multiple fields

@CompoundIndex(def="{'fieldA':1, 'fieldB':-1}") means fieldA is ascending and fieldB is descending

@DbRef acts like a join between two Documents


You can fetch data in two ways
with index and without

without index each document is scanned. slow search. fast insert and update


with index, does not perform collection scan, fast search and slow inserts/updates

Three components of query
criteria for filtering data
sorting definition of data
paging definition




