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
			// System.out.println("顶点："+v.getName());
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
		System.out.println("第三方包太大，传不上来");
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
//		txtString = txtString.replaceAll("[\\pP]", "-"); // 正则表达式
//		txtString = txtString.replaceAll("[\\pZ]", "-"); // 正则表达式
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
		String[] arr = txtString.split("[^a-zA-Z]+");//简单方法！！！
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
		// System.out.println(retStr);//这是处理过的完整的单词
		// System.out.println(retStr.get(5));
		List<String> strIndex = new LinkedList<String>();
		for (int i = 0; i < retStr.size(); i++) {
			if (!strIndex.contains(retStr.get(i))) {
				strIndex.add(retStr.get(i));
			} else {
				// System.out.println("repeat:"+retStr.get(i));
			}

		}
		// System.out.println(strIndex);//这是不重复的单词
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
				for (int j = 0; j < eList.size(); j++)// 边已存在
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
		// 为了格式更好看，才减1
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
					// 还要选择一个单词
					outputText += bridgeWordArr[randomIndex] + " ";
				}
			}
		}
		outputText += arr[arr.length - 1];
		System.out.println("桥接词新文本:" + outputText);
		return outputText;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入文件位置生成有向图:"
				+ "例如C:\\Users\\mwq\\Desktop\\readme.txt");
		String filename = input.nextLine();
		Graph testGraph = createDirectedGraph(filename);
		while (true) {
			System.out.println("请输入操作指令 1.退出\n"
					+ "2.展示有向图\n3.查询桥接词\n4.根据bridge word生成新文本\n"
					+ "5.计算两个单词之间的最短路径\n6.随机游走");
			String str = input.nextLine();
			// System.out.println(str);

			if (str.equals("1")) {
				System.out.println("退出");
				break;
			} else if (str.equals("2")) {
				System.out.println("您选择了展示有向图");
				showDirectedGraph(testGraph);
				System.out.println("有向图生成完毕,"
						+ "请到当前目录下查看selenium.png");

			} else if (str.equals("3")) {
				System.out.println("您选择了查询桥接词,请输入两个单词，用空格隔开");
				String word = input.nextLine();
				String[] arr = word.split(" ");
				String word1 = arr[0];
				String word2 = arr[1];
				String outstr = queryBridgeWords(testGraph, word1, word2);
				System.out.println(word1 + 
						"  " + word2 + "的桥接词:" + outstr);
			} else if (str.equals("4")) {
				System.out.println("您选择了根据bridge word"
						+ "生成新文本,请输入一行文本");
				String line = input.nextLine();
				generateNewText(testGraph, line);
			} else if (str.equals("5")) {
				System.out.println("您选择了计算两个单词之间的最短路径,"
						+ "请输入两个单词，用空格隔开");
				String word = input.nextLine();
				String[] arr = word.split(" ");
				String word1 = arr[0];
				String word2 = arr[1];
				calcShortestPath(testGraph, word1, word2);
			} else if (str.equals("6")) {
				System.out.println("您选择了随机游走");
				randomWalk(testGraph);
			}
		}
	}
}
