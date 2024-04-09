
package com.codestudy.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.codestudy.common.LoginUtil;
import com.codestudy.dao.LikesDao;
import com.codestudy.dao.NoteDao;
import com.codestudy.entity.Likes;
import com.codestudy.entity.Note;
import com.codestudy.entity.Result1;
import com.codestudy.service.NoteService;
import com.codestudy.vo.NoteVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private LikesDao likesDao;

    @Override
    public Result1 addNote(Note note) {
        note.setCreateTime(new Date());
        note.setUpdateTime(new Date());
        int haveId = noteDao.checkIdHave(LoginUtil.getCurrentId());  //获取用户上下文
        if (note.getType() < 1 || note.getType() > 4) {
            return Result1.error("类型不在范围之内");
        } else if (haveId == 0) {
            return Result1.error("没有该用户Id,添加失败");
        }

        noteDao.addNote(note);
        return Result1.success("添加成功");
    }

    @Override
    public NoteVO queryInfo(int noteId) {

        Note note = noteDao.findById(noteId);
        // 对象工具类
        // 判断是否拿到这篇文章了
        if (Objects.isNull(note)) {
            return null;
        }


        // 查询点赞数
        Likes likes = new Likes();
        likes.setActive(1);
        likes.setNoteId(noteId);
        List<Likes> likesList = likesDao.queryByCondition(likes);

        int likeCount = likesList.size();


//        NoteVO noteVO = NoteEntityCovert.INSTANCE.covertEntityToVO(note);
        NoteVO noteVO = new NoteVO();

        noteVO.setId( note.getId() );
        noteVO.setUserId( note.getUserId() );
        noteVO.setTitle( note.getTitle() );
        noteVO.setMsg( note.getMsg() );
        noteVO.setImg( note.getImg() );
        noteVO.setAnnex( note.getAnnex() );
        noteVO.setType( note.getType() );
        noteVO.setCreateTime( note.getCreateTime() );
        noteVO.setUpdateTime( note.getUpdateTime() );

        //???
        // 评论量

        noteVO.setLikeCount(likeCount);

        return noteVO;
    }

    @Override
    public List<Note> lookNote(int userId) {
        List<Note> notes = noteDao.lookNote(userId);

        return notes;
    }
}
