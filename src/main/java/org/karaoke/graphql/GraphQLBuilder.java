package org.karaoke.graphql;

import com.github.benmanes.caffeine.cache.Cache;
import graphql.GraphQL;
import graphql.execution.ExecutorServiceExecutionStrategy;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.schema.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.concurrent.ExecutorService;

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

    @Autowired
    ExecutorService es;

    @Autowired
    Cache<String, PreparsedDocumentEntry> parsedDocumentCache;

    private GraphQL graphQL;



    @PostConstruct
    public void setUp() {

        GraphQLObjectType Karaoke = newObject()
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

        GraphQLEnumType company = newEnum()
                .name("COMPANY")
                .value("KY")
                .value("TJ")
                .build();

        GraphQLEnumType category = newEnum()
                .name("CATEGORY")
                .value("SINGER")
                .value("SONG")
                .value("NUMBER")
                .build();

        GraphQLInputObjectType karaoke = GraphQLInputObjectType.newInputObject()
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

        GraphQLObjectType objectType = newObject().name("selectKaraoke")
                .field(newFieldDefinition()
                        .name("Karaoke")
                        .type(new GraphQLList(Karaoke))
                        .dataFetcher(dataFetcher)
                        .argument(GraphQLArgument.newArgument()
                                .name("karaoke")
                                .type(karaoke))
                        .argument(GraphQLArgument.newArgument()
                                .name("page")
                                .type(GraphQLInt))).build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .build();

        graphQL = GraphQL.newGraphQL(schema)
                .queryExecutionStrategy(new ExecutorServiceExecutionStrategy(es))
                .preparsedDocumentProvider(parsedDocumentCache::get)
                .build();
    }

    @Bean
    public GraphQL getGraphQL() {
        return graphQL;
    }
}
