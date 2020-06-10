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
@RequestMapping("admin/type_info")
public class TypeController extends BaseController {

    @Autowired
    private TypeInfoService typeInfoService;

    /**
     * 查询所有文章分类
     */
    @RequestMapping("/list_type.action")
    public String list(ModelMap map) {
        List<TypeInfo> list = typeInfoService.list();
        map.put("typeInfo", list);
        return "web/admin/type_info/list_type";
    }


    /**
     * 保存/修改类型信息
     *
     * @param id   类型id
     * @param sort 类型顺序
     * @param name 类型名称
     * @return json数据
     */
    @ResponseBody
    @RequestMapping("/save.json")
    public JsonResult<Void> saveHandler(@RequestParam("id") Integer id,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam("name") String name) {
        typeInfoService.save(id, sort, name);
        return new JsonResult<>();
    }

    /**
     * 删除文章分类
     *
     * @param id 文章分类id数组
     * @return json数据
     */
    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonResult<Void> delete(@RequestParam("id") Integer id) {
        typeInfoService.delete(id);
        return new JsonResult<>();
    }
}
