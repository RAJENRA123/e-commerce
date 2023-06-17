package com.bikkadiT.demo.helper;

import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.UserDto;
import com.bikkadiT.demo.entyties.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public static<U,V> PageableResponse<V> getPageableReaponse(Page<U> page,Class<V> type){
        List<U> entity=page.getContent();
        List<V>dtoList=entity.stream().map(object-> new ModelMapper().map(object,type)).collect(Collectors.toList());
        PageableResponse<V> response=new PageableResponse<>();
        response.setContent(dtoList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotolElements(page.getTotalElements());
        response.setTotolPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        PageableResponse<UserDto> reaponse = Helper.getPageableReaponse(page, UserDto.class);
        return response;
    }
}
