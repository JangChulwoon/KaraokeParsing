### 개요     

코인노래방이 증가하면서, 부르고 싶은 노래를 저장하고 싶은 때가 있습니다.       
그래서 아는 분이 구현한 프로젝트가 이겁니다...     

[노래노트](http://www.noraenote.com/)     

해당 프로젝트는 노래노트에 사용되는 API를 구현한 것으로, 노래방 번호 및 곡 제목 등의 Text 정보를 가져올 수 있습니다.    


#### 노래방 번호 파싱     

태진(TJ)노래방과 금영노래방의 노래 번호를 파싱하는 프로젝트.      

api 형태로 제작 중이며, 다음과 같이 사용할 수 있습니다.    

	/TJ/singer/가수명  
	/TJ/music/제목     

> 예시   

	{"number":"62349","title":"봄이좋냐??","singer":"10cm","lyricist":"권정열,윤철종","composer":"권정열,윤철종"}   

현재, TJ만 구현 되어 있으며 추후 금영노래방과 Caching 기능을 구현할 예정입니다.      

#### 구현       

> Controller 부분     

	@GetMapping(value = "/{company}/{category}/{title}")
	public List<?> TitleView(@PathVariable String company, @PathVariable String category, @PathVariable String title,
			Model model) throws IOException {
		List<?> list = null;
		Parser ms = Parser.initCompany(company);
		list = ms.checkType(category, title);
		return list;
	}     

controller에서 RESTful한 방식으로 url을 설계했습니다.       

> /{company}/{category}/{title}       
 
company 와 category(String)를 받아, Factory Pattern으로 객체를 생성했습니다.    
    
	// 객체 생성
	Parser ms = Parser.initCompany(company);

	public static Parser initCompany(String info) {
		// 회사 추출
		if (info.equals("TJ")) {
			return new TJParser();
		} else {
			return null;
		}
	} 

	// 검색 
	list = ms.checkType(category, title);     

	public List<Karaoke> checkType(String category, String name) throws IOException {
		if (category.equals("music")) {
			return this.parseTitle(name);
		} else if (category.equals("singer")) {
			return this.parseSinger(name);
		} else {
			return null;
		}
	}      

> checkType 부분은 수정해볼것 생각해볼 것.     
> try catch 도 바꿔야 할 부분. 
> service layer 추가할 것. 

+ 추가해야 할 부분  
    
 1. 구조       
 2. 캐싱
 3. 금영  






#### 패치노트   

> ~ 2016.12       
      
TJ 노래방 파싱 완료 
	 
> 2017.08.13     

기존 프로젝트 소스 정리      

 + Java doc 진행 
 + Scheduling 진행 

FE 
- throttle
- debounce 
thinking .. 