package org.chembar.glockchem.core;

import java.util.ArrayList;
import java.util.List;

/** ��ѧ����ʽ����
 * @author DuckSoft
 */
public class Equation {
	/** ��Ӧ���б�*/
	public List<Pair<Formula,Integer>> reactant = new ArrayList<Pair<Formula,Integer>>();
	/** �������б�*/
	public List<Pair<Formula,Integer>> product = new ArrayList<Pair<Formula,Integer>>();
	
	/** �Ը����ĺ�����Ч��ʽ�Ļ�ѧ����ʽ�ַ�������һ��Equation����<br/>
	 * <p>�ɽ��ܵĸ�ʽʾ�����£�
	 * <li>2C + O2 = 2CO</li>
	 * <li>2C + O2 -> 2CO</li>
	 * <li>2C + O2 === 2CO</li>
	 * <li>2C + O2 ==> 2CO</li>
	 * </p>
	*/
	public Equation(String strEquation) throws Exception {
		this.parseEquation(strEquation);
	}

	/** ��������ʽ
	 * @param strEquation �������ķ���ʽ
	 * @throws Exception
	 */
	public void parseEquation(String strEquation) throws Exception {
		// �����ظ�����
		assert((this.reactant.isEmpty() && this.product.isEmpty()));
		
		// ��������Ϊ��
		if (strEquation.isEmpty()) {
			//TODO: ����Ϊ�յĴ���
			throw new Exception("����Ϊ��");
		}
		
		// �����ظ�����
		
		String partLeft = "";		// ��Ӧ������ﻺ����
		String partRight = "";
		boolean isRight = false;	// �Ƿ����������־ 
		boolean bAuxFlag = false;	// ������־
		
		for (char i : strEquation.toCharArray()) {
			if (i == '=' || i == '-') {	// ���� = �� - ����ʱ�ж�Ϊ��Ӧ����� 
				isRight = true;
				if (bAuxFlag == true) {	// ���ֳ���һ�ηָ��������ж�Ϊ���� 
					throw new Exception("���ֶ���һ���ķ�Ӧ��-������ָ���");
				}
				continue;
			} else if (i == ' ' || i == '>') {	// ���Ե� ---> ��ʽ�е� > �ַ��Լ��հ��ַ�
				continue;
			} else {
				if (isRight == true) {	// ���ѵ��������ﲿ�� 
					bAuxFlag = true; 	// �趨������־ 
				}
			}
			
			if (isRight) {
				partRight += String.valueOf(i);
			} else {
				partLeft += String.valueOf(i);
			}
		}
		
		// ���ڷ��뵽�б���
		if (partLeft.isEmpty() || partRight.isEmpty()) {
			throw new Exception("������ķ�Ӧ���������Ϊ��");
		}

		boolean isStarting = true;	// ��־���Ƿ��ǻ�ѧʽ�Ŀ�ͷ
		String strTempA = "";			// ϵ���洢
		String strTempB = "";			// ��ѧʽ�洢
		
		for (char i : partLeft.toCharArray()) {
			if (isStarting == true) {	// ��Ϊ��ѧʽ�Ŀ�ͷ 
				if (('0' <= i) && (i <= '9')) { // �ж��Ƿ�Ϊ���� 
					strTempA += String.valueOf(i);				// ��Ϊ����ϵ������뵽ϵ���ݴ��� 
				} else {
					isStarting = false;	// �������ʾ���ֲ��ֽ���
					
					if (strTempA.isEmpty()){	// ����û��ϵ������� 
						strTempA = "1";		// ��û��ϵ���������ϵ��"1" 
					}
					
					if (i == '+') {	// ��ֹ��ͷ������"+"�ŵ�������� 
						throw new Exception("�б�ͷ�����հ���");
					} 
					
					strTempB += String.valueOf(i);	// �������ַ����뻯ѧʽ�洢�� 
				}
			} else {	// ���ǻ�ѧʽ�Ŀ�ͷ 
				if (i == '+') {	// ��Ϊ"+"�� 
					if (strTempB.isEmpty() || strTempA.isEmpty()) { // ��ֹ��ѧʽ��ϵ��Ϊ��ʱ�����б�
						throw new Exception("Equation::parseFormulaList: �б��������հ���");
					} else {
						this.reactant.add(new Pair<Formula,Integer>(new Formula(strTempB),new Integer(strTempA)));
						// ��ʼ��״̬ 
						strTempA = "";
						strTempB = "";
						isStarting = true;
					}
				} else {		// ����"+"�� 
					strTempB += String.valueOf(i);	// ֱ�Ӽ��뻯ѧʽ���� 
				}
			}
		}
		
		// ѭ������
		if (!(strTempA.isEmpty() || strTempB.isEmpty())) {
			this.reactant.add(new Pair<Formula,Integer>(new Formula(strTempB),new Integer(strTempA)));
			// ��ʼ��״̬ 
			strTempA = "";
			strTempB = "";
			isStarting = true;
		}
		
		strTempA = "";
		strTempB = "";
		isStarting = true;
		
		for (char i : partRight.toCharArray()) {
			if (isStarting == true) {	// ��Ϊ��ѧʽ�Ŀ�ͷ 
				if (('0' <= i) && (i <= '9')) { // �ж��Ƿ�Ϊ���� 
					strTempA += String.valueOf(i);				// ��Ϊ����ϵ������뵽ϵ���ݴ��� 
				} else {
					isStarting = false;	// �������ʾ���ֲ��ֽ���
					
					if (strTempA.isEmpty()){	// ����û��ϵ������� 
						strTempA = "1";		// ��û��ϵ���������ϵ��"1" 
					}
					
					if (i == '+') {	// ��ֹ��ͷ������"+"�ŵ�������� 
						throw new Exception("�б�ͷ�����հ���");
					} 
					
					strTempB += String.valueOf(i);	// �������ַ����뻯ѧʽ�洢�� 
				}
			} else {	// ���ǻ�ѧʽ�Ŀ�ͷ 
				if (i == '+') {	// ��Ϊ"+"�� 
					if (strTempB.isEmpty() || strTempA.isEmpty()) { // ��ֹ��ѧʽ��ϵ��Ϊ��ʱ�����б�
						throw new Exception("Equation::parseFormulaList: �б��������հ���");
					} else {
						this.product.add(new Pair<Formula,Integer>(new Formula(strTempB),new Integer(strTempA)));
						// ��ʼ��״̬ 
						strTempA = "";
						strTempB = "";
						isStarting = true;
					}
				} else {		// ����"+"�� 
					strTempB += String.valueOf(i);	// ֱ�Ӽ��뻯ѧʽ���� 
				}
			}
		}
		
		// ѭ������
		if (!(strTempA.isEmpty() || strTempB.isEmpty())) {
			this.product.add(new Pair<Formula,Integer>(new Formula(strTempB),new Integer(strTempA)));
			// ��ʼ��״̬ 
			strTempA = "";
			strTempB = "";
			isStarting = true;
		}
	}
}
