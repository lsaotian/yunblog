package cn.qinxianyun.blog.controller.admin;

import cn.qinxianyun.blog.service.ArticleService;
import cn.qinxianyun.blog.service.CategoryService;
import cn.qinxianyun.blog.service.PartnerService;
import cn.qinxianyun.blog.service.TagService;
import cn.qinxianyun.blog.vo.Category;
import cn.qinxianyun.blog.vo.Pager;
import cn.qinxianyun.blog.vo.Partner;
import cn.qinxianyun.blog.vo.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    @Resource
    private PartnerService partnerService;
    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/home")
    public String homePage(){
        return "redirect:/admin/article/list";
    }

    /**
     * 跳转到文章列表页面
     * @return
     */
    @RequestMapping("/article/list")
    public String articlePage(Model model){
        List<Tag> tagList = tagService.getTagList();
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("tagList",tagList);
        model.addAttribute("categoryList",categoryList);
        return "admin/article/articleList";
    }


    @RequestMapping("/tag/list")
    public String labelPage(){
        return "admin/label/labelList";
    }
    /**
     * 加载友链分页
     * @param pager
     * @param model
     * @return
     */
    @RequestMapping("/partner/load")
    public String loadPartner(Pager pager, Model model,String param){
        pager.setStart((pager.getPage()-1)*pager.getLimit());
        List<Partner> partnerList =partnerService.loadPartner(pager,param);
        model.addAttribute("partnerList",partnerList);
        return "admin/partner/partnerTable";
    }

    /**
     * 跳转添加友链页面
     * @return
     */
    @RequestMapping("/partner/addJump")
    public String partnerAddPage(){
        return "admin/partner/partnerAdd";
    }

    @RequestMapping("/partner/editJump/{id}")
    public String partnerEditPage(@PathVariable Integer id, Model model){
        Partner partner = partnerService.getPartnerById(id);
        model.addAttribute("partner",partner);
        return "admin/partner/partnerEdit";
    }

    /**跳转到友链展示页面
     * @return
     */
    @RequestMapping("/partner/list")
    public String partnerPage(){
        return "admin/partner/partnerList";
    }
}
