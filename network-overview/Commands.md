MATCH (n) DETACH DELETE n

MATCH (e:Equipment)-[r:INTERFACE]->(i:Interface) RETURN e,r,i

MATCH (e:Equipment)-[r:INTERFACE]->(i:Interface) where e.name = "myswitch" RETURN e,r,i

MATCH p=()-->() RETURN p LIMIT 25
