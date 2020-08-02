package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 项目名称：cloud2020
 * 类 名 称：MySelfLoadBalance
 * 类 描 述：TODO
 * 创建时间：2020/5/10 12:02
 * 创 建 人：shuchang
 */
@Component
public class MySelfLoadBalancer implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //负载均衡算法：rest接口第几次请求 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计算从1开始。
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement()%serviceInstances.size();
         return serviceInstances.get(index);
    }

    public final int getAndIncrement(){
        int current,next;
        do{
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("************第几次访问，次数next:"+next);
        return next;
    }
}
