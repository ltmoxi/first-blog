package com.moses.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moses.blog.controller.BaseController;
import com.moses.blog.entity.Article;
import com.moses.blog.entity.JsonResult;
import com.moses.blog.entity.TypeInfo;
import com.moses.blog.service.ArticleService;
import com.moses.blog.service.TypeInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责处理文章详细数据的管理
 * @author Moses
 */
@Controller
@RequestMapping("admin")
public class ArticleController extends BaseController {

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
    @RequestMapping("article_info/list_normal.action")
    public String listNormal(ModelMap map,
                             @RequestParam(required = false, value = "typeId") Integer typeId,
                             @RequestParam(required = false, value = "startDate") String startDate,
                             @RequestParam(required = false, value = "endDate") String endDate,
                             @RequestParam(required = false, value = "keyWord") String keyWord,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) {

        //把所有条件都放在一个map里,以便查询
        Map<String, Object> param = new HashMap<>();
        param.put("typeId", typeId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }
        param.put("status", "1");

        //pageHelper分页插件
        //只要在查询之前调用,传入当前页码,以及每一页显示多少条
        //这个分页插件具体怎么用的不清楚,反正这么写能实现功能
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<>(list);


        map.put("typeId", typeId);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("keyWord", keyWord);
        //查询所有类型信息并把类型信息放在Model中,
        List<TypeInfo> typeInfoList = typeInfoService.list();
        map.put("typeList", typeInfoList);
        map.put("pageInfo", pageInfo);
        return "admin/article_info/list_normal";
    }

    /**
     * 查看所有被放入回收站的文章
     *
     * @param map map
     * @return 视图
     */
    @RequestMapping("article_info/list_recycle.action")
    public String listRecycle(ModelMap map,
                              @RequestParam(required = false, value = "typeId") Integer typeId,
                              @RequestParam(required = false, value = "startDate") String startDate,
                              @RequestParam(required = false, value = "endDate") String endDate,
                              @RequestParam(required = false, value = "keyWord") String keyWord,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) {

        Map<String, Object> param = new HashMap<>();
        param.put("typeId", typeId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }
        param.put("status", "0");

        //pageHelper分页插件
        //只要在查询之前调用,传入当前页码,以及每一页显示多少条
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<>(list);


        map.put("typeId", typeId);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("keyWord", keyWord);
        //查询所有类型信息并把类型信息放在Model中,
        List<TypeInfo> typeInfoList = typeInfoService.list();
        map.put("typeList", typeInfoList);
        map.put("pageInfo", pageInfo);
        return "admin/article_info/list_recycle";
    }

    /**
     * 编辑指定id的文章
     *
     * @param id  文章id
     * @param map map
     * @return 视图
     */
    @RequestMapping("article_info/edit.action")
    public String edit(@RequestParam(required = false, value = "id") Integer id,
                       ModelMap map) {
        //查询单个文章的信息
        if (id != null && id != -1) {
            Article article = articleService.findArticleById(id);
            map.put("article", article);
            map.put("id", id);
        } else {
            /*
                不知道为什么edit.jsp页面41行如果获取到的是null值,则会默认为1,所以需要把新增文章的id设置为-1,
                为什么设置为-1可以看一下那一行
             */
            map.put("id", -1);
        }

        //查询所有文章分类
        map.put("typeList", typeInfoService.list());

        return "admin/article_info/edit";
    }

    /**
     * 上传封面
     *
     * @param file    封面文件
     * @param request request
     * @return 处理信息
     * @throws IOException IO异常
     */
    @RequestMapping("article_info/upload.json")
    @ResponseBody
    public JsonResult<String> upload(@RequestParam("file") CommonsMultipartFile file,
                                     HttpServletRequest request) throws IOException {

        //用户上传的文件储存到的文件夹的名称
        String uploadDirName = "upload";
        //用户数上传的文件储存到的文件夹的路径
        String parentDirPath = request.getServletContext().getRealPath(uploadDirName);
        //用户数航船的文件储存到的文件夹
        File parentDir = new File(parentDirPath);
        //确保文件夹存在
        if (!parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.err.println("文件夹创建失败!");
            }
        }
        //获取原始文件名
        String originalFileName = file.getOriginalFilename();
        //获取原始文件名的扩展名
        int beginIndex = originalFileName.lastIndexOf(".");
        String suffix = originalFileName.substring(beginIndex);

        //用户上传的文件储存的文件名
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date) + suffix;
        //确定用户上传的文件在服务器的路径
        String imgPath = uploadDirName + "/" + fileName;
        //用户上传的文件存储到服务器端的文件对象
        File dest = new File(parentDir, fileName);
        //将用户数上传的文件存储到服务器端的文件对象
        file.transferTo(dest);
        System.out.println(request.getServletContext().getRealPath(imgPath));
        return new JsonResult<>(2000, "上传成功!", imgPath);
    }

    /**
     * 保存文章
     *
     * @param article 文章数据
     * @return json格式的数据
     */
    @RequestMapping("article_info/save.json")
    @ResponseBody
    public JsonResult<Void> save(Article article) {

        //注意这里判定了article.getId()是否为-1,原因在上面
        if (article.getId() != null && article.getId() != -1) {
            //如果文章id不等于0,且不等于-1,那么就是修改文章,需要更新日期信息
            article.setUpdateTime(new Date());
            articleService.update(article);
        } else {
            //如果id为空,或者id为-1,那么就是新增文章,需要添加一些网页上不会填写的信息
            article.setUpdateTime(new Date());
            article.setStatus(1);
            article.setViewCount(1);
            articleService.insert(article);
        }
        return new JsonResult<>();
    }


    /**
     * 批量更改文章的类型
     *
     * @param idArr  文章id数组
     * @param typeId 类型id
     * @return json返回值
     */
    @RequestMapping("article_info/move.json")
    @ResponseBody
    public JsonResult<Void> move(@RequestParam("idArr") Integer[] idArr,
                                 @RequestParam("typeId") Integer typeId) {
        articleService.updateTypeId(idArr, typeId);

        return new JsonResult<>();
    }

    /**
     * 批量更新文章状态
     *
     * @param idArr  文章id数组
     * @param status 状态的值
     * @return json返回值
     */
    @RequestMapping("article_info/update_status.json")
    @ResponseBody
    public JsonResult<Void> updateStatus(@RequestParam("idArr") Integer[] idArr, @RequestParam("status") Integer status) {

        articleService.updateStatus(idArr, status);

        return new JsonResult<>();
    }


    /**
     * 批量删除文章
     *
     * @param idArr 文章id数组
     * @return 返回json数据
     */
    @RequestMapping("article_info/delete.json")
    @ResponseBody
    public JsonResult<Void> delete(@Param("idArr") Integer[] idArr) {

        articleService.delete(idArr);

        return new JsonResult<>();
    }
}
