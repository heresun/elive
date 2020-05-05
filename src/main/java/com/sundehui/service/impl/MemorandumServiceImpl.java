package com.sundehui.service.impl;

import com.sundehui.domain.Memorandum;
import com.sundehui.mapper.MemorandumMapper;
import com.sundehui.service.MemorandumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemorandumServiceImpl implements MemorandumService {

    @Autowired
    private MemorandumMapper mapper;

    @Override
    public List<Memorandum> getAll(Integer uId) {
        List<Memorandum> memorandums = mapper.getAll(uId);
        return memorandums;
    }


    @Override
    public int deleteOne(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int addOne(Memorandum memorandum) {
        int i = mapper.insertSelective(memorandum);
        return i;
    }

    @Override
    public int changeStatus(Integer id, Integer status) {
        int i = mapper.changeStatus(id,status);
        return i;
    }
}
