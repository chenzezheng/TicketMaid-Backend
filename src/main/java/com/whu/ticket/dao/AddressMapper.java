package com.whu.ticket.dao;

import com.whu.ticket.pojo.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {
    int insertAddress(Address address);
    int deleteAddress(int id,int user_id);
    List<Address> selectByUserID(int user_id,int offset,int limit);
    void updateAddress(Address address);
    Address selectById(int id);
}
