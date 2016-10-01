package org.chembar.glockchem.core;

/** AdvNum - ���������˫��������
 * @author DuckSoft
 * @version 0.1
 */
public class AdvNum {
	// === �ڲ����� ===
	/** AdvNum���ڲ���ֵ��
	 * @see #numInner
	 * @see #numMin
	 * @see #numMax
	 * */
	double numInner;
	/** AdvNum�����ֵ��
	 * @see #numInner
	 * @see #numMin
	 * @see #numMax
	 * */
	double numMax;
	/** AdvNum����Сֵ��
	 * @see #numInner
	 * @see #numMin
	 * @see #numMax
	 * */
	double numMin;
	
	// === ����ת�� ===
	/** ��AdvNum����תΪString
	 * @return ����String
	 */
	public String toString() {
		return "{" + String.valueOf(this.numInner) + 
				"; " + String.valueOf(this.numMin) + 
				", " + String.valueOf(this.numMax) + "}";
	}
	/** ��AdvNum����תΪdouble
	 * @return AdvNum������ڲ���ֵ{@link #numInner}
	 */
	public double toDouble() {
		return this.numInner;
	}
	// === ���캯�� ===
	/** ���캯��
	 * <p>�ù��캯�����Թ���һ���յ�AdvNum��</p>
	 * @see #AdvNum()
	 * @see #AdvNum(double)
	 * @see #AdvNum(double, double)
	 * @see #AdvNum(double, double, double)	 */ 
	public AdvNum() {
		super();
	}
	/** ���캯��
	 * <p>�ù��캯�����Թ���ĳһ˫����ֵ��AdvNum��</p>
	 * @param numIn ����д������AdvNum�е�˫����ֵ
	 * @see #AdvNum()
	 * @see #AdvNum(double)
	 * @see #AdvNum(double, double)
	 * @see #AdvNum(double, double, double)	 */ 
	public AdvNum(double numIn) {
		this.numInner = this.numMax = this.numMin = numIn;
	}
	/** ���캯��
	 * <p>�ù��캯�����Թ�����ĳһ˫������Ϊ���Ĳ�������AdvNum��</p>
	 * @param numCenter ����д���������ֵ
	 * @param numError ��<b>�������ֵ</b>��Ϊ������ֵָ���������ֵ
	 * @see #AdvNum()
	 * @see #AdvNum(double)
	 * @see #AdvNum(double, double)
	 * @see #AdvNum(double, double, double)	 */ 
	public AdvNum(double numCenter, double numError) {
		// ���ֵ����Ϊ�Ǹ���
		assert(numError >= 0);
		
		this.numInner = numCenter;
		this.numMin = numCenter - numError;
		this.numMax = numCenter + numError;
	}
	/** ���캯��
	 * <p>�ù��캯�����԰��û����뷨����AdvNum��</p>
	 * <p><b>ע�����ֵ���Ҫ������Сֵ��</b></p>
	 * @param numCenter ����д���������ֵ
	 * @param numInMin Ϊ������ֵָ������Сֵ
	 * @param numInMax Ϊ������ֵָ�������ֵ
	 * @see #AdvNum()
	 * @see #AdvNum(double)
	 * @see #AdvNum(double, double)
	 * @see #AdvNum(double, double, double)	 */ 
	public AdvNum(double numCenter, double numInMin, double numInMax) {
		// ������ֵ������Сֵ
		assert(numInMin <= numInMax);
		
		this.numInner = numCenter;
		this.numMin = numInMin;
		this.numMax = numInMax;
	}
	
	// === �������㺯�� ===
	public AdvNum add(AdvNum numIn) {
		double numInner = this.numInner + numIn.numInner;
		double numMin = this.numMin + numIn.numMin;
		double numMax = this.numMax + numIn.numMax;
		
		return new AdvNum(numInner, numMin, numMax);
	}
	public AdvNum add(double numIn) {
		return add(new AdvNum(numIn));
	}
	public AdvNum subtract(AdvNum numIn) {
		double numInner = this.numInner - numIn.numInner;
		double numMin = this.numMin - numIn.numMax;
		double numMax = this.numMax - numIn.numMin;
		
		return new AdvNum(numInner, numMin, numMax);
	}
	public AdvNum subtract(double numIn) {
		return subtract(new AdvNum(numIn));
	}
	public AdvNum multiply(AdvNum numIn) {
		double numInner = this.numInner * numIn.numInner;
		double numMin = this.numMin * numIn.numMin;
		double numMax = this.numMax * numIn.numMax;
		
		return new AdvNum(numInner, numMin, numMax);
	}
	public AdvNum multiply(double numIn) {
		return multiply(new AdvNum(numIn));
	}
	public AdvNum divide(AdvNum numIn) {
		double numInner = this.numInner / numIn.numInner;
		double numMin = this.numMin / numIn.numMax;
		double numMax = this.numMax / numIn.numMin;
		
		return new AdvNum(numInner, numMin, numMax);
	}
	public AdvNum divide(double numIn) {
		return divide(new AdvNum(numIn));
	}

	// === �������� ===
	public AdvNum set(double numToSet) {
		this.numInner = this.numMin = this.numMax = numToSet;
		return this;
	}
	
	/** ��ȡAdvNum�����Ļ���
	 * <p>ע��÷�������ȡ���Ļ���������ʹ��AdvNum���Ļ���<br>
	 * ��Ҫ��ĳ��AdvNum���Ļ�����μ�{@link #Centerize()}������</p>
	 * @see #Centerize()
	 * @return ���Ļ����������������ֵ����Сֵ��ƽ����
	 */
	public double getCenterizedNumber() {
		return (this.numMin + this.numMax) / 2;
	}
	
	/** ��AdvNum���Ļ�
	 * <p>����������AdvNum���Ļ�����ʹ���ڲ����ݱ�Ϊ��AdvNum���ֵ����Сֵ��ƽ������<br>
	 * �������ȡĳAdvNum�����Ļ�������μ�{@link #getCenterizedNumber()}������</p>
	 * @see #getCenterizedNumber()
	 * @return ����AdvNum
	 */
	public AdvNum Centerize() {
		this.numInner = this.getCenterizedNumber();
		return this;
	}
}
