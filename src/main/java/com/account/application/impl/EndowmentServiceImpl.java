package com.account.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.core.DataStructureTool;
import com.account.core.StringTool;
import com.account.infrastructure.mapper.EndowmentMapper;
import com.account.domain.model.Endowment;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;
import com.account.application.IEndowmentService;

/**
 * Created by Summer.Xia on 10/8/2015.
 */
@Service
public class EndowmentServiceImpl implements IEndowmentService{
    @Autowired
    private EndowmentMapper endowmentMapper;

    public void addEndowment(Endowment endowment) throws AppServiceException {
        try {
            endowmentMapper.addEndowment(endowment);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
    }

    public void updateEndowment(Endowment endowment) throws AppServiceException {
        try {
            endowmentMapper.updateEndowment(endowment);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
    }

    public void deleteEndowment(String id) throws AppServiceException {
        try {
            endowmentMapper.deleteEndowment(id);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
    }

    public int countEndowments(Endowment endowment) throws AppServiceException {
        int result = 0;
        try {
            result = endowmentMapper.countEndowments(endowment);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }

    public List<Endowment> getEndowments(Endowment endowment, Page page) throws AppServiceException {
        List<Endowment> result = null;
        try {
            result = endowmentMapper.getEndowments(endowment,page);
            if(DataStructureTool.isNotEmpty(result)){
                for (Endowment item : result) {
                    String time = item.getTime();
                    item.setYear(time);
                    if(!StringTool.isNullOrEmpty(time)){
                        String[] args = time.split("-");
                        if(args.length>=2){
                            item.setYear(args[0]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }
}
