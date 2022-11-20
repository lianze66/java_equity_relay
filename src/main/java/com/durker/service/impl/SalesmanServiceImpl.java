package com.durker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.Salesman;
import com.durker.mapper.SalesmanMapper;
import com.durker.service.ISalesmanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SalesmanServiceImpl extends ServiceImpl<SalesmanMapper, Salesman> implements ISalesmanService {

    @Value("${file.server}")
    private String fileServer;

    @Override
    public List<Salesman> list() {
        List<Salesman> list = super.list();
        for (Salesman salesman : list) {
            salesman.setQrCode(fileServer + salesman.getQrCode());
        }
        return list;
    }

    @Override
    public Salesman getById(Serializable id) {
        Salesman salesman = super.getById(id);
        salesman.setQrCode(fileServer + salesman.getQrCode());
        return salesman;
    }
}
