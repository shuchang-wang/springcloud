import java.time.ZonedDateTime;

/**
 * 项目名称：cloud2020
 * 类 名 称：Test
 * 类 描 述：TODO
 * 创建时间：2020/5/21 20:39
 * 创 建 人：shuchang
 */
public class Test {
    public static void main(String[] args){
        ZonedDateTime zdt = ZonedDateTime.now();//默认时区
        System.out.println(zdt);//2020-05-21T20:41:19.013+08:00[Asia/Shanghai]
    }
}
