package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public class UserDaoImpl implements UserDao {
  @Autowired
  DataSource ds;

  int FAIL = 0;

  @Override
  public in deleteUser(String id) throws Exception{
    int rowCnt = FAIL;
    String sql = "delete from user where id = ?";

    try(  //try-with-resources
      connection conn = ds.getConnection();
      PreparedStatement pstmt = conn.prepareStatment(sql);
    ){
      pstmt.setString(1, id);
      return pstmt.executeUpdate():
    }
  }

  @Override
  public User selectUser(String id) throws Exception{
    User user = null;
    String wql = "Select * from user where id = ?";
    try(
      Connection conn = ds.getConnection():
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.excuteQuery();
    ){
      pstmt.setString(1, id); //id set
      
      if(re.next()){
        user = new User(); //user get
        user.setId(rs.getString(1)); // id get(index number)
        user.setPwd(rs.getString(2)); // pwd get
        user.setName(rs.getString(3)); //get name
        user.setEmail(rs.getString(4)); // get email
        user.setBirth(new Date(rs.getDate(5).getTime())); // getDate=>sql date format
        user.setSns(rs.getString(6)); //get sns
        user.setReg)date(new Date(rs.getTimestamp(7).getTime())); // get register date
        
      }
    }
    return user;
  }
  @Override
  public int insertUser(User user){
    int rowCnt = 0;
    String sql = "insert into user values (?,?,?,?,?,?,now())";

    try(
      Connection conn = ds.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql);
    ){
      pstmt.setString(1, user.getID()); // 파라미터로 전달받은 user 객체의 getId()로 id를 불러와set
      pstmt.setString(2, user.getPwd());
      pstmt.setString(3, user.getName());
      pstmt.setString(4, user.getEmail());
      pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
      pstmt.setString(6, user.getSns());

      return pstmt.executeUpdate();
    }
  }

  @Override
  public int updateUser(User user) throws Exception {
    int rowCnt = 0;
    String sql = "Update user" +
                 "set pwd = ?, name = ?, birth = ?, sns = ? , reg_date = ?" +
                 "where id = ?";
    try(
      Connection conn = ds.getConnection();
      PreparedStatement pstmt = conn.prpareStatement(sql);
      
    ){
      pstmt.setString(1, user.getPwd());
      pstmt.setString(2, user.getName());
      pstmt.setString(3, user.getEmail());
      pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
      pstmt.setString(5, user.getSns());
      pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));
      pstmt.setString(7, user.getId());
      
      rowCnt = pstmt.executeUpdate(); // 잘 업데이트 되었으면 1

    }
    return rowCnt;
  }

  @Override
  public countUser(){
    String sql = "count * from user";
    try(
      Conncetion conn = ds.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql);
    ){
      // pstmt.excuteUpdate(); 틀렸당
      rs.next();
      int result = rs.getInt(1);
      // count 쿼리 실행이 잘 이해가 안감 찾아보기
      return result;
    }
  }

  @Override
  public void deleteAll() throws Exception {
    String sql = "delete from user";
    try(
      Connection conn = ds.getConnection();
      PreparedStatment pstmt = conn.prepareStatement(sql);
    ){
      pstmt.executeUpdate();      
    }
  }
}
