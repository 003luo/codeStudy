package com.codestudy.service;

import com.codestudy.entity.Note;
import com.codestudy.entity.Result1;
import com.codestudy.vo.NoteVO;

import java.util.List;

public interface NoteService {
    List<Note> lookNote(int userId);

    Result1 addNote(Note note);

    NoteVO queryInfo(int noteId);
}
