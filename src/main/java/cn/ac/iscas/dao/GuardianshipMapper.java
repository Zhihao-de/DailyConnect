package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Guardianship;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianshipMapper {
    //add guardianship
    int addGuardianship(Guardianship record);

    //get kid info by parent id
    Guardianship selectKidByParentId(Integer id);

}