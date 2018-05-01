[![Build Status](https://travis-ci.org/JangChulwoon/KaraokeParsing.svg?branch=master)](https://travis-ci.org/JangChulwoon/KaraokeParsing)    
[![Coverage Status](https://coveralls.io/repos/github/JangChulwoon/KaraokeParsing/badge.svg)](https://coveralls.io/github/JangChulwoon/KaraokeParsing)

> 현재 프로젝트를 재 구성하고 있습니다.  아래 내용은 이전에 작성한 문서로,
> 현재 버전과는 조금 다를 수 있습니다. - 2017.11.13  



### 개요     

코인노래방이 증가하면서, 부르고 싶은 노래를 저장하고 싶은 때가 있습니다.       
그래서 아는 분이 구현한 프로젝트가 이겁니다...     

[노래노트](http://www.noraenote.com/)
        

해당 프로젝트는 노래노트에 사용되는 API를 구현한 것으로, 노래방 번호 및 곡 제목 등의 Text 정보를 가져올 수 있습니다.    
GraphiQL을 통해 Graphql명세를 확인하실 수 있습니다. [API Server](211.249.62.150) 


#### Spec   

	1. GraphQL 적용   
	2. Spring Scheduler를 이용한 cache clear   -> 추후 Redis로 대체 
	3. Java 8 활용 ok
	4. Travis 적용 + heroku 적용 완료 
	5. Accept persiste query 01.01 구현시작 
	6. sonarQUBE 도 해보고싶다 



GraphQL에 대한 컨셉 및 개념은 다음을 [참고](https://jangchulwoon.github.io/graphql/2017/10/15/GraphQL/)하면 됩니다.

#### 사용법         

GraphQL을 가장 빨리 만나는 방법(?)은 아래와 같습니다. 
	
    {
      Karaoke(karaoke: {company: TJ, category: SINGER, keyword: "우원재", page: 1}) {
        number
        title
        singer
      }
    }




그런데 이렇게 `정적`으로 사용한다라고 하면,  `Query Language` 라고 할 수 있을까요 ?
    
Language는 기본적으로 변수나 함수 라는 개념이 어느정도 있어야하지 않을까요 ? 

있습니다. 

즉, 함수 및 변수를 사용하는 방법은 아래와 같습니다.

    {
      "query": "\n  query select($karaoke :karaoke){\n    Karaoke(karaoke : $karaoke){\n    number\n    title\n    singer\n  }\n  }\n  \n",
      "variables": {
        "karaoke": {
          "company": "TJ",
          "category": "SINGER",
          "keyword": "우원재",
          "page": 1
        }
      },
      "operationName": "select"
    }

위와 같이 VARIABLES를 둬서 유동적인 쿼리를 작성할 수 있습니다.   

해당 소스는 현재 [heroku](http://ec2-18-219-31-27.us-east-2.compute.amazonaws.com:8080)를 통해 확인해 보실 수 있습니다.

> 아무래도 외국에 서버가 있다보니, 반응이 느린점 양해부탁드립니다 ..

#### 기타

물론 REST하게 접근할 수 있도록 진행했습니다 ~
   
   http://ec2-18-219-31-27.us-east-2.compute.amazonaws.com:8080/TJ/SONG/
   
> 이렇게도 사용가능합니다




