package com.sishuok.architecture1.common.vo;

import java.io.Serializable;

import com.sishuok.pageutil.Page;

public class BaseModel<M> implements Serializable {
	private Integer uuid;
	private Page<M> page = new Page<M>();

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Page<M> getPage() {
		return page;
	}

	public void setPage(Page<M> page) {
		this.page = page;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseModel other = (BaseModel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
