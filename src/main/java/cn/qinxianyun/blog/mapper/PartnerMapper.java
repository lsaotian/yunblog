package cn.qinxianyun.blog.mapper;

import cn.qinxianyun.blog.vo.Pager;
import cn.qinxianyun.blog.vo.Partner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PartnerMapper {


List<Partner> findAll();


    void savePartner(Partner partner);

    /**
     * 分页查询好友列表
     * @param pager 分页条件
     * @param param
     * @return
     */
    List<Partner> loadPartner(@Param("pager") Pager pager, @Param("param") String param);

    Partner getPartnerById(Integer id);

    void deletePartner(Integer id);

    void updatePartner(Partner partner);

    int initPage(Pager pager);
}
