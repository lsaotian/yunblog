package cn.qinxianyun.blog.mapper;

import cn.qinxianyun.blog.vo.Pager;
import cn.qinxianyun.blog.vo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {

    int getTagCount();

    Tag getTagById(Integer id);

    List<Tag> loadTagList(@Param("pager") Pager pager, @Param("tagName") String tagName);

    void saveTag(Tag tag);

    int checkExist(Tag tag);

    void updateTag(Tag tag);

    int initPage(Pager pager);

    List<Tag> getTagList();

    /**
     * 加载此tag下的所有文章
     * @return
     * @param tagId
     */
    int articleTagPage(int tagId);
}
