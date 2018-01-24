package com.liujunwen.loan;
/**
 * 等额本息还款法:
	每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
	每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
	每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
	总利息=还款月数×每月月供额-贷款本金
 * @author jwliu
 *
 */
public class LoanUtil {
	
	public static void main(String[] args) {
		//本金
		double capital = 1040000;
		//月利率
		double rateOfMonth = 4.9*1.15/100/12;
		//贷款时间（月）
		int loanMonth = 30*12;
		//提前还款的时间
		int advanceMonth = 12;
		//提前还款数量
		double advanceCapital = 100000;
		
		double everyMonthCapitalWithInterest = everyMonthCapitalWithInterest(capital, rateOfMonth, loanMonth);
		System.out.println("104每月月供："+everyMonthCapitalWithInterest);
		
		double totalInterest = totalInterest(capital, rateOfMonth, loanMonth);		
		System.out.println("104总利息："+totalInterest);
		
		//还款前已还利息和
		double multMonthIntetrestTotal = multMonthIntetrestTotal(capital, rateOfMonth, loanMonth, advanceMonth);
		System.out.println("还款前已还利息和："+multMonthIntetrestTotal);
		
		//还款前已还本金和
		double multMonthCapitalTotal = multMonthCapitalTotal(capital, rateOfMonth, loanMonth, advanceMonth);
		System.out.println("还款前已还本金和："+multMonthCapitalTotal);
		
		//提前还款后的本金
		double capitalForAdvance = capital-multMonthCapitalTotal-advanceCapital;
		System.out.println("提前还款后的本金:"+capitalForAdvance);
		
		//提前还款后的月供额
		double everyMonthCapitalWithInterestForAdvance = everyMonthCapitalWithInterest(capitalForAdvance, rateOfMonth, loanMonth-advanceMonth);
		System.out.println("提前还款后的月供额:"+everyMonthCapitalWithInterestForAdvance);
		
		//提前还款后未还的利息总和
		double totalInterestForAdvance = totalInterest(capitalForAdvance, rateOfMonth, loanMonth-advanceMonth);		
		System.out.println("提前还款后未还的利息总和："+totalInterestForAdvance);
		
		System.out.println("提前还款减少的利息:"+(totalInterest-totalInterestForAdvance-multMonthIntetrestTotal));
		
		double bigFirstPayTotalInterest = totalInterest(capital-advanceCapital, rateOfMonth, loanMonth);
		System.out.println("首付多付一些，利息和："+bigFirstPayTotalInterest);
		
		System.out.println("提前还款比多首付，亏了多少利息："+(totalInterestForAdvance+multMonthIntetrestTotal-bigFirstPayTotalInterest));
	}
	
	/**
	 * 每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
	 * @param capital
	 * @param rateOfMonth
	 * @param loanMonth
	 * @return
	 */
	public static double everyMonthCapitalWithInterest(double capital,double rateOfMonth,int loanMonth)
	{
		return capital*rateOfMonth*Math.pow(1+rateOfMonth, loanMonth)/(Math.pow(1+rateOfMonth, loanMonth)-1);
	}
	
	/**
	 * 每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
	 * @param capital
	 * @param rateOfMonth
	 * @param loanMonth
	 * @return
	 */
	public static double someMonthInterest(double capital,double rateOfMonth,int loanMonth,int advanceMonth)
	{
		return capital*rateOfMonth*(Math.pow(1+rateOfMonth, loanMonth)-Math.pow(1+rateOfMonth, advanceMonth-1))/(Math.pow(1+rateOfMonth, loanMonth)-1);
	}
	
	/**
	 * 每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
	 * @param capital
	 * @param rateOfMonth
	 * @param loanMonth
	 * @return
	 */
	public static double someMonthCapital(double capital,double rateOfMonth,int loanMonth,int advanceMonth)
	{
		return capital*rateOfMonth*Math.pow(1+rateOfMonth, advanceMonth-1)/(Math.pow(1+rateOfMonth, loanMonth)-1);
	}
	
	/**
	 * 总利息=还款月数×每月月供额-贷款本金
	 */
	public static double totalInterest(double capital,double rateOfMonth,int loanMonth)
	{
		return everyMonthCapitalWithInterest(capital, rateOfMonth, loanMonth)*loanMonth-capital;
	}
	
	/**
	 * 提前还贷前几个月的利息和
	 * @param capital
	 * @param rateOfMonth
	 * @param loanMonth
	 * @param advanceMonth
	 * @return
	 */
	public static double multMonthIntetrestTotal(double capital,double rateOfMonth,int loanMonth,int advanceMonth)
	{
		double multMonthIntetrestTotal = 0;
		for(int i = 1;i<=advanceMonth;i++)
		{
			multMonthIntetrestTotal += someMonthInterest(capital, rateOfMonth, loanMonth, i);
		}
		return multMonthIntetrestTotal;
	}
	
	/**
	 * 提前还贷前几个月的本金和
	 * @param capital
	 * @param rateOfMonth
	 * @param loanMonth
	 * @param advanceMonth
	 * @return
	 */
	public static double multMonthCapitalTotal(double capital,double rateOfMonth,int loanMonth,int advanceMonth)
	{
		double multMonthCapitalTotal = 0;
		for(int i = 1;i<=advanceMonth;i++)
		{
			multMonthCapitalTotal += someMonthCapital(capital, rateOfMonth, loanMonth, i);
		}
		return multMonthCapitalTotal;
	}
}
