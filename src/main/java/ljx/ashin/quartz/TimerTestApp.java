package ljx.ashin.quartz;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器Timer类的使用
 * Created by AshinLiang on 2017/7/19.
 */
public class TimerTestApp {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        //基本的定时器timer类的使用
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ "定时器开始执行啦");

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+ "子定时器开始执行啦");
                    }
                },3*1000);
            }
        },2*1000);

        while (true){
            Thread.sleep(4*1000);
            System.out.println(Thread.currentThread().getName()+ new Date().getSeconds());
        }*/


        //回调函数调用timer
        class MyTimerTask extends TimerTask{

            @Override
            public void run() {
                System.out.println("MyTimerTask"+Thread.currentThread().getName()+ "定时器开始执行啦");
                new Timer().schedule(new MyTimerTask() {
                    @Override
                    public void run() {
                        count = (count+1)%2;
                        System.out.println(Thread.currentThread().getName()+ "定时器开始执行啦");

                        new Timer().schedule(new MyTimerTask()
                                ,3*1000+count*2000);
                    }
                },2*1000);
            }
        }

        new Timer().schedule(new MyTimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ "定时器开始执行啦");

                new Timer().schedule(new MyTimerTask()
                        ,3*1000);
            }
        },2*1000);

        while (true){
            Thread.sleep(4*1000);
            System.out.println(Thread.currentThread().getName()+ new Date().getSeconds());
        }
    }
}
