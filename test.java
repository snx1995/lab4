package Shiyan1;

import java.io.*;
import java.util.*;
//import org.apache.commons.io.FileUtils;

import Shiyan1.Graph.Edge;
import Shiyan1.Graph.Vertex;

public class Test {
	public static String getPath(Graph g) {
		List<String> colorList = new LinkedList<String>();
		colorList.add("{color:#c6531e}");
		colorList.add("{color:#b01700}");
		colorList.add("{color:#db8e3c}");
		colorList.add("{color:#ffe35f}");
		colorList.add("{color:#95cde5}");
		colorList.add("{color:#95cde5}");
		Map<Vertex, List<Edge>> map = g.getVer_edgeList_map();
		List<Vertex> vList = g.getVertexList();
		Random random = new Random();
		String path = "";
		for (int i = 0; i < vList.size(); i++) {
			Vertex v = vList.get(i);
			// System.out.println("���㣺"+v.getName());
			List<Edge> eList = map.get(v);
			if (eList == null) {
				continue;
			}
			for (int j = 0; j < eList.size(); j++) {
				Edge e = eList.get(j);
				path += e.getStartVertex().getName() + "->" 
				+ e.getEndVertex().getName() + "\n";
			}
		}

		List<Vertex> vlist = g.getVertexList();
		for (int i = 0; i < vlist.size(); i++) {
			int a = random.nextInt(colorList.size() - 1);
			path += vlist.get(i).getName() 
					+ " " + colorList.get(a) + "\n";
		}

		return path;

	}

	public static void showDirectedGraph(Graph G) {
		String path = getPath(G);

		/*
		 * String file_chrome = "chromedriver.exe";
		 * System.setProperty("webdriver.chrome.driver", file_chrome); WebDriver
		 * driver=new ChromeDriver(); driver.get("http://arborjs.org/halfviz/");
		 * new WebDriverWait(driver,20).until(ExpectedConditions.
		 * presenceOfElementLocated(By.xpath("//*[@id=\"code\"]")));
		 */

		/*
		 * DesiredCapabilities dcaps = new DesiredCapabilities();
		 * dcaps.setCapability("cssSelectorsEnabled", true);
		 * dcaps.setJavascriptEnabled(true);
		 * dcaps.setCapability("takesScreenshot", true);
		 * dcaps.setCapability("phantomjs.page.customHeaders.User-Agent",
		 * "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"
		 * ); dcaps.setCapability(PhantomJSDriverService.
		 * PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"phantomjs.exe"); PhantomJSDriver
		 * driver = new PhantomJSDriver(dcaps);
		 * driver.manage().window().maximize();
		 * driver.get("http://arborjs.org/halfviz/"); new
		 * WebDriverWait(driver,20).until(ExpectedConditions.
		 * presenceOfElementLocated(By.xpath("//*[@id=\"code\"]")));
		 * 
		 * 
		 * 
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO:
		 * handle exception }
		 * driver.findElement(By.xpath("//*[@id=\"code\"]")).click();
		 * driver.findElement(By.xpath("//*[@id=\"code\"]")).clear();
		 * driver.findElement(By.xpath("//*[@id=\"code\"]")).sendKeys(path);
		 * driver.findElement(By.xpath("//*[@id=\"dashboard\"]/ul/li[4]/span")).
		 * click(); Actions action = new Actions(driver); WebElement source =
		 * driver.findElement(By.xpath("//*[@id=\"grabber\"]"));
		 * action.clickAndHold(source).moveByOffset(500, 0);
		 * action.moveToElement(source).release(); action.perform(); try {
		 * Thread.sleep(1000); } catch (InterruptedException e) {
		 * 
		 * } try { File scrFile = ((TakesScreenshot)
		 * driver).getScreenshotAs(OutputType.FILE); FileUtils.copyFile(scrFile,
		 * new File("selenium.jpg")); }catch (Exception e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * driver.quit();
		 */
		System.out.println("��������̫�󣬴�������");
	}

	public static Graph createDirectedGraph(String filename) {
		File file = new File(filename);
		BufferedReader reader = null;
		String tempString = null;
		String txtString = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
				// System.out.println(tempString);
				txtString += tempString + "\n";
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {

				}
			}
		}
//		System.out.println(txtString);
//		txtString = txtString.replaceAll("[\\pP]", "-"); // ������ʽ
//		txtString = txtString.replaceAll("[\\pZ]", "-"); // ������ʽ
//		txtString = txtString.replaceAll("\t", "-");
//		txtString = txtString.replaceAll("\n", "-");
//		// System.out.println(txtString);
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < txtString.length(); i++) {
//			char c = txtString.charAt(i);
//			if ((c <= 'z' && c >= 'a') || 
//					(c <= 'Z' && c >= 'A') || (c == '-')) {
//				sb.append(c);
//			}
//		}
//		// System.out.println(sb);
//		txtString = sb.toString();
//		// System.out.println(txtString);
		String[] arr = txtString.split("[^a-zA-Z]+");//�򵥷���������
		List<String> retStr = new LinkedList<String>();

		// StringBuffer retStr = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			String temp = arr[i];

			if ("".equals(temp)){
				continue;
			}
			retStr.add(temp.toLowerCase());
			// retStr.append(temp.toLowerCase());
		}
		// System.out.println(retStr);//���Ǵ�����������ĵ���
		// System.out.println(retStr.get(5));
		List<String> strIndex = new LinkedList<String>();
		for (int i = 0; i < retStr.size(); i++) {
			if (!strIndex.contains(retStr.get(i))) {
				strIndex.add(retStr.get(i));
			} else {
				// System.out.println("repeat:"+retStr.get(i));
			}

		}
		// System.out.println(strIndex);//���ǲ��ظ��ĵ���
		Map<String, Vertex> name_Vertex_map 
		= new HashMap<String, Vertex>();
		for (int i = 0; i < strIndex.size(); i++) {
			String word = strIndex.get(i);
			Vertex v = new Vertex(word);

			name_Vertex_map.put(word, v);
		}

		Map<Vertex, List<Edge>> vertex_edgeList_map 
		= new HashMap<Graph.Vertex, List<Edge>>();
		for (int i = 0; i < retStr.size(); i++) {

			if (i == retStr.size() - 1) {

				break;
			}
			Vertex tmp = name_Vertex_map.get(retStr.get(i));
			Vertex next = name_Vertex_map.get(retStr.get(i + 1));
			if (tmp.getName().equals(next.getName())) {
				break;
			}
			if (!vertex_edgeList_map.containsKey(tmp)) {
				List<Edge> eList = new LinkedList<Graph.Edge>();
				eList.add(new Edge(tmp, next, 1));
				vertex_edgeList_map.put(tmp, eList);

			} else {
				List<Edge> eList = vertex_edgeList_map.get(tmp);
				boolean edge_exit = false;
				for (int j = 0; j < eList.size(); j++)// ���Ѵ���
				{
					Edge tmp_Edge = eList.get(j);
					if (tmp_Edge.getEndVertex().getName().equals(next.getName())) {
						tmp_Edge.setWeight(tmp_Edge.getWeight() + 1);
						eList.set(j, tmp_Edge);
						edge_exit = true;
					}
				}
				if (!edge_exit) {
					eList.add(new Edge(tmp, next, 1));
				}
				vertex_edgeList_map.put(tmp, eList);
			}
		}
		List<Vertex> verList = new LinkedList<Graph.Vertex>();
		for (int i = 0; i < strIndex.size(); i++) {
			Vertex tmp = name_Vertex_map.get(strIndex.get(i));
			verList.add(tmp);
		}

		Graph testGraph = new Graph(verList, vertex_edgeList_map);
		return testGraph;
	}

	public static String queryBridgeWords(Graph g, String word1, String word2) {

		int startIndex = g.getVertexListIndex(word1);
		int destIndex = g.getVertexListIndex(word2);
		if (startIndex == -1 || destIndex == -1) {
			System.out.println("No bridge words from " 
		+ word1 + " to " + word2 + "!");
			return "";
		}
		Vertex start = g.getVertexList().get(startIndex);
		Vertex dest = g.getVertexList().get(destIndex);
		// System.out.println(start.getName()+dest.getName());
		List<Vertex> bridgeVertexList = new LinkedList<Graph.Vertex>();
		for (Edge e : g.getVer_edgeList_map().get(start)) {
			Vertex childVertex = e.getEndVertex();
			for (Edge childedge : g.getVer_edgeList_map().get(childVertex)) {
				Vertex finalVertex = childedge.getEndVertex();
				if (finalVertex.getName().equals(dest.getName())) {
					Vertex bridgeVertex = childedge.getStartVertex();
					bridgeVertexList.add(bridgeVertex);
				}
			}

		}
		String str = "";
		// Ϊ�˸�ʽ���ÿ����ż�1
		for (int i = 0; i < bridgeVertexList.size() - 1; i++) {
			// System.out.println("----------------");
			Vertex v = bridgeVertexList.get(i);
			str += v.getName() + "--";
		}
		if (!(bridgeVertexList.size() == 0)) {
			str += bridgeVertexList.get(bridgeVertexList.size() - 1).getName();
		}
		return str;
	}

	public static void randomWalk(Graph g) {
		List<Vertex> vertexList = g.getVertexList();
		Map<Vertex, List<Edge>> ver_edgeList_map = g.getVer_edgeList_map();
		String path = "";
		Random random = new Random();
		int vertexNum = vertexList.size();
		if (vertexNum < 1) {
			System.out.println("vertex is empty!");
			return;
		}
		int randomVertex = 0;
		if (vertexNum == 1) {
			randomVertex = 0;
		} else {
			randomVertex = random.nextInt(vertexNum - 1);
		}

		Vertex start = vertexList.get(randomVertex);
		path = start.getName();
		// System.out.println(start.getName()+"->");

		List<Edge> startEdgeList = ver_edgeList_map.get(start);
		int startedgeNum = startEdgeList.size();
		if (startedgeNum < 1) {
			System.out.println(path);
			System.out.println("edge is empty!");
			return;
		}
		Vertex tmpVertex = start;
		List<Edge> edgeRecordList = new LinkedList<Edge>();
		Scanner input2 = new Scanner(System.in);
		while (!(ver_edgeList_map.get(tmpVertex) == null) &&
				!(ver_edgeList_map.get(tmpVertex).size() == 0)) {
			List<Edge> edgeList = ver_edgeList_map.get(tmpVertex);

			int edgeNum = edgeList.size();
			// System.out.println(edgeNum);
			int randomEdge = 0;
			if (edgeNum == 1) {
				randomEdge = 0;
			} else {
				randomEdge = random.nextInt(edgeNum - 1);
			}
			Edge randEdge = edgeList.get(randomEdge);
			Vertex nextVertex = randEdge.getEndVertex();
			if (edgeRecordList.contains(randEdge)) {
				path += "->" + nextVertex.getName() +
						"\nedge repeat!";
				break;
			}
			path += "->" + nextVertex.getName();
			// System.out.println(nextVertex.getName()+"->");
			edgeRecordList.add(randEdge);
			tmpVertex = nextVertex;
			System.out.println(path);
			String str = input2.nextLine();

		}
		System.out.println(path);
	}

	public static String calcShortestPath(Graph g, String word1, String word2) {
		List<Vertex> vertexList = g.getVertexList();
		int startIndex = g.getVertexListIndex(word1);
		int destIndex = g.getVertexListIndex(word2);
		if (startIndex == -1 || destIndex == -1) {
			System.out.println(word1 + " or " + 
		word2 + " not in the graph");
			return "";
		}
		Vertex start = vertexList.get(startIndex);
		Vertex dest = vertexList.get(destIndex);
		String path = "[" + dest.getName() + "]";

		g.setRoot(start);
		g.updateChildren(vertexList.get(startIndex));
		g.setRoot(start);
		// System.out.println("+++++++++++++");
		int shortest_length = dest.getAdjuDist();

		while ((dest.getParent() != null) && (!dest.equals(start))) {
			path = "[" + dest.getParent().getName() + "] --> " 
		+ path;
			dest = dest.getParent();
			// System.out.println("---------");
		}
		if (path.equals("[" + vertexList.get(destIndex).getName() + "]")) {
			path = "[" + vertexList.get(startIndex).getName() + 
					"] to [" + vertexList.get(destIndex).getName()
					+ "] shortest path : " + "NO PATH";
			System.out.println(path);
			return path;
		}
		System.out.println("[" + vertexList.get(startIndex).getName() + 
				"] to [" + vertexList.get(destIndex).getName()
				+ "] shortest path : " + path);
		System.out.println("shortest length::" + shortest_length);
		return path;
	}

	public static String generateNewText(Graph g, String inputText) {
		String outputText = "";
		String[] arr = inputText.split(" ");
		// List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length - 1; i++) {
			String temp = arr[i];
			String next = arr[i + 1];
			String bridgeWord = queryBridgeWords(g, temp, next);
			outputText += temp + " ";
			if (!"".equals(bridgeWord)) {
				String[] bridgeWordArr = bridgeWord.split(" ");
				if (bridgeWordArr.length == 1) {
					outputText += bridgeWord + " ";
				} else {

					Random random = new Random();
					int randomIndex = random.nextInt(bridgeWordArr.length - 1);
					// ��Ҫѡ��һ������
					outputText += bridgeWordArr[randomIndex] + " ";
				}
			}
		}
		outputText += arr[arr.length - 1];
		System.out.println("�ŽӴ����ı�:" + outputText);
		return outputText;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("�������ļ�λ����������ͼ:"
				+ "����C:\\Users\\mwq\\Desktop\\readme.txt");
		String filename = input.nextLine();
		Graph testGraph = createDirectedGraph(filename);
		while (true) {
			System.out.println("���������ָ�� 1.�˳�\n"
					+ "2.չʾ����ͼ\n3.��ѯ�ŽӴ�\n4.����bridge word�������ı�\n"
					+ "5.������������֮������·��\n6.�������");
			String str = input.nextLine();
			// System.out.println(str);

			if (str.equals("1")) {
				System.out.println("�˳�");
				break;
			} else if (str.equals("2")) {
				System.out.println("��ѡ����չʾ����ͼ");
				showDirectedGraph(testGraph);
				System.out.println("����ͼ�������,"
						+ "�뵽��ǰĿ¼�²鿴selenium.png");

			} else if (str.equals("3")) {
				System.out.println("��ѡ���˲�ѯ�ŽӴ�,�������������ʣ��ÿո����");
				String word = input.nextLine();
				String[] arr = word.split(" ");
				String word1 = arr[0];
				String word2 = arr[1];
				String outstr = queryBridgeWords(testGraph, word1, word2);
				System.out.println(word1 + 
						"  " + word2 + "���ŽӴ�:" + outstr);
			} else if (str.equals("4")) {
				System.out.println("��ѡ���˸���bridge word"
						+ "�������ı�,������һ���ı�");
				String line = input.nextLine();
				generateNewText(testGraph, line);
			} else if (str.equals("5")) {
				System.out.println("��ѡ���˼�����������֮������·��,"
						+ "�������������ʣ��ÿո����");
				String word = input.nextLine();
				String[] arr = word.split(" ");
				String word1 = arr[0];
				String word2 = arr[1];
				calcShortestPath(testGraph, word1, word2);
			} else if (str.equals("6")) {
				System.out.println("��ѡ�����������");
				randomWalk(testGraph);
			}
		}
	}
}
