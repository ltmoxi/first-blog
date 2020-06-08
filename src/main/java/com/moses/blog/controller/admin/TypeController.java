package com.moses.blog.controller.admin;

import com.moses.blog.controller.BaseController;
import com.moses.blog.entity.JsonResult;
import com.moses.blog.entity.TypeInfo;
import com.moses.blog.service.TypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 负责处理文章类型的管理
 *
 * @author Moses
 */
@Controller
@RequestMapping("admin")
public class TypeController extends BaseController {

    @Autowired
    private TypeInfoService typeInfoService;

    /**
     * 查询所有文章分类
     */
    @RequestMapping("type_info/list.action")
    public String list(ModelMap map) {
        List<TypeInfo> list = typeInfoService.list();
        map.put("list", list);
        return "admin/type_info/list_type";
    }

    /**
     * 批量保存/修改类型信息
     *
     * @param idArr   类型id数组
     * @param sortArr 类型顺序数组
     * @param nameArr 类型名称数组
     * @return json数据
     */
    @ResponseBody
    @RequestMapping("type_info/save.json")
    public JsonResult<Void> saveListHandler(@RequestParam("idArr") String[] idArr,
                                            @RequestParam("sortArr") String[] sortArr,
                                            @RequestParam("nameArr") String[] nameArr) {

        //idArr   类型id数组
        //sortArr 类型排序数组
        //nameArr 类型名称数组
        typeInfoService.save(idArr, sortArr, nameArr);

        return new JsonResult<>();
    }

    /**
     * 批量删除文章分类
     *
     * @param idArr 文章分类id数组
     * @return json数据
     */
    @RequestMapping("type_info/delete.json")
    @ResponseBody
    public JsonResult<Void> delete(@RequestParam("idArr") Integer[] idArr) {
        typeInfoService.delete(idArr);
        return new JsonResult<>();
    }
}
