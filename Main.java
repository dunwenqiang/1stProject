/**
 */
package works.thought;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author dun
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// �����ļ�IDΪ1��2��3����Ӧ��input1��2��3��txt�ļ���������Χ�˳�
		System.out.println("������1��2����3��");
		Scanner fileID = new Scanner(System.in);
		int id = fileID.nextInt();
		fileID.close();
		if (id != 1 && id != 2 && id != 3)
			return;
		String fileName = "C:/input" + id + ".txt";

		Scanner scan = null;
		int i = 0, j = 0;
		String[][] item = new String[50][2];// ��Ʒ����
		int[] quatity = new int[50];// ÿ����Ʒ������
		double[] price = new double[50], taxedPrice = new double[50];// ���ۺ�˰���
		double tax = 0;// ÿ����Ʒ��˰
		double total = 0, salesTaxes = 0;// Ҫ����Ľ�����ܼۺ���˰��
		// ���ļ�
		File file = new File(fileName);
		try {
			scan = new Scanner(file);
			scan.useDelimiter(" at |\n");// �ָ�������͵���
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("��input"+id+".txt�ļ�");
		// ������Ϣ�����
		while (scan.hasNext()) {
			// jΪ0ʱ��next()������������
			if (0 == j) {
				item[i][0] = scan.next();// �洢�������������ƣ���1 book
				String[] tempStrArr = item[i][0].split("\\s");// ���ո��֣��õ�����
				quatity[i] = Integer.valueOf(tempStrArr[0]);
				j = 1;// ��һ��while����else���õ�����
			} else {// j=1ʱ��next()�ǵ���
				item[i][1] = scan.next();// �洢���ǵ��ۣ���12.49
				price[i] = Double.parseDouble(item[i][1]) * quatity[i];// ������Ʒ��˰ǰ�ܼ�
				// ����ÿ����Ʒ��˰��
				if (item[i][0].contains(" imported ")) {
					if ((item[i][0].contains(" pills")) || (item[i][0].contains(" chocolate"))) {
						tax = (Math.round(price[i])) * 0.05;
					} else {
						tax = (Math.round(price[i] * 3)) * 0.05;
					}
				} else {
					if ((item[i][0].contains(" pills")) || (item[i][0].contains(" chocolate"))
							|| (item[i][0].contains(" book"))) {
						tax = 0;
					} else {
						tax = (Math.round(price[i] * 2)) * 0.05;
					}
				}
				// ������Ʒ��˰���
				taxedPrice[i] = price[i] + tax;
				// ��˰�ܶ�
				salesTaxes += tax;
				// Ӧ���ܶ�
				total += taxedPrice[i];
				// ���
				System.out.println(item[i][0] + ":" + new DecimalFormat("0.00").format(taxedPrice[i]));
				i++;// ��i����Ʒ����������i+1
				j = 0;
			}
		}
		scan.close();
		// �˴���ӡ�ܼ۸����˰���ʾ��λС��
		System.out.println("Sales Taxes" + ":" + new DecimalFormat("0.00").format(salesTaxes));
		System.out.println("Total" + ":" + new DecimalFormat("0.00").format(total));
	}
}
