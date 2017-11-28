package contenedoresLineales;

public class clsNode {
	private Object nodeInfo;
	private clsNode nextNode;


	clsNode(Object nodeInfo){
		this(nodeInfo,null);
	}

	clsNode(Object nodeInfo, clsNode nextNode){
		this.nodeInfo=nodeInfo;
		this.nextNode=nextNode;
	}
	
	Object getNodeInfo(){	return this.nodeInfo; }
	void setNodeInfo(Object nodeInfo){	this.nodeInfo=nodeInfo; }
	
	clsNode getNextNode(){	return this.nextNode; }
	void setNextNode(clsNode nextNode){	this.nextNode=nextNode;	}
	
}
