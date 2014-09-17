package com.ddshell.framework.security.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.common.util.GenericCrudService;
import com.ddshell.framework.security.entity.Menu;
import com.ddshell.framework.security.repository.MenuRepository;
import com.ddshell.framework.security.shiro.PathFilter;

@Transactional(readOnly = true)
public abstract class MenuService<T extends Menu> extends
		GenericCrudService<T, Long> {

	@Autowired
	private PathFilter pathFilter;

	public List<T> findLevel1Menus() {
		MenuRepository<T> menuRep = (MenuRepository<T>) getRepository();
		return filter(menuRep.findByLevelOrderByDispOrderAsc(1));
	}

	/**
	 * 递归筛选有权限访问的菜单
	 * 
	 * @param menus
	 * @return 筛选结果
	 */
	@SuppressWarnings("unchecked")
	private List<T> filter(List<T> menus) {
		Iterator<T> itr = menus.iterator();
		while (itr.hasNext()) {
			T menu = itr.next();
			List<T> children = (List<T>) menu.getChildren();
			if (children == null || children.isEmpty()) {
				if (!pathFilter.isAccessAllowed(menu.getUrl())) {
					itr.remove();
				}
			} else {
				if (filter(children).isEmpty()) {
					itr.remove();
				}
			}
		}

		return menus;
	}

}
