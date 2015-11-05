package com.sishuok.architecture1.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.danga.MemCached.MemCachedClient;
import com.sishuok.architecture1.common.vo.BaseModel;
import com.sishuok.util.ClassInfoUtil;
import com.sishuok.util.GenericsUtils;

@Repository
public abstract class BaseDAOMemcachedImpl<M extends BaseModel<M>, QM>
		implements BaseDAO<M, QM> {

	@Resource
	private MemCachedClient memCachedClient;
	
	private BaseDAO<M, QM> mapper;
	

	public void setMapper(BaseDAO<M, QM> mapper) {
		this.mapper = mapper;
	}

	protected Class entityClass;

	public BaseDAOMemcachedImpl() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	protected final Class getEntityClass() {
		return entityClass;
	}

	private String getKey(Serializable uuid) {
		return entityClass.getSimpleName() + "_" + uuid;
	}

	@Override
	public void create(M m) {
		mapper.create(m);
	}

	@Override
	public void update(M m) {
		mapper.update(m);
		// 查询缓存里面是否有这条数据，有的话，需要更新缓存
		if (memCachedClient.get(getKey(m.getUuid())) != null) {
			memCachedClient.set(getKey(m.getUuid()), m);
		}
	}

	@Override
	public void delete(int uuid) {
		mapper.delete(uuid);
		// 查询缓存里面是否有这条数据，有的话，需要从缓存中删除
		if (memCachedClient.get(getKey(uuid)) != null) {
			memCachedClient.delete(getKey(uuid));
		}
	}

	@Override
	public M getByUuid(int uuid) {
		M m = null;
		// 1、查缓存，如果有就从缓存中取值并返回
		Object obj = memCachedClient.get(getKey(uuid));
		if (obj != null) {
			m = (M) obj;
			return m;
		}
		// 2、缓存没有，从数据库中取
		m = mapper.getByUuid(uuid);
		// 3、把这个数据设置到缓存中
		memCachedClient.set(getKey(m.getUuid()), m);
		return m;
	}

	@Override
	public List<M> getByConditionPage(QM qm) {
		// 1、根据条件查询出所有的ids
		List<Serializable> ids = mapper.getIdsByConditionPage(qm);
		// 2、在内存中找这些id对应的对象
		List<M> cacheList = new ArrayList<M>();
		List<Serializable> missIdList = new ArrayList<Serializable>();
		for (Serializable id : ids) {
			Object obj = memCachedClient.get(getKey(id));
			if (obj != null) {
				cacheList.add((M) obj);
			} else {
				missIdList.add(id);
			}
		}
		System.out.println("missIdList====" + missIdList);
		// 3、内存中找不到时，从数据库里面获取，并设置到缓存中
		List<M> dbList = null;
		if (missIdList.size() > 0) {
			dbList = mapper.getByIds(missIdList);
			cacheList.addAll(dbList);
			for (M m : dbList) {
				memCachedClient.set(getKey(m.getUuid()), m);
			}
		}

		return cacheList;
	}

	@Override
	public List<Serializable> getIdsByConditionPage(QM qm) {
		throw new UnsupportedOperationException(ClassInfoUtil.getCurrentMethodName()+" can not be used when query cache!");
	}

	@Override
	public List<M> getByIds(List<Serializable> ids) {
		throw new UnsupportedOperationException(ClassInfoUtil.getCurrentMethodName()+" can not be used when query cache!");
	}

	
}
