package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.system.domain.SysEmail;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Api("面向用户购票")
@Anonymous
@RestController
@RequestMapping
public class SightsTicketAnController {


    public static void main(String[] args) {

        // 用于存储 路径的 那就是存 地点 id -> 用List存
        List<Long> Path = new LinkedList<>();
        // 用于存储到各点最短路径的 权值 <数据> 和  假设为Long  我们的评价指标应该是一个对象
        List<Long> ShortPathTable = new LinkedList<>();

        List<SysEmail> list = new LinkedList<>();

        Map<String, List<Map<String, SightsBase>>> map = new HashMap<>();
        Map<String, List<SightsBase>> map1 = new HashMap<>();  // 这样也okk呀

        EntityUtils.toMap(list, e1 -> e1.getEmailId(), e2 -> {
            List<Map<String,SysEmail>> maps = new LinkedList<>();

            list.size();
            return null;
        });



    }
}
