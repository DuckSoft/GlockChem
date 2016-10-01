package org.chembar.glockchem.core;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** ��ѧʽ����
 * @author DuckSoft
 * @version 0.4 Stable
 */
public class Formula {

	/** ��ѧʽԭʼ�ַ���
	 * <p>������Formula���ʼ��ʱ����ı�ʾ�û�ѧʽ��String��</p>
	 * @see #Formula(String)
	 */
	String strRaw;
	/** ��ѧʽԭ���嵥
	 * <p>������Formula��ʼ��ʱ�����String�з����õ��ĸû�ѧʽ���е�����ԭ�Ӽ�����Ŀ����Ե��б�</p>
	 * @see #Formula(String)
	 */
	public Map<String,Integer> mapAtomList = new HashMap<String,Integer>();
	
	/** ��ȡ��ѧʽԭʼ�ַ���
	 * <p>��ȡ������Formula���ʼ��ʱ����ı�ʾ�û�ѧʽ��String��</p>
	 * @see #strRaw
	 * @see #Formula(String)
	 */
	public String getRawString() {
		return strRaw;
	}
	
	public String toString() {
		return this.mapAtomList.toString();
	}
	// �쳣��
	public class InvalidFormulaException extends Exception {
		private static final long serialVersionUID = 2L;
		String formula;
		
		InvalidFormulaException(String inStr) {
			this.formula = inStr;
		}
		
		public String getFormulaString() {
			return this.formula;
		}
	}
	
	/** ���캯��
	 * <p>�Ӵ���Ļ�ѧʽString�й���Formula��</p>
	 * @param inFormula ��ʾ�û�ѧʽ��String
	 * @see #strRaw
	 * @throws InvalidFormulaException
	 */
	public Formula(String inFormula) throws InvalidFormulaException{
		this.strRaw = inFormula;
		this.parseFormula(inFormula, 1);
	}
	
	/** �б�ϲ��㷨
	 * <p>�������ԭ���б�ϲ�����Formula��ԭ���б�{@link #mapAtomList}�С�</p>
	 * @param pairToInsert �����ϲ���ԭ���б�
	 */
	private void insertList(Pair<String,Integer> pairToInsert) {
		int numTodo = 0;
		try {
			numTodo = this.mapAtomList.get(pairToInsert.getL());
		} catch(Exception e) {
			
		} finally {
			this.mapAtomList.put(pairToInsert.getL(),numTodo + pairToInsert.getR());
		}
	}
	
	/** ��ѧʽ�����㷨
	 * <p>������ѧʽString������String��</p>
	 * @param inFormula Ҫ������String
	 * @param numMultiplier �ö�Stringԭ����ǰ��ϵ��
	 * @throws InvalidFormulaException
	 */
	private void parseFormula(String inFormula, int numMultiplier) throws InvalidFormulaException {
		Matcher sm;	// ����ƥ����
		
		Pattern e = Pattern.compile("^([A-Z][a-z]*)(\\d*)"),	// ԭ��ƥ������
				f = Pattern.compile("\\*(\\d*)([^*]+)[\\*]??"),	// �ηָ���"*"ƥ�� 
				g = Pattern.compile("\\(([^\\*]*)\\)(\\d*)"),	// ����ƥ��#1
				h = Pattern.compile("\\(([^\\*\\(]*)\\)(\\d*)");// ����ƥ��#2
		
		while (!inFormula.isEmpty()) {
			sm = e.matcher(inFormula);
						// ��һ��ԭ�� 
						// sm[1]: ԭ������
						// sm[2]: ԭ������(�п���Ϊ�հ�)
			if (sm.find()) {// ���ɹ���ȡ��ԭ��
				int tempNum;
				if(sm.group(2).isEmpty()) {// ��û���±� 
					tempNum = 1 * numMultiplier;	// Ĭ���±�Ϊ1 
				} else {// ���±�
					tempNum = Integer.valueOf(sm.group(2)) * numMultiplier; 
				}
				
				String tempStr = sm.group(1);	// ���������㷨����
				this.insertList(new Pair<String,Integer>(tempStr, tempNum));
				inFormula = inFormula.substring(sm.group(0).length());
			} else if (inFormula.charAt(0) == '*') {	// ����һ�� 
				sm = f.matcher(inFormula);	// �Ҷ�
											// sm[1]: �γ���
											// sm[2]: ������
				if (sm.find()) {
					int tempNum;
					if (sm.group(1).isEmpty()) {
						tempNum = 1;

					} else {
						tempNum = Integer.valueOf(sm.group(1));
					}
					
					String strTemp = sm.group(2);
					inFormula = inFormula.substring(sm.group(0).length());
					this.parseFormula(strTemp, tempNum);
				} else {// �նεĴ���
					//TODO: �ն�
				}
			} else if (inFormula.charAt(0) == '(') {
				// TODO: ���ŵĴ���
				
				int intCounter = 0; // ��һ�γ��ֺ������ǰ ǰ�����ų��ֵĴ���
				
				for (char ch : inFormula.toCharArray()) {
					if (intCounter > 2) {
						break;
					}
					switch (ch) {
						case '(': intCounter++; break;
						case ')': break;
						case '*': break;
					}
				}
				
				if (intCounter == 1) {	// ����������һ�Σ�˵�����ڲ� 
					sm = h.matcher(inFormula);
					sm.find();
					
					if (!sm.group(1).isEmpty()) {
						int tempNum;
						if (sm.group(2).isEmpty()) {
							tempNum = 1 * numMultiplier;
						} else {
							tempNum = Integer.valueOf(sm.group(2)) * numMultiplier;
						}
						
						String strTemp = sm.group(1);
						inFormula = inFormula.substring(sm.group(0).length());
						this.parseFormula(strTemp, tempNum);
					} else {
						//TODO: fucking
					}
				} else if (intCounter == 2) {	// �����˲�ֹһ�Σ�˵������� 
					sm = g.matcher(inFormula);
					sm.find();
					
					sm = h.matcher(inFormula);
					sm.find();
					
					int tempNum;
					if (sm.group(2).isEmpty()) {
						tempNum = 1 * numMultiplier;
					} else {
						tempNum = Integer.valueOf(sm.group(2)) * numMultiplier;
					}
					
					String strTemp = sm.group(1);
					inFormula = inFormula.substring(sm.group(0).length());
					this.parseFormula(strTemp, tempNum);
				}
			} else {
				throw new InvalidFormulaException("��ѧʽ�з��ַǷ��ַ� - " + inFormula.substring(0, 1));
			}
		}
	}
}
