package org.karaoke.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersistentQueryMap {

    ConcurrentHashMap<String,String> persistentQuery;

    private final String query = "query selectKaraoke($karaoke : karaoke){\n" +
            "  Karaoke(karaoke:$karaoke){\n" +
            "    number\n" +
            "    title\n" +
            "    singer\n" +
            "  }\n" +
            "}";

    private final String structureQuery ="\n" +
            "  query IntrospectionQuery {\n" +
            "    __schema {\n" +
            "      queryType { name }\n" +
            "      mutationType { name }\n" +
            "      subscriptionType { name }\n" +
            "      types {\n" +
            "        ...FullType\n" +
            "      }\n" +
            "      directives {\n" +
            "        name\n" +
            "        description\n" +
            "        locations\n" +
            "        args {\n" +
            "          ...InputValue\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "\n" +
            "  fragment FullType on __Type {\n" +
            "    kind\n" +
            "    name\n" +
            "    description\n" +
            "    fields(includeDeprecated: true) {\n" +
            "      name\n" +
            "      description\n" +
            "      args {\n" +
            "        ...InputValue\n" +
            "      }\n" +
            "      type {\n" +
            "        ...TypeRef\n" +
            "      }\n" +
            "      isDeprecated\n" +
            "      deprecationReason\n" +
            "    }\n" +
            "    inputFields {\n" +
            "      ...InputValue\n" +
            "    }\n" +
            "    interfaces {\n" +
            "      ...TypeRef\n" +
            "    }\n" +
            "    enumValues(includeDeprecated: true) {\n" +
            "      name\n" +
            "      description\n" +
            "      isDeprecated\n" +
            "      deprecationReason\n" +
            "    }\n" +
            "    possibleTypes {\n" +
            "      ...TypeRef\n" +
            "    }\n" +
            "  }\n" +
            "\n" +
            "  fragment InputValue on __InputValue {\n" +
            "    name\n" +
            "    description\n" +
            "    type { ...TypeRef }\n" +
            "    defaultValue\n" +
            "  }\n" +
            "\n" +
            "  fragment TypeRef on __Type {\n" +
            "    kind\n" +
            "    name\n" +
            "    ofType {\n" +
            "      kind\n" +
            "      name\n" +
            "      ofType {\n" +
            "        kind\n" +
            "        name\n" +
            "        ofType {\n" +
            "          kind\n" +
            "          name\n" +
            "          ofType {\n" +
            "            kind\n" +
            "            name\n" +
            "            ofType {\n" +
            "              kind\n" +
            "              name\n" +
            "              ofType {\n" +
            "                kind\n" +
            "                name\n" +
            "                ofType {\n" +
            "                  kind\n" +
            "                  name\n" +
            "                }\n" +
            "              }\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n";

    @PostConstruct
    public void setUp(){
        persistentQuery = new ConcurrentHashMap<>();
        persistentQuery.put("0",structureQuery);
        persistentQuery.put("1",query);

    }


    public Map<String,String> getQueryMap(){
        return persistentQuery;
    }

    public String getQuery(String key){
        return persistentQuery.get(key);
    }

    public void insertQuery(String key, String value){
        persistentQuery.put(key,query);
    }

    public boolean hasKey(String key){
        return persistentQuery.contains(key);
    }

}
