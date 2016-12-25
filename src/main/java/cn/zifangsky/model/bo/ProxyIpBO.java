package cn.zifangsky.model.bo;

import java.io.Serializable;

import cn.zifangsky.model.ProxyIp;

public class ProxyIpBO extends ProxyIp implements Serializable{
	private static final long serialVersionUID = -5395611529404702931L;
	/**
	 * 标志检测代理可用性时的状态，新增或者更新
	 */
	private CheckIPType checkType;

	public CheckIPType getCheckType() {
		return checkType;
	}

	public void setCheckType(CheckIPType checkType) {
		this.checkType = checkType;
	}

	public enum CheckIPType {
		ADD(1, "新增代理IP"), UPDATE(2, "检测数据库中IP");
		private Integer code;
		private String description;

		private CheckIPType(Integer code, String description) {
			this.code = code;
			this.description = description;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}
}
