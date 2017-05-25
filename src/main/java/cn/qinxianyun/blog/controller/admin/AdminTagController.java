package cn.qinxianyun.blog.controller.admin;

import cn.qinxianyun.blog.service.TagService;
import cn.qinxianyun.blog.util.ResultInfo;
import cn.qinxianyun.blog.util.ResultInfoFactory;
import cn.qinxianyun.blog.vo.Pager;
import cn.qinxianyun.blog.vo.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Resource
    private TagService tagService;


    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager, Model model){
        tagService.initPage(pager);
        return pager;
    }

    @RequestMapping("/editJump/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);
        return "admin/label/labelEdit";
    }

    @RequestMapping("/addJump")
    public String addPage(){
        return "admin/label/labelAdd";
    }

    @RequestMapping("/load")
    public String loadTagList(Pager pager,String tagName,Model model){
        List<Tag> tagList = tagService.loadTagList(pager,tagName);
        model.addAttribute("tagList",tagList);
        return "admin/label/labelTable";
    }


    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo saveTag(Tag tag){
        try {
            tag.setAliasName(URLDecoder.decode(tag.getAliasName(),"UTF-8"));
            tag.setTagName(URLDecoder.decode(tag.getTagName(),"UTF-8"));
            if (tagService.checkExist(tag)){
                return ResultInfoFactory.getErrorResultInfo("标签名或别名已经存在");
            }
            tagService.saveTag(tag);
        } catch (UnsupportedEncodingException e) {
            ResultInfoFactory.getErrorResultInfo("添加失败,字符串格式化错误");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateTag(Tag tag){
        if (tagService.checkExist(tag)){
            return ResultInfoFactory.getErrorResultInfo("已存在相同的标签名或者别名");
        }
        tagService.updateTag(tag);
        return ResultInfoFactory.getSuccessResultInfo();
    }
}
