package com.moses.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moses.blog.entity.Article;
import com.moses.blog.entity.JsonResult;
import com.moses.blog.entity.TypeInfo;
import com.moses.blog.exception.ServiceException;
import com.moses.blog.service.ArticleService;
import com.moses.blog.service.TypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责处理游客信息访问
 *
 * @author Moses
 */
@Controller
@RequestMapping("portal")
public class PortalController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeInfoService typeInfoService;


    /**
     * 查看所有未被放入回收站的文章
     *
     * @param map map
     * @return 视图
     */
    @RequestMapping("index.action")
    public String listNormal(ModelMap map,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        Map<String, Object> param = new HashMap<>();
        param.put("status", "1");

        //pageHelper分页插件
        //只要在查询之前调用,传入当前页码,以及每一页显示多少条
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<>(list);

        map.put("pageInfo", pageInfo);

        map.put("pageNum", pageNum);
        return "/web/index";
    }


    @RequestMapping("get_type.json")
    @ResponseBody
    public JsonResult<List<TypeInfo>> listType() {

        List<TypeInfo> typeInfoList = typeInfoService.list();

        return new JsonResult<>(2000, "获取成功", typeInfoList);
    }


    /**
     * 返回未被回收的所有文章
     *
     * @param map      map
     * @param typeId   类型id
     * @param pageNum  分页插件的参数,第几页
     * @param pageSize 分页插件的参数,一页有几个数据
     * @return view
     */
    @RequestMapping("type.action")
    public String type(ModelMap map,
                       @RequestParam(value = "typeId") String typeId,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "100") int pageSize) {

        map.put("pageNum", pageNum);
        map.put("typeId", typeId);
        return "web/type";
    }

    /**
     * 返回未被回收的所有文章(如果填写了分类id参数,那么就会根据分类id参数来查找)
     *
     * @param typeId   类型id
     * @param pageNum  分页插件的参数,第几页
     * @param pageSize 分页插件的参数,一页有几个数据
     * @return view
     */
    @ResponseBody
    @RequestMapping("type.json")
    public JsonResult<PageInfo<Article>> gettype(@RequestParam(required = false, value = "typeId") String typeId,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println(pageNum);

        Map<String, Object> param = new HashMap<>();
        param.put("typeId", typeId);
        param.put("status", "1");

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        if (list.size() == 0) {
            throw new ServiceException("未能找到文章数据!");
        }
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return new JsonResult<>(pageInfo);
    }


    /**
     * 根据文章id查询单个文章的数据,返回文章详情页面
     *
     * @param map ModelMap
     * @param id  文章数据
     * @return view
     */
    @RequestMapping("article.action")
    public String article(ModelMap map,
                          @RequestParam(value = "id") Integer id) {

        Article articleInfo = articleService.findArticleById(id);
        if (articleInfo == null) {
            return "web/error/404";
        }
        map.put("article", articleInfo);

        return "web/article";
    }


    @ResponseBody
    @RequestMapping("search.json")
    public JsonResult<PageInfo<Article>> getSearchResult(ModelMap map,
                                                         @RequestParam(required = false, value = "keyWord") String keyWord,
                                                         @RequestParam(defaultValue = "1", value = "pageNum") int pageNum,
                                                         @RequestParam(defaultValue = "100", value = "pageSize") int pageSize) {

        Map<String, Object> param = new HashMap<>();
        param.put("keyWord", "%" + keyWord + "%");
        param.put("status", "1");

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        if (list.size() == 0) {
            throw new ServiceException("未能找到文章数据!");
        }
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return new JsonResult<>(pageInfo);
    }

    /**
     * 搜索文章
     */
    @RequestMapping("search.action")
    public String search(ModelMap map,
                         @RequestParam(value = "keyWord") String keyWord,
                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "100") int pageSize) {

        map.put("keyWord", keyWord);
        return "web/search";
    }

    /**
     * 关于我页面
     *
     * @param map ModelMap
     * @return view
     */
    @RequestMapping("about.action")
    public String about(ModelMap map) {

        return "web/about";
    }

    /**
     * group页面
     *
     * @return view
     */
    @RequestMapping("group.action")
    public String group() {

        return "web/group";
    }


}
