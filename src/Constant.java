/**
 * 描述: 定义项目所有的常量
 */
public interface Constant {

    //路径的方向总数
    int PATH_NUM = 4;

    //右方向
    int PATH_RIGHT = 0;

    //下方向
    int PATH_DOWN = 1;

    //左方向
    int PATH_LEFT = 2;

    //上方向
    int PATH_UP = 3;

    //路径可以行走
    int STATE_OK = 5;

    //路径不能行走
    int STATE_ERR = 6;
}
