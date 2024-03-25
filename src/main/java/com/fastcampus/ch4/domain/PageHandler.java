package com.fastcampus.ch4.domain;

public class PageHandler {
    int totalCnt; // 총 게시물 개수
    int pageSize; //한 페이지으 ㅣ크기
    int naviSize; // 페이지 내비게이션의 크기
    int totalPage; // 전체 페이지의 수
    int page; // 현재 페이지
    int beginPage; // 내비게이션의 첫번째 페이지
    int endPage; // 내비게이션의 마지막 페이지
    boolean showPrev; // 이전 페이지로 이동하는 링크를 보여줄 것인지 여부
    boolean showNext; // 다음 페이지로 이동하는 링크를 보여줄 것인지 여부
}
