package core;

import core.RMDatabase.AtomNameNotFoundException;

/** ��ѧ����ʽ������
 * <p>������ѧ����ʽ�ļ򵥱�ֵ����</p>
 * @author DuckSoft
 */
public final class EquationCalculator {
	/** �ڲ��Ļ�ѧ����ʽ
	 * <p>�ɹ��캯��{@link #EquationCalculator(Equation)}Ϊ�丳ֵ��</p>
	 * @see #EquationCalculator(Equation)
	 */
	Equation equInner;
	/** ���ڼ�������������ݿ� */
	RMDatabase dbRM = new RMDatabase();
	
	/** ���캯��
	 * <p>��ʼ��һ����ѧ����ʽ������</p>
	 * @param equToCalculate ���ڼ����{@link Equation}����
	 */
	public EquationCalculator(Equation equToCalculate) {
		this.equInner = equToCalculate;
	}
	
	/** ������Ӧ���ʵ�����
	 * <p></p>
	 * @param condition �ṩ������
	 * @param refItem �������������
	 * @return ��������������
	 * @throws AtomNameNotFoundException
	 */
	public AdvNum calcMass(EquationCondition condition, Pair<Formula, Integer> refItem) throws AtomNameNotFoundException {
		return condition.getConditionMass(this.dbRM)
				.divide(this.dbRM.queryMolarMass(condition.getConditionItem().getL()))
				.divide(condition.getConditionItem().getR())
				.multiply(this.dbRM.queryMolarMass(refItem.getL()))
				.multiply(refItem.getR());
	}
	
	/** ������Ӧ���ʵ����ʵ���
	 * <p></p>
	 * @param condition �ṩ������
	 * @param refItem �������������
	 * @return �����������ʵ�����
	 * @throws AtomNameNotFoundException
	 */
	public AdvNum calcMole(EquationCondition condition, Pair<Formula, Integer> refItem) throws AtomNameNotFoundException {
		return condition.getConditionMole(this.dbRM)
				.divide(condition.getConditionItem().getR())
				.multiply(refItem.getR());
	}
	
	/** ����������������
	 * <p>����Ϊ{@link EquationCalculator}�ļ����ṩ������</p>
	 * @author DuckSoft
	 */
	public final static class EquationConditionMass implements EquationCondition {
		Pair<Formula, Integer> refItem;
		AdvNum massInner;
		
		public EquationConditionMass(Pair<Formula, Integer> refItem, AdvNum massInner) {
			this.massInner = massInner;
			this.refItem = refItem;
		}

		public AdvNum getConditionMass(RMDatabase rmDatabase) throws AtomNameNotFoundException {
			return this.massInner;
		}
		public AdvNum getConditionMole(RMDatabase rmDatabase) throws AtomNameNotFoundException {
			return this.massInner.divide(rmDatabase.queryMolarMass(this.refItem.getL()));
		}
		public Pair<Formula, Integer> getConditionItem() {
			return this.refItem;
		}
	}
	
	/** �������������ʵ�����
	 * <p>����Ϊ{@link EquationCalculator}�ļ����ṩ������</p>
	 * @author DuckSoft
	 */
	public final static class EquationConditionMole implements EquationCondition {
		Pair<Formula, Integer> refItem;
		AdvNum moleInner;
		
		public EquationConditionMole(Pair<Formula, Integer> refItem, AdvNum moleInner) {
			this.moleInner = moleInner;
			this.refItem = refItem;
		}

		public AdvNum getConditionMass(RMDatabase rmDatabase) throws AtomNameNotFoundException {
			return this.moleInner.multiply(rmDatabase.queryMolarMass(this.refItem.getL()));
		}
		public AdvNum getConditionMole(RMDatabase rmDatabase) {
			return this.moleInner;
		}
		public Pair<Formula, Integer> getConditionItem() {
			return this.refItem;
		}
	}
	
	/** ��������
	 * <p>����Ϊ{@link EquationCalculator}�ļ����ṩ������</p>
	 * @author DuckSoft
	 * @see EquationConditionMass
	 * @see EquationConditionMole
	 */
	public static interface EquationCondition {
		/** ��ȡ�����е�����*/
		public AdvNum getConditionMass(RMDatabase rmDatabase) throws AtomNameNotFoundException;
		/** ��ȡ�����е����ʵ���*/
		public AdvNum getConditionMole(RMDatabase rmDatabase) throws AtomNameNotFoundException;
		/** ��ȡ�����е���������*/
		public Pair<Formula, Integer> getConditionItem();
	}
}