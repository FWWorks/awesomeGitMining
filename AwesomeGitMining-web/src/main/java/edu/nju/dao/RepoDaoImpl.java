package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import edu.nju.model.SystemContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tj on 2016/5/3.
 */
@Service("repoDao")
public class RepoDaoImpl implements IRepoDao {
    @Resource
    private RepositoryMapper mapper;

    @Override
    public Repository selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Repository> getAll() {
        return mapper.selectAll();
    }

    @Override
    public Pager<Repository> getAllPaged() {
        Map<String, Object> map = createMap();
        List<Repository> data = mapper.selectAllPaged(map);
        return createPage(data,map);
    }


    @Override
    public Repository getReposByFullName(String full_name) {
        return mapper.selectByFullName(full_name);
    }

    @Override
    public Pager<Repository> searchRepository(String name) {
        Map<String, Object> map = createMap();
        map.put("fullName",name);
        List<Repository> data = mapper.searchRepository(map);
        Pager<Repository> page = createPage(data,map);
        page.setTotal(mapper.countSearch(name));
        return page;
    }

    @Override
    public Pager<Repository> getReposSortedByFork() {
        Map<String, Object> map = createMap();
        List<Repository> data = mapper.selectReposSortedByFork(map);
        return createPage(data,map);
    }

    @Override
    public Pager<Repository> getReposSortedByContribute() {
        Map<String, Object> map = createMap();
        List<Repository> data = mapper.selectReposSortedByContribute(map);
        return createPage(data,map);
    }

    @Override
    public Pager<Repository> getReposSortedByStar() {
        Map<String, Object> map = createMap();
        List<Repository> data = mapper.selectReposSortedByStar(map);
        return createPage(data,map);
    }

    @Override
    public Map<String, Integer> getLanguageStatistics() {
        return null;
    }

    @Override
    public Map<String, Integer> getCreatedTimeStatistics() {
        return null;
    }

    @Override
    public Map<String, Integer> getCodeFrequency(String name) {
        return null;
    }

    @Override
    public Pager<Repository> getReposByYear(int year) {
        Map<String, Object> map = createMap();
        map.put("year",year);
        List<Repository> data = mapper.selectReposByYear(map);
        Pager<Repository> page = createPage(data,map);
        page.setTotal(mapper.countYear(year));
        return page;
    }

    @Override
    public Pager<Repository> getReposByLanguage(String language) {
        Map<String, Object> map = createMap();
        map.put("language",language);
        List<Repository> data = mapper.selectReposByLanguage(map);
        Pager<Repository> page = createPage(data,map);
        page.setTotal(mapper.countLanguage(language));
        return page;
    }
    @Override
    public Pager<Repository> getReposByLan_Key_Year(String language, String keyword, String year) {
        if(language!=null) {
            language = "^" + language + "$";
        }
        System.out.println("keyword="+keyword);
        Map<String, Object> map = createMap();
        List<Repository> data = mapper.selectReposByLan_Key_Year(language,keyword,year,(int)map.get("pageSize"),(int)map.get("pageOffset"));
        Pager<Repository> page = createPage(data,map);
        page.setTotal(mapper.countLan_Key_Year(language,keyword,year));

        return page;
    }

    @Override
    public List<String> getContributors(String repo_fullname) {
        List<String>list=mapper.getContributors(repo_fullname);
        return list;
    }

    @Override
    public List<String> getCollaborators(String repo_fullname) {
        List<String>list=mapper.getCollaborators(repo_fullname);
        return list;
    }

    @Override
    public Pager<Repository> getReposByKey(String key) {
        Map<String, Object> map = createMap();
        map.put("keyword",key);
        List<Repository> data = mapper.searchRepository(map);
        Pager<Repository> page = createPage(data,map);
        page.setTotal(mapper.countKey(key));
        return page;
    }



    private Map<String, Object> createMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        int pageSize = SystemContext.getSize();
        int pageOffset = SystemContext.getOffset();
        map.put("pageSize", pageSize);
        map.put("pageOffset", pageOffset);
        return map;
    }

    private Pager<Repository> createPage(List<Repository> data, Map<String, Object> map) {
        Pager<Repository> pages = new Pager<Repository>(data, (int)map.get("pageOffset"),
                (int)map.get("pageSize"), mapper.countAll());
        return pages;
    }
}
