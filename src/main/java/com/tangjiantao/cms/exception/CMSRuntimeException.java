package com.tangjiantao.cms.exception;

public class CMSRuntimeException extends Exception{

	
	    /**
	    * @Fields serialVersionUID : tt
	    */
	    
	private static final long serialVersionUID = -7807186100527904932L;

		//提供无参构造
		public CMSRuntimeException() {
			
		}
		
		//提供一个有参数的构造方法   参数:可以传入异常信息
		public CMSRuntimeException(String message) {
			super(message);//调用Exception的有参数的构造方法
		}
}
