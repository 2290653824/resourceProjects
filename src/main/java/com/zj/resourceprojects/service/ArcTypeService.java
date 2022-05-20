package com.zj.resourceprojects.service;


import com.zj.resourceprojects.entity.ArcType;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

public interface ArcTypeService {


    /**
     * 分页查询资源类型
     * @param Page   第几页，从1开始
     * @param limit  每页的大小

     * @param properties  排序字段,sort越大，比重越高
     * @return
     */
    public List<ArcType> find(int Page, int limit, String properties);


    /**
     * 查找所有资源类型且不进行分页，sort越大，比重越高
     * @return
     */
    public List<ArcType> findAll(String properties);

    /**
     * 获取总计录数
     */
    public Long getCount();

    /**
     * 添加一个资源类型，不允许相同名字录入
     * 添加成功返回1，否则返回0
     */
    public int insert(ArcType arcType);

    /**
     * 根据id删除一条资源类型
     */
    public int deleteById(Integer id);

    /**
     * 根据id查询一条资源类型
     */
    public ArcType getById(Integer id);


    /**
     * 更新一个资源类型
     * @param arcType
     */
    public int update(ArcType arcType);

    /**
     * 根据名字查找资源类型
     * @param name
     * @return
     */
    public ArcType findByArcTypeName(String name);
}
