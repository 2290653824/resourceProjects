package com.zj.resourceprojects.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zj.resourceprojects.Mapper.ArcTypeMapper;
import com.zj.resourceprojects.entity.ArcType;
import com.zj.resourceprojects.service.ArcTypeService;
import javafx.scene.shape.Arc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArcTypeServiceImpl implements ArcTypeService {



    @Autowired
    private ArcTypeMapper arcTypeMapper;

    /**
     * 注意这里排序的使用
     * @param page
     * @param limit  每页的大小

     * @param properties  排序字段
     * @return
     */
    @Override
    public List<ArcType> find(int page, int limit, String properties) {
        Page<ArcType> pages = new Page<>(page, limit);
        Page<ArcType> arcTypePage = arcTypeMapper.selectPage(pages, new QueryWrapper<ArcType>().orderByDesc(properties));
        return arcTypePage.getRecords();

    }

    @Override
    public List<ArcType> findAll( String properties) {
        return arcTypeMapper.selectList(new QueryWrapper<ArcType>().orderByDesc(properties));
    }

    @Override
    public Long getCount() {
        return arcTypeMapper.selectCount(null);
    }

    @Override
    public int insert(ArcType arcType) {
        //不允许邮相同名字的资源类型
        String name = arcType.getArcTypeName();
        ArcType byArcTypeName = findByArcTypeName(name);
        if(byArcTypeName!=null){
            return 0;
        }

        return arcTypeMapper.insert(arcType);


    }

    @Override
    public int deleteById(Integer id) {
        return arcTypeMapper.deleteById(id);
    }


    @Override
    public ArcType getById(Integer id) {
        return arcTypeMapper.selectById(id);
    }

    @Override
    public int update(ArcType arcType) {
        return arcTypeMapper.updateById(arcType);
    }

    @Override
    public ArcType findByArcTypeName(String name) {
        return arcTypeMapper.selectOne(new QueryWrapper<ArcType>().eq("arc_type_name",name));


    }
}
