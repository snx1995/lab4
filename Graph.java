package shiyan1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.lang.NullPointerException;
public class Graph{
	private List<Vertex> vertexList;   //ͼ�Ķ��㼯
	private Map<Vertex, List<Edge>> ver_edgeList_map;  //ͼ��ÿ�������Ӧ�������

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
		private Vertex startVertex;  //������ߵ���ʼ��
		private Vertex endVertex;  //������ߵ��յ�
		private int weight;  //������ߵ�Ȩֵ
		
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
		private String name;  //�ڵ�����
		private boolean known; //�˽ڵ�֮ǰ�Ƿ���֪
		private int adjuDist; //�˽ڵ����
		private Vertex parent; //��ǰ�ӳ�ʼ�ڵ㵽�˽ڵ�����·���£��ĸ��ڵ㡣
		
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
		 * ����Object�����equals����
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
			
			//����ӽڵ�֮ǰδ֪����ѵ�ǰ�ӽڵ��������б�
			if(!childVertex.isKnown())
			{
				childVertex.setKnown(true);
				childVertex.setAdjuDist(v.getAdjuDist()+e.getWeight());
				childVertex.setParent(v);
				childrenList.add(childVertex);
			}
			
			//�ӽڵ�֮ǰ��֪����Ƚ��ӽڵ��ajduDist&&nowDist
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
		
	    //����ÿһ���ӽڵ�
		for(Vertex vc:childrenList)
		{
			updateChildren(vc);
		}
	}

}


