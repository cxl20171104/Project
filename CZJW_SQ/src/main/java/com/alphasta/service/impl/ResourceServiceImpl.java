package com.alphasta.service.impl;

import com.alphasta.commons.result.Tree;
import com.alphasta.commons.utils.Config;
import com.alphasta.mapper.ResourceMapper;
import com.alphasta.mapper.RoleMapper;
import com.alphasta.mapper.UserRoleMapper;
import com.alphasta.model.Resource;
import com.alphasta.model.User;
import com.alphasta.service.ResourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResourceServiceImpl implements ResourceService {

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Tree> findTree(User user) {
        List<Tree> trees = new ArrayList<Tree>();
        // 超级管理
        if (user.getLoginname().equals("admin")) {
            List<Resource> resourceFather = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
            if (resourceFather == null) {
                return null;
            }

            for (Resource resourceOne : resourceFather) {
                Tree treeOne = new Tree();

                treeOne.setId(resourceOne.getId());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

                if (resourceSon != null) {
                    List<Tree> tree = new ArrayList<Tree>();
                    for (Resource resourceTwo : resourceSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
            return trees;
        }

        // 普通用户
        Set<Resource> resourceIdList = new HashSet<Resource>();
        List<Long> roleIdList = userRoleMapper.findRoleIdListByUserId(user.getId());
        for (Long i : roleIdList) {
            List<Resource> resourceIdLists = roleMapper.findResourceIdListByRoleIdAndType(i);
            for (Resource resource: resourceIdLists) {
                resourceIdList.add(resource);
            }
        }
        for (Resource resource : resourceIdList) {
                if (resource != null && resource.getPid() == null) {
                    Tree treeOne = new Tree();
                    treeOne.setId(resource.getId());
                    treeOne.setText(resource.getName());
                    treeOne.setIconCls(resource.getIcon());
                    treeOne.setAttributes(resource.getUrl());
                    List<Tree> tree = new ArrayList<Tree>();
                    for (Resource resourceTwo : resourceIdList) {
                        if (resourceTwo.getPid() != null && resource.getId().longValue() == resourceTwo.getPid().longValue()) {
                            Tree treeTwo = new Tree();
                            treeTwo.setId(resourceTwo.getId());
                            treeTwo.setText(resourceTwo.getName());
                            treeTwo.setIconCls(resourceTwo.getIcon());
                            treeTwo.setAttributes(resourceTwo.getUrl());
                            tree.add(treeTwo);
                        }
                    }
                    treeOne.setChildren(tree);
                    trees.add(treeOne);
                }
        }
        return trees;
    }

    @Override
    public List<Resource> findResourceAll() {
        return resourceMapper.findResourceAll();
    }

    @Override
    public void addResource(Resource resource) {
        resourceMapper.insert(resource);
    }

    @Override
    public List<Tree> findAllTree() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有的一级树
        List<Resource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }
        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            // 查询所有一级树下的菜单
            List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());
            System.out.println(resourceSon);
            if (resourceSon != null) {
                List<Tree> tree = new ArrayList<Tree>();
                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();
                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());
                    tree.add(treeTwo);
                }
                treeOne.setChildren(tree);
            } else {
                treeOne.setState("closed");
            }
            trees.add(treeOne);
        }
        return trees;
    }
    /**
     * 三级树
     */
    @Override
    public List<Tree> findAllTrees() {
        List<Tree> treeOneList = new ArrayList<Tree>();

        // 查询所有的一级树
        List<Resource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }
        
        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();
            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());
            List<Tree> tree2List = new ArrayList<Tree>();
            for(Resource r2: resourceSon) {
            	 Tree t2=new Tree();
            	 t2.setId(r2.getId());
                 t2.setText(r2.getName());
                 t2.setIconCls(r2.getIcon());
                 t2.setAttributes(r2.getUrl());
                 List<Resource> resourceThree = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_BUTTON, r2.getId());
                 List<Tree> tree3List = new ArrayList<Tree>();
                 for(Resource r3:resourceThree) {
                	 Tree t3=new Tree();
                	 t3.setId(r3.getId());
                     t3.setText(r3.getName());
                     t3.setIconCls(r3.getIcon());
                     t3.setAttributes(r3.getUrl());
                     
                     tree3List.add(t3);//////
                 }
                 List<Resource> resourceThreeM = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, r2.getId());
                 for(Resource r3:resourceThreeM) {
                	 Tree t3=new Tree();
                	 t3.setId(r3.getId());
                     t3.setText(r3.getName());
                     t3.setIconCls(r3.getIcon());
                     t3.setAttributes(r3.getUrl());
                     tree3List.add(t3);///
                 }
                 
                 t2.setChildren(tree3List);
                 
                 
                 
                tree2List.add(t2);
            }
            treeOne.setChildren(tree2List);
            
            
            
            
            
            
            
            treeOneList.add(treeOne);
        }
        return treeOneList;
    }

    @Override
    public void updateResource(Resource resource) {
        int update = resourceMapper.updateResource(resource);
        if (update != 1) {
            throw new RuntimeException("更新失败");
        }
    }

    @Override
    public Resource findResourceById(Long id) {
        return resourceMapper.findResourceById(id);
    }

    @Override
    public void deleteResourceById(Long id) {
        int delete = resourceMapper.deleteResourceById(id);
        if (delete != 1) {
            throw new RuntimeException("删除失败");
        }
    }

}
