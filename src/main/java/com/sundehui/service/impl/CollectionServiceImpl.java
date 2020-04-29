package com.sundehui.service.impl;

import com.sundehui.domain.Collection;
import com.sundehui.domain.House;
import com.sundehui.domain.User;
import com.sundehui.mapper.CollectionMapper;
import com.sundehui.mapper.UserMapper;
import com.sundehui.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    //通过用户id获取该用户所有收藏的房屋
    @Autowired
    private CollectionMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<House> getAllCollection(String account) {
        System.out.println("===============nimei======================");
        List<House> allCollection = mapper.getAllCollection(account);
        // 根据账户从collection表中查询出该账户对应的用户所收藏的房屋
        List<Collection> collections = mapper.getAll(account);

        // 从collections列表中剔除房源信息不为空的元素
        List<Collection> notNullCollections = new ArrayList<>();
        for (int i = 0; i < allCollection.size(); i++) {
            for (int j = 0; j < collections.size(); j++) {
                if (allCollection.get(i) != null &&
                        allCollection.get(i).getId() == collections.get(j).getHouseId()) {
                    notNullCollections.add(collections.get(j));
                }
            }
        }

        collections.remove(notNullCollections);

        int i = 0;
        // 把已经下架但是还存在用户的收藏列表中的房屋添加到返回的数据集中
        for (int j = 0; j < allCollection.size(); j++) {
            if (allCollection.get(j) ==null){
                House house = new House();
                house.setcId(collections.get(i).getId());
                allCollection.set(j, house);
                i++;
            }
        }


        System.out.println(collections);
        System.out.println(allCollection);

        return allCollection;
    }
    // 获取每套房子被收藏的次数
    @Override
    public int selectCollectedCount(Integer id) {
        int i = mapper.selectCollectedCount(id);
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);

        return i;
    }

    @Override
    public int deleteByHouseIdAndUserId(Integer houseId, Integer userId) {
        int i = mapper.deleteByHouseIdAndUserId(houseId, userId);
        return i;
    }


    // 插入一条收藏记录
    @Override
    public int insert(Collection record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public int insertSelective(Collection record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public Collection selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Collection record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Collection record) {
        return 0;
    }
}
