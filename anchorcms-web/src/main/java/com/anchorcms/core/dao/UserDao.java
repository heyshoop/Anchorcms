package com.anchorcms.core.dao;

import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.core.model.CmsUser;

import java.util.List;


/**
 * 用户DAO接口
 */
public interface UserDao {
	public Pagination getPage(String username, String email, Integer siteId,
							  Integer groupId, Boolean disabled, Boolean admin, Integer rank,
							  String realName, Integer roleId,
							  Boolean allChannel,
							  int pageNo, int pageSize);
	
	public List<CmsUser> getList(String username, String email, Integer siteId,
								 Integer groupId, Boolean disabled, Boolean admin, Integer rank);

	public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel,
									  Boolean disabled, Integer rank);
	
	public Pagination getAdminsByRoleId(Integer roleId, int pageNo, int pageSize);

	public CmsUser findById(Integer id);

	public CmsUser findByUsername(String username);

	public int countByUsername(String username);
	
	public int countMemberByUsername(String username);

	public int countByEmail(String email);

	public CmsUser save(CmsUser bean);

	public CmsUser updateByUpdater(Updater<CmsUser> updater);

	public CmsUser deleteById(Integer id);
}