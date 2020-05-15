MATCH (n) DETACH DELETE n

MATCH (e:Equipment)-[r:INTERFACE]->(i:Interface) RETURN e,r,i

MATCH (e:Equipment)-[r:INTERFACE]->(i:Interface) where e.name = "myswitch" RETURN e,r,i

MATCH p=()-->() RETURN p LIMIT 1000

## Get Equipments
```
MATCH (n:Equipment) RETURN n LIMIT 25
```

## Get interfaces
```
MATCH (n:Interface) RETURN n LIMIT 25
```

