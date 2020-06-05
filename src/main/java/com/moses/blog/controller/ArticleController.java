package com.moses.blog.controller;

import com.moses.blog.service.ArticleService;
import com.moses.blog.service.TypeInfoService;
import com.moses.blog.view.Article;
import com.moses.blog.view.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Moses
 */
@Controller
@RequestMapping("article_info")
public class ArticleController {

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
    @RequestMapping("list_normal.action")
    public String listNormal(ModelMap map) {
        List<Article> list = articleService.list();
        System.err.println(list.size());
        map.put("list", list);
        return "admin/article_info/list_normal";
    }

    /**
     * 编辑文章
     *
     * @param id  id
     * @param map map
     * @return 视图
     */
    @RequestMapping("edit.action")
    public String edit(@RequestParam(required = false, value = "id") Integer id,
                       ModelMap map) {
        //查询单个文章的信息
        if (id != null) {
            Article article = articleService.findArticleById(id);
            map.put("article", article);
        }
        //查询所有文章分类
        map.put("typeList", typeInfoService.list());
        map.put("id", id);

        return "admin/article_info/edit";
    }

    @RequestMapping("upload.json")
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
    @RequestMapping("save.json")
    @ResponseBody
    public JsonResult<Void> save(Article article) {

        if (article.getId() != null) {
            article.setUpdateTime(new Date());
            articleService.update(article);
        } else {
            article.setUpdateTime(new Date());
            article.setStatus(1);
            article.setViewCount(1);
            articleService.insert(article);
        }
        return new JsonResult<>();
    }
}
