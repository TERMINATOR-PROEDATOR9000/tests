package ImageConvertor;


class Points{
    public int startW=-1, startH=-1, endW=-1, endH=-1;//, length=-1;
    public Direction direction=Direction.STUB;
    //private int length;
    
    public String toString() {	
	return String.format("Length: %d, direction: %s, xW: %d, yW %d, xH %d, yH %d%n", getLength(), direction, startW, endW,startH,endH);
    }
    
    public int getLength() {	
	return (int) Math.sqrt((endW-startW)*(endW-startW)+(endH-startH)*(endH-startH));
    }
}