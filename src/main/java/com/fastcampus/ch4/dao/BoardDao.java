package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

public class BoardDao {

    @Autowired
    SqlSession session;

    public BoardDto select(int bno) throws Exception {
        return session.selectOne("select", bno);
    }
}
