package com.alphasta.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.AttachmentMapper;
import com.alphasta.model.Attachment;
import com.alphasta.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public void save(Attachment att) {
		attachmentMapper.save(att);

	}

	public List<Attachment> page(Attachment pageInfo) {
		return attachmentMapper.page(pageInfo);
	}

	@Override
	public void delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			// TODO 删除对应的物理文件
			attachmentMapper.delete(id);
		}

	}

	@Override
	public List<String> getAppIndexImg(Map<String, Object> map, String bath) {

		List<String> list = attachmentMapper.getAppIndexImg(map);
		if (bath != null && list.size() > 0 && list.get(0) != null) {
			List<String> listurl = new ArrayList<String>();
			for (String str : list) {
				System.out.println(str);
				if (str == null) {
					break;
				}
				str = bath + str;
				listurl.add(str);
			}
			return listurl;
		}

		return list;
	}

	@Override
	public void deleteattbyOwner(String owner) {
		attachmentMapper.deleteattbyOwner(owner);
		
	}
}
