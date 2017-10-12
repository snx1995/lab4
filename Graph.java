package shiyan1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.lang.NullPointerException;
public class Graph{
	private List<Vertex> vertexList;   //图的顶点集
	private Map<Vertex, List<Edge>> ver_edgeList_map;  //图的每个顶点对应的有向边

	public Graph(List<Vertex> vertexList, Map<Vertex, List<Edge>> ver_edgeList_map) {
		super();
		this.vertexList = vertexList;
		this.ver_edgeList_map = ver_edgeList_map;
	}

	public List<Vertex> getVertexList() {
		return vertexList;
	}

	public void setVertexList(List<Vertex> vertexList) {
		this.vertexList = vertexList;
	}
	public int getVertexListIndex(String str) {
		String s=str.toLowerCase();
		List<Vertex> vList=getVertexList();
		for(int i=0;i<vList.size();i++)
		{
			Vertex v=vList.get(i);
			if (s.equals(v.getName())) {
				return i;
			}
		}
		return -1;
	}
	
	public Map<Vertex, List<Edge>> getVer_edgeList_map() {
		return ver_edgeList_map;
	}

	public void setVer_edgeList_map(Map<Vertex, List<Edge>> ver_edgeList_map) {
		this.ver_edgeList_map = ver_edgeList_map;
	}

	static class Edge{
		private Vertex startVertex;  //此有向边的起始点
		private Vertex endVertex;  //此有向边的终点
		private int weight;  //此有向边的权值
		
		public Edge(Vertex startVertex, Vertex endVertex, int weight) {
			super();
			this.startVertex = startVertex;
			this.endVertex = endVertex;
			this.weight = weight;
		}
		
		public Edge()
		{}
		
		public Vertex getStartVertex() {
			return startVertex;
		}
		public void setStartVertex(Vertex startVertex) {
			this.startVertex = startVertex;
		}
		public Vertex getEndVertex() {
			return endVertex;
		}
		public void setEndVertex(Vertex endVertex) {
			this.endVertex = endVertex;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
	}
	
	static class Vertex {
		private final static int infinite_dis = Integer.MAX_VALUE;
		private String name;  //节点名字
		private boolean known; //此节点之前是否已知
		private int adjuDist; //此节点距离
		private Vertex parent; //当前从初始节点到此节点的最短路径下，的父节点。
		
		public Vertex()
		{
			this.known = false;
			this.adjuDist = infinite_dis;
			this.parent = null;
		}
		public Vertex(String name)
		{
			this.known = false;
			this.adjuDist = infinite_dis;
			this.parent = null;
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isKnown() {
			return known;
		}
		public void setKnown(boolean known) {
			this.known = known;
		}
		public int getAdjuDist() {
			return adjuDist;
		}
		public void setAdjuDist(int adjuDist) {
			this.adjuDist = adjuDist;
		}
		public Vertex getParent() {
			return parent;
		}
		public void setParent(Vertex parent) {
			this.parent = parent;
		}
		/**
		 * 重新Object父类的equals方法
		 */
		@Override
	    public boolean equals(Object obj) {
			if (!(obj instanceof Vertex)) {
				throw new ClassCastException("an object to compare with a Vertext must be Vertex");
			}
			
	    	if (this.name==null) {
				throw new NullPointerException("name of Vertex to be compared cannot be null");
			}
	    	
	    	return this.name.equals(obj);
	    }
	}
	public void setRoot(Vertex v)
	{
		v.setParent(null);
		v.setAdjuDist(0);
	}

	public void updateChildren(Vertex v)
	{
		if (v==null) {
			return;
		}
		
		if (ver_edgeList_map.get(v)==null||ver_edgeList_map.get(v).size()==0) {
			return;
		}
		
		
		List<Vertex> childrenList = new LinkedList<Graph.Vertex>();
		for(Edge e:ver_edgeList_map.get(v))
		{
			Vertex childVertex = e.getEndVertex();
			
			//如果子节点之前未知，则把当前子节点假如更新列表
			if(!childVertex.isKnown())
			{
				childVertex.setKnown(true);
				childVertex.setAdjuDist(v.getAdjuDist()+e.getWeight());
				childVertex.setParent(v);
				childrenList.add(childVertex);
			}
			
			//子节点之前已知，则比较子节点的ajduDist&&nowDist
			int nowDist = v.getAdjuDist()+e.getWeight();
			if(nowDist>=childVertex.getAdjuDist())
			{
				continue;
			}
			else {
				childVertex.setAdjuDist(nowDist);
				childVertex.setParent(v);
			}
		}
		
	    //更新每一个子节点
		for(Vertex vc:childrenList)
		{
			updateChildren(vc);
		}
	}

}


