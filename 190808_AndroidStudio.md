#### 2019-08-08 ( 9일차 )

# AndroidStudio

#### Broadcast Receiver

1. 안드로이드 시스템으로부터 나오는 신호(배터리 용량부족, 와이파이, USB케이블 연결/비연결)

2. 사용자 Application에서 발생시키는 임의의 신호

   - Broadcast Receiver는 Broadcast를 청취하는 component

   - Broadcast Receiver는 사용자와의 대면은 하지 않음

   - signal만 청취하는 component



---

![image-20191210202918751](assets/image-20191210202918751.png)

새로운 Broadcast Receiver 만들기

![image-20191210202936189](assets/image-20191210202936189.png)

Broadcast Receiver를 상속받아서 class가 하나 만들어진다.

![image-20191210202945975](assets/image-20191210202945975.png)

onReceive()에서 처리해주면된다.

**AndroidManifest.xml**에서

![image-20191210203015939](assets/image-20191210203015939.png)

이 부분추가해준다

긍데 안됨!! 7.0까지됨!!!



---

### 카카오 API 도서검색

![image-20191210203151652](assets/image-20191210203151652.png)

![image-20191210203155475](assets/image-20191210203155475.png)

![image-20191210203159231](assets/image-20191210203159231.png)

**activity_main.xml**

![image-20191210203211136](assets/image-20191210203211136.png)

![image-20191210203238696](assets/image-20191210203238696.png) 

![image-20191210203251635](assets/image-20191210203251635.png)



![image-20191210203439915](assets/image-20191210203439915.png)

onCreate(), onStartCommand(),onDestroy() 반드시 필요!!



![image-20191210203509748](assets/image-20191210203509748.png)

앱을 하나 더 만들어준다.

![image-20191210203522820](assets/image-20191210203522820.png)

REST API 키 필요

![image-20191210203614709](assets/image-20191210203614709.png)

![image-20191210203621989](assets/image-20191210203621989.png)

를 자바코드로 변경 해야 한다.



Manifest.xml에서 permission 추가해준다

![image-20191210203646019](assets/image-20191210203646019.png)



![image-20191210203651947](assets/image-20191210203651947.png)

Jackson library 추가



![image-20191210203701067](assets/image-20191210203701067.png)



![image-20191210203707275](assets/image-20191210203707275.png)



KAKAOBookVO에서

데이터 marshaling (마샬링)

![image-20191210203716280](assets/image-20191210203716280.png)