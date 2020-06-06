package com.moses.blog.controller;

import com.moses.blog.service.TypeInfoService;
import com.moses.blog.entity.JsonResult;
import com.moses.blog.entity.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Moses
 */
@Controller
@RequestMapping("type_info")
public class TypeController extends BaseController {

    @Autowired
    private TypeInfoService typeInfoService;

    /**
     * 查询所有文章分类
     */
    @RequestMapping("list.action")
    public String list(ModelMap map) {
        List<TypeInfo> list = typeInfoService.list();
        map.put("list", list);
        System.out.println(list);
        return "admin/type_info/list";
    }

    @ResponseBody
    @RequestMapping("save.json")
    public JsonResult<Void> saveListHandler(@RequestParam("idArr") String[] idArr,
                                            @RequestParam("sortArr") String[] sortArr,
                                            @RequestParam("nameArr") String[] nameArr) {
        System.out.println("TypeController.saveListHandler");
        typeInfoService.save(idArr, sortArr, nameArr);

        return new JsonResult<>();
    }

    /**
     * 批量删除文章分类
     *
     * @param idArr 文章分类id数组
     * @return json数据
     */
    @RequestMapping("delete.json")
    @ResponseBody
    public JsonResult<Void> delete(@RequestParam("idArr") Integer[] idArr) {
        typeInfoService.delete(idArr);
        return new JsonResult<>();
    }
}
