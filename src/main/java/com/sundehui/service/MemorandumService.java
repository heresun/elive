package com.sundehui.service;

import com.sundehui.domain.Memorandum;

import java.util.List;

public interface MemorandumService {
    List<Memorandum> getAll(Integer uId);

    int deleteOne(Integer id);

    int addOne(Memorandum memorandum);

    int changeStatus(Integer id,Integer status);
}
