package org.chembar.glockchem.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import org.chembar.glockchem.core.Equation;
import org.chembar.glockchem.core.EquationBalancer;
import org.chembar.glockchem.core.EquationCalculator;

public class AwtUI extends Frame{
	private Button a,b,c,d,e;
	public static AwtUI window;
	Equation equation = null;
	EquationCalculator calc = null;
	EquationBalancer balance;
	bbb bbb1;
	TextField t;
	String pass;
	public AwtUI(String str) {
		super(str);
		setSize(400,400);
		setVisible(true);
		add(a=new Button("��ʼ"));
		addWindowListener(new WindowAdapter(){
			 public void windowClosing(WindowEvent e) {
				exit();
			}
		  });
		setVisible(true);
		pass = "�����뷽��ʽ";
	}
	public class bbb extends KeyAdapter implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			 if(e.getSource()==a){
				 start(pass);
			 }
			 if(e.getSource()==b || e.getSource()==t){
				 input();
			 }
			 if(e.getSource()==c){
				 
			 }
			 if(e.getSource()==e){
				 init();
			 }
			 requestFocus();
		  }
	}
	private void start(String str) {
		removeAll();
		setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
		add(new Label(str));
		t = new TextField("���뷽��ʽ", 40);
		add(t);
		add(b = new Button("ȷ��"));
		b.addActionListener(bbb1);
		t.addActionListener(bbb1);
		setVisible(true);
	}
	private void input() {
		String strInput = t.getText();
		if (strInput == "") {
			start("��������Ϊ�գ�");
		}
		try {
			equation = new Equation(strInput);
		} catch (Exception e) {
			start("��������" + e.getMessage());
		}
		bal("����ʽ����ɹ���");
	}
	private void bal(String str) {
		removeAll();
		setLayout(new GridLayout(6,1));
		add(new Label(str));
		add(new Label("���ڼ����ƽ..."));
		setVisible(true);
		balance = new EquationBalancer(equation);
		if (balance.checkBalance() == false) {
			add(new Label("δ��ƽ"));
			add(new Label("����ʹ�ø�˹��Ԫ������ƽ����ʽ..."));
			if (balance.balanceGaussian() == true) {
				bal("��ƽ�ɹ�");
			} else {
				add(new Label("��ƽʧ��"));
				add(a = new Button("ȷ��"));
				pass= "��ƽʧ��";
				a.addActionListener(bbb1);
			}
		} else {
			cal("����ƽ");
		}
		setVisible(true);
	}
	private void cal(String str) {
		removeAll();
		setLayout(new GridLayout(7,1));
		add(new Label(str));
		add(new Label(equation.toString()));
		//TODO 
	}
	public void exit(){
		setVisible(false);
		dispose();
		System.exit(0);
	}
	private void init(){
		window = new AwtUI("GlockChem GUI - v." + ConsoleUI.version);
		init1();
	}
	private void init1(){
		bbb1 = window.new bbb();
		a.addActionListener(bbb1);
	}
	public static void main(String args[]) {
		window = new AwtUI("GlockChem GUI - v." + ConsoleUI.version);
		window.init1();
	}
}
