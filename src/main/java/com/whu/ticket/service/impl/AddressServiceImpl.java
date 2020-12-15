package com.whu.ticket.service.impl;

import com.whu.ticket.dao.AddressMapper;
import com.whu.ticket.pojo.Address;
import com.whu.ticket.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public void addAddress(Address address) {
        addressMapper.insertAddress(address);
    }

    @Override
    public void deleteAddress(int id,int user_id){
        Address res = addressMapper.selectById(id);
        if(res != null){
            addressMapper.deleteAddress(id,user_id);
            return;
        }
        throw new RuntimeException("无对应地址信息");
    }

    @Override
    public List<Address> queryAddress(int user_id, int pageNo, int pageSize) {
        return addressMapper.selectByUserID(user_id,(pageNo-1)*pageSize,pageSize);
    }

    @Override
    public void modifyAddress(Address address) {
        Address res = addressMapper.selectById(address.getId());
        if(res != null){
            addressMapper.updateAddress(address);
            return;
        }
        throw new RuntimeException("无对应地址信息");
    }
}