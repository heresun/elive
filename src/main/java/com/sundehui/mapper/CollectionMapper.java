package com.sundehui.mapper;

import com.sundehui.domain.Collection;
import com.sundehui.domain.House;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CollectionMapper {


    //通过用户账户获取该用户所有收藏的房屋
    List<House> getAllCollection(String account);
    // 获取每套房子被收藏的次数
    int selectCollectedCount(@Param("houseId") Integer houseId);

    int deleteByPrimaryKey(Integer id);

    int deleteByHouseIdAndUserId(@Param("houseId") Integer houseId, @Param("userId") Integer userId);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);



    List<Collection> getAll(@Param("account") String account);
}