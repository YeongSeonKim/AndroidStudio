#### 2019-08-05 ( 6일차 )

# AndroidStudio

기존의 도서검색 프로그램은 데이터베이스로부터 검색된 도서명만 결과로 받아서 Android Activity 에 ListView로 단순출력



---

확장된 도서검색 프로그램은 데이터베이스로부터 키워드로 검색된 도서에 대한 도서 이미지, 도서제목, 도서저자, 도서 가격을 Android Activity 에 Custom ListView로 단순출력



이클립스 BookSearch_Workspace 들어가기

![image-20191210200303407](assets/image-20191210200303407.png)

새로운 controller만들기 Servlet

![image-20191210200310798](assets/image-20191210200310798.png)

![image-20191210200454405](assets/image-20191210200454405.png)

![image-20191210200504244](assets/image-20191210200504244.png)

default값으로!! 설정

![image-20191210200517265](assets/image-20191210200517265.png)

get방식으로할꺼임!!

![image-20191210200525438](assets/image-20191210200525438.png)

![image-20191210200530064](assets/image-20191210200530064.png)

Dto(VO) 만들꺼임

![image-20191210200538142](assets/image-20191210200538142.png)

![image-20191210200543337](assets/image-20191210200543337.png)

![image-20191210200547170](assets/image-20191210200547170.png)



BookService에 

![image-20191210200557239](assets/image-20191210200557239.png)

getBooks에 마우스 가져다대면 새로 만든다는 작업클릭하면

![image-20191210200607098](assets/image-20191210200607098.png)

자동생성이 된다.



VO 만들기

![image-20191210200621079](assets/image-20191210200621079.png)

![image-20191210200625748](assets/image-20191210200625748.png)



CustomListViewAdapter 만들기

![image-20191210200644934](assets/image-20191210200644934.png)

![image-20191210200715007](assets/image-20191210200715007.png)