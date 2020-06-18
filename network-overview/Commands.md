docker run --publish=7474:7474 --publish=7687:7687  -d -v $HOME/neo4j/data:/data -v $HOME/neo4j/logs:/logs -v $HOME/neo4j/import:/var/lib/neo4j/import -v $HOME/neo4j/plugins:/plugins neo4j:3.5.14

## Delete all
```
MATCH (n) DETACH DELETE n
```

## Get equipment and interface relationship
```
MATCH (e:Equipment)-[r:PORT]->(i:Interface) RETURN e,r,i
```

MATCH (e:Equipment)-[r:PORT]->(i:Interface) where e.name = "switch1" RETURN e,r,i

MATCH p=()-->() RETURN p LIMIT 1000

## Get Equipments
```
MATCH (n:Equipment) RETURN n LIMIT 25
```

## Get interfaces
```
MATCH (n:Interface) RETURN n LIMIT 25
```

## Get vlans
```
MATCH (v:Vlan),(i:Interface) WHERE v.vlanid = 100 RETURN v,i
```
