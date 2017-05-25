package cn.qinxianyun.blog.controller.admin;

import cn.qinxianyun.blog.service.PartnerService;
import cn.qinxianyun.blog.util.ResultInfo;
import cn.qinxianyun.blog.util.ResultInfoFactory;
import cn.qinxianyun.blog.vo.Pager;
import cn.qinxianyun.blog.vo.Partner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLDecoder;

@RestController
@RequestMapping("/admin/partner")
public class AdminPartnerController {
    @Resource
    private PartnerService partnerService;

    @RequestMapping("/initPage")
    public Pager initPage(Pager pager){
        partnerService.initPage(pager);
        return pager;
    }

    @RequestMapping("/save")
    public ResultInfo savePartner(Partner partner){
        try {
            partner.setSiteName(URLDecoder.decode(partner.getSiteName(), "utf-8"));
            partner.setSiteDesc(URLDecoder.decode(partner.getSiteDesc(), "utf-8"));
            partner.setSiteUrl(URLDecoder.decode(partner.getSiteUrl(), "utf-8"));
            partnerService.savePartner(partner);
        }catch (Exception e){
            return ResultInfoFactory.getErrorResultInfo();
        }
       return ResultInfoFactory.getSuccessResultInfo();

    }

    @RequestMapping("/update")
    public ResultInfo updatePartner(Partner partner) {
        try {
            partner.setSiteName(URLDecoder.decode(partner.getSiteName(), "utf-8"));
            partner.setSiteDesc(URLDecoder.decode(partner.getSiteDesc(), "utf-8"));
            partner.setSiteUrl(URLDecoder.decode(partner.getSiteUrl(), "utf-8"));
            if (partner.getSort()==null){
                partner.setSort(0);
            }
            partnerService.updatePartner(partner);
        } catch (Exception e) {
            return ResultInfoFactory.getErrorResultInfo();
        }
       return ResultInfoFactory.getSuccessResultInfo();

    }
    @RequestMapping("/delete/{id}")
    public ResultInfo deletePartner(@PathVariable Integer id){
        try {
            partnerService.deletePartner(id);
        }catch (Exception e){
            return ResultInfoFactory.getErrorResultInfo();
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }
}
