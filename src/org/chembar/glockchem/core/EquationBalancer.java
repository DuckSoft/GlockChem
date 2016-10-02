package org.chembar.glockchem.core;

import java.util.HashMap;
import java.util.Map;

/**方程式配平类
 * <p>用作与化学方程式配平有关的操作。</p>
 * @author DuckSoft
 */
public class EquationBalancer {
	Equation equInner;
	
	/**构造函数
	 * @param equation 欲进行操作的方程式
	 */
	public EquationBalancer(Equation equation) {
		this.equInner = equation;
	}
	
	/** 检查方程式是否平衡
	 * <p>遍历方程式内{@link Formula}内的原子个数表，以验证方程式是否平衡。<br>
	 * 若平衡，则返回{@code true}；若否，则返回{@code false}。</p>
	 * @return 方程式是否平衡
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
	
	/** 高斯消元法配平方程式
	 * <p>使用高斯消元法配平Equation。<br>
	 * 成功则返回已配平的{@code Equation}，否则返回{@code null}。</p>
	 * @return 配平结果
	 */
	public Equation balanceGaussian() {
		//TODO: 高斯消元算法
		
		
		return null;
	}
}
