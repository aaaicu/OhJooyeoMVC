# OhJooYeo API 문서

본 문서는 오주여 프로젝트의 REST API 정의 문서를 정리해 놓은 문서이다. 

반드시 멤버들 간의 협의를 거쳐 통과한 내용만 수정해서 반영해야한다.

**Current Version: v 0.2.0 (19.10.08)**

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
### [1] _POST_ /worship/list

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
{
    "churchId": 13  (교회 ID)
}
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
### [2] _POST_ /worship/info


1) Description

```
현재 worship id에 해당하는 최신버전의 예배 순서를 가져온다.
```

2) Headers

```
Content-Type: application/json
```

3) Body

```
{
    "churchId": 13,  (교회 ID)
    "worshipId": "19-001", (예배 ID)
    "version": 3 (광고 버전)
}
```

4) Response Data

- 응답이 null일 경우는 최신데이터임.

```
{
    "mainPresenter": "인도자 이름" [String],
    "worshipOrder": [{
        "title": "순서1 - 순서 1에 해당하는 제목" [String],
        "detail": "순서1 - 순서 1에 해당하는 상세 항목" [String],
        "presenter": "순서1 - 순서 1에 해당하는 대표자" [String],
        "order": 예배 순서 [Int],
        "orderId": 각 순서에 대한 식별값ID 정보 [Int]
    }],
    "nextPresenter": {
        "mainPresenter":"다음주 인도자 이름" [String],
        "prayer":"다음주 기도자 이름" [String],
        "offer":"다음주 헌금위원 이름 [String]"
    },
    "version": 예배 순서 정보의 버전 [Int]
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
	    }],
    "version": 1
}
```

- Response Description

```
{}이 반환되었다면 현재 값이 최신버전인 것이다.
```

----

`[Enabled]`
### [3] _ POST_ /phrase

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
        "창세기 1:1(-1:3)(/출애굽기 1:2(-1:4))" [String],
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
 
----

### [4] _POST_ /signin

1) Description

```
로그인 API
```

2) Headers

```
Content-Type: application/json

```

3) Body

```
{
    "id": "admin",
    "pw": "admin"
}
```

4) Response Data

```
{
	"churchId"
}
```

### [5] _POST_ /launch

1) Description

```
올해의 말씀 API
```

2) Headers

```
Content-Type: application/json

```

3) Body

```
{
    "churchId": 13  (교회 ID)
}
```

4) Response Data

```
{
    "yearlyPhrase": "~~~~~~~~~~~~~~" [String]
}
```



### [6] _POST_ /notice/list

1) Description

```
공지사항 API
```

2) Headers

```
Content-Type: application/json

```

3) Body

```
{
    "churchId": 13,  (교회 ID)
    "noticeId": 10   (불러온 게시판 마지막글의 noticeId - 0일경우 가장 처음 호출)
}
```

4) Response Data

- 응답이 null일 경우는 최신데이터임.

```
[{
	 "noticeId": 1,
    "title": "공지사항1",
    "content": "공지내용1",
    "regDate": "2019-09-09",
    "userId": "admin(작성자)",
    "order": 1,
},
{
	 "noticeId": 2,
    "title": "공지사항2",
    "content": "공지내용2",
    "regDate": "2019-09-13",
    "userId": "admin(작성자)",
    "order": 2,
},
{
	 "noticeId": 3,
    "title": "공지사항3",
    "content": "공지내용3",
    "regDate": "2019-09-18",
    "userId": "admin(작성자)",
    "order": 3,
}]
```
