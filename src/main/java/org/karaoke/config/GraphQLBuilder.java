package org.karaoke.config;

import graphql.GraphQL;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Company;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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

    GraphQLObjectType objectType = newObject()
            .name("selectKaraoke")
            .field(newFieldDefinition()
                    .name("Karaoke")
                    .type(new GraphQLList(Karaoke))
                    .dataFetcher((env) -> {
                        Argument arg = new Argument()
                                .setCompany(Company.valueOf(env.getArgument("company")))
                                .setCategory(Category.valueOf(env.getArgument("category")))
                                .setWord(env.getArgument("keyword"));
                        return service.parseKaraoke(arg, env.getArgument("page"));
                    })
                    .argument(GraphQLArgument.newArgument()
                            .name("company")
                            .type(GraphQLString))
                    .argument(GraphQLArgument.newArgument()
                            .name("category")
                            .type(GraphQLString))
                    .argument(GraphQLArgument.newArgument()
                            .name("keyword")
                            .type(GraphQLString))
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
