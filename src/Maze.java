

public class Maze {
    //存储迷宫路径信息
    public MazeNode[][] mazePath;

    //求解迷宫路径所需要的栈类型
    private MazeStack stack;

    //表示迷宫路径的大小(sizes[0]=x轴长度，sizes[1]=y轴长度)
    private int [] sizes;

    /**
     * 迷宫对象的初始化
     */
    public Maze(int [][] paths) {
        this.sizes = new int[]{paths.length, paths[0].length};
        stack = new MazeStack();
        mazePath = new MazeNode[this.sizes[0]][this.sizes[1]];

        //赋值
//        System.out.println("迷宫路径信息初始值:");
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                int data = paths[i][j];
                setMazeNode(i, j, data);
//                System.out.print(data+" ");
            }
//            System.out.println();
        }
    }

    /**
     * 给迷宫路径生成相应的节点
     * @param i
     * @param j
     * @param data
     */
    public void setMazeNode(int i, int j, int data) {
        mazePath[i][j] = new MazeNode(i, j, data);
    }

    /**
     * 开始寻找迷宫路径信息
     */
    public void findPath() throws Exception {
        fixPathState();

        //开始寻找迷宫路径
        int i=0;
        int j=findIn(i);
        if(j < 0){
            throw new Exception("未找到入口");
        }
        stack.push(mazePath[i][j]);

        while(!stack.empty()) {
            i=stack.top().getX();
            j=stack.top().getY();
            if(i == this.sizes[0] - 1 || j ==this.sizes[1] - 1) {
                break;
            }
            //向右
            if(mazePath[i][j].getPath()[Constant.PATH_RIGHT] == Constant.STATE_OK) {
                //将该节点 行走 状态设置为不行
                mazePath[i][j].setPathState(Constant.PATH_RIGHT, Constant.STATE_ERR);
                //将该节点 左边行走 状态设置为不行
                mazePath[i][j+1].setPathState(Constant.PATH_LEFT, Constant.STATE_ERR);
                ////将该节点右边节点入栈
                stack.push(mazePath[i][j+1]);
                continue;
            }
            //向下
            if(mazePath[i][j].getPath()[Constant.PATH_DOWN] == Constant.STATE_OK) {
                //将该节点 行走 状态设置为不行
                mazePath[i][j].setPathState(Constant.PATH_DOWN, Constant.STATE_ERR);
                //将该节点 上边行走状态设置为不行
                mazePath[i+1][j].setPathState(Constant.PATH_UP, Constant.STATE_ERR);
                //将该节点下边节点入栈
                stack.push(mazePath[i+1][j]);
                continue;
            }
            //向左
            if(mazePath[i][j].getPath()[Constant.PATH_LEFT] == Constant.STATE_OK) {
                //将该节点 行走 状态设置为不行
                mazePath[i][j].setPathState(Constant.PATH_LEFT, Constant.STATE_ERR);
                //将该节点 右边行走 状态设置为不行
                mazePath[i][j-1].setPathState(Constant.PATH_RIGHT, Constant.STATE_ERR);
                //将该节点左边节点入栈
                stack.push(mazePath[i][j-1]);
                continue;
            }
            //向上
            if(mazePath[i][j].getPath()[Constant.PATH_UP] == Constant.STATE_OK) {
                //将该节点 行走 状态设置为不行
                mazePath[i][j].setPathState(Constant.PATH_UP, Constant.STATE_ERR);
                //将该节点 下边行走 状态设置为不行
                mazePath[i-1][j].setPathState(Constant.PATH_DOWN, Constant.STATE_ERR);
                //将该节点上边节点入栈
                stack.push(mazePath[i-1][j]);
                continue;
            }
            stack.pop();
        }
        if(stack.empty()) {
            throw new Exception("无出口");
        }
    }

    /**
     * 寻找迷宫入口
     * 因为读取图片那块时，像素值读取反了，这边反过来取，取y轴
     * @return
     */
    private int findIn(int i){
        for(int j = 0;j<mazePath[0].length;j++){
            if(mazePath[i][j].getVal() == ColorNumber.WHITE_NULBER.getCode()){
                return j;
            }
        }
        return -1;
    }

    /**
     * 调整迷宫路径所有节点的行走状态
     */
    private void fixPathState() {
        for(int i=0; i<sizes[0]; ++i){
            for(int j=0; j<sizes[1]; ++j){
                // i, j
                //向右
                if(j<sizes[1]-1 && mazePath[i][j+1].getVal() == 0) {
                    mazePath[i][j].setPathState(Constant.PATH_RIGHT, Constant.STATE_OK);
                }
                //向下
                if(i<sizes[0]-1 && mazePath[i+1][j].getVal() == 0) {
                    mazePath[i][j].setPathState(Constant.PATH_DOWN, Constant.STATE_OK);
                }
                //向左
                if(j>0 && mazePath[i][j-1].getVal() == 0) {
                    mazePath[i][j].setPathState(Constant.PATH_LEFT, Constant.STATE_OK);
                }
                //向上
                if(i>0 && mazePath[i-1][j].getVal() == 0) {
                    mazePath[i][j].setPathState(Constant.PATH_UP, Constant.STATE_OK);
                }
            }
        }
    }

    /**
     * 打印迷宫路径信息
     */
    public void showPath() {
        while(!stack.empty()) {
            int i=stack.top().getX();
            int j=stack.top().getY();
            this.setMazeNode(i, j, ColorNumber.RED_NULBER.getCode());
            stack.pop();
        }
//        System.out.println("迷宫路径信息( "+ColorNumber.RED_NULBER.getCode()+"表示路径可以走):");
//        for(int i=0 ; i<sizes[0];i++) {
//            for(int j=0;j<sizes[1];j++) {
//                System.out.print(this.mazePath[i][j].getVal()+" ");
//            }
//            System.out.println();
//        }
    }
}
