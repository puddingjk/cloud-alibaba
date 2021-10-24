package com.wanwei.common.base.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Random;

/**
 * @author tan
 * @date 2020-04
 *
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 */
public class SnowflakeIdUtils {

    // ==============================Fields===========================================


    /**
     * 机器id所占的位数
     */
    private static final int WORK_LEN = 5;

    /**
     * 数据标识id所占的位数
     */
    private static final int DATA_LEN = 5;

    /**
     * 毫秒内序列的长度
     */
    private static final int SEQ_LEN = 12;

    /**
     * 时间部分所占长度
     */
    private static final int TIME_LEN = 41;

    /**
     * 开始时间截 (2019-01-01)
     */
    private static final long START_TIME = 1546272000000L;


    /**
     * 上次生成iD的时间戳
     */
    private static volatile long LAST_TIME_STAMP = -1L;


    /**
     * 时间部分向左移动的位数22(雪花算法总长度64,最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是 1)
     */
    private static final int TIME_LEFT_BIT = 64 - 1 - TIME_LEN;


    private static final long DATA_ID = getDataId();

    private static final long WORK_ID = getWorkId();

    /**
     * 数据中心id最大值 31
     */
    private static final int DATA_MAX_NUM = ~(-1 << DATA_LEN);

    /**
     * 数据中心id最大值 31
     */
    private static final int WORK_MAX_NUM = ~(-1 << WORK_LEN);


    /**
     * 机器随机获取数据中中心id的参数 32
     */
    private static final int DATA_RANDOM = DATA_MAX_NUM + 1;


    /**
     * 随机获取的机器id的参数
     */
    private static final int WORK_RANDOM = WORK_MAX_NUM + 1;

    /**
     * 数据中心id左移位数 17
     */
    private static final int DATA_LEFT_BIT = TIME_LEFT_BIT - DATA_LEN;

    /**
     * 机器id左移位数 12
     */
    private static final int WROK_LEFT_BIT = DATA_LEFT_BIT - WORK_LEN;

    /**
     * 上一次毫秒的序列值
     */
    private static volatile long LAST_SEQ = 0L;


    /**
     * 毫秒内序列的最大值 4095
     */
    private static final long SEQ_MAX_NUM = ~(-1 << SEQ_LEN);





    // ==============================Methods==========================================

    /**
     * 根据host name 取余，发生异常就获取0到31之间的随机数
     * @return
     */
    public static int getWorkId() {

        try {
            return getHostId(Inet4Address.getLocalHost().getHostAddress(), WORK_MAX_NUM);
        } catch (UnknownHostException e) {
            return new Random().nextInt(WORK_RANDOM);
        }

    }

    /**
     * 根据host name 取余，发生异常就获取0到31之间的随机数
     * @return
     */
    public static int getDataId() {

        try {
            return getHostId(Inet4Address.getLocalHost().getHostAddress(), DATA_MAX_NUM);
        } catch (UnknownHostException e) {
            return new Random().nextInt(DATA_RANDOM);
        }

    }

    /**
     * 根据host name 取余
     *
     * @return
     */
    private static int getHostId(String s, int max) {
        byte[] bytes = s.getBytes();
        int sums = 0;
        for (byte b : bytes) {
            sums += b;
        }
        return sums % (max + 1);
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeIdAnnotation
     */
    public static  synchronized long genId() {
        long now = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (now < LAST_TIME_STAMP) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", LAST_SEQ - now));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (now == LAST_TIME_STAMP) {
            LAST_SEQ = (LAST_SEQ + 1) & SEQ_MAX_NUM;
            //毫秒内序列溢出
            if (LAST_SEQ == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                now = tilNextMillis(LAST_TIME_STAMP);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            LAST_SEQ = 0L;
        }

        //上次生成ID的时间截
        LAST_TIME_STAMP = now;

        //移位并通过或运算拼到一起组成64位的ID
        return ((now - START_TIME) << TIME_LEFT_BIT)
                | (DATA_ID << DATA_LEFT_BIT)
                | (WORK_ID << WROK_LEFT_BIT)
                | LAST_SEQ;
    }

    /**
     * 获得String类型的雪花ID
     *
     * @return SnowflakeIdAnnotation
     */
    public static String genIdStr(){
       return String.valueOf(genId());
    }
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static  long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    //==============================Test=============================================

    /**
     * 测试
     */
    public static void main(String[] args) {
        HashSet<Long> ids = new HashSet<>(100);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            long id = genId();
            System.out.println(id);
            ids.add(id);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("共生成id["+ids.size()+"] 个,共花费时间["+(endTime-startTime)+"]");
    }
}

