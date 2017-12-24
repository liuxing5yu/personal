package com.hwj.modules.codelist.model;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:15 
 *
 */
public class CodelistModel {
	private String id;
	private String codekind;
	private String codekindDesc;
	private String codename;
	private String codevalue;
	private String status;
	private String opertime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodekind() {
		return codekind;
	}

	public void setCodekind(String codekind) {
		this.codekind = codekind;
	}

	public String getCodekindDesc() {
		return codekindDesc;
	}

	public void setCodekindDesc(String codekindDesc) {
		this.codekindDesc = codekindDesc;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getCodevalue() {
		return codevalue;
	}

	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpertime() {
		return opertime;
	}

	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}

}
