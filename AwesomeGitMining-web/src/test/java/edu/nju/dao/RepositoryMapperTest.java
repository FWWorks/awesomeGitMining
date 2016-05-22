package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepositoryMapperTest {


    @Resource
    private RepositoryMapper dao;

    //    @Test
//    public void selectAll() throws Exception {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 300);
//        map.put("pageOffset", 0);
//        List<Repository> list = dao.selectAllPaged(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getDescription());
//    }
//
//    @Test
//    public void selectByFullName() throws Exception {
//
//    }
//
//    @Test
//    public void testForkSort() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 2);
//        map.put("pageOffset", 20);
//        List<Repository> list = dao.selectReposSortedByFork(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getForksCount());
//    }
//
    @Test
    public void testSearch() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("full_name", "tj");
        List<Repository> list = dao.searchRepository(map);
        for (Repository u : list)
            System.out.println(u.getCreated_at());

    }


    @Test
    public void selectRankOfFork() throws Exception {
        System.out.println(dao.selectRankOfFork("jquery/jquery"));
    }

    @Test
    public void selectRankOfSize() throws Exception {
        System.out.println(dao.selectRankOfSize("jquery/jquery"));
    }

//

//    @Test
//    public void testCount() {
//        System.out.println(dao.countSearch("tj"));
//    }
//    @Test
//    public void testYear(){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 3000);
//        map.put("pageOffset", 0);
//        map.put("year",2010);
//        List<Repository> list = dao.selectReposByYear(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getCreatedAt());
//    }
//    @Test
//    public void testLanguage(){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 3000);
//        map.put("pageOffset", 0);
//        map.put("language","java");
//        List<Repository> list = dao.selectReposByLanguage(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getLanguage());
//    }
//    @Test
//    public void testKey(){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 3000);
//        map.put("pageOffset", 0);
//        map.put("keyword","java");
//        List<Repository> list = dao.selectReposByKey(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getDescription());
//    }
//
//    @Test
//    public void testLan_Key_Year(){
//        String lan="Ruby";
//        String key="Ruby";
//        String year="2008";
////            System.out.println(dao.countLan_Key_Year(lan,key,year));
//    }
//
    @Test
    public void selectReposByLan_Key_Year() {

        List<Repository> list = dao.selectReposByLan_Key_Year("", "", "", "forks_count", 10, 0);
        for (Repository u : list)
            System.out.println(u.getFull_name() + ":" + u.getDescription());
        System.out.println(list.size());
    }


    @Test
    public void selectFullNameTest() throws Exception {
        List<String> list = dao.selectFullName();
        for (String u : list)
            System.out.println(u);
    }

    @Test
    public void getContributorsTest() throws Exception {
        String name = "technomancy/leiningen";
        List<String> list = dao.getContributors(name);
        for (String u : list)
            System.out.println(u);

    }


    @Test
    public void getCollaTest() throws Exception {
        String name = "technomancy/leiningen";
        List<String> list = dao.getCollaborators(name);
        for (String u : list)
            System.out.println(u);


    }

    @Test
    public void getYearTest() throws Exception {
        List<String> list = dao.getYear();
        for (String u : list)
            System.out.println(u);


    }

    @Test
    public void countLanguagesCreatedTest() throws Exception {
        System.out.println(dao.countLanguagesCreated("2008", "Ruby"));
    }


    @Test
    public void eachYearTest() throws Exception {
        List<String> list = dao.eachYear();
        for (String u : list)
            System.out.println(u);

    }

    @Test
    public void eachSizeTest() throws Exception {
        List<Integer> list = dao.eachSize();
        for (Integer u : list)
            System.out.println(u);

    }

    @Test
    public void countAverageSize_year() throws Exception {
        System.out.println(dao.countAverageSize_year());

    }

    @Test
    public void testSelectLanguage() {
        List<String> list = dao.selectLanguages();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}