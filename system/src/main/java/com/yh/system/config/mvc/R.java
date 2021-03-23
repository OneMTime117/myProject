package com.yh.system.config.mvc;

import lombok.Data;

@Data
public class R {

	private Object data;
	private Integer code;
	private String msg="";

	public static R ok(){
		R r = new R();
		r.code=200;
		return r;
	}

	public static R ok(Object data){
		R r = new R();
		r.code=200;
		r.data=data;
		return r;
	}

	public static R error(){
		R r = new R();
		r.code=500;
		return r;
	}

	public static R error(String msg){
		R r = new R();
		r.code=500;
		r.msg=msg;
		return r;
	}
}
