import java.util.Date;

public class MazeTest {

    public static void main(String[] args) throws Exception {
        long startTime = new Date().getTime();
        //读取图片,获取像素值集合
        Image rc = new Image();
        int [][] paths = rc.getImagePixel("./maze.gif");

        //调用算法，计算出口方案
        Maze maze = new Maze(paths);
        //开始寻找路径信息
        maze.findPath();
        //打印最终的路径信息
        maze.showPath();

        //根据出口像素值，生成最终图片
        rc.generateImage(maze.mazePath,"./maze_qjm&sw&zcn.jpg");

        //耗时
        long endTime = new Date().getTime();
        System.out.println("耗时："+(endTime-startTime));

    }
}
