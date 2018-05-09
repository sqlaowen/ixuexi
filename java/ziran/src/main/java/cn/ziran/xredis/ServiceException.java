package cn.ziran.xredis;

public class ServiceException extends RuntimeException {
  private static final long serialVersionUID = -3270242593662831743L;

  public ServiceException(String message) {
    super(message);
  }

  /**
   * 重写fillInStackTrace，不在返回堆栈异常
   * 
   * @return Throwable
   */
  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }

  /**
   * 重写message，返回ip和错误信息
   * 
   * @return ip和错误信息
   */
  @Override
  public String getMessage() {
    return String.format("{Message:\"%s\"}", super.getMessage());
  }

  /**
   * 重写tostring，返回ip和错误信息
   * 
   * @return ip和错误信息
   */
  @Override
  public String toString() {
    return getMessage();
  }
}
