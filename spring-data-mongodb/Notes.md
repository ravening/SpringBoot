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
criteria for filtering data. Mandatory
sorting definition for ordering data
paging definition


Query execution

without index
Each document is scanned and evaluated. slow search. fast insert/update


with index
Each document is not scanned. fast search. slow insert/update



@TextIndexed works only on string or array of strings
Each document is scanned
a score is computed internally based of the textindex weight
results are sorted based on score

Weights are added using
@TextIndexed(weight=2) which means this field 2 times more important than other fields

Converters are used to map between java objects and mongo db type

First create a write converter to map java object to mongo
Then write reader converter to read from mongo to java
register them as spring beans

