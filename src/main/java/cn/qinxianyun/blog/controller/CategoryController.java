package cn.qinxianyun.blog.controller;

import cn.qinxianyun.blog.service.CategoryService;
import cn.qinxianyun.blog.service.PartnerService;
import cn.qinxianyun.blog.vo.ArticleCustom;
import cn.qinxianyun.blog.vo.Pager;
import cn.qinxianyun.blog.vo.Partner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Resource
    CategoryService categoryService;
    @Resource
    PartnerService  partnerService;

    /**
     * 获取某个标签的分页文章
     * @param model
     * @param pager
     * @param categoryId
     * @return
     */
    @RequestMapping("/load/{categoryId}")
    public String loadArticleByCategory(Model model, Pager pager, @PathVariable Integer categoryId){
        List<ArticleCustom> articleList = categoryService.loadArticleByCategory(pager,categoryId);
        if (!articleList.isEmpty()){
            model.addAttribute("articleList",articleList);
            model.addAttribute("pager",pager);
            model.addAttribute("categoryName",articleList.get(0).getCategoryName());
        }
        return "blog/part/categorySummary";
    }

    /**
     * 跳转到分类的页面 暂时停用
     * @param model
     * @param categoryId
     * @return
     */
    @Deprecated
    @RequestMapping("/details/{categoryId}")
    public String categoryPage(Model model,@PathVariable Integer categoryId){
        List<Partner> partnerList = partnerService.findAll();
        model.addAttribute("partnerList",partnerList);
        model.addAttribute("categoryId",categoryId);
        return "category";
    }



}
