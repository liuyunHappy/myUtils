package com.liujunwen.loan;
/**
 * �ȶϢ���:
	ÿ���¹���=�������������ʡ�(1��������)�޻����������¡�(1��������)�޻�������-1��
	ÿ��Ӧ����Ϣ=�����������ʡ���(1+������)^��������-(1+������)^(���������-1)���¡�(1+������)^��������-1��
	ÿ��Ӧ������=�����������ʡ�(1+������)^(���������-1)�¡�(1+������)^��������-1��
	����Ϣ=����������ÿ���¹���-�����
 * @author jwliu
 *
 */
public class LoanUtil {
	
	public static void main(String[] args) {
		//����
		double capital = 1040000;
		//������
		double rateOfMonth = 4.9*1.15/100/12;
		//����ʱ�䣨�£�
		int loanMonth = 30*12;
		//��ǰ�����ʱ��
		int advanceMonth = 12;
		//��ǰ��������
		double advanceCapital = 100000;
		
		double everyMonthCapitalWithInterest = everyMonthCapitalWithInterest(capital, rateOfMonth, loanMonth);
		System.out.println("104ÿ���¹���"+everyMonthCapitalWithInterest);
		
		double totalInterest = totalInterest(capital, rateOfMonth, loanMonth);		
		System.out.println("104����Ϣ��"+totalInterest);
		
		//����ǰ�ѻ���Ϣ��
		double multMonthIntetrestTotal = multMonthIntetrestTotal(capital, rateOfMonth, loanMonth, advanceMonth);
		System.out.println("����ǰ�ѻ���Ϣ�ͣ�"+multMonthIntetrestTotal);
		
		//����ǰ�ѻ������
		double multMonthCapitalTotal = multMonthCapitalTotal(capital, rateOfMonth, loanMonth, advanceMonth);
		System.out.println("����ǰ�ѻ�����ͣ�"+multMonthCapitalTotal);
		
		//��ǰ�����ı���
		double capitalForAdvance = capital-multMonthCapitalTotal-advanceCapital;
		System.out.println("��ǰ�����ı���:"+capitalForAdvance);
		
		//��ǰ�������¹���
		double everyMonthCapitalWithInterestForAdvance = everyMonthCapitalWithInterest(capitalForAdvance, rateOfMonth, loanMonth-advanceMonth);
		System.out.println("��ǰ�������¹���:"+everyMonthCapitalWithInterestForAdvance);
		
		//��ǰ�����δ������Ϣ�ܺ�
		double totalInterestForAdvance = totalInterest(capitalForAdvance, rateOfMonth, loanMonth-advanceMonth);		
		System.out.println("��ǰ�����δ������Ϣ�ܺͣ�"+totalInterestForAdvance);
		
		System.out.println("��ǰ������ٵ���Ϣ:"+(totalInterest-totalInterestForAdvance-multMonthIntetrestTotal));
		
		double bigFirstPayTotalInterest = totalInterest(capital-advanceCapital, rateOfMonth, loanMonth);
		System.out.println("�׸��ึһЩ����Ϣ�ͣ�"+bigFirstPayTotalInterest);
		
		System.out.println("��ǰ����ȶ��׸������˶�����Ϣ��"+(totalInterestForAdvance+multMonthIntetrestTotal-bigFirstPayTotalInterest));
	}
	
	/**
	 * ÿ���¹���=�������������ʡ�(1��������)�޻����������¡�(1��������)�޻�������-1��
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
	 * ÿ��Ӧ����Ϣ=�����������ʡ���(1+������)^��������-(1+������)^(���������-1)���¡�(1+������)^��������-1��
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
	 * ÿ��Ӧ������=�����������ʡ�(1+������)^(���������-1)�¡�(1+������)^��������-1��
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
	 * ����Ϣ=����������ÿ���¹���-�����
	 */
	public static double totalInterest(double capital,double rateOfMonth,int loanMonth)
	{
		return everyMonthCapitalWithInterest(capital, rateOfMonth, loanMonth)*loanMonth-capital;
	}
	
	/**
	 * ��ǰ����ǰ�����µ���Ϣ��
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
	 * ��ǰ����ǰ�����µı����
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
