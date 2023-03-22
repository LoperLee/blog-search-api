# blog-search-api

## dependency
| 라이브러리 명               | 사용목적                               |
|---------------------------|------------------------------------|
| spring-boot-starter-web        | 스프링을 이용해 RestAPI를 사용하기 위해 이용       |
| spring-boot-starter-data-jpa   | JPA를 사용하기 위해서 이용                   |
| spring-boot-starter-validation | API의 파라미터에서 유효성 검사를 진행하기 위해 이용     |
| spring-cloud-starter-openfeign | 외부 API 호출을 위해서 이용                  |
| querydsl-jpa                   | 동적 쿼리 생성을 위해서 이용                   |
| lombok                         | 생성자, Getter, Setter 개발의 편의를 위해서 이용 |
| h2                             | 테스트 목적으로 이용하기 위해서 인메모리 데이터베이스 이용   |
| spring-boot-starter-test       | 스프링 프레임워크의 테스트 진행을 위해서 이용          |

## API Reference

### API 요약
* 목적 : 가장 많이 검색된 인기 검색어 검색
* 요청 URL: `http://localhost:8080/api/history/rank`
* 요청 방식: `GET`
* 요청 인자: 없음
### 응답 형식
인기 검색어 목록을 JSON 배열 형태로 반환합니다. 각 인기 검색어는 아래와 같은 형식으로 구성됩니다.

```json
[
      {
            "keyword": "example",
            "count": 10
      },
      ...
]
```
* keyword: 검색어
* count: 검색어가 검색된 횟수
인기 검색어는 검색된 횟수(count)가 높은 순서대로 정렬되어 반환되며 최대 10개 까지 반환됩니다.

---
### API 요약
* 목적 : 통합 블로그 검색
* 요청 URL: http://localhost:8080/api/search/blog
* 요청 방식: GET
* 요청 인자
  * query: 검색어 (필수)
  * size: 한 페이지에 보여질 문서 개수 (옵션, 기본값 10)
  * page: 페이지 번호 (옵션, 기본값 1)
  * sort: 정렬 방식 (옵션, 기본값 accuracy)
    * accuracy: 정확도순
    * recency: 최신순
### 응답 형식
```json
{
    "meta": {
        "total_count": 1,
        "pageable_count": 1,
        "is_end": false
    },
    "documents": [
        {
            "title": "title",
            "contents": "string",
            "thumbnail": "thumbnail",
            "blog_name": "blog_name",
            "url": "url"
        }
    ]
}
```
* meta: 검색 결과 메타 정보
  * total_count: 검색된 문서 전체 개수
  * pageable_count: 요청한 페이지에 포함되는 문서 개수
  * is_end: 요청한 페이지가 마지막 페이지인지 여부
* documents: 검색된 문서 목록
  * title: 문서 제목
  * contents: 문서 내용
  * thumbnail: 썸네일 이미지 URL
  * blog_name: 블로그 이름
  * url: 문서 URL

## How to run

```shell
java -jar blog-search-api.jar
```

## Download

[다운로드 링크](https://drive.google.com/file/d/1-5ujpfd8tA8Vks1igEoyBlkCzbueZbmQ/view?usp=sharing)