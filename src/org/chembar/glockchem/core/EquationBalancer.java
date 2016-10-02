package org.chembar.glockchem.core;

import java.util.HashMap;
import java.util.Map;

/**����ʽ��ƽ��
 * <p>�����뻯ѧ����ʽ��ƽ�йصĲ�����</p>
 * @author DuckSoft
 */
public class EquationBalancer {
	Equation equInner;
	
	/**���캯��
	 * @param equation �����в����ķ���ʽ
	 */
	public EquationBalancer(Equation equation) {
		this.equInner = equation;
	}
	
	/** ��鷽��ʽ�Ƿ�ƽ��
	 * <p>��������ʽ��{@link Formula}�ڵ�ԭ�Ӹ���������֤����ʽ�Ƿ�ƽ�⡣<br>
	 * ��ƽ�⣬�򷵻�{@code true}�������򷵻�{@code false}��</p>
	 * @return ����ʽ�Ƿ�ƽ��
	 */
	public boolean checkBalance() {
		Map<String,Integer> atomReactant = new HashMap<String,Integer>();
		Map<String,Integer> atomProduct = new HashMap<String,Integer>();
		
		for (Pair<Formula,Integer> pair : this.equInner.reactant) {
			for (Map.Entry<String,Integer> entry : pair.getL().mapAtomList.entrySet()) {
				int numTodo = 0;
				try {
					numTodo = atomReactant.get(entry.getKey());
				} catch(Exception e) {
					
				}
				atomReactant.put(entry.getKey(),entry.getValue() * pair.getR() + numTodo);
			}
		}
		
		for (Pair<Formula,Integer> pair : this.equInner.product) {
			for (Map.Entry<String,Integer> entry : pair.getL().mapAtomList.entrySet()) {
				int numTodo = 0;
				try {
					numTodo = atomProduct.get(entry.getKey());
				} catch(Exception e) {
				
				}
				atomProduct.put(entry.getKey(),entry.getValue() * pair.getR() + numTodo);
			}
		}
		
		for (Map.Entry<String,Integer> entry : atomReactant.entrySet()) {
			if (atomProduct.containsKey(entry.getKey())) {
				if (atomProduct.get(entry.getKey()) == entry.getValue()) {
					continue;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	/** ��˹��Ԫ����ƽ����ʽ
	 * <p>ʹ�ø�˹��Ԫ����ƽEquation��<br>
	 * �ɹ��򷵻�����ƽ��{@code Equation}�����򷵻�{@code null}��</p>
	 * @return ��ƽ���
	 */
	public Equation balanceGaussian() {
		//TODO: ��˹��Ԫ�㷨
		
		
		return null;
	}
}
