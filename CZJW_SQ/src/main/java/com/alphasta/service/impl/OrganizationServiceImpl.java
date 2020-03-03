package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.model.Organization;
import com.alphasta.service.OrganizationService;

@Service("organization")
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Organization> findOrganizationByPid(Long pid) {
        return organizationMapper.findOrganizationByPid(pid);
    }

    @Override
    public List<Organization> findTreeGrid() {
        return organizationMapper.findOrganizationAll();
    }

    @Override
    public void addOrganization(Organization organization) {
        organizationMapper.insert(organization);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return organizationMapper.findOrganizationById(id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationMapper.updateOrganization(organization);
    }

    @Override
    public void deleteOrganizationById(Long id) {
        organizationMapper.deleteOrganizationById(id);
    }

}
