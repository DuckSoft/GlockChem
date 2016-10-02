package org.chembar.glockchem.core;
// ��δ���
// TODO:����

/** ������
 * <p>Ϊ���{@link EquationBalancer#balanceGaussian()}��д��һ������ļ���ʵ�֡�</p>
 * @author DuckSoft
 */
public final class Matrix {
	public int[][] matrix;
	
	/**���캯��
	 * <p>����һ���µľ����ࡣ<br>
	 * ע�⣺�������������������Ϊ������</p>
	 * @param lines �¾����������
	 * @param cols �¾����������
	 */
	public Matrix(int lines, int cols) {
		assert((lines > 0) && (cols > 0));
		// ��ʼ������
		matrix = new int[lines][cols];
	}
	
	public Matrix(int[][] matArray) {
		this.matrix = matArray;
	}
	
	public final int[][] toArray() {
		return this.matrix;
	}
	
	public final String toString() {
		String strTemp = new String();
		for (int ln=0; ln<this.matrix.length; ln++) {
			for (int col=0; col<this.matrix[0].length; col++) {
				strTemp += "[";
				strTemp += this.matrix[ln][col];
				strTemp += "]";
			}
			strTemp += "\n";
		}
		
		return strTemp;
	}
	
	public final void swapLines(int ln1, int ln2) {
		int[] lnTemp = this.matrix[ln1].clone();
		
		for (int i=0; i<this.matrix[0].length; i++) {
			this.matrix[ln1][i] = this.matrix[ln2][i];
			this.matrix[ln2][i] = lnTemp[i];
		}
	}
}
