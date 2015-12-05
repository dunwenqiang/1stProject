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
		// 输入文件ID为1或2或3，对应打开input1、2、3。txt文件，超出范围退出
		System.out.println("请输入1、2或者3：");
		Scanner fileID = new Scanner(System.in);
		int id = fileID.nextInt();
		fileID.close();
		if (id != 1 && id != 2 && id != 3)
			return;
		String fileName = "C:/input" + id + ".txt";

		Scanner scan = null;
		int i = 0, j = 0;
		String[][] item = new String[50][2];// 商品详情
		int[] quatity = new int[50];// 每样商品的数量
		double[] price = new double[50], taxedPrice = new double[50];// 单价和税后价
		double tax = 0;// 每个商品的税
		double total = 0, salesTaxes = 0;// 要输出的结果：总价和总税额
		// 打开文件
		File file = new File(fileName);
		try {
			scan = new Scanner(file);
			scan.useDelimiter(" at |\n");// 分割成数量和单价
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("打开input"+id+".txt文件");
		// 解析信息并输出
		while (scan.hasNext()) {
			// j为0时，next()是数量和名称
			if (0 == j) {
				item[i][0] = scan.next();// 存储的是数量和名称，如1 book
				String[] tempStrArr = item[i][0].split("\\s");// 按空格拆分，得到数量
				quatity[i] = Integer.valueOf(tempStrArr[0]);
				j = 1;// 下一个while进入else，得到单价
			} else {// j=1时，next()是单价
				item[i][1] = scan.next();// 存储的是单价，如12.49
				price[i] = Double.parseDouble(item[i][1]) * quatity[i];// 该种商品的税前总价
				// 计算每种商品的税额
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
				// 该种商品的税后价
				taxedPrice[i] = price[i] + tax;
				// 缴税总额
				salesTaxes += tax;
				// 应付总额
				total += taxedPrice[i];
				// 输出
				System.out.println(item[i][0] + ":" + new DecimalFormat("0.00").format(taxedPrice[i]));
				i++;// 第i项商品结束，进入i+1
				j = 0;
			}
		}
		scan.close();
		// 此处打印总价格和总税额，显示两位小数
		System.out.println("Sales Taxes" + ":" + new DecimalFormat("0.00").format(salesTaxes));
		System.out.println("Total" + ":" + new DecimalFormat("0.00").format(total));
	}
}
