package edu.nju.service;

import edu.nju.dao.MemberMapper;
import edu.nju.model.Member;
import edu.nju.model.Recommender;
import edu.nju.model.Repository;

import java.util.List;

/**
 * Created by tj on 2016/5/7.
 */
public interface IMemberService {
    /**
     * 用户注册
     * @param member
     * @return 注册失败返回错误信息 成功返回null
     */
    String register(Member member);

    /**
     * 用户登录
     * @param member
     * @return 登陆失败返回错误信息 成功返回null
     */
    String login(Member member);

    /**
     * 得到收藏的项目
     * @param userName
     * @return
     */
    List<Repository> getStaredRepos(String userName);

    /**
     * 根据搜索记录获得推荐的项目
     * @param userName
     * @return
     */
    List<Recommender> getRecommendBySearched(String userName);
}
