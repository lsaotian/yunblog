package cn.qinxianyun.blog.service;


import cn.qinxianyun.blog.vo.ArticleCustom;
import cn.qinxianyun.blog.vo.Category;
import cn.qinxianyun.blog.vo.CategoryCustom;
import cn.qinxianyun.blog.vo.Pager;

import java.util.List;

public interface CategoryService {



    List<ArticleCustom> loadArticleByCategory(Pager pager, Integer categoryId);

    /**
     * 初始化分类信息
     * @return
     */
    List<CategoryCustom> initCategoryList();

    Category getCategoryById(Integer id);

    List<Category> loadCategory(Pager pager, String categoryName);

    boolean checkExist(Category category);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void initPage(Pager pager);

    List<Category> getCategoryList();

    void ArticleCatePage(Pager pager, int categoryId);

    List<ArticleCustom> loadArticleByArchive(String createTime, Pager pager);

    int getArticleCountByCategoryId(Integer categoryId);

    boolean deleteCategoryById(Integer categoryId);
}
