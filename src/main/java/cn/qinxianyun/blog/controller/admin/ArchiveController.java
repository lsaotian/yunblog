package cn.qinxianyun.blog.controller.admin;

import cn.qinxianyun.blog.vo.ArticleCustom;
import cn.qinxianyun.blog.service.CategoryService;
import cn.qinxianyun.blog.vo.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ArchiveController {

    @Resource
    private CategoryService categoryService;
    /*文章归档列表*/
    @RequestMapping("/createTime/load/{createTime}")
    public String categoryList(@PathVariable String createTime, Pager pager, Model model){
        List<ArticleCustom> articleList = categoryService.loadArticleByArchive(createTime,pager);
        if (articleList != null && !articleList.isEmpty()) {
            model.addAttribute("articleList", articleList);
            model.addAttribute("pager", pager);
            model.addAttribute("categoryName", articleList.get(0).getCategoryName());
        }
        return "blog/part/categorySummary";
    }
}
