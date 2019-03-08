package com.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Hello world!
 *
 */
@Controller
@EnableAutoConfiguration
public class App {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "SpringBoot小白教程";
    }

    @RequestMapping("/wallpapers")
    @ResponseBody
    public List<Map<String, Object>> wallpapers() {
        String sql = "select * from wallpaper";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    @CrossOrigin(origins = "*")
    @RequestMapping("/auth")
    @ResponseBody
    public TokenInfo auth(@RequestParam(required = true, defaultValue = "") String loginId,
            @RequestParam(required = true, defaultValue = "") String loginPassword) {
        String sql = "select login_password from dt_account where login_id = ?";
        Object[] object = { loginId };
        String password = jdbcTemplate.queryForObject(sql, object, String.class);
        if (password.equals(loginPassword)) {
            return new TokenInfo(JwtUtils.getToken(loginId), 200, "");
        } else {
            return new TokenInfo("", 500, "登录失败，用户名密码错误");
        }
    }
    @CrossOrigin(origins = "*")
    @RequestMapping("/list")
    @ResponseBody
    public PageBaseInfo findWallpaperList(@RequestParam(required = true, defaultValue = "") String token,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int rows,
            @RequestParam(required = false, defaultValue = "") String title) {
        try {
            String account = JwtUtils.checkToken(token);
            if (!account.equals(null)) {
                List<Object> queryList = new ArrayList<Object>();
                String whereCondition = "";
                if (!title.equals("")) {
                    whereCondition = " and title like ? ";
                    queryList.add("%" + title + "%");
                }
                Integer iCount = (Integer) jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM wallpaper where 1 = 1" + whereCondition, queryList.toArray(),
                        java.lang.Integer.class);

                String sql = "select * from wallpaper where 1 = 1" + whereCondition;
                sql += " limit " + String.valueOf((page - 1) * rows) + "," + String.valueOf(rows);
                List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, queryList.toArray());
                PageBaseInfo info = new PageBaseInfo(list, page, iCount, rows);
                info.setAccount(account);
                return info;
            } else {
                return new PageBaseInfo(401, "token error");
            }
        } catch (Exception e) {
            return new PageBaseInfo(500, e.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
