package com.sundehui.service;

import com.sundehui.domain.Collection;
import com.sundehui.domain.House;

import java.util.List;

public interface CollectionService {

    //通过用户id获取该用户所有收藏的房屋
    List<House> getAllCollection(String account);

    // 获取每套房子被收藏的次数
    int selectCollectedCount(Integer  hosueId);


    int deleteByPrimaryKey(Integer id);

    int deleteByHouseIdAndUserId(Integer houseId, Integer uId);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

}
