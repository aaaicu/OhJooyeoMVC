# OhJooYeo API 문서

본 문서는 오주여 프로젝트의 REST API 정의 문서를 정리해 놓은 문서이다. 

반드시 멤버들 간의 협의를 거쳐 통과한 내용만 수정해서 반영해야한다.

**Current Version: v 0.1.3 (18.11.19)**

----

## - REST API Option

- **Base URL**

```
http://aaaicu.synology.me:8088/OhJooYeoMVC
```

- **Return Type에 별도의 표기 없이 Example만 적었다면 모두 String 타입이다.**


----

## - API List


`[Enabled]`
### [1] _ POST_ `/worship-id/{id}/check/version/{version}` 

1) Description

```
현재 날짜 기준 최신 주보의 버전을 가져온다.-> 예배 id에 따라 주보의 버전을 가져온다.-> 앱에 저장되어 있는 버전이 응답받은 서버의 버전과 다르면 데이터 새로고침을 위한 API를 호출하고, 같다면 API호출 없이 내부적으로 저장된 데이터를 사용한다.

[ex] /date/2018-04-29/worship-id/36-09/check/version/acb

Description: 버전(a - z): z로 갈수록 최신 버전

"acb"를 예로 들었을 경우 다음과 같다.

a: 요청 date에서 "순서"에 대한 수정 버전(첫번째 문자)  -> 순서에 대한 첫번째 버전
c: 요청 date에서 "광고"에 대한 수정 버전(두번째 문자)  -> 광고에 대한 세번째 업데이트 버전
b: 요청 date에서 "악보"에 대한 수정 버전(세번째 문자)  -> 악보에 대한 두번째 업데이트 버전

*: 최초 요청
```

2) Headers

```
Content-Type: application/json
```

3) Body

```
No Parameter
```

4) Response Data

```
{
    "worship": {
        "mainPresenter": "인도자 이름" [String],
        "worshipOrder": [{
            "title": "순서1 - 순서 1에 해당하는 제목" [String],
            "detail": "순서1 - 순서 1에 해당하는 상세 항목" [String],
            "presenter": "순서1 - 순서 1에 해당하는 대표자" [String],
            "order": 예배 순서 [Int],
            "orderId": 각 순서에 대한 식별값ID 정보 [Int]
        }],
        "nextPresenter": {
            "mainPresenter":"다음주 인도자 이름",
            "prayer":"다음주 기도자 이름",
            "offer":"다음주 헌금위원 이름"
        }
    },
    "advertisement": [{
        "title": "광고 소식1" [String],
        "content":"광고 내용1" [String],
        "order": 광고 순서 [Int]
    }],
    "music": [{
        "id": 악보 이미지 데이터 index값 [Int],
        "title": "찬양 제목1" [String],
        "imageName": "악보 이미지 파일 이름.확장자" [String],
        "category": 카테고리 번호(자세한 종류는 아래 category 참고) [String],
        "order": 악보 순서 [Int],
        "lylics": 악보 가사 [String],
        "orderId": worshipOrder에 매칭되는 ID 값 [Int]
    }],
    "currentVersion": "현재 서버에서의 버전 정보" [String],
    "worshipDate": "예배 날짜" [String]
}
```


- Category Enum Values

```
0: "일반순서"
1: "성경봉독"
2: "찬양"
```


- Response Example

```
{
    "worship": {
        "nextPresenter": {
            "offer": "서동주",
            "prayer": "박요한",
            "mainPresenter": "김한나"
        },
        "mainPresenter": "박요한",
        "worshipOrder": [{
            "presenter": "회중",
            "title": "경배와찬양",
            "order": 1
        },
        {
            "presenter": "정애림",
            "title": "기도",
            "order": 2
        },
        {
            "presenter": "인도자",
            "detail": "욘 2:7-2:10/고전 2:1-3:1",
            "title": "성경봉독",
            "order": 3
        },
        {
            "presenter": "김희선전도사님",
            "detail": "감사의 노래",
            "title": "설교",
            "order": 4
        },
        {
            "presenter": "표준범",
            "title": "헌금",
            "order": 5
        },
        {
            "presenter": "설교자",
            "title": "헌금기도",
            "order": 6
        },
        {
            "presenter": "인도자",
            "title": "성도의교제",
            "order": 7
        },
        {
            "presenter": "회중",
            "title": "파송찬양",
            "order": 8
        },
        {
            "presenter": "회중",
            "title": "주기도문",
            "order": 9
        }]
    },
    "advertisement": [{
        "title": "환영",
        "content": "돈암동교회 청년예배에 처음 방문하신 여러분을 환영합니다.",
        "order": 1
    },
    {
        "title": "청년예배",
        "content": "주일 오후2시 입니다.",
        "order": 2
    },
    {
        "title": "기도모임",
        "content": "주일 오후1시30분(1330기도회)",
        "order": 3
    },
    {
        "title": "대표기도 및 특송",
        "content": "신청하고자 하시는 분은 임원에게 문의해주십시오",
        "order": 4
    },
    {
        "content": "오늘은 사순절 제3주 입니다.",
        "order": 5
    }],
    "music": [{
        "id": 5,
        "title": "주께 경배",
        "imageName": "img005.png",
        "category": 1
        "order": (integer값 - 악보 순서)
    },
    {
        "id": 12,
        "title": "주께 경배",
        "imageName": "img012.png",
        "category": 1
        "order": 1
    },
    {
        "id": 9,
        "title": "주께 경배",
        "imageName": "img009.png",
        "category": 2
        "order": 1
    }],
    "currentVersion": "bbb",  <- test용!
    "worshipDate": "2018-08-04"
}
```


- Response Description

```
worship, phrase, advertisement, praise가 null이라면 최신버전인 것이다.
```

----
`[Enabled]`
### [2] _ GET_ `/worship-list`

1) Description

```
현재 주보 정보가 있는 날짜와 worship id에 대한 리스트 정보를 불러온다.
```

2) Headers

```
Content-Type: application/json
```

3) Body

```
No Parameter
```

4) Response Data

```
[{
    "date": "주보 정보가 있는 날짜" [String],
    "worshipId": "예배 별 ID 값" [String]
}]
```

----
`[Enabled]`
### [3] _ POST_ `/phrase`

1) Description

```
주보 순서지에 해당하는 말씀 내용을 불러온다. Request Parameter에 포함된 phrase에 해당하는 말씀을 Response 받는다.
```

2) Headers

```
Content-Type: application/json
```

3) Body

```
{
    "phraseRange": [ 
        "성경 a:b(-a:b)(/성경 a:b(-a:b))" [String],
        "성경 a:b(-a:b)(/성경 a:b(-a:b))" [String],
        ...
    ]
}
```

4) Response Data

```
[
    [
        {
            "phrase":"창 1:3",
            "contents":"말씀내용"
        },
        {
            "phrase":"창 1:4",
            "contents":"말씀내용"
        },
        ...
    ],
    [
        {
            "phrase":"창 1:3",
            "contents":"말씀내용"
        },
        {
            "phrase":"창 1:4",
            "contents":"말씀내용"
        },
        ...
    ],
    ...
]
```
 