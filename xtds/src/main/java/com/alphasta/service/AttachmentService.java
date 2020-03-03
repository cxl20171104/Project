package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Attachment;

public interface AttachmentService {
	public void save(Attachment att);

	public List<Attachment> page(Attachment att);

	public void delete(String ids);

	public List<String> getAppIndexImg(Map<String, Object> map, String bath);
	public void deleteattbyOwner(String owner);
}
