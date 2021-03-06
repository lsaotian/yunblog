package cn.qinxianyun.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PagerMapper {
    /**
     * 找到目标分类下的分页条件
     * @param categoryId
     * @return
     */
    int loadCategoryPager(Integer categoryId);

    /**
     * 通过tagId获取文章总数量
     * @param tagId
     * @return
     */
    int loadTagPager(Integer tagId);
}
