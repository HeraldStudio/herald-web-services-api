package cn.edu.seu.herald.ws.api;

/**
 * 可配置的服务接口
 */
public interface ConfigurableService {

    /**
     * 设置服务超时
     * @param timeout 超时时间（毫秒）
     */
    public void setConnectionTimeout(int timeout);
}
