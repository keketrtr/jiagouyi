package com.sishuok.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.danga.MemCached.MemCachedClient;

public class MySessionDAO extends AbstractSessionDAO {
	private static final int EXPIRE_TIME = 60;
	@Resource
	private MemCachedClient mcc;
	
	@Override
	public void delete(Session session) {
		mcc.delete(session.getId().toString());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO 暂不实现
		return new ArrayList<Session>();
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		mcc.set(session.getId().toString(), session, new Date(EXPIRE_TIME));
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sid = this.generateSessionId(session);
		assignSessionId(session, sid);
		mcc.add(sid.toString(), session, new Date(EXPIRE_TIME));
		return sid;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session s = (Session) mcc.get(sessionId.toString());
		//计算过期时间，是从最后一次使用开始
		mcc.set(sessionId.toString(), s, new Date(EXPIRE_TIME));
		return s;
	}

}
