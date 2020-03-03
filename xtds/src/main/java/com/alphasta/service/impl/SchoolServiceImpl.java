package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.result.Tree;
import com.alphasta.mapper.SchoolMapper;
import com.alphasta.model.Organization;
import com.alphasta.model.SchoolModel;
import com.alphasta.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolMapper schoolMapper;

	@Override
	public List<Tree> findTree() {
		List<Tree> trees = new ArrayList<Tree>();

		List<Organization> organizationFather = schoolMapper
				.findOrganizationAllByPidNull();

		if (organizationFather != null) {
			for (Organization organizationOne : organizationFather) {
				Tree treeOne = new Tree();

				treeOne.setId(organizationOne.getId());
				treeOne.setText(organizationOne.getName());
				treeOne.setIconCls(organizationOne.getIcon());

				List<Organization> organizationSon = schoolMapper
						.findOrganizationAllByPid(organizationOne.getId());

				if (organizationSon != null) {
					List<Tree> tree = new ArrayList<Tree>();
					for (Organization organizationTwo : organizationSon) {
						Tree treeTwo = new Tree();
						treeTwo.setId(organizationTwo.getId());
						treeTwo.setText(organizationTwo.getName());
						treeTwo.setIconCls(organizationTwo.getIcon());
						tree.add(treeTwo);
					}
					treeOne.setChildren(tree);
				} else {
					treeOne.setState("closed");
				}
				trees.add(treeOne);
			}
		}
		return trees;
	}

	@Override
	public List<SchoolModel> findSchoolGrid() {
		return schoolMapper.findSchoolGrid();
	}

	@Override
	public void addSchool(SchoolModel schoolModel) {
		schoolMapper.insert(schoolModel);
	}

	@Override
	public Organization findOrganizationById(Long id) {
		return schoolMapper.findOrganizationById(id);
	}

	@Override
	public void updateOrganization(Organization organization) {
		schoolMapper.updateOrganization(organization);
	}

	@Override
	public void deleteOrganizationById(Long id) {
		schoolMapper.deleteOrganizationById(id);
	}

}
