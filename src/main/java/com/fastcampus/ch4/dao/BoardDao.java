package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;

public interface BoardDao {
    BoardDto selectId(int bno) throws Exception;
}
