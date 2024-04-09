package com.codestudy.dao;

import com.codestudy.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoteDao {

    @Insert("insert into note (user_id, title, msg, type,create_time,update_time) values (#{userId},#{title},#{msg},#{type},#{createTime},#{updateTime})")
    void addNote(Note note);

    @Select("select * from note where user_id = #{userId}")
    List<Note> lookNote(int userId);

    @Select("select count(*) from users where id = #{userId}")
    int checkIdHave(int userId);

    @Select("select * from note where id=#{noteId} ;")
    Note findById(Integer noteId);
}
