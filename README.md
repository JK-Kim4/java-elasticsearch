# Book API

Spring Boot 기반의 도서 정보 관리 API 서버 초기 프로젝트입니다. 현재 단계에서는 도메인 엔티티 설계와 기본적인 빌드 구성을 포함하고 있습니다.

## 주요 구성 요소
- Spring Boot 3.x (Web, Data JPA, Validation, Actuator)
- PostgreSQL 드라이버 (런타임)
- Gradle 빌드 스크립트

## 패키지 구조
```
src/main/java/com/example/bookapi
├── BookApiApplication.java
├── common/
│   └── AuditableEntity.java
└── entity/
    ├── enums/
    │   ├── AssetType.java
    │   ├── AvailabilityStatus.java
    │   ├── BookFormat.java
    │   ├── BookRelationType.java
    │   └── ReviewStatus.java
    ├── Author.java
    ├── Book.java
    ├── BookAsset.java
    ├── BookAuthor.java
    ├── BookAvailability.java
    ├── BookCategory.java
    ├── BookPrice.java
    ├── BookRatingSummary.java
    ├── BookReview.java
    ├── Category.java
    ├── Publisher.java
    ├── RelatedBook.java
    └── Series.java
```

## OpenSearch 연동
- `docker run -p 9200:9200 -e discovery.type=single-node -e OPENSEARCH_INITIAL_ADMIN_PASSWORD=admin --name opensearch opensearchproject/opensearch:2.11.0`과 같이 OpenSearch를 로컬에서 실행합니다. (테스트 용도로 보안을 비활성화하려면 `-e plugins.security.disabled=true` 옵션을 추가하고 비밀번호 설정을 제거합니다.)
- `src/main/resources/application.yml`의 `opensearch` 섹션을 환경에 맞게 조정합니다.
- 도서 등록 API가 성공하면 Spring `ApplicationEvent`가 발행되고, 커밋 이후 OpenSearch 인덱스(`books`)에 문서가 생성됩니다.

## 다음 단계 아이디어
- JPA 엔티티 간 연관관계를 활용한 CRUD 서비스 및 REST 컨트롤러 구현
- Flyway/Liquibase 기반 스키마 관리
- 검색 조건/필터 API 구현 및 테스트 작성
