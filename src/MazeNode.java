

public class MazeNode {
    //存储节点的值
    private int val;

    //存储节点的坐标
    private int x;
    private int y;

    //存储节点四个方向的行走状态
    private int[] path;

    /**
     * 节点对象的初始化
     * @param i
     * @param j
     * @param data
     */
    public MazeNode(int i, int j, int data) {
        this.x = i;
        this.y = j;
        this.val = data;

        //初始化节点的四个方向行走信息的时候，都初始化成不能走
        path = new int[Constant.PATH_NUM];
        for(int k=0; k<path.length; ++k){
            path[k] = Constant.STATE_ERR;
        }
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getPath() {
        return path;
    }

    public void setPath(int[] path) {
        this.path = path;
    }

    /**
     * 把节点相应的方向path，的行走状态，修改为state
     */
    public void setPathState(int pathid, int state) {
        this.path[pathid] = state;
    }
}
