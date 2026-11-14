package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dtos.UbsDTO;
import dev.rlucassmd.meusOculosApi.entities.Ubs;
import dev.rlucassmd.meusOculosApi.repositories.UbsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbsService {
    @Autowired
    private UbsRepository ubsRepository;

    public Page<UbsDTO>findAll(Pageable pageable){
        Page<Ubs> list = ubsRepository.findAll(pageable);
        List<Ubs> list2 = ubsRepository.findAll();


        System.out.println(list2.stream().toString());

        return list.map(UbsDTO::new);
    }
}
