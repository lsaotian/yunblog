package cn.qinxianyun.blog.mapper;


import cn.qinxianyun.blog.vo.Category;
import cn.qinxianyun.blog.vo.CategoryCustom;
import cn.qinxianyun.blog.vo.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 初始化分类信息
     *
     * @return
     */
    List<CategoryCustom> initCategoryList();

    Category getCategoryById(Integer id);

    List<Category> loadCategory(@Param("pager") Pager pager, @Param("categoryName") String categoryName);

    int checkExist(Category category);

    void saveCategory(Category category);

    void updateCategory(Category category);

    int initPage(Pager pager);

    List<Category> getCategoryList();

    /**
     * 获取当前id的文章数量
     *
     * @param categoryId
     * @return
     */
    int ArticleCatePage(int categoryId);

    void deleteCategoryById(Integer categoryId);
}
