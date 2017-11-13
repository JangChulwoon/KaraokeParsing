package org.karaoke.graphql;

import graphql.GraphQL;
import graphql.schema.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLEnumType.newEnum;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
@Slf4j
public class GraphQLBuilder {

    @Autowired
    DataFetcher dataFetcher;

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

    GraphQLObjectType objectType;
    private GraphQL graphQL;
    @PostConstruct
    public void setUp(){
        objectType = newObject()
                .name("selectKaraoke")
                .field(newFieldDefinition()
                        .name("Karaoke")
                        .type(new GraphQLList(Karaoke))
                        .dataFetcher(dataFetcher)
                        .argument(GraphQLArgument.newArgument()
                                .name("karaoke")
                                .type(karaoke))
                        .argument(GraphQLArgument.newArgument()
                                .name("page")
                                .type(GraphQLInt))
                )
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .build();
        graphQL = GraphQL.newGraphQL(schema)
                .build();
    }


    @Bean
    public GraphQL getGraphQL() {
        return graphQL;
    }
}
