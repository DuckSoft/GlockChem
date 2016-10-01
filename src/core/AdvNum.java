package core;

public class AdvNum {
	// �ڲ�����
	double numInner;
	double numMax;
	double numMin;
	
	// ���캯��
	public AdvNum() {// Ĭ�Ϲ��캯��
		this.set(0);
	}
	public AdvNum(double numInit) {// ��ͨ��
		this.set(numInit);
	}
	public AdvNum(double numCenter, double numError) {// �ֶ�ָ�����
		this.numInner = numCenter;
		this.numMin = numCenter - numError;
		this.numMax = numCenter + numError;
	}
	public AdvNum(double numCenter, double numInMin, double numInMax) {// ָ�������С��
		this.numInner = numCenter;
		this.numMin = numInMin;
		this.numMax = numInMax;
	}
	
	// ��������
	public AdvNum set(double numToSet) {// ��ֵ
		numInner = numMax = numMin = numToSet;
		return this;
	}
	public AdvNum centerize() {// ���Ļ�
		this.numInner = this.getCenterizedNumber();
		return this;
	}
	public double getCenterizedNumber() {// ��ȡ���Ļ���
		return (this.numMin + this.numMax) / 2;
	}
	public double getValue() {
		return this.numInner;
	}
	
	// ���㺯��
	public AdvNum add(AdvNum numIn) {// ��
		this.numInner += numIn.numInner;
		this.numMin += numIn.numMin;
		this.numMax += numIn.numMax;
		
		return this;
	}
	public AdvNum subtract(AdvNum numIn) {// ��
		this.numInner -= numIn.numInner;
		this.numMin -= numIn.numMin;
		this.numMax -= numIn.numMax;
		
		return this;
	}
	public AdvNum multiply(AdvNum numIn) {// ��
		this.numInner *= numIn.numInner;
		this.numMin *= numIn.numMin;
		this.numMax *= numIn.numMax;
		
		return this;
	}
	public AdvNum divide(AdvNum numIn) {// ��
		this.numInner /= numIn.numInner;
		this.numMin /= numIn.numMax;
		this.numMax /= numIn.numMin;
		
		return this;
	}
	
	public AdvNum add(double numIn) {// ��
		this.numInner += numIn;
		this.numMin += numIn;
		this.numMax += numIn;
		
		return this;
	}
	public AdvNum subtract(double numIn) {// ��
		this.numInner -= numIn;
		this.numMin -= numIn;
		this.numMax -= numIn;
		
		return this;
	}
	public AdvNum multiply(double numIn) {// ��
		this.numInner *= numIn;
		this.numMin *= numIn;
		this.numMax *= numIn;
		
		return this;
	}
	public AdvNum divide(double numIn) {// ��
		this.numInner /= numIn;
		this.numMin /= numIn;
		this.numMax /= numIn;
		
		return this;
	}
}
