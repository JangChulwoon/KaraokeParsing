[![Build Status](https://travis-ci.org/JangChulwoon/KaraokeParsing.svg?branch=master)](https://travis-ci.org/JangChulwoon/KaraokeParsing)    


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
	3. Java 8 활용
	4. Travis 적용 
	5. 그 외 ..  
