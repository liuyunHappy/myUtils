package com.liujunwen.util;

import java.math.BigDecimal;

/**
 * 求自然常数e：可以精确到任意多少位小数：NaturalConstant
 */
public class NaturalConstant
{
    public static void main(String[] args)
    {
        System.out.println(getNaturalConstant(2500, 2001));
    }

    /**
     * 获取自然常量：精确到小数点scale位
     * 公式：1/(0!)+1/(1!)+1/(2!)+...1/(n!)+...
     *
     * @param scale  小数精确位数
     * @param addCount 公式加法中n的取值
     * @return 自然常量的值
     */
    public static String getNaturalConstant(int scale, int addCount)
    {
        BigDecimal a = new BigDecimal(0);
        BigDecimal b = new BigDecimal(1);
        for (int i = 0; i < addCount + 1; i++)
        {
            a = b.divide(factorial(i), scale, BigDecimal.ROUND_HALF_UP).add(a);
        }
        return a.toString();
    }

    /**
     * 获取自然常量：精确到小数点scale位
     * 公式：1/(0!)+1/(1!)+1/(2!)+...1/(n!)+...
     *
     * @param scale  小数精确位数
     * @return 自然常量的值
     */
    public static String getNaturalConstant(int scale)
    {
        return getNaturalConstant(scale, scale);
    }

    /**
     * 整数n的阶乘
     *
     * @param n 入参整数
     * @return 阶乘的结果
     */
    public static BigDecimal factorial(int n)
    {
        if (n == 0 || n == 1)
        {
            return new BigDecimal(1);
        }
        else
        {
            return factorial(n - 1).multiply(new BigDecimal(n));
        }
    }
}