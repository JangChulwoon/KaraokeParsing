package org.karaoke.config;

import graphql.GraphQL;
import graphql.schema.*;
import static graphql.schema.GraphQLInputObjectType.*;
import static graphql.schema.GraphQLEnumType.*;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
@Slf4j
public class GraphQLBuilder {
    // 코드 정리가 필요할거 같다

    @Autowired
    private KaraokeService service;

    private GraphQLObjectType Karaoke = newObject()
            .name("Karaoke")
            .description("노래방 번호,제목,가수명을 갖고있는 Type 입니다. ")
            .field(newFieldDefinition()
                    .name("number")
                    .type(GraphQLString))
            .field(newFieldDefinition()
                    .name("title")
                    .type(GraphQLString))
            .field(newFieldDefinition()
                    .name("singer")
                    .type(GraphQLString))
            .build();

    private GraphQLEnumType company = newEnum()
            .name("COMPANY")
            .value("KY")
            .value("TJ")
            .build();

    private GraphQLEnumType category = newEnum()
            .name("CATEGORY")
            .value("SINGER")
            .value("SONG")
            .build();

    private GraphQLInputObjectType karaoke = GraphQLInputObjectType.newInputObject()
            .name("karaoke")
            .field(GraphQLInputObjectField.newInputObjectField()
                    .name("company")
                    .type(company)
                    .build())
            .field(GraphQLInputObjectField.newInputObjectField()
                    .name("category")
                    .type(category)
                    .build())
            .field(GraphQLInputObjectField.newInputObjectField()
                    .name("keyword")
                    .type(GraphQLString)
                    .build())
            .build();

    GraphQLObjectType objectType = newObject()
            .name("selectKaraoke")
            .field(newFieldDefinition()
                    .name("Karaoke")
                    .type(new GraphQLList(Karaoke))
                    .dataFetcher((env) -> {
                        Map<String,String> map = new HashMap();
                        map = env.getArgument("karaoke");
                        log.info("{}",map);
                         Argument arg = new Argument()
                                .setCompany(Company.valueOf(map.get("company")))
                                .setCategory(Category.valueOf(map.get("category")))
                                .setWord(map.get("keyword"));
                        return service.parseKaraoke(arg, env.getArgument("page"));
                    })
                    .argument(GraphQLArgument.newArgument()
                            .name("karaoke")
                            .type(karaoke).build())
                    .argument(GraphQLArgument.newArgument()
                            .name("page")
                            .type(GraphQLInt))
            )
            .build();

    private GraphQLSchema schema = GraphQLSchema.newSchema()
            .query(objectType)
            .build();

    private GraphQL graphQL = GraphQL.newGraphQL(schema)
            .build();


    @Bean
    public GraphQL getGraphQL() {
        return graphQL;
    }
}
