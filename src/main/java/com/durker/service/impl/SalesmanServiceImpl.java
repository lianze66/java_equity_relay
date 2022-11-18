package com.durker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.Salesman;
import com.durker.mapper.SalesmanMapper;
import com.durker.service.ISalesmanService;
import org.springframework.stereotype.Service;

@Service
public class SalesmanServiceImpl extends ServiceImpl<SalesmanMapper, Salesman> implements ISalesmanService {

}
