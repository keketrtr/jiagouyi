package com.sishuok.architecture1.goodsmgr.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;
//这个地方不用Repository，是因为ApplicationContext.xml文件中配置的MapperScannerConfigurer会扫描Repository标记，试图生成mybatis的Dao实现类。
@Service
public class GoodsMongoDaoImpl implements GoodsMongoDao {
	@Resource
	private MongoTemplate mt = null;
	
	@Override
	public void create(GoodsModel m) {
		mt.insert(m);
	}

	@Override
	public void update(GoodsModel m) {
		mt.save(m);
	}

	@Override
	public void delete(int uuid) {
		mt.remove(getByUuid(uuid));
	}

	@Override
	public GoodsModel getByUuid(int uuid) {
		return mt.findById(uuid, GoodsModel.class);
	}

	@Override
	public List<GoodsModel> getByConditionPage(GoodsQueryModel qm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Serializable> getIdsByConditionPage(GoodsQueryModel qm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsModel> getByIds(List<Serializable> ids) {
		return mt.find(Query.query(Criteria.where("uuid").in(ids)), GoodsModel.class);
	}

	@Override
	public Page<GoodsModel> getByCondition(GoodsQueryModel qm) {
		Criteria criteria = new Criteria();
		List<Criteria> cList = new ArrayList<Criteria>();
		if(qm.getUuid() != null && qm.getUuid() > 0){
			cList.add(Criteria.where("uuid").is(qm.getUuid()));
		}
		if(StringUtils.isNotBlank(qm.getName())){
			cList.add(Criteria.where("name").regex(qm.getName()));
		}
		if(StringUtils.isNotBlank(qm.getDescription())){
			cList.add(Criteria.where("description").regex(qm.getDescription()));
		}
		if(cList.size()>0){
			criteria.andOperator(cList.toArray(new Criteria[cList.size()]));
		}
		
		int totalCount = (int) mt.count(Query.query(criteria), GoodsModel.class);
		qm.getPage().setTotalCount(totalCount);
		
		List<GoodsModel> list = mt.find(Query.query(criteria).skip(qm.getPage().getStartIndex()).limit(qm.getPage().getNumPerPage()), GoodsModel.class);
		qm.getPage().setResult(list);
		
		return qm.getPage();
	}

}
