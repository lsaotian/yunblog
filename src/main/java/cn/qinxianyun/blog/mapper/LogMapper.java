package cn.qinxianyun.blog.mapper;

import cn.qinxianyun.blog.vo.LogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    /**
     * 保存日志信息
     * @param log
     */
    void save(LogInfo log);
}
