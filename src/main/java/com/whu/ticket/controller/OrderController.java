package com.whu.ticket.controller;

import com.whu.ticket.annotation.UserLogin;
import com.whu.ticket.entity.Favorite;
import com.whu.ticket.entity.Order;
import com.whu.ticket.pojo.Result;
import com.whu.ticket.service.OrderService;
import com.whu.ticket.util.JwtUtil;
import com.whu.ticket.vo.OrderVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private  static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @UserLogin
    @PostMapping("/add")
    public Result addOrder(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int eventId = Integer.parseInt(request.getParameter("event_id"));
        Date time = new Date();
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Order order = new Order();
        order.setUser_id(userId);
        order.setEvent_id(eventId);
        order.setTime(time);
        order.setQuantity(quantity);
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);

        try {
            orderService.addOrder(order);
            return new Result(0, order, "添加订单成功");
        } catch (Exception e) {
//            e.printStackTrace();
            return new Result(-1, null, e.getMessage());
        }
    }

    @UserLogin
    @DeleteMapping("/remove")
    public Result removeOrder(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.removeOrder(id, userId);
        return new Result(0, null, "删除订单成功");
    }

    @UserLogin
    @GetMapping("/pages")
    public Result queryPages(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int nums = orderService.queryPageNums(userId, pageSize);
        return new Result(0, nums, "查询页数成功");
    }

    @UserLogin
    @GetMapping("/query")
    public Result queryOrder(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<OrderVO> orders = orderService.queryOrder(userId, pageNo, pageSize);
        return new Result(0, orders, "查询订单列表成功");
    }

    @UserLogin
    @GetMapping("/info")
    public Result queryOrderInfo(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int userId = JwtUtil.getUserID(token);
        int orderId = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.queryOrderInfo(orderId, userId);
        return new Result(0, order, "查询订单详情成功");
    }
}
