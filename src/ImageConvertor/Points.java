package ImageConvertor;


class Points{
    public short startW=-1, startH=-1, endW=-1, endH=-1;//, length=-1;
    public Direction direction=Direction.STUB;
    private boolean isVisited=false;
    //private int length;
    
    public String toString() {	
	return String.format("Length: %d, direction: %s, xW: %d, yW %d, xH %d, yH %d%n", getLength(), direction, startW, endW,startH,endH);
    }
    
    public int getLength() {	
	return (int) Math.sqrt((endW-startW)*(endW-startW)+(endH-startH)*(endH-startH));
    }
    
    public boolean isVisited() {
	return isVisited;
    }
    
    public void setVisited(boolean isVisited) {
	this.isVisited = isVisited;
    }
}