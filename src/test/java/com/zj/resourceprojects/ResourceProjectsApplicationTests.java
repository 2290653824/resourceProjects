package com.zj.resourceprojects;

import com.zj.resourceprojects.Mapper.UserMapper;
import com.zj.resourceprojects.entity.ArcType;
import com.zj.resourceprojects.entity.User;
import com.zj.resourceprojects.service.ArcTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.Oneway;
import java.util.List;

@SpringBootTest
class ResourceProjectsApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private ArcTypeService arcTypeService;
    /**
     * 第一次测试
     */
    @Test
    void contextLoads() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void findArcTypes(){
        List<ArcType> sort = arcTypeService.find(0, 3, "sort");
        System.out.println(sort);
    }

    @Test
    public void insertArcType(){
        ArcType arcType = new ArcType();
        arcType.setArcTypeName("机器学习资源");
        arcType.setSort(2);
        arcType.setRemark("机器学习资源大全");
        arcTypeService.insert(arcType);
//        ArcType arcType = new ArcType();
//        arcType.setArcTypeName("后端资源");
//        arcType.setSort(1);
//        arcType.setRemark("后端资源大全");
//        arcTypeService.insert(arcType);
    }

    @Test
    public void getCount(){
        System.out.println(arcTypeService.getCount());
    }

}
