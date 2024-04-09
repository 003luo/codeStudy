package com.codestudy.dao;

import com.codestudy.entity.Likes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Goodorbad)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-09 09:15:21
 */
@Mapper
public interface LikesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Likes queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param likes 查询条件
     * @param \
     * @return 对象列表
     */
    List<Likes> queryByCondition(Likes likes);

    /**
     * 统计总行数
     *
     * @param likes 查询条件
     * @return 总行数
     */
    int count(Likes likes);

    /**
     * 新增数据
     *
     * @param likes 实例对象
     * @return 影响行数
     */
    int insert(Likes likes);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Goodorbad> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Likes> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Goodorbad> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Likes> entities);

    /**
     * 修改数据
     *
     * @param likes 实例对象
     * @return 影响行数
     */
    int update(Likes likes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

