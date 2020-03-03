package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Attachment;
import com.github.pagehelper.PageInfo;

public interface AttachmentMapper {
	public void save(Attachment att);

	public List<Attachment> page(Attachment att);

	public void delete(String id);

	public List<String> getArtUrls(String ownerId);

	public List<String> getAppIndexImg(Map<String, Object> map);
	public void deleteattbyOwner(String owner);
}
