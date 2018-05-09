package api.pay.utils;

/**
 * 服务抛出业务异常 Created by suntao on 15/8/17.
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 8725750561764630265L;

	public ServiceException(String message) {
		super(message);
	}

	/**
	 * 重写fillInStackTrace，不在返回堆栈异常
	 */
	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	/**
	 * 重写message
	 * 
	 * @return String ip和错误信息
	 */
	@Override
	public String getMessage() {
		return String.format("{ServiceIp:\"%s\",Message:\"%s\"}", IPUtil.getLocalAddress().getHostAddress(), super.getMessage());
	}

	/**
	 * 重写toString
	 * 
	 * @return String ip和错误信息
	 */
	@Override
	public String toString() {
		return getMessage();
	}
}
