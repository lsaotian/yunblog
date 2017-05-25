package cn.qinxianyun.blog.controller;

import cn.qinxianyun.blog.vo.*;
import cn.qinxianyun.blog.service.ArticleService;
import cn.qinxianyun.blog.service.CategoryService;
import cn.qinxianyun.blog.service.PartnerService;
import cn.qinxianyun.blog.service.TagService;
import cn.qinxianyun.blog.util.ResultInfo;
import cn.qinxianyun.blog.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private PartnerService partnerService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    @RequestMapping("/list/{id}")
    @ResponseBody
    public ResultInfo articleList(Pager pager){

        return null;
    }

    @RequestMapping("/load")
    public String loadArticle(Pager<Article> pager, Model model){
        List<ArticleCustom> articleList = articleService.articleList(pager);
        model.addAttribute("articleList",articleList);
        return "blog/part/articleSummary";
    }

    /**
     * 加载文章
     * 包括总标签数
     * 总文章数量
     * 分类及每个分类文章数量
     * 友链集合
     *
     * @return
     */
    @RequestMapping("/details/{articleId}")
    public String loadArticle(@PathVariable Integer articleId, Model model){
        List<Partner> partnerList = partnerService.findAll();
        List<CategoryCustom> categoryList = categoryService.initCategoryList();
        Article lastArticle = articleService.getLastArticle(articleId);
        Article nextArticle = articleService.getNextArticle(articleId);
        articleService.addArticleCount(articleId);
        int articleCount = articleService.getArticleCount();
        int tagCount = tagService.getTagCount();
        ArticleCustom articleCustom = articleService.getArticleCustomById(articleId);
        model.addAttribute("lastArticle",lastArticle);
        model.addAttribute("nextArticle",nextArticle);
        model.addAttribute("article",articleCustom);
        model.addAttribute("categoryCount",categoryList.size());
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("tagCount",tagCount);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("partnerList",partnerList);
        return "article";
    }

    @RequestMapping("/content/search")
    public String search(String keyword,Model model){
        List<Article> articleList = articleService.getArticleListByKeywords(keyword);
        model.addAttribute("articleList",articleList);
        return "blog/part/search-info";
    }


}
