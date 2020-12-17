package com.whu.ticket.controller;

import com.whu.ticket.annotation.UserLogin;
import com.whu.ticket.entity.Address;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.service.AddressService;
import com.whu.ticket.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;

    @UserLogin
    @PostMapping("/add")
    public Result addAddress(HttpServletRequest request) {
        String name = request.getParameter("name");
        String token = request.getHeader("access_token");
        int user_id = JwtUtil.getUserID(token);
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Address Taddress = new Address();
        Taddress.setAddress(address);
        Taddress.setUser_id(user_id);
        Taddress.setName(name);
        Taddress.setPhone(phone);
        try {
            addressService.addAddress(Taddress);
            return new Result(0, null, "加入地址信息成功");
        } catch (Exception e) {
            log.info("add fail");
            return new Result(-1, null, e.getMessage());
        }
    }

    @UserLogin
    @GetMapping("/query")
    public Result queryAddress(HttpServletRequest request) {
        int pageNo = 0;
        int pageSize = 0;
        if (!StringUtils.isBlank(request.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        if (!StringUtils.isBlank(request.getParameter("pageSize"))) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        String token = request.getHeader("access_token");
        int user_id = JwtUtil.getUserID(token);
        addressService.queryAddress(user_id, pageNo, pageSize);
        return new Result(0, addressService.queryAddress(user_id, pageNo, pageSize), "查询成功");
    }

    @UserLogin
    @DeleteMapping("/remove")
    public Result deleteAddress(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String token = request.getHeader("access_token");
        int user_id = JwtUtil.getUserID(token);
        try {
            addressService.deleteAddress(id, user_id);
            return new Result(0, null, "删除成功");
        } catch (Exception e) {
            log.info("delete fail");
            return new Result(0, null, e.getMessage());
        }
    }

    @UserLogin
    @PutMapping("/modify")
    public Result modifyAddress(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String token = request.getHeader("access_token");
        int user_id = JwtUtil.getUserID(token);
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Address newAddress = new Address();
        newAddress.setId(id);
        newAddress.setAddress(address);
        newAddress.setName(name);
        newAddress.setPhone(phone);
        newAddress.setUser_id(user_id);
        addressService.modifyAddress(newAddress);
        return new Result(0, null, "修改成功");
    }
}
